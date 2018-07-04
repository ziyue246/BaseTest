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
        for(String movieId : movieIds){
            if(movieId.startsWith("#")||movieId.length()<3)continue;



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
        for(String line:stringHashSet){
            FileOperation.appendWrite(line+"\n",contentFilePath2);
        }
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

        String content = "{\"data\":[{\"directors\":[\"�¿���\"],\"rate\":\"9.5\",\"cover_x\":494,\"star\":\"50\",\"title\":\"������\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/1291546\\/\",\"casts\":[\"�Ź���\",\"�ŷ���\",\"����\",\"����\",\"Ӣ��\"],\"cover\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p1910813120.jpg\",\"id\":\"1291546\",\"cover_y\":732},{\"directors\":[\"����\"],\"rate\":\"8.7\",\"cover_x\":1500,\"star\":\"45\",\"title\":\"���ӵ���\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/3742360\\/\",\"casts\":[\"����\",\"����\",\"����\",\"������\",\"����\"],\"cover\":\"https://img1.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p1512562287.jpg\",\"id\":\"3742360\",\"cover_y\":2200},{\"directors\":[\"����ΰ\"],\"rate\":\"9.2\",\"cover_x\":3645,\"star\":\"45\",\"title\":\"������֮��ʥȢ��\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/1292213\\/\",\"casts\":[\"���ǳ�\",\"���ϴ�\",\"����\",\"���ٷ�\",\"������\"],\"cover\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2455050536.jpg\",\"id\":\"1292213\",\"cover_y\":5103},{\"directors\":[\"����ΰ\"],\"rate\":\"8.9\",\"cover_x\":1005,\"star\":\"45\",\"title\":\"������֮�¹ⱦ��\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/1299398\\/\",\"casts\":[\"���ǳ�\",\"���ϴ�\",\"�޼�Ӣ\",\"������\",\"Ī��ε\"],\"cover\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p1280323646.jpg\",\"id\":\"1299398\",\"cover_y\":1437},{\"directors\":[\"�⾩\"],\"rate\":\"7.1\",\"cover_x\":1024,\"star\":\"35\",\"title\":\"ս��2\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/26363254\\/\",\"casts\":[\"�⾩\",\"�����ˡ�������\",\"���\",\"�ź�\",\"¬���\"],\"cover\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2494701965.jpg\",\"id\":\"26363254\",\"cover_y\":1548},{\"directors\":[\"���\"],\"rate\":\"7.4\",\"cover_x\":4134,\"star\":\"35\",\"title\":\"���ه�;̩֮��\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/10574622\\/\",\"casts\":[\"���\",\"����ǿ\",\"�Ʋ�\",\"�պ�\",\"л�\"],\"cover\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p1793720172.jpg\",\"id\":\"10574622\",\"cover_y\":5906},{\"directors\":[\"������\"],\"rate\":\"7.3\",\"cover_x\":2143,\"star\":\"35\",\"title\":\"ʧ��33��\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/4873490\\/\",\"casts\":[\"�װٺ�\",\"����\",\"�ż���\",\"��ҫ��\",\"������\"],\"cover\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p1252876266.jpg\",\"id\":\"4873490\",\"cover_y\":3000},{\"directors\":[\"�ܻ�\"],\"rate\":\"7.9\",\"cover_x\":717,\"star\":\"40\",\"title\":\"���ڶ�\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/24751756\\/\",\"casts\":[\"��С��\",\"����\",\"�ź���\",\"����\",\"���׷�\"],\"cover\":\"https://img1.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2292976849.jpg\",\"id\":\"24751756\",\"cover_y\":1000},{\"directors\":[\"���ǳ�\"],\"rate\":\"6.7\",\"cover_x\":3500,\"star\":\"35\",\"title\":\"������\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/19944106\\/\",\"casts\":[\"�˳�\",\"��־��\",\"�����\",\"����\",\"���\"],\"cover\":\"https://img1.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2316177058.jpg\",\"id\":\"19944106\",\"cover_y\":4889},{\"directors\":[\"Ѧ��·\"],\"rate\":\"7.4\",\"cover_x\":700,\"star\":\"35\",\"title\":\"������������ͼ\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/10574468\\/\",\"casts\":[\"��Ψ\",\"���㲨\",\"����\",\"������\",\"������\"],\"cover\":\"https://img1.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p1878328589.jpg\",\"id\":\"10574468\",\"cover_y\":1000},{\"directors\":[\"����\"],\"rate\":\"7.1\",\"cover_x\":6458,\"star\":\"35\",\"title\":\"�������\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/25805741\\/\",\"casts\":[\"���ܷ�\",\"�°���\",\"�Ӻ���\",\"����\",\"ԬȪ\"],\"cover\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2195469915.jpg\",\"id\":\"25805741\",\"cover_y\":9213},{\"directors\":[\"�¿���\"],\"rate\":\"7.6\",\"cover_x\":1017,\"star\":\"40\",\"title\":\"�й��ϻ���\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/11529526\\/\",\"casts\":[\"������\",\"�˳�\",\"١��Ϊ\",\"�ž�\"],\"cover\":\"https://img1.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p1959304567.jpg\",\"id\":\"11529526\",\"cover_y\":1500},{\"directors\":[\"������\"],\"rate\":\"8.2\",\"cover_x\":5906,\"star\":\"40\",\"title\":\"���μ�֮��ʥ����\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/26277313\\/\",\"casts\":[\"����\",\"���ӽ�\",\"������\",\"ͯ����\",\"������\"],\"cover\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2255028780.jpg\",\"id\":\"26277313\",\"cover_y\":8268},{\"directors\":[\"�ֳ���\"],\"rate\":\"8.4\",\"cover_x\":1429,\"star\":\"40\",\"title\":\"�캣�ж�\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/26861685\\/\",\"casts\":[\"����\",\"�ƾ��\",\"����\",\"�Ž�\",\"���ϼ\"],\"cover\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2514119443.jpg\",\"id\":\"26861685\",\"cover_y\":2000},{\"directors\":[\"�Ʒ�\",\"���ħ\"],\"rate\":\"7.4\",\"cover_x\":5906,\"star\":\"35\",\"title\":\"�����ط���\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/25964071\\/\",\"casts\":[\"����\",\"����\",\"����\",\"����\",\"����\"],\"cover\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2264377763.jpg\",\"id\":\"25964071\",\"cover_y\":8268},{\"directors\":[\"���ǳ�\",\"���ӽ�\"],\"rate\":\"7.1\",\"cover_x\":5906,\"star\":\"35\",\"title\":\"���ν�ħƪ\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/5308265\\/\",\"casts\":[\"����\",\"���\",\"�Ʋ�\",\"��־��\",\"������\"],\"cover\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p1856385532.jpg\",\"id\":\"5308265\",\"cover_y\":8346},{\"directors\":[\"����\"],\"rate\":\"8.3\",\"cover_x\":578,\"star\":\"40\",\"title\":\"����ʯͷ\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/1862151\\/\",\"casts\":[\"����\",\"����\",\"����\",\"�Ʋ�\",\"���\"],\"cover\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p712241453.jpg\",\"id\":\"1862151\",\"cover_y\":764},{\"directors\":[\"��ޱ\"],\"rate\":\"6.6\",\"cover_x\":717,\"star\":\"35\",\"title\":\"�������ս���ȥ���ഺ\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/6973376\\/\",\"casts\":[\"�����\",\"����͢\",\"����\",\"����Ӱ\",\"����ɪ\"],\"cover\":\"https://img1.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p1935067049.jpg\",\"id\":\"6973376\",\"cover_y\":1000},{\"directors\":[\"�ֳ���\"],\"rate\":\"8.0\",\"cover_x\":1429,\"star\":\"40\",\"title\":\"�ع����ж�\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/25815034\\/\",\"casts\":[\"�ź���\",\"������\",\"�﴾\",\"�±���\",\"���ľ�\"],\"cover\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2380677316.jpg\",\"id\":\"25815034\",\"cover_y\":2000},{\"directors\":[\"��С��\"],\"rate\":\"7.7\",\"cover_x\":2000,\"star\":\"40\",\"title\":\"����\",\"url\":\"https:\\/\\/movie.douban.com\\/subject\\/26862829\\/\",\"casts\":[\"����\",\"����\",\"�ӳ���\",\"�����\",\"������\"],\"cover\":\"https://img3.doubanio.com\\/view\\/photo\\/s_ratio_poster\\/public\\/p2507227732.jpg\",\"id\":\"26862829\",\"cover_y\":2802}]}\n";
        String result = StringProcess.regex2StrSplitByMark(content,"movie.douban.com.*?\"","\n");

        System.out.println(result);
    }
}
