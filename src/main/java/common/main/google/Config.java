package common.main.google;

import common.system.FileOperation;

public class Config {



    public static int searchTopKeyNum=30;   // top-k
    public static String httpProxy="127.0.0.1:9666";   //
    public static String mysqlHost="192.168.3.78:3306";  //
    public static String mysqlDatabaseName="junkewei";   //
    public static String mysqlUserName="pxqb";           //
    public static String mysqlPassword="1!p@";     //


//    searchTopKeyNum=30
//    mysqlHost=192.168.3.78:3306
//    mysqlDatabaseName=junkewei
//    mysqlUserName=pxqb
//    mysqlPassword=1!p@ssword



    public static void init(){
        String content = FileOperation.read("config/config.txt");
        String []lines = content.split("\n");

        for(String line:lines){
            if(line.length()>3&&!line.startsWith("#")){
                line=line.trim();
                if(line.startsWith("mysqlHost")){
                    mysqlHost=line.split("=")[1].trim();
                }else if(line.startsWith("mysqlDatabaseName")){
                    mysqlDatabaseName=line.split("=")[1].trim();
                }else if(line.startsWith("mysqlUserName")){
                    mysqlUserName=line.split("=")[1].trim();
                }else if(line.startsWith("mysqlPassword")){
                    mysqlPassword=line.split("=")[1].trim();
                }else if(line.startsWith("httpProxy")){
                    httpProxy=line.split("=")[1].trim();
                }else if(line.startsWith("searchTopKeyNum")){
                    searchTopKeyNum=Integer.parseInt(line.split("=")[1].trim());
                }
            }
        }

    }


}
