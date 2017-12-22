package common.system;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {

    public static AbstractApplicationContext  app ;

    public static void initial(){
        app = new ClassPathXmlApplicationContext
                ("config/applicationContext.xml");
    }

    public static void close(){
        app.close();
    }
}
