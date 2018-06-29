package common.main.google;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
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
            String url ="jdbc:mysql://"+databaseHost+"/"    //autoReconnect=true&characterEncoding=utf-8
                    +databaseName+"?user="+userName+"&password="+password+"&autoReconnect=true&characterEncoding=UTF-8";
            //Connection conn = DriverManager.getConnection("jdbc:mysql://"+databaseHost+"/" + databaseName, userName, password);
            Connection conn = DriverManager.getConnection(url);

            Statement stmt = conn.createStatement();

            String sql_model = "insert into googlePaperSearchTopK(title,orderId,authors,authorsUrl,brief,pubYear,searchKeyword) " +
                    "values ('<title>',<orderId>,'<authors>','<authorsUrl>','<brief>',<pubYear>,'<searchKeyword>')";

            // 创建数据库中的表，

            for(GooglePaperData data:list) {

                String sql = sql_model.replace("<title>",data.getTitle().replace("'","\\'")+"")
                        .replace("<orderId>",data.getOrderId()+"")
                        .replace("<authors>",data.getAuthors().replace("'","\\'")+"")
                        .replace("<authorsUrl>",data.getAuthorsUrl().replace("'","\\'")+"")
                        .replace("<brief>",data.getBrief().replace("'","\\'").replace("\n"," ")+"")
                        .replace("<pubYear>",data.getPubYear()+"")
                        .replace("<searchKeyword>",data.getSearchKeyword().replace("'","\\'")+"");

                try {

                    //sql = new String(sql.getBytes("gbk"),"utf-8");
                    //sql = filterOffUtf8Mb4(sql);
                    stmt.executeUpdate(sql);
                }catch (Exception e) {
                    logger.info("insert sql:"+sql);
                    e.printStackTrace();
                }
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
                    "values ('<title>','<authors>',<pubYear>,<citeNum>,'<citeUrl>','<yearsCite>')";

            // 创建数据库中的表，

            for(GooglePaperData data:list) {

                String sql = sql_model.replace("<title>",data.getTitle().replace("'","\\'"))
                        .replace("<authors>",data.getAuthors().replace("'","\\'"))
                        .replace("<pubYear>",data.getPubYear()+"")
                        .replace("<citeNum>",data.getCiteNum()+"")
                        .replace("<citeUrl>",data.getCiteNumUrl()+"")
                        .replace("<yearsCite>",data.getYearsCite());


                try {

                    //sql = new String(sql.getBytes("gbk"),"utf-8");
                    //sql = filterOffUtf8Mb4(sql);
                    stmt.executeUpdate(sql);
                }catch (Exception e) {
                    logger.info("insert sql:"+sql);
                    e.printStackTrace();
                }
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private  static  String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException {
        byte[] bytes = text.getBytes("UTF-8");
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        int i = 0;
        while (i < bytes.length) {
            short b = bytes[i];
            if (b > 0) {
                buffer.put(bytes[i++]);
                continue;
            }
            b += 256;
            if ((b ^ 0xC0) >> 4 == 0) {
                buffer.put(bytes, i, 2);
                i += 2;
            }
            else if ((b ^ 0xE0) >> 4 == 0) {
                buffer.put(bytes, i, 3);
                i += 3;
            }
            else if ((b ^ 0xF0) >> 4 == 0) {
                i += 4;
            }
        }
        buffer.flip();
        return new String(buffer.array(), "utf-8");
    }
}
