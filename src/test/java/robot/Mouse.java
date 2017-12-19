package robot;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by ziyue on 2017/12/15.
 */
public class Mouse {


    private static Logger logger = Logger.getLogger(Mouse.class);


    @Test
    public void tastMain(){

             Robot robot=null;
             try {
                 robot = new Robot();//创建Robot对象
             } catch (Exception e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
             while(true) {
                 //这里是按下和释放alt键，这个键的功能是调用菜单
                 robot.mousePress(KeyEvent.BUTTON1_MASK);
                 robot.mouseRelease(KeyEvent.BUTTON1_MASK);
                 robot.delay(2000);//延迟500ms
                 //这里是按下和释放回车键，用于确定是file选项
                 robot.mouseWheel(5);
                 robot.mouseWheel(5);
                 robot.mouseWheel(5);

                 robot.mouseRelease(KeyEvent.BUTTON1_MASK);
                 robot.delay(1000);
                 logger.info("滑动一次");
             }
         }


}
