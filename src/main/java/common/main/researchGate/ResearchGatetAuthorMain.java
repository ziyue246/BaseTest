package common.main.researchGate;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.system.FileOperation;
import common.utils.DomTree;
import org.apache.log4j.Logger;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ResearchGatetAuthorMain {

    private  static Logger logger = Logger.getLogger(ResearchGatetAuthorMain.class);

    public static void main(String []arg) throws  Exception{

        Config.init();
        final String entrance_url
                = "https://www.researchgate.net/profile/<authorName>";
        SimpleHttp http = new SimpleHttp();

        HtmlInfo html = new HtmlInfo();

        html.setEncode("utf-8");
        String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36 QIHU 360SE";
        html.setUa(ua);

        while(true) {

            try {
                Set<String> authorNameSet = SaveDataToSql.getPaperAutorDatas();
                if (authorNameSet.size() == 0) {
                    logger.info("crawler over");
                    return ;
                }
                logger.info("db authorNames size:" + authorNameSet.size());

                for (String authorName : authorNameSet) {

                    logger.info("authorName:" + authorName);
                    String url = entrance_url.replace("<authorName>", authorName.trim());

                    logger.info("url:" + url);
                    html.setOrignUrl(url);
                    http.simpleGet(html);

                    String htmlContent = html.getContent();//http请求获取的数据
                    if (htmlContent == null) {
                        logger.warn("please check your net");
                        Thread.sleep(1000 * 120);
                        continue;
                    }

                    ResearchGateAuthorData data = new ResearchGateAuthorData();

                    DocumentFragment page_node = DomTree.getNode(htmlContent, html.getEncode());
                    //data.setName(extractOne(page_node,ResearchGateAuthorDataXpath.name));
                    data.setName(authorName);//H-index，reads，total citations，research items
                    data.setUrl(url);
                    data.setInstitution(extractOne(page_node, ResearchGateAuthorDataXpath.institution));
                    data.setCurrPosition(extractOne(page_node, ResearchGateAuthorDataXpath.currPosition, ("Current position")));
                    data.setAuthorRgScore(extractfloatOne(page_node, ResearchGateAuthorDataXpath.authorRgScore)); //H-index
                    data.setCitationsNun(extractIntOne(page_node, ResearchGateAuthorDataXpath.citationsNun)); //total citations
                    data.setReadsNum(extractIntOne(page_node, ResearchGateAuthorDataXpath.readsNum)); //reads
                    data.setResearchItemsNum(extractIntOne(page_node, ResearchGateAuthorDataXpath.researchItemsNum)); //research items
                    data.setSkills(extractOneList(page_node, ResearchGateAuthorDataXpath.skills));
                    //extractFollowerModal(http,html,data,ResearchGateAuthorDataXpath.followersNum);
                    //data.setFollowersNum(extractFollowerModal(http,html,data,ResearchGateAuthorDataXpath.followersNum));
                    //data.setFollowingNum(extractFollowerModal(http,html,data,ResearchGateAuthorDataXpath.followingNum));

                    logger.info("data detail:");
                    logger.info("name           :" + data.getName());
                    logger.info("authorRgScore  :" + data.getAuthorRgScore());
                    logger.info("researchItemsNum:" + data.getResearchItemsNum());
                    logger.info("readsNum       :" + data.getReadsNum());
                    logger.info("citationsNun   :" + data.getCitationsNun());
                    logger.info("institution    :" + data.getInstitution());
                    logger.info("currPosition   :" + data.getCurrPosition());
                    logger.info("skills         :" + data.getSkills());
                    logger.info("\n");
                    SaveDataToSql.insertResearchGateAuthorsInfo(data);
                    Thread.sleep(1000 * 30);  //sleep 30s
                }
            } catch (Exception e) {
                Thread.sleep(1000 * 300);  //sleep 30s
                logger.error(e.getMessage());
            }
        }

    }//

    private  static int extractFollowerModal(SimpleHttp http,HtmlInfo html,ResearchGateAuthorData data,String type) throws Exception{


        logger.info("extractFollowerModal type : "+type);
        String nameMark = data.getUrl().split("profile\\/")[1].split("\\?")[0];

        HashMap<String,String> hashMap = new HashMap<>();

        hashMap.put("accept","application/json");
        hashMap.put("accept-encoding","gzip, deflate, sdch, br");
        hashMap.put("accept-language","zh-CN,zh;q=0.8");
        hashMap.put("referer","https://www.researchgate.net/profile/"+nameMark);
        hashMap.put("x-requested-with","XMLHttpRequest");
        hashMap.put("Host","www.researchgate.net");
        html.setHeadMap(hashMap);
        String followerModalUrl =null;


        if(type==ResearchGateAuthorDataXpath.followersNum)    {
            followerModalUrl = "https://www.researchgate.net/publicprofile.details.about.FollowerModal.html?accountKey=<nameMark>&type=Followers";
        }else if(type==ResearchGateAuthorDataXpath.followingNum)    {
            followerModalUrl = "https://www.researchgate.net/publicprofile.details.about.FollowerModal.html?accountKey=<nameMark>&type=Following";
        }

        if(followerModalUrl ==null){
            logger.info("extractFollowerModal followerModalUrl==null type : "+type);
            return 0;
        }
        followerModalUrl = followerModalUrl.replace(("<nameMark>"),nameMark);

        html.setOrignUrl(followerModalUrl);
        http.simpleGet(html);
        if(html.getContent()==null){
            logger.info("extractFollowerModal html.getContent()==null type : "+type);
            return 0;
        }

        if(!html.getContent().contains("\"count\"")){
            logger.info("extractFollowerModal !html.getContent().contains(\"count\") type : "+type);
            return 0;
        }


        logger.info("extractFollowerModal json:"+html.getContent());
        String result = html.getContent().replace(" ","").
                replaceAll("\"","").split("count:")[1].split(",")[0].replace(",","");
        Thread.sleep(1000*60);  //sleep 30s
        return Integer.parseInt(result);

    }


    private static void extractUrlList(List<ResearchGateAuthorData> list,
                                       DocumentFragment node, String xpath){
        NodeList nl = DomTree.commonList(xpath, node);
        for(int i=0;i<nl.getLength()&&i<2;i++) {
            String itemContent = nl.item(i).getTextContent();
            ResearchGateAuthorData data = new ResearchGateAuthorData();
            data.setUrl(itemContent.trim());
            list.add(data);
        }
    }

    private static String extractOne(DocumentFragment node, String xpath){
        NodeList nl = DomTree.commonList(xpath, node);
        for(int i=0;i<nl.getLength();i++) {
            String itemContent = nl.item(i).getTextContent();
            return itemContent.trim();
        }
        return "";
    }

    private static String extractOne(DocumentFragment node, String xpath ,String replaceMark){
        String strResult =  extractOne(node,xpath);
        if(strResult==null||strResult=="")return "";
        else{
            return strResult.replace(replaceMark,"");
        }
    }
    private static String extractOneList(DocumentFragment node, String xpath){
        NodeList nl = DomTree.commonList(xpath, node);

        String result = "";
        for(int i=0;i<nl.getLength();i++) {
            String itemContent = nl.item(i).getTextContent();
            result = result + itemContent+";";
        }
        return result;
    }
    private static int extractIntOne(DocumentFragment node, String xpath){

        String strResult =  extractOne(node,xpath);
        if(strResult==null||strResult=="")return 0;
        else{
            logger.info("strResult:"+strResult);
            strResult = strResult.replaceAll("[a-zA-Z|,]","");
            return Integer.parseInt(strResult.trim());
        }
    }
    private static float extractfloatOne(DocumentFragment node, String xpath){

        String strResult =  extractOne(node,xpath);
        if(strResult==null||strResult=="")return 0;
        else{
            strResult = strResult.replaceAll("[a-zA-Z|,]","");
            return Float.parseFloat(strResult);
        }
    }
}
