package testOther;

import common.test.FunClass;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by ziyue on 2018/5/29.
 */
public class BeanTest {



    @Test
    public void test() throws Exception
    //public static void main(String[] args) throws  Exception
    {


        System.out.println("12312312");
        String className = "common.test.FunClass";
        String methodName = "sayHello";
        Class<?> clz = Class.forName(className);
        //
        Object obj = clz.newInstance();
        //获取方法
        Method m = clz.getMethod(methodName, String.class);
        //调用方法
        String result = (String) m.invoke(obj, "aaaaa");
        System.out.println(result);

        FunClass funClass = new FunClass();

        Method m2 =  funClass.getClass().getMethod(methodName,String.class);
        String result2 = (String) m.invoke(funClass, "m2");
        System.out.println(result2);





    }



}



