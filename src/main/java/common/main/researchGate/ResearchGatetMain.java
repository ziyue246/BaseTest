package common.main.researchGate;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.system.FileOperation;
import common.system.StringProcess;
import common.utils.DomTree;
import common.utils.StringUtil;
import org.apache.log4j.Logger;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResearchGatetMain {

    private  static Logger logger = Logger.getLogger(ResearchGatetMain.class);



    public static void main(String []arg) throws  Exception{

        Config.init();
        final String entrance_url
                = "https://www.researchgate.net/search/authors?q=<keyword>";
                //= "https://www.researchgate.net/search/authors?q=fei-yue+wang"
        //  Generative+adversarial+networks



        String content = FileOperation.read("config_researchGate/authorsName.txt");


        String []lines = content.split("\n");


        SimpleHttp http = new SimpleHttp();

        HtmlInfo html = new HtmlInfo();

        html.setEncode("utf-8");
        String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36 QIHU 360SE";
        html.setUa(ua);

        for(String line : lines){

            String searchKeyword  = line.trim();

            if(searchKeyword.startsWith("#")||searchKeyword.length()<=1){
                continue;
            }


            logger.info("searchKeyword:"+searchKeyword);
            String url = entrance_url.replace("<keyword>",searchKeyword.replace("  "," ").replace(" ","+"));

            int dataCount = 0;
            {

                logger.info("url:"+url);
                html.setOrignUrl(url);

                http.simpleGet(html);  //请求数据

//                String path  = "C:/Users/lenovo/Desktop/1.html";
//                String htmlcontent = FileOperation.read(path);
//                html.setContent(htmlcontent);

                String htmlContent = html.getContent();//http请求获取的数据
                if(htmlContent==null){
                    logger.warn("请检查代理是否正确，网络是否连通");
                    System.exit(-1);
                }
                DocumentFragment node = DomTree.getNode(htmlContent, html.getEncode());
                List<ResearchGateAuthorData> list = new ArrayList<>();
                extractUrlList(list,node,ResearchGateAuthorDataXpath.url);
                for(ResearchGateAuthorData data:list){
                    String detailUlr = data.getUrl();
                    html.setOrignUrl(detailUlr);
                    http.simpleGet(html);
                    Thread.sleep(1000*60);  //sleep 30s
                    DocumentFragment page_node = DomTree.getNode(htmlContent, html.getEncode());
                    data.setName(extractOne(page_node,ResearchGateAuthorDataXpath.name));
                    data.setInstitution(extractOne(page_node,ResearchGateAuthorDataXpath.institution));
                    data.setCurrPosition(extractOne(page_node,ResearchGateAuthorDataXpath.currPosition,("Current positionPresident")));
                    data.setAuthorRgScore(extractfloatOne(page_node,ResearchGateAuthorDataXpath.authorRgScore));
                    data.setCitationsNun(extractIntOne(page_node,ResearchGateAuthorDataXpath.citationsNun));
                    data.setReadsNum(extractIntOne(page_node,ResearchGateAuthorDataXpath.readsNum));
                    data.setResearchItemsNum(extractIntOne(page_node,ResearchGateAuthorDataXpath.researchItemsNum));
                    data.setSkills(extractOneList(page_node,ResearchGateAuthorDataXpath.skills));
                    extractFollowerModal(http,html,data,ResearchGateAuthorDataXpath.followersNum);
                    data.setFollowersNum(extractFollowerModal(http,html,data,ResearchGateAuthorDataXpath.followersNum));
                    data.setFollowingNum(extractFollowerModal(http,html,data,ResearchGateAuthorDataXpath.followingNum));
                }

                SaveDataToSql.insertResearchGateAuthorsInfo(list);
                logger.info("save data size:"+list.size());
                dataCount+=list.size();
                Thread.sleep(1000*60);  //sleep 30s
            }

        }
        logger.info("all search crawler top-k is over!!!");
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
        if(strResult==null||strResult=="")return null;
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
            strResult = strResult.replaceAll("[a-zA-Z|,]","");
            return Integer.parseInt(strResult);
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
