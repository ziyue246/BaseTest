package common.testMain;

import common.pojo.NameTest;
import common.system.AppContext;
import common.system.Systemconfig;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ziyue on 2017/12/20.
 */
public class MysqlTest {

    private static Logger logger = Logger.getLogger(MysqlTest.class);


    @Before
    public void start(){
        AppContext.initial();
    }

    @Test
    public void testMainIdMethod() throws Exception{

        System.out.println(Systemconfig.commonService.getNames());
    }



    @Test
    public void testMainIdMethodExecuteTime() throws Exception{



        Long mtime = Systemconfig.commonService.testExecuteTime();
        System.out.println(mtime);
        System.out.println(mtime/1000.0);
    }




    @Test
    public void testInsertUpdateId() throws Exception{



        NameTest name = new NameTest();

        name.setName("");
        Systemconfig.commonService.testInsertUpdateId(name);
        System.out.println(name);
    }
//    46073505
//    6360997
//    9470910

}
