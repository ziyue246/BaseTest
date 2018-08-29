package common.main.google;

import common.system.EnAnalysis;
import org.junit.Test;

/**
 * Created by ziyue on 2018/8/24.
 */
public class TestCode {



    @Test
    public void test(){
        String str1="Historical Review of the ITS Society";
        String str2="Historical Review of the ITS Society h";
        double value  = EnAnalysis.getSimilarity(str1,str2);

        System.out.println(value);















    }
}
