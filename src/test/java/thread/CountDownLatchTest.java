package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by ziyue on 2018/6/6.
 */
public class CountDownLatchTest {



        static CountDownLatch c = new CountDownLatch(2);

        public static void main(String[] args) throws Exception {
            new Thread(new Runnable() {


                public void run()  {

                    try {
                        Thread.sleep(1000 * 10);
                    }catch (Exception e){

                    }
                    System.out.println(1);
                    c.countDown();
                    System.out.println(2);
                    c.countDown();
                }
            }).start();

            c.await(1, TimeUnit.SECONDS);
            System.out.println("3");
            System.exit(-1);
            return ;
        }


}
