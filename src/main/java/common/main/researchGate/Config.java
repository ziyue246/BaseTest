package common.main.researchGate;

import common.system.FileOperation;

public class Config {




    public static String mysqlHost="192.168.3.78:3306";  //
    public static String mysqlDatabaseName="junkewei";   //
    public static String mysqlUserName="pxqb";           //
    public static String mysqlPassword="1!p@";     //



    public static void init(){
        String content = FileOperation.read("config_researchGate/config.txt");
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
                }
            }
        }

    }


}
