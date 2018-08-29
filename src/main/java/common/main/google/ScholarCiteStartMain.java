package common.main.google;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.system.EnAnalysis;
import common.system.FileOperation;
import common.utils.DomTree;
import common.utils.StringUtil;
import org.apache.log4j.Logger;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NodeList;

import java.util.*;

public class ScholarCiteStartMain {


    private  static Logger logger = Logger.getLogger(ScholarCiteStartMain.class);



    private static SimpleHttp http = new SimpleHttp();

    private static HtmlInfo html = new HtmlInfo();

    public static void main(String []arg) throws  Exception{
        Config.init();
        final String entrance_url = "https://scholar.google.com.hk/scholar?hl=zh-CN&as_sdt=0%2C5&q=<keyword>";
        html.setEncode("utf-8");
        String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36 QIHU 360SE";
        html.setUa(ua);

        html.setProxy(Config.httpProxy);

        while(true) {


            try {
                List<GooglePaperData> list = SaveDataToSql.getPaperDatas();
                if (list.size() == 0) {
                    logger.info("crawler over");
                    return;
                }

                for (GooglePaperData data : list) {
                    String title = data.getTitle();
                    logger.info("title:" + title);
                    String url = entrance_url.replace("<keyword>",
                            data.getTitle().replace("  ", " ").replace(" ", "+"));

                    logger.info("url:" + url);
                    html.setOrignUrl(url);

                    http.simpleGet(html);

//            String path  = "C:/Users/lenovo/Desktop/1.html";
//            String htmlcontent = FileOperation.read(path);
//            html.setContent(htmlcontent);

                    String htmlContent = html.getContent();
                    DocumentFragment node = DomTree.getNode(htmlContent, html.getEncode());
                    extractPaperInfoList(data, node, GoogleScholarXpath.paperInfo);
                    //extractAuthor(data, node, GoogleScholarXpath.authors);
                    extractPubYear(data, node, GoogleScholarXpath.pubYear);
                    extractTitle(data, node, GoogleScholarXpath.title);

                    double similarityValue = EnAnalysis.getSimilarity(data.getTitle(), title);
                    logger.info("title_src   :" + title);
                    logger.info("title_google:" + data.getTitle());
                    logger.info("title similarityValue : " + similarityValue);
                    if (similarityValue > 0.8) {
                        extractCiteNum(data, node, GoogleScholarXpath.citeNum);
                        if (data.getCiteNum() > 0) extractYearsCite(data, node, GoogleScholarXpath.yearsCite);
                        data.setUpdate(1);
                        SaveDataToSql.updateGooglePaperYearsCite(data);
                        logger.info("updateGooglePaperYearsCite success");
                    } else {
                        data.setUpdate(-1);
                        SaveDataToSql.updateGooglePaperYearsCite(data);
                        logger.info("updateGooglePaperYearsCite failed");
                    }
                    logger.info("data message :");
                    logger.info("id       :" + data.getId());
                    logger.info("title    :" + data.getTitle());
                    logger.info("pubYear  :" + data.getPubYear());
                    logger.info("citeNum  :" + data.getCiteNum());
                    logger.info("yearsCite:" + data.getYearsCite() + "\n");
                    Thread.sleep(1000 * 40);

                }
            }catch (Exception e){
                Thread.sleep(1000 * 300);
                logger.error(e.getMessage());
            }
        }
    }





    private static void extractPaperInfoList(GooglePaperData data,DocumentFragment node,String xpath){
        NodeList nl = DomTree.commonList(xpath, node);
        for(int i=0;i<nl.getLength();i++) {
            String itemContent = nl.item(i).getTextContent();
            data.setPaperInfo(itemContent.trim());
            break;
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
            break;
        }
    }

    private static void extractPubYear(GooglePaperData data,DocumentFragment node,String xpath){
        NodeList nl = DomTree.commonList(xpath, node);
        for(int i=0;i<nl.getLength();i++) {
            String itemContent = nl.item(i).getTextContent();
            if(data.getPaperInfo().contains(itemContent)) {
                String yearStr = StringUtil.extractOne(itemContent,"(19|20)\\d{2}");
                if(yearStr.length()==4)             data.setPubYear(Integer.parseInt(yearStr.trim()));
                else data.setPubYear(2008);
            }
            break;
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
            break;
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
            break;
        }
    }



    private static void extractYearsCite(GooglePaperData data,DocumentFragment node,String xpath) throws Exception{

        if(data.getCiteNum()<=0)return ;
        final String cite_url = "https://scholar.google.com.hk/scholar?" +
                "hl=zh-CN&as_sdt=0%2C5&sciodt=0%2C5&cites=<cite_id>&scipsc=&as_ylo=<year>&as_yhi=<year>";


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int startYear = data.getPubYear();
        int endYear = calendar.get(Calendar.YEAR)+1;
        if(data.getCiteNumUrl()==null||!data.getCiteNumUrl().contains("cites=")){
            logger.info("data.getCiteNumUrl(),  error");
            return ;
        }

        if(startYear<1900){
            logger.info("startYear error:"+startYear);
            return ;
        }


        String cite_id  =data.getCiteNumUrl().split("cites=")[1].split("&")[0];

        SimpleHttp http = new SimpleHttp();


        int total = data.getCiteNum();

        String yearCite = "";

        logger.info("curr startYear:"+startYear);
        logger.info("curr endYear:"+endYear);
        for(int year=startYear-1;year<=endYear;++year){
            logger.info("curr year:"+year);
            String url = cite_url.replace("<cite_id>",cite_id).replace("<year>",year+"");
            logger.info("url:"+url);
            html.setOrignUrl(url);
            http.simpleGet(html);

            int citeNum=0;
            DocumentFragment nodeData = DomTree.getNode(html.getContent(), html.getEncode());
            NodeList nl = DomTree.commonList(xpath, nodeData);



            for(int i=0;i<nl.getLength();i++) {
                String itemContent = nl.item(i).getTextContent();
                if(itemContent==null||!itemContent.contains(" ")){
                    break;
                }
                itemContent = itemContent.trim().split(" ")[1];
                itemContent = itemContent.replace(",","");
                citeNum = Integer.parseInt(itemContent);
                break;
            }
            total-=citeNum;
            yearCite += year+"="+citeNum+",";
            if(total<=0){
                break;
            }
            Thread.sleep(1000*40);
        }
        data.setYearsCite(yearCite);
    }

}
