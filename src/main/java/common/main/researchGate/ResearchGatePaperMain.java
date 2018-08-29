package common.main.researchGate;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.system.FileOperation;
import common.system.StringProcess;
import common.utils.DomTree;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NodeList;
import java.util.List;
import java.util.Map;

public class ResearchGatePaperMain {

    private  static Logger logger = Logger.getLogger(ResearchGatePaperMain.class);



    public static void main(String []arg) throws  Exception{

        Config.init();
        final String entrance_url
                = "https://www.researchgate.net/search.Search.html?query=<doi>";
        //10.1109%2FTVT.2016.2594706

        //Map<String,Integer> authorsMap = SaveDataToSql.getRgAuthorDatas();
        while(true) {
            try {
                List<ResearchGatePaperData> list = SaveDataToSql.getPaperDatas();
                if (list.size() == 0) break;

                SimpleHttp http = new SimpleHttp();

                HtmlInfo html = new HtmlInfo();

                html.setEncode("utf-8");
                String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36 QIHU 360SE";
                html.setUa(ua);

                html.setHost("www.researchgate.net");
                html.setReferUrl("https://www.researchgate.net/profile/Zhiyuan_Ren8");
                html.getHeadMap().put("Accept-Encoding", "gzip, deflate, sdch, br");
                html.getHeadMap().put("Accept-Language", "zh-CN,zh;q=0.8");
                html.getHeadMap().put("Connection", "keep-alive");
                html.getHeadMap().put("Upgrade-Insecure-Requests", "1");
                html.getHeadMap().put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                String cookie = "__gads=ID=4e445c7f6cbbcf2d:T=1516689105:S=ALNI_MadYHLx1rnTU4tG6OC93JDuzoTDHA; classification=nonResearcher; _mkto_trk=id:931-FMK-151&token:_mch-researchgate.net-1531712002250-67718; cookieconsent_dismissed=true; sid=9b1oQAuuQgiuQLTcuSviwAUntALa6Hx3wq7HiXVxlZPEV0MIKdjUf0GoQqdEwRmTXFeuyreg3kEa7NmONR0p8FdHmQ1ZLxtbPgmfp3qDLsWurMEgPPu7EiwM4vHns1K3; cili=_2_Y2Q5M2RlN2EyMzZiZjUzMjMxNDkxNWExNzIyOTc0ODRmMGQ2OTUxNWE1MGIyNGU3OTdjNjQ0YTE0YjFjNWUzNl8xODY2OTI5Njsw; cirgu=_1_H5u93v3Rv%2BAQmv1BsUaj%2Bt5YZxycf%2FkjxnKNVRABKQ90y1D82gbIEw8t7YUYnMBcF3rniA%3D%3D; did=h6FGDAQUUDF5PvjCL02Yu2lIn6OjUJvzst9gzhfCoLv5AIGWg0DtLwrw8XRIXI0c; pl=LgAxLGPDa6pHTAQM7D0dKaiOa24GEVW0kC7sIQh8IJGLzKPjqrSpiVinFp6VRZhEse870f5snHlj8Bjy4iiuMeXo5qiQt9V9Tr01zfOE7UZJ2dXCTJ0YfKAW06o551VC; ptc=RG1.2353551659361274694.1516689070; _ga=GA1.2.1459405830.1516689073; _gid=GA1.2.2073943948.1535350239; _gat_UA-58591210-1=1";
                cookie = "__gads=ID=4e445c7f6cbbcf2d:T=1516689105:S=ALNI_MadYHLx1rnTU4tG6OC93JDuzoTDHA; classification=nonResearcher; _mkto_trk=id:931-FMK-151&token:_mch-researchgate.net-1531712002250-67718; cookieconsent_dismissed=true; sid=CyEzLIQ1V1mQlOYVHXwPPKXsx46rtxb1ABbW25RKHv05tyEv6AfnoXujfQ6iivolt18y8OOIfpuQssV62oLBCe2BMexplja7GHOUgpukeK0mZrwAOwk623rPf1skaB32; cili=_2_ZDdmMmVmYjM1ODc0NGYwMTU4OTViNTc1MGQyZDM5YWVlNzNhMzhjYWRjN2JmN2I1OTUxNzdhZWFjZjQ1MzViYl8xODY2OTI5Njsw; cirgu=_1_btVhyHs%2BIaF34iAobmKb3orqwU%2FsAXb9wkzrydTjMiVmB0%2FyTya2efghZK7UzVD86csJuA%3D%3D; did=h6FGDAQUUDF5PvjCL02Yu2lIn6OjUJvzst9gzhfCoLv5AIGWg0DtLwrw8XRIXI0c; pl=LgAxLGPDa6pHTAQM7D0dKaiOa24GEVW0kC7sIQh8IJGLzKPjqrSpiVinFp6VRZhEse870f5snHlj8Bjy4iiuMeXo5qiQt9V9Tr01zfOE7UZJ2dXCTJ0YfKAW06o551VC; ptc=RG1.2353551659361274694.1516689070; _gat=1; _gat_UA-58591210-1=1; _ga=GA1.2.1459405830.1516689073; _gid=GA1.2.2073943948.1535350239";
                html.setCookie(cookie);


                for (ResearchGatePaperData data : list) {

                    logger.info("title:" + data.getTitle());
                    logger.info("doi:" + data.getDoi());
                    String url = entrance_url.replace("<doi>", StringProcess.urlEncode(data.getDoi()));

                    logger.info("url:" + url);
                    html.setOrignUrl(url);

                    http.simpleGet(html);  //请求数据


                    String htmlContent = html.getContent();//http请求获取的数据
                    if (htmlContent == null) {
                        logger.warn("please check your net");
                        Thread.sleep(1000 * 120);
                        continue;

                    }
                    if (htmlContent.contains("window.location")) {
                        url = htmlContent.split("window.location")[1].split("\"")[1].split("\"")[0];
                        url = url.replace("\\", "");
                        logger.info("real_url:" + url);
                        html.setOrignUrl(url);
                        http.simpleGet(html);  //请求数据
                        htmlContent = html.getContent();//http请求获取的数据
                        if (htmlContent == null) {
                            logger.warn("please check your net");
                            Thread.sleep(1000 * 120);
                            continue;

                        }
                    }
                    DocumentFragment node = DomTree.getNode(htmlContent, html.getEncode());
                    extractItems01(data, node, null);
                    extractAuthors(data, node, null);
                    data.setRg_update(1);
                    SaveDataToSql.updateResearchGateData(data);
                    logger.info("data detail :");
                    logger.info("Title          :" + data.getTitle());
                    logger.info("Doi            :" + data.getDoi());
                    logger.info("Authors_rg     :" + data.getAuthors_rg());
                    logger.info("Rg_reads       :" + data.getRg_reads());
                    logger.info("Recommendations_rg:" + data.getRecommendations_rg());
                    logger.info("Followers_rg   :" + data.getFollowers_rg());
                    logger.info("Cite_num_rg    :" + data.getCite_num_rg());
                    logger.info("Comments_rg    :" + data.getComments_rg());
                    logger.info("RelatedResearch_rg:" + data.getRelatedResearch_rg());
                    logger.info("\n");
                    Thread.sleep(1000 * 25);  //sleep 30s


                }
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }
        logger.info("all search crawler top-k is over!!!");
    }
    private static void extractItems01(ResearchGatePaperData data,
                                       DocumentFragment node, String xpath){

        xpath="//DIV[@class='content-grid__columns--narrow']//DIV[@class='nova-o-pack__item']|" +
                "//DIV[@class='content-grid__columns--narrow']//BUTTON[@type='button']";  //<em class="minor commafy last">
        NodeList nl = DomTree.commonList(xpath, node);

        //System.out.println("nl.getLength():"+nl.getLength());
        //System.out.println(nl.item(0).getTextContent());
        String wordStr = DomTree.parserNodeByXpathGetString(node, xpath,"");
        logger.info("wordStr:"+wordStr);
        wordStr = wordStr.toLowerCase();
        //String wordStr = "Reads7,264Recommendations9Followers32Citations93";
        String []words = wordStr.toLowerCase().split("[a-z]+");




        //Reads460Followers2RecommendationsCitations108
        if(wordStr.contains("reads")){
            int value = Integer.parseInt(wordStr.split("reads")[1].split("[a-z]+")[0].replace(",",""));
            data.setRg_reads(value);
        }
        if(wordStr.contains("followers")){
            int value = Integer.parseInt(wordStr.split("followers")[1].split("[a-z]+")[0].replace(",",""));
            data.setFollowers_rg(value);
        }
        if(wordStr.contains("recommendations")){
            int value = Integer.parseInt(wordStr.split("recommendations")[1].split("[a-z]+")[0].replace(",",""));
            data.setRecommendations_rg(value);
        }
        if(wordStr.contains("citations")){
            int value = Integer.parseInt(wordStr.split("citations")[1].split("[a-z]+")[0].replace(",",""));
            data.setCite_num_rg(value);
        }


        xpath = "//DIV[@class='nova-e-text nova-e-text--size-m nova-e-text--family-sans-serif nova-e-text--spacing-none nova-e-text--color-inherit nova-c-nav__item-label']";

        //nl = DomTree.commonList(xpath, node);
        wordStr = DomTree.parserNodeByXpathGetString(node, xpath,"##");

        logger.info("wordStr:"+wordStr);
        String []words_str  = wordStr.split("##");

        //Overview##Comments##Citations (108)##References (39)##Related research (10+)##Public Full-texts
        if(words_str[1].contains("(")) {
            logger.info("Comments"+words_str[1]);
            String comments = words_str[1].split("\\(")[1].split("\\)")[0];
            data.setComments_rg(StringProcess.str2Int(comments));
        }
        if(words_str[4].contains("(")) {
            logger.info("Related research : "+words_str[4]);
            String relatedResearchStr = words_str[4].split("\\(")[1].split("\\)")[0];
            if(relatedResearchStr.contains("+"))
                data.setRelatedResearch_rg(11);
            else
                data.setRelatedResearch_rg(StringProcess.str2Int(relatedResearchStr));
        }




    }


    private static void extractAuthors(ResearchGatePaperData data,
                                       DocumentFragment node, String xpath){
        xpath = "//SPAN/A[@class='nova-e-link nova-e-link--color-inherit nova-e-link--theme-bare research-detail-author'][contains(@href,'profile')]/@href";
        NodeList nl = DomTree.commonList(xpath, node);
        String authorUrls = DomTree.parserNodeByXpathGetString(node, xpath,"\n");
        //profile/Young-Jin_Cha?_sg=uMtx61daZ721D7VUqGD98ZYPmNo0Y4xXF6rnsYzmD-BNk_zgYVgz3ME1vfKy0NjObFGd2o0.KGtGRqGoW450vxRmNJuy9qyegrBErEpXxRBEnMK3Cj8HP5a4bAQofP4e0b0OxKPnRxcGnKf61vvUHhsD2CirjQ

        if(authorUrls==null||authorUrls.length()<2)return;

        String authorNames = "";
        for(String authorUrl : authorUrls.split("\n")){
            String authorName = authorUrl.split("profile/")[1].split("\\?")[0];
            authorNames=authorNames+authorName+";";
        }
        data.setAuthors_rg(authorNames);
    }

    @Test
    public void test(){



        System.out.println(StringProcess.urlEncode("10.1111/mice.12263"));

        String content = FileOperation.read("C:\\Users\\Administrator\\Desktop\\1.html");
        if(content.contains("window.location")){
            content = content.split("window.location")[1].split("\"")[1].split("\"")[0];
            content = content.replace("\\","");
            System.out.println(content);
        }

        String wordStr = "Reads7,264Recommendations9Followers32Citations93";
        String []words = wordStr.toLowerCase().split("[a-z]+");
        System.out.println("#####");
        for(int i=0;i<words.length;++i){
            System.out.println(i+"\t"+words[i]);
        }
        //Overviwe##Comments (1)##Citations (93)##References (55)##Related research (10+)##Public Full-text (1)
        String wordStr2 = "Overviwe##Comments (1)##Citations (93)##References (55)##Related research (10+)##Public Full-text (1)";

        System.out.println(wordStr2.split("\\(")[4].split("\\)")[0]);

        String authorsUrl = "profile/Young-Jin_Cha?_sg=uMtx61daZ721D7VUqGD98ZYPmNo0Y4xXF6rnsYzmD-BNk_zgYVgz3ME1vfKy0NjObFGd2o0.KGtGRqGoW450vxRmNJuy9qyegrBErEpXxRBEnMK3Cj8HP5a4bAQofP4e0b0OxKPnRxcGnKf61vvUHhsD2CirjQ\n";

        System.out.println("authorsUrl:"+authorsUrl.split("profile/")[1].split("\\?")[0]);
    }
}
