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
                 robot = new Robot();//����Robot����
             } catch (Exception e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
             while(true) {
                 //�����ǰ��º��ͷ�alt����������Ĺ����ǵ��ò˵�
                 robot.mousePress(KeyEvent.BUTTON1_MASK);
                 robot.mouseRelease(KeyEvent.BUTTON1_MASK);
                 robot.delay(2000);//�ӳ�500ms
                 //�����ǰ��º��ͷŻس���������ȷ����fileѡ��
                 robot.mouseWheel(5);
                 robot.mouseWheel(5);
                 robot.mouseWheel(5);

                 robot.mouseRelease(KeyEvent.BUTTON1_MASK);
                 robot.delay(1000);
                 logger.info("����һ��");
             }
         }


}
