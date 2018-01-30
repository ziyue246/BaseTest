package common.testMain;

import common.pojo.BbsData;
import common.pojo.NameTest;
import common.pojo.NewsData;
import common.pojo.WeiboData;
import common.system.AppContext;
import common.system.FileOperation;
import common.system.Systemconfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.rules.TestName;

import java.util.List;

/**
 * Created by ziyue on 2017/12/20.
 */
public class GetMysqlDateTest {

    private static Logger logger = Logger.getLogger(GetMysqlDateTest.class);


    @Test
    public void testMainIdMethod() throws Exception{

        AppContext.initial();


        System.out.println(Systemconfig.commonService.getNames());
    }



    @Test
    public void testMainIdMethodExecuteTime() throws Exception{

        AppContext.initial();

        Long mtime = Systemconfig.commonService.testExecuteTime();
        System.out.println(mtime);
        System.out.println(mtime/1000.0);
    }

    @Test
    public void testInsertUpdateId() throws Exception{

        AppContext.initial();

        NameTest name = new NameTest();

        name.setName("ÕÅÈý");
        Systemconfig.commonService.testInsertUpdateId(name);
        System.out.println(name);
    }
//    46073505
//    6360997
//    9470910

}
