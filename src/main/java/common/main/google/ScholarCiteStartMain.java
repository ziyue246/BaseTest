package common.main.google;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.system.FileOperation;
import common.utils.DomTree;
import common.utils.StringUtil;
import org.apache.log4j.Logger;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NodeList;

import java.util.*;

public class ScholarCiteStartMain {

    private  static Logger logger = Logger.getLogger(ScholarCiteStartMain.class);
    public static void main(String []arg) throws  Exception{

        Config.init();
        final String entrance_url = "https://scholar.google.com.hk/scholar?hl=zh-CN&as_sdt=0%2C5&q=<keyword>";

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

            List<GooglePaperData> list = new ArrayList<>();
            logger.info("title:"+searchKeyword);
            String url = entrance_url.replace("<keyword>",searchKeyword.replace("  "," ").replace(" ","+"));




            logger.info("url:"+url);
            html.setOrignUrl(url);

            //http.simpleGet(html);

            String path  = "C:/Users/lenovo/Desktop/1.html";
            String htmlcontent = FileOperation.read(path);
            html.setContent(htmlcontent);

            String htmlContent = html.getContent();

            DocumentFragment node = DomTree.getNode(htmlContent, html.getEncode());

            GooglePaperData data = new GooglePaperData();
            extractAuthor(data, node, GoogleScholarXpath.authors);
            extractPubYear(data, node, GoogleScholarXpath.pubYear);
            extractTitle(data, node, GoogleScholarXpath.title);
            extractCiteNum(data, node, GoogleScholarXpath.citeNum);
            extractYearsCite(data, node, GoogleScholarXpath.citeNum);
            list.add(data);
            SaveDataToSql.insertGooglePaperSearchTopK(list);
            list.clear();
            Thread.sleep(1000*30);








        }
    }









    private static void extractAuthor(GooglePaperData data,DocumentFragment node,String xpath){
        NodeList nl = DomTree.commonList(xpath, node);
        for(int i=0;i<nl.getLength();i++) {
            String itemContent = nl.item(i).getTextContent();

            if(data.getPaperInfo().contains(itemContent)) {
                if(itemContent.contains("-")){
                    itemContent = itemContent.split("-")[0];
                }

                data.setAuthors(itemContent.trim());
            }
        }
    }

    private static void extractPubYear(GooglePaperData data,DocumentFragment node,String xpath){
        NodeList nl = DomTree.commonList(xpath, node);
        for(int i=0;i<nl.getLength();i++) {
            String itemContent = nl.item(i).getTextContent();

            if(data.getPaperInfo().contains(itemContent)) {
                String yearStr = StringUtil.extractOne(itemContent,"(19|20)\\d{2}");
                data.setPubYear(Integer.parseInt(yearStr.trim()));

            }
        }
    }


    private static void extractTitle(GooglePaperData data,DocumentFragment node,String xpath){
        NodeList nl = DomTree.commonList(xpath, node);
        for(int i=0;i<nl.getLength();i++) {
            String itemContent = nl.item(i).getTextContent();


            if(data.getPaperInfo().contains(itemContent)) {
                if(itemContent.contains("] ")){
                    itemContent=itemContent.split("] ")[1];
                }
                data.setTitle(itemContent);

            }
            data.setTitle(itemContent);
        }
    }

    private static void extractCiteNum(GooglePaperData data,DocumentFragment node,String xpath){
        NodeList nl = DomTree.commonList(xpath, node);
        for(int i=0;i<nl.getLength();i++) {
            String itemContent = nl.item(i).getTextContent();
            String citeUrl = nl.item(i).getAttributes().getNamedItem("href").getTextContent();
            String citeNum = StringUtil.extractOne(itemContent,"\\d*\\d");
            data.setCiteNum(Integer.parseInt(citeNum));
            data.setCiteNumUrl("https://scholar.google.com.hk"+citeUrl);
        }
    }



    private static void extractYearsCite(GooglePaperData data,DocumentFragment node,String xpath){

        if(data.getCiteNum()<=0)return ;
        final String cite_url = "https://scholar.google.com.hk/scholar?" +
                "hl=zh-CN&as_sdt=0%2C5&sciodt=0%2C5&cites=<cite_id>&scipsc=&as_ylo=<year>&as_yhi=<year>";


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int startYear = data.getPubYear();
        int endYear = calendar.get(Calendar.YEAR);
        if(data.getCiteNumUrl()==null||!data.getCiteNumUrl().contains("cites=")){

            logger.info("data.getCiteNumUrl(),  error");
            return ;
        }
        String cite_id  =data.getCiteNumUrl().split("cites=")[1].split("&")[0];



        for(int year=startYear;year<=endYear;++year){
            String url = cite_url.replace("<cite_id>",cite_id).replace("<year>",cite_id);
        }


    }

}
