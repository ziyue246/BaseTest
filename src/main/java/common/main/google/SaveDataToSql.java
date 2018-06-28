package common.main.google;

import java.sql.*;
import java.util.List;

public class SaveDataToSql {



    public static void insertGooglePaperSearchTopK(List<GooglePaperData> list) {

        try {
            //Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String databaseHost = Config.mysqlHost;//
            String databaseName = Config.mysqlDatabaseName;// 已经在MySQL数据库中创建好的数据库。
            String userName = Config.mysqlUserName;// MySQL默认的root账户名
            String password = Config.mysqlPassword;// 默认的root账户密码为空
            String url ="jdbc:mysql://"+databaseHost+"/"
                    +databaseName+"?user="+userName+"&password="+password+"&useUnicode=true&characterEncoding=UTF-8";
            //Connection conn = DriverManager.getConnection("jdbc:mysql://"+databaseHost+"/" + databaseName, userName, password);
            Connection conn = DriverManager.getConnection(url);

            Statement stmt = conn.createStatement();

            String sql_model = "insert into googlePaperSearchTopK(title,orderId,authors,authorsUrl,brief,pubYear,searchKeyword) " +
                    "values ('<title>',<orderId>,'<authors>','<authorsUrl>','<brief>','<pubYear>','<searchKeyword>')";

            // 创建数据库中的表，

            for(GooglePaperData data:list) {

                String sql = sql_model.replace("<title>",data.getTitle())
                        .replace("<orderId>",data.getOrderId()+"")
                        .replace("<authors>",data.getAuthors())
                        .replace("<authorsUrl>",data.getAuthorsUrl())
                        .replace("<brief>",data.getBrief())
                        .replace("<pubYear>",data.getPubYear()+"")
                        .replace("<searchKeyword>",data.getSearchKeyword());

                stmt.executeUpdate(sql);
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void insertGooglePaperYearsCite(List<GooglePaperData> list) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            String databaseName = "phildatabase";// 已经在MySQL数据库中创建好的数据库。
            String userName = "root";// MySQL默认的root账户名
            String password = "";// 默认的root账户密码为空
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, userName, password);

            Statement stmt = conn.createStatement();

            String sql_model = "insert into googlePaperSearchTopK(title,orderId,authors,brief,pubYear,searchKeyword) " +
                    "values ('<title>',<orderId>,'<authors>','<brief>','<pubYear>','<searchKeyword>')";

            // 创建数据库中的表，

            for(GooglePaperData data:list) {

                String sql = sql_model.replace("<title>",data.getTitle())
                        .replace("<orderId>",data.getOrderId()+"")
                        .replace("<authors>",data.getAuthors())
                        .replace("<brief>",data.getBrief())
                        .replace("<pubYear>",data.getPubYear()+"")
                        .replace("<searchKeyword>",data.getSearchKeyword());

                stmt.executeUpdate(sql);
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
