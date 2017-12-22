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
    public void testMain() throws Exception{

        AppContext.initial();


        //2362408
        for(int i=0;i<2362408/1000;i++)
        {
            List<BbsData> bbsDataList = Systemconfig.hotSearchService.getBbsDatesBySplitPage(i);
            for (BbsData data : bbsDataList) {
                FileOperation.appendWrite("file/hotSearch/bbs.txt", data.getCONTENT());
            }
        }
        logger.info("bbs Over...");
        //10899
        for(int i=0;i<10899/1000;i++) {
            List<NewsData> newsDataList = Systemconfig.hotSearchService.getNewsDatesBySplitPage(i);
            for (NewsData data : newsDataList) {
                FileOperation.appendWrite("file/hotSearch/news.txt", data.getCONTENT());
            }
        }
        logger.info("news Over...");
        //18644614
        for(int i=0;i<18644614/1000;i++) {
            List<WeiboData> weiboDataList = Systemconfig.hotSearchService.getWeiboDatesBySplitPage(i);
            for (WeiboData data : weiboDataList) {
                FileOperation.appendWrite("file/hotSearch/weibo.txt", data.getContent());
            }
        }
        logger.info("weibo Over...");
        logger.info("all Over...");
    }

}
