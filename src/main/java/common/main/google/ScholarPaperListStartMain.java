package common.main.google;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.system.FileOperation;
import common.utils.DomTree;
import common.utils.StringUtil;
import org.apache.log4j.Logger;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScholarPaperListStartMain {

    private  static Logger logger = Logger.getLogger(ScholarPaperListStartMain.class);
    public static void main(String []arg) throws  Exception{

        Config.init();
        final String entrance_url = "https://scholar.google.com.hk/scholar?hl=zh-CN&as_sdt=0%2C5&q=<keyword>";
        final String cite_url = "https://scholar.google.com.hk/scholar?" +
                "hl=zh-CN&as_sdt=0%2C5&sciodt=0%2C5&cites=<cite_id>&scipsc=&as_ylo=<startYear>&as_yhi=<endYear>";
        //  Generative+adversarial+networks


        int crawler_top_k = Config.searchTopKeyNum;

        String content = FileOperation.read("config/googleKeyword.txt");


        String []lines = content.split("\n");


        SimpleHttp http = new SimpleHttp();

        HtmlInfo html = new HtmlInfo();

        html.setEncode("utf-8");
        String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36 QIHU 360SE";
        html.setUa(ua);

        html.setProxy(Config.httpProxy);


        for(String line : lines){

            String searchKeyword  = line.trim();

            if(searchKeyword.length()<5){
                continue;
            }


            logger.info("searchKeyword:"+searchKeyword);
            String url = entrance_url.replace("<keyword>",searchKeyword.replace("  "," ").replace(" ","+"));

            int dataCount = 0;
            while(url!=null&&dataCount<=crawler_top_k) {

                logger.info("url:"+url);
                html.setOrignUrl(url);

                http.simpleGet(html);

//                String path  = "C:/Users/lenovo/Desktop/1.html";
//                String htmlcontent = FileOperation.read(path);
//                html.setContent(htmlcontent);

                String htmlContent = html.getContent();
                if(htmlContent==null){
                    logger.warn("请检查代理是否正确，网络是否连通");
                    System.exit(-1);
                }
                DocumentFragment node = DomTree.getNode(htmlContent, html.getEncode());
                List<GooglePaperData> list = new ArrayList<>();
                extractPaperInfoList(list, node, GoogleScholarXpath.paperInfo, dataCount);
                extractBriefList(list, node, GoogleScholarXpath.brief);
                extractAuthorList(list, node, GoogleScholarXpath.authors);
                extractAuthorUrlList(list, node, GoogleScholarXpath.authorsUrl);
                extractPubYearList(list, node, GoogleScholarXpath.pubYear);
                extractTitleList(list, node, GoogleScholarXpath.title);
                url = extractNextUrl(list, node, GoogleScholarXpath.nextURL);
                for(GooglePaperData data:list){
                    data.setSearchKeyword(searchKeyword);
                }
                SaveDataToSql.insertGooglePaperSearchTopK(list);
                logger.info("save data size:"+list.size());
                dataCount+=list.size();
                Thread.sleep(1000*30);
            }

        }
        logger.info("all search crawler top-k is over!!!");
    }

    private static void extractPaperInfoList(List<GooglePaperData> list,DocumentFragment node,String xpath,int baseIndex){
        NodeList nl = DomTree.commonList(xpath, node);
        for(int i=0;i<nl.getLength();i++) {
            String itemContent = nl.item(i).getTextContent();
            GooglePaperData data = new GooglePaperData();
            data.setPaperInfo(itemContent.trim());
            list.add(data);
            data.setOrderId(i+baseIndex+1);
        }
    }

    private static void extractBriefList(List<GooglePaperData> list,DocumentFragment node,String xpath){
        NodeList nl = DomTree.commonList(xpath, node);

        for(int i=0;i<nl.getLength()&&i<list.size();i++) {
            String itemContent = nl.item(i).getTextContent();
            for(int k=i;k<list.size();k++) {
                GooglePaperData data = list.get(k);
                if (data.getPaperInfo().contains(itemContent)) {
                    data.setBrief(itemContent.trim());
                    break;
                }
            }
        }
    }
    private static void extractAuthorList(List<GooglePaperData> list,DocumentFragment node,String xpath){
        NodeList nl = DomTree.commonList(xpath, node);
        for(int i=0;i<nl.getLength()&&i<list.size();i++) {
            String itemContent = nl.item(i).getTextContent();
            for(int k=i;k<list.size();k++) {
                GooglePaperData data = list.get(k);
                if(data.getPaperInfo().contains(itemContent)) {
                    if(itemContent.contains("-")){
                        itemContent = itemContent.split("-")[0];
                    }
                    data.setAuthors(itemContent.trim());
                    break;
                }
            }
        }
    }
    private static void extractAuthorUrlList(List<GooglePaperData> list,DocumentFragment node,String xpath){
        NodeList nl = DomTree.commonList(xpath, node);
        HashMap<String,String> authorUrlmap = new HashMap<>();

        for(int i=0;i<nl.getLength();i++) {
            String itemContent = nl.item(i).getTextContent();
            String itemhref = nl.item(i).getAttributes().getNamedItem("href").getTextContent();
            itemhref = "https://scholar.google.com.hk"+itemhref.trim();
            authorUrlmap.put(itemContent.trim(),itemhref.trim());

        }
        for(GooglePaperData  data:list){
            String authorStr = data.getAuthors();
            if(authorStr==null||authorStr.length()==0){
                continue;
            }
            String []authors = authorStr.split(",");
            String authorUrl = "";
            for(String author:authors){
                author=author.trim();
                if(authorUrlmap.get(author)!=null){
                    authorUrl+=author+":"+authorUrlmap.get(author);
                }
            }
            data.setAuthorsUrl(authorUrl);
        }

    }
    private static void extractPubYearList(List<GooglePaperData> list,DocumentFragment node,String xpath){
        NodeList nl = DomTree.commonList(xpath, node);
        for(int i=0;i<nl.getLength()&&i<list.size();i++) {
            String itemContent = nl.item(i).getTextContent();
            for(int k=i;k<list.size();k++) {
                GooglePaperData data = list.get(k);
                if (data.getPaperInfo().contains(itemContent)) {
                    String yearStr = StringUtil.extractOne(itemContent,"(19|20)\\d{2}");
                    data.setPubYear(Integer.parseInt(yearStr.trim()));
                    break;
                }
            }
        }
    }

    private static String extractNextUrl(List<GooglePaperData> list,DocumentFragment node,String xpath){
        NodeList nl = DomTree.commonList(xpath, node);
        for(int i=0;i<nl.getLength()&&i<list.size();i++) {
            String itemContent = nl.item(i).getTextContent();
            String nextUrl = "https://scholar.google.com.hk"+itemContent.trim();
        }
        return null;
    }

    private static void extractTitleList(List<GooglePaperData> list,DocumentFragment node,String xpath){
        NodeList nl = DomTree.commonList(xpath, node);
        for(int i=0;i<nl.getLength()&&i<list.size();i++) {
            String itemContent = nl.item(i).getTextContent();
            GooglePaperData data = list.get(i);

            if(data.getPaperInfo().contains(itemContent)) {
                if(itemContent.contains("] ")){
                    itemContent=itemContent.split("] ")[1];
                }
                data.setTitle(itemContent);
            }
            data.setTitle(itemContent);
        }
    }



}
