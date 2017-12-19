package weixin;

import common.analysis.EnAnalysis;
import common.system.FileOperation;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
Created by ziyue on 2017/12/15.


 */
public class Weixin {



    @Test
    public void  weixinMessage()
    //public static void  main(String []args)
    {

        String []lines = FileOperation.read("D:\\tmp\\encode.txt").split("\n");
        StringBuffer  sb = new StringBuffer();
        for(String line:lines){
            //System.out.println(line+"##");
            //https://mp.weixin.qq.com/s?src=11&timestamp=1512377206
            if(line.startsWith("Response fullUrl:")&&line.contains("timestamp")){
                line = line.split("Response fullUrl:")[1].
                        split("&key")[0].replace("https","http");
                {
                    sb.append("\n" + line + "##");
                    //System.out.print("\n" + line + "##");
                }

            }else if(line.startsWith("Response body")&&line.contains("like_num")
                    &&line.contains("read_num")){

                //,"read_num":7,"like_num":0,
                String readNumStr = line.split("read_num\":")[1].split(",")[0];
                String likeNumStr = line.split("like_num\":")[1].split(",")[0];
                sb.append(readNumStr+"##"+likeNumStr);
                //System.out.print(readNumStr+"##"+likeNumStr);
            }

        }


        lines = FileOperation.read("file/weixin").split("\n");
        Map<Integer,String> weixinUrlMap = new HashMap<>();
        for(String line:lines){
            int id = Integer.parseInt(line.split("##")[0]);
            String url = line.split("##")[1];
            weixinUrlMap.put(id,url);
        }




        lines =sb.toString().split("\n");


        System.out.print("\n\n\n\n\n");

        Set<String> set = new HashSet<>();
        for(String line:lines){
            line = line.trim();
            if(line==null||line.length()<10||line.endsWith("##")||set.contains(line))continue;
            set.add(line);
            Set<Integer> idSet = weixinUrlMap.keySet();
            for(int id :idSet){
                String url = weixinUrlMap.get(id);
                if(EnAnalysis.getSimilarity(url,line)>0.90){
                    //System.out.println(id+"##"+line);

                    weixinUrlMap.put(id,line);
                }
            }
        }

        Set<Integer> idSet = weixinUrlMap.keySet();
        for(int id :idSet){
            String url = weixinUrlMap.get(id);
            System.out.println(id+"##"+url);
        }


    }


    @Test
    public void test(){
        String a1="https://mp.weixin.qq.com/s?src=3&timestamp=1511504033&ver=1&signature=dwjgjYq3hpTBjIPC3YqpLCPdz3aw6vrQDl3bbA5svGCMXVk1T0BlRgW5IXYF8-nmh9oJbnlEiWuxo1JAkcTyFYJZSEeT5NZ4-Qtn-B46M7cR0iLQQLtRuxH2CwtO5OlYQ-BHzrzzx-T4vPrqDhccxFHEgcA4Va7FSvwJkZ--m0Q=&key=32b31719e59e4223e2fd8338d1a70953b544a9954f6a1c1a65920f5fc79512d6422c19dddbc8b4ce8b9f35b7e036156ad49bdfb4f3a87acfad1ac5d4a9ba3881d0569a6b6c83632e2bb75b8f7e134d2b&ascene=1&uin=MTU4MTM4MzI2Mg%3D%3D&d";
    }
}
