package common.main.google;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.sql.*;
import java.util.ArrayList;
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
    public static List<GooglePaperData> getPaperDatas() {
        ArrayList<GooglePaperData> list = new ArrayList<GooglePaperData>();
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

            String sql_model = "select id, title from paper_data where google_update=0 ordery by limit 1000";

            sql_model = "select id, title,cite_num_wos from paper_data where google_update=0  and cite_num_wos>50  limit 1000";
            PreparedStatement ps = conn.prepareStatement(sql_model);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                GooglePaperData data = new GooglePaperData();
                data.setId(id);
                data.setTitle(title);
                list.add(data);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public static void updateGooglePaperYearsCite(GooglePaperData data) {

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

            String sql_model = "update paper_data set every_year_cite_num_google = '<every_year_cite_num_google>' " +
                    " ,cite_num_google = <cite_num_google> , google_update=<google_update>  " +
                    "where id = <id>";


            String sql = sql_model.replace("<every_year_cite_num_google>",
                            data.getYearsCite()+"")
                    .replace("<cite_num_google>",data.getCiteNum()+"")
                    .replace("<google_update>",data.getUpdate()+"")
                    .replace("<id>",data.getId()+"");
            try {
                stmt.executeUpdate(sql);
            }catch (Exception e) {
                logger.info("update sql:"+sql);
                e.printStackTrace();
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
