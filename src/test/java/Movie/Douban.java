package Movie;


import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.system.FileOperation;
import common.system.StringProcess;
import common.system.SystemCommon;
import common.utils.DomTree;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.w3c.dom.Node;

import java.io.File;
import java.util.HashSet;

/**
 * Created by ziyue on 2018/7/2.
 */
public class Douban {



    private static Logger logger = Logger.getLogger(Douban.class);

    @Test
    public void testMain(){


        SimpleHttp http = new SimpleHttp();

        HtmlInfo html = new HtmlInfo();

        String ua =  "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
        html.setUa(ua);
        String cookie = "ll=\"108288\"; bid=y_ODTLitUTA; gr_user_id=31715441-716a-46dd-a6ea-3a29827e1a28; __yadk_uid=aBqDOQuKaXBGjbF2LC2oL7YP4XuYiqcE; viewed=\"26883982_25746399_24934182_25895442_10590856\"; _vwo_uuid_v2=E54CAF7B5549DC33EE9DA0E2097FA8FB|4bc3a113b76eed149e8de44361591418; ap=1; _pk_ref.100001.4cf6=%5B%22%22%2C%22%22%2C1530517903%2C%22https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DwZU-iWJVqJF01tfEwYJO-qVObdFsGDQnMHP8x5XpF52f-MgnXB2R-7hn51pl9cIf%26wd%3D%26eqid%3Db3da928f00022a5a000000035b39bd8d%22%5D; ps=y; dbcl2=\"180589465:fPIDyIVcmH8\"; ck=k26S; _pk_id.100001.4cf6=1282f48289f6cbad.1525054607.7.1530518595.1530515297.; _pk_ses.100001.4cf6=*; __utma=30149280.2007297328.1508064956.1530510739.1530517904.26; __utmb=30149280.4.10.1530517904; __utmc=30149280; __utmz=30149280.1530510739.25.22.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utma=223695111.245788569.1525054607.1530510739.1530518441.7; __utmb=223695111.0.10.1530518441; __utmc=223695111; __utmz=223695111.1530510739.6.4.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; push_noty_num=0; push_doumail_num=0";
        //html.setCookie(cookie);
        html.setUa("utf-8");

        String movieIdFilePath = "file/movie/douban_movieId.txt";
        String contentFilePath = "file/movie/douban_content.txt";

        //FileOperation.appendWrite("title##star##comment"+"\n",contentFilePath);


        final String url_model= "https://movie.douban.com/subject/<movie_id>/comments?start=<page>&limit=20&sort=new_score&status=P";
        String movieIdsStr = FileOperation.read(movieIdFilePath);
        String []movieIds = movieIdsStr.split("\n");


        boolean startMark=false;
        for(String movieId : movieIds){



            if(movieId.startsWith("#")||movieId.length()<3||!startMark){
                if(movieId.startsWith("start")) {
                    startMark = true;
                }
                continue;
            }




            int MaxPage=999;
            String url =url_model.replace("<movie_id>",movieId.trim());

            for(int i=0;i<MaxPage;++i){


                String realUrl = url.replace("<page>",""+(i*20));

                logger.info("realUrl:"+realUrl);
                html.setOrignUrl(realUrl);
                http.simpleGet(html);

                String htmlContent = html.getContent();
                if(htmlContent==null){
                    logger.info("htmlContent==null");
                    SystemCommon.sleepsSecond(100);
                    break;
                }
                //htmlContent = FileOperation.read("C:/Users/Administrator/Desktop/1.html");

                String TitleXpath="//TITLE";

                Node node = DomTree.getNode(htmlContent,html.getEncode());

                String contentXpath = "//P";
                String starXpath = "//H3//SPAN[@class='comment-info']//SPAN[contains(@class,'allstar')]/@class";
                String headCommentXpath = "//DIV[@class='comment-item']";
                String headComment = DomTree.parserNodeByXpathGetStringSplitByMark(node,headCommentXpath,"##");

                String title = DomTree.parserNodeByXpathGetString(node,TitleXpath);
                title = title.split(" ")[0];

                if(headComment==null){
                    logger.info("headComment==null");
                    SystemCommon.sleepsSecond(30);
                    break;
                }
                String []headCommentsStr =  headComment.split("##");

                if(headCommentsStr.length==0){
                    logger.info("headCommentsStr.length==0");
                    break;
                }
                StringBuffer onePageSB =  new StringBuffer();
                for(int k=0;k<headCommentsStr.length;++k){
                    try {

                        String start = DomTree.parserNodeByXpathGetString(node,headCommentXpath+"["+(k+1)+"]"+starXpath);

                        String commentontent = DomTree.parserNodeByXpathGetString(node,headCommentXpath+"["+(k+1)+"]"+contentXpath);


                        start = StringProcess.regex2StrSplitByMark(start, "\\d+", "");

                        commentontent = commentontent.replace("\n", "");

                        String line = title + "##" + start.substring(0,1) + "##" +commentontent;
                        onePageSB.append(line + "\n");
                        System.out.println(line);
                    }catch (Exception e){
                        logger.error(e.getMessage());
                    }
                }
                FileOperation.appendWrite(onePageSB.toString(),contentFilePath);
                SystemCommon.sleepsSecond(30);
            }
        }


    }

    @Test
    public void testMovieDup(){
        String contentFilePath = "file/movie/douban_content.txt";
        String contentFilePath2 = "file/movie/douban_content2.txt";
        String content = FileOperation.read(contentFilePath);

        HashSet<String> stringHashSet = new HashSet<>();
        for(String line : content.split("\n")){
            stringHashSet.add(line.trim());
        }
        StringBuffer sb = new StringBuffer();
        for(String line:stringHashSet){
            sb.append(line+"\n");
        }
        FileOperation.write(sb.toString(),contentFilePath2);
        System.out.println("OVer");
    }


    @Test
    public void jsonGetMovieId(){

        String movieIdFilePath = "file/movie/douban_movieId.txt";
        String movieIdsStr = FileOperation.read(movieIdFilePath);

        HashSet<String> stringHashSet = new HashSet<>();
        String []movieIds = movieIdsStr.split("\n");
        for(String movieId : movieIds) {
            if (movieId.length() < 3) continue;
            movieId=movieId.replace("#","");
            stringHashSet.add(movieId.trim());
        }

        String movie_json = "file/movie/movie_json.txt";

        String content = FileOperation.read(movie_json);

        //"url":"https://movie.douban.com/subject/1291546/",
        content = StringProcess.regex2StrSplitByMark(content,"movie.douban.com.*?\"","##");

        for(String line : content.split("##")){
            String line2 = StringProcess.regex2StrSplitByMark(line,"\\d.*\\d","##");
            if(stringHashSet.contains(line2.trim())){
                continue;
            }
            //System.out.println(line);
            System.out.println(line2);
        }
    }


    @Test
    public void test(){

        String content = "";
        String result = StringProcess.regex2StrSplitByMark(content,"movie.douban.com.*?\"","\n");

        System.out.println(result);
    }
}
