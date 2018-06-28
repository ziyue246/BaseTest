package common.main.google;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public class SaveDataToSql {


    private static Logger logger = Logger.getLogger(SaveDataToSql.class);

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
                    "values ('<title>',<orderId>,'<authors>','<authorsUrl>','<brief>',<pubYear>,'<searchKeyword>')";

            // 创建数据库中的表，

            for(GooglePaperData data:list) {

                String sql = sql_model.replace("<title>",data.getTitle().replace("'","\\'"))
                        .replace("<orderId>",data.getOrderId()+"")
                        .replace("<authors>",data.getAuthors().replace("'","\\'"))
                        .replace("<authorsUrl>",data.getAuthorsUrl().replace("'","\\'"))
                        .replace("<brief>",data.getBrief().replace("'","\\'").replace("\n"," "))
                        .replace("<pubYear>",data.getPubYear()+"")
                        .replace("<searchKeyword>",data.getSearchKeyword().replace("'","\\'"));

                logger.info("insert sql:"+sql);
                stmt.executeUpdate(sql);
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void insertGooglePaperYearsCite(List<GooglePaperData> list) {

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

            String sql_model = "insert into googlePaperYearsCite(title,authors,pubYear,citeNum,citeUrl,yearsCite) " +
                    "values ('<title>',<authors>,<pubYear>,<citeNum>,'<citeUrl>',<yearsCite>)";

            // 创建数据库中的表，

            for(GooglePaperData data:list) {

                String sql = sql_model.replace("<title>",data.getTitle().replace("'","\\'"))
                        .replace("<authors>",data.getAuthors().replace("'","\\'"))
                        .replace("<pubYear>",data.getPubYear()+"")
                        .replace("<citeNum>",data.getCiteNum()+"")
                        .replace("<citeUrl>",data.getCiteNumUrl()+"")
                        .replace("<yearsCite>",data.getYearsCite());

                logger.info("insert sql:"+sql);
                stmt.executeUpdate(sql);
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
