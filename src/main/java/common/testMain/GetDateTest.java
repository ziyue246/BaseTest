package common.testMain;

import common.pojo.BbsData;
import common.pojo.NewsData;
import common.pojo.WeiboData;
import common.system.AppContext;
import common.system.FileOperation;
import common.system.Systemconfig;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

/**
 * Created by ziyue on 2017/12/20.
 */
public class GetDateTest {

    private static Logger logger = Logger.getLogger(GetDateTest.class);


    @Test
    public void testMainRowNumMethod() throws Exception{

        AppContext.initial();


//        //2362408
//        for(int i=0;i<2362408;i+=10000)
//        {
//            List<BbsData> bbsDataList = Systemconfig.hotSearchService.getBbsDatesBySplitPage(i);
//            for (BbsData data : bbsDataList) {
//                FileOperation.appendWrite("file/hotSearch/bbs.txt", data.getCONTENT());
//            }
//            System.out.println("bbs:"+1.0*(10000+i)/2362408);
//        }
//        logger.info("bbs Over...");
//        //10899
//        for(int i=0;i<10899;i+=10000) {
//            List<NewsData> newsDataList = Systemconfig.hotSearchService.getNewsDatesBySplitPage(i);
//            for (NewsData data : newsDataList) {
//                FileOperation.appendWrite("file/hotSearch/news.txt", data.getCONTENT());
//            }
//            System.out.println("news:"+1.0*(10000+i)/10899);
//        }
//        logger.info("news Over...");
        //18644614
        for(int i=0;i<18644614;i+=10000) {
            List<WeiboData> weiboDataList = Systemconfig.hotSearchService.getWeiboDatesBySplitPage(i);
            for (WeiboData data : weiboDataList) {
                FileOperation.appendWrite("file/hotSearch/weibo.txt", data.getContent());
            }
            System.out.println("weibo:"+1.0*(10000+i)/18644614);

        }
        logger.info("weibo Over...");
        logger.info("all Over...");
    }


    @Test
    public void testMainIdMethod() throws Exception{

        AppContext.initial();


        //2362408
        for(int i=0;i<9470910;i+=10000)
        {
            List<BbsData> bbsDataList = Systemconfig.hotSearchService.getBbsDatesByIdSplitPage(i);
            for (BbsData data : bbsDataList) {
                FileOperation.appendWrite("file/hotSearch/bbs.txt", data.getCONTENT());
            }
            System.out.println("bbs:"+1.0*(10000+i)/9470910);
        }
        logger.info("bbs Over...");
        //10899
        for(int i=0;i<6360997;i+=10000) {
            List<NewsData> newsDataList = Systemconfig.hotSearchService.getNewsDatesByIdSplitPage(i);
            for (NewsData data : newsDataList) {
                FileOperation.appendWrite("file/hotSearch/news.txt", data.getCONTENT());
            }
            System.out.println("news:"+1.0*(10000+i)/6360997);
        }
        logger.info("news Over...");
        //18644614
        for(int i=0;i<46073505;i+=10000) {
            List<WeiboData> weiboDataList = Systemconfig.hotSearchService.getWeiboDatesByIdSplitPage(i);
            for (WeiboData data : weiboDataList) {
                FileOperation.appendWrite("file/hotSearch/weibo.txt", data.getContent());
            }
            System.out.println("weibo:"+1.0*(10000+i)/46073505);

        }
        logger.info("weibo Over...");
        logger.info("all Over...");
    }
//    46073505
//    6360997
//    9470910

}
