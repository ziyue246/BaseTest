package common.main.researchGate;

import org.apache.log4j.Logger;
import org.junit.Test;


import java.sql.*;
import java.util.*;

public class SaveDataToSql {


    private static Logger logger = Logger.getLogger(SaveDataToSql.class);

    public static void insertResearchGateAuthorsInfo(ResearchGateAuthorData data) {

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
                    "( `authorName`, `url`, `authorRgScore`, `institution`, `currPosition`, `skills`, `researchItemsNum`, " +
                    "`readsNum`, `citationsNun`) " +
             "VALUES ( '<authorName>', '<url>', <authorRgScore>, '<institution>', '<currPosition>', '<skills>', <researchItemsNum>, " +
                    "<readsNum>, <citationsNun>)";

            String sql = sql_model.replace("<authorName>",data.getName())
                            .replace("<url>",data.getUrl()+"")
                            .replace("<authorRgScore>",data.getAuthorRgScore()+"")
                            .replace("<institution>",data.getInstitution())
                            .replace("<currPosition>",data.getCurrPosition())
                            .replace("<skills>",data.getSkills())
                            .replace("<currPosition>",data.getCurrPosition())
                            .replace("<skills>",data.getSkills())
                            .replace("<researchItemsNum>",data.getResearchItemsNum()+"")
                            .replace("<readsNum>",data.getReadsNum()+"")
                            .replace("<citationsNun>",data.getCitationsNun()+"")
                            ;
            try {
                stmt.executeUpdate(sql);
            }catch (Exception e) {
                logger.info("insert sql:"+sql);
                e.printStackTrace();
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String,Integer> getRgAuthorDatas() {
        Map<String,Integer> map = new HashMap<>();
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

            sql_model = "select id, name from researchGateAuthors";
            PreparedStatement ps = conn.prepareStatement(sql_model);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                map.put(name,id);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static List<ResearchGatePaperData> getPaperDatas() {
        ArrayList<ResearchGatePaperData> list = new ArrayList<ResearchGatePaperData>();
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
            logger.info("sql conn url:"+url);
            Connection conn = DriverManager.getConnection(url);

            String sql_model = "select id, title from paper_data where google_update=0 ordery by limit 1000";

            sql_model = "select id, title,doi,cite_num_wos from paper_data where rg_update=0  and cite_num_wos>5  limit 1000";
            PreparedStatement ps = conn.prepareStatement(sql_model);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String doi = rs.getString("doi");
                ResearchGatePaperData data = new ResearchGatePaperData();
                data.setId(id);
                data.setTitle(title);
                data.setDoi(doi);
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


    public static Set<String> getPaperAutorDatas() {
        Set<String> authorNameSet = new HashSet<>();
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
            logger.info("sql conn url:"+url);
            Connection conn = DriverManager.getConnection(url);

            String sql_model = "select id,authorName,url,authorRgScore,institution,currPosition,skills,researchItemsNum,readsNum,citationsNun from researchGateAuthors";

            sql_model = "select distinct(authorName) as authorName from rgPaperAuthor " +
                    "where char_length(authorName) >2 and authorName not in " +
                    "(select authorName from researchGateAuthors) limit 1000";
            PreparedStatement ps = conn.prepareStatement(sql_model);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                authorNameSet.add(rs.getString("authorName"));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorNameSet;
    }

    @Test
    public void buildRgPaper2AuthorTable() {
        Config.init();
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

            logger.info("sql conn url:"+url);
            Connection conn = DriverManager.getConnection(url);

            String sql_model = "select id,authorName,url,authorRgScore,institution,currPosition,skills,researchItemsNum,readsNum,citationsNun from researchGateAuthors";


            sql_model = "select id,authors_rg from paper_data where rg_update>0 and id not in \n" +
                            "(select paper_id from rgPaperAuthor)";
            PreparedStatement ps = conn.prepareStatement(sql_model);
            ResultSet rs = ps.executeQuery();

            HashMap<Integer,List<String>> authorPaperIdMap = new HashMap<>();
            while(rs.next()) {
                int paper_id = rs.getInt("id");
                String authors_rg = rs.getString("authors_rg");
                if(authors_rg==""||authors_rg.length()<2){
                    authorPaperIdMap.put(paper_id,null);
                    continue;
                }
                List<String> nameList = new ArrayList<>();

                for(String name:authors_rg.split(";")){
                    nameList.add(name);
                }
                authorPaperIdMap.put(paper_id,nameList);
            }

            logger.info("authorPaperIdMap size:"+authorPaperIdMap.size());
            for(int paper_id : authorPaperIdMap.keySet()){
                if(authorPaperIdMap.get(paper_id)==null){
                    String tmp_sql = "insert into rgPaperAuthor(paper_id,authorName) values("+paper_id+",'')";
                    ps.execute(tmp_sql);
                }else{
                    for(String authorName : authorPaperIdMap.get(paper_id)){
                        String tmp_sql = "insert into rgPaperAuthor(paper_id,authorName) values("+paper_id+",'"+authorName+"')";
                        ps.execute(tmp_sql);
                    }
                }
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("buildRgPaper2AuthorTable success");
    }

    public static void updateResearchGateData(ResearchGatePaperData data) {

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

            String sql_model = "update paper_data set rg_reads = '<rg_reads>' " +
                    " ,recommendations_rg = <recommendations_rg> , followers_rg=<followers_rg>  " +
                    " ,comments_rg = <comments_rg> , cite_num_rg=<cite_num_rg>  " +
                    " ,relatedResearch_rg = <relatedResearch_rg> , rg_update=<rg_update> , authors_rg='<authors_rg>'  " +
                    "where id = <id>";

            String sql = sql_model.replace("<rg_reads>", data.getRg_reads()+"")
                    .replace("<recommendations_rg>",data.getRecommendations_rg()+"")
                    .replace("<followers_rg>",data.getFollowers_rg()+"")
                    .replace("<comments_rg>",data.getComments_rg()+"")
                    .replace("<cite_num_rg>",data.getCite_num_rg()+"")
                    .replace("<relatedResearch_rg>",data.getRelatedResearch_rg()+"")
                    .replace("<rg_update>",data.getRg_update()+"")
                    .replace("<authors_rg>",data.getAuthors_rg()+"")
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
