package common.main.researchGate;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class SaveDataToSql {


    private static Logger logger = Logger.getLogger(SaveDataToSql.class);

    public static void insertResearchGateAuthorsInfo(List<ResearchGateAuthorData> list) {

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

            String sql_model =
            "INSERT INTO `researchGateAuthors` " +
                    "( `name`, `url`, `authorRgScore`, `institution`, `currPosition`, `skills`, `researchItemsNum`, " +
                    "`readsNum`, `citationsNun`, `followersNum`, `followingNum`) " +
             "VALUES ( '<name>', '<url>', <authorRgScore>, '<institution>', '<currPosition>', '<skills>', <researchItemsNum>, " +
                    "<readsNum>, <citationsNun>, <followersNum>, <followingNum>)";



            for(ResearchGateAuthorData data:list) {

                String sql = sql_model.replace("<name>",data.getName())
                                .replace("<authorRgScore>",data.getAuthorRgScore()+"")
                                .replace("<institution>",data.getInstitution())
                                .replace("<currPosition>",data.getCurrPosition())
                                .replace("<skills>",data.getSkills())
                                .replace("<currPosition>",data.getCurrPosition())
                                .replace("<skills>",data.getSkills())
                                .replace("<researchItemsNum>",data.getResearchItemsNum()+"")
                                .replace("<readsNum>",data.getReadsNum()+"")
                                .replace("<citationsNun>",data.getCitationsNun()+"")
                                .replace("<followersNum>",data.getFollowersNum()+"")
                                .replace("<followingNum>",data.getFollowingNum()+"");

                try {
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
}
