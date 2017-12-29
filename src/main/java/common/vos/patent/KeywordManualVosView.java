package common.vos.patent;

import common.pojo.PatentData;
import common.system.FileOperation;
import common.system.StringProcess;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.*;

/**
 * Created by ziyue on 2017/12/22.
 */
public class KeywordManualVosView {


    class KeywordData{
        private int id;
        private String keyword;
        private Map<Integer,Integer> relationMap = new HashMap<>();
        private int patentId;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public Map<Integer, Integer> getRelationMap() {
            return relationMap;
        }

        public void setRelationMap(Map<Integer, Integer> relationMap) {
            this.relationMap = relationMap;
        }

        public int getPatentId() {
            return patentId;
        }

        public void setPatentId(int patentId) {
            this.patentId = patentId;
        }
    }


    private static Logger logger = Logger.getLogger(KeywordManualVosView.class);

    @Test
    public void getnetWork(){

        String keywordPath  = "file/vos/data/patent/keyword/keywordRecord.txt";
        String []lines = FileOperation.read(keywordPath).split("\n");


        HashMap<String,Integer> keywordRecord = new HashMap<>();
        int index=1;
        List<KeywordData> keywordDataList = new ArrayList<>();
        List<PatentData> patentDataList = new ArrayList<>();

        for(String line:lines){
            line = line.trim();
            String []strs = line.split("\t");
            KeywordData keywordData = new KeywordData();
            keywordDataList.add(keywordData);
            keywordData.setId(StringProcess.str2Int(strs[0].trim()));
            keywordData.setKeyword(strs[1].trim());
        }


        //1-35     35
        //36-168   132
        //169-196  27
        int threshold1=10;
        int threshold11=10;
        int threshold2=10;
        int threshold22=10;
        double threshold3=0.005;
        double threshold4=0.02;
        for(int i=0;i<keywordDataList.size();++i){
            KeywordData data1 = keywordDataList.get(i);
            //if ((Math.random() >0.5))continue;
            for(int k=i;k<patentDataList.size();k++) {
//                if(data1.getKeyword().endsWith("1")){
//                    data1.getRelationMap().put(k,//+ (int) (Math.random() * (57 - k)
//                            (int) (Math.random() * threshold1));
//                    continue;
//                }else if(data1.getKeyword().endsWith("2")){
//                    data1.getRelationMap().put(k,//+ (int) (Math.random() * (57 - k)
//                            (int) (Math.random() * threshold1));
//                    continue;
//                }
                if ((Math.random() <threshold4)) {
                    data1.getRelationMap().put(k+1,//+ (int) (Math.random() * (57 - k)
                            (int) (Math.random() * threshold2+threshold22));
                }
            }
            for(int j=i;j<35;j++){

//                if(data1.getKeyword().endsWith("1")){
//                    data1.getRelationMap().put(j+1,//+ (int) (Math.random() * (57 - k)
//                            threshold1);
//                    continue;
//                }else if(data1.getKeyword().endsWith("2")){
//                    data1.getRelationMap().put(j+1,//+ (int) (Math.random() * (57 - k)
//                            threshold1);
//                    continue;
//                }

                    data1.getRelationMap().put(j+1,(int)(Math.random()*threshold1+threshold11));
            }
            if(i<35)continue;
            for(int j=i;j<168;j++){
//                if(data1.getKeyword().endsWith("1")){
//                    data1.getRelationMap().put(j+1,//+ (int) (Math.random() * (57 - k)
//                            threshold1);
//                    continue;
//                }else if(data1.getKeyword().endsWith("2")){
//                    data1.getRelationMap().put(j+1,//+ (int) (Math.random() * (57 - k)
//                            threshold1);
//                    continue;
//                }
                if ((Math.random() >0.2))continue;
                data1.getRelationMap().put(j+1,(int)(Math.random()*threshold1+threshold11));
            }
            if(i<168)continue;
            for(int j=i;j<keywordDataList.size();j++){

//                if(data1.getKeyword().endsWith("1")){
//                    data1.getRelationMap().put(j+1,//+ (int) (Math.random() * (57 - k)
//                            threshold1);
//                    continue;
//                }else if(data1.getKeyword().endsWith("2")){
//                    data1.getRelationMap().put(j+1,//+ (int) (Math.random() * (57 - k)
//                            threshold1);
//                    continue;
//                }

                data1.getRelationMap().put(j+1,(int)(Math.random()*threshold1+threshold11));
            }
        }

        String path = "file/vos/data/patent/keyword/network_sparse_manual.txt";


        FileOperation.delete(path);


        Map<Integer,KeywordData> KeywordDataMap = new HashMap<>();
        for(KeywordData data:keywordDataList){
            if(data.getId()<0)continue;
            Set<Integer> relationSet  = data.getRelationMap().keySet();
            KeywordDataMap.put(data.getId(),data);
            for(int id:relationSet){
                int weight = data.getRelationMap().get(id);
                String writeLine = data.getId()+"\t"+id+"\t"+weight;
                FileOperation.appendWrite(path,writeLine+"\n");
                System.out.println(writeLine);
            }
        }
//        String mapPath = "file/vos/data/patent/keyword/map_notLabel_manual.txt";
//        if(FileOperation.exist(mapPath))
//        {
//            getLabelMap(KeywordDataMap);
//        }

    }

    public void getLabelMap(Map<Integer,KeywordData> KeywordDataMap){

        String path = "file/vos/data/patent/keyword/map_notLabel_manual.txt";
        String []lines = FileOperation.read(path).split("\n");

        String mapPath = "file/vos/data/patent/keyword/map_manual.txt";
        String networkPath = "file/vos/data/patent/keyword/network_manual.txt";

        FileOperation.delete(mapPath);
        FileOperation.delete(networkPath);
        for(String line:lines){
            line = line.trim();
            if(line.startsWith("id")){
                line=line+"\t"+"label";
            }else{
                int id = StringProcess.str2Int(line.split("\t")[0].trim());
                KeywordData data = KeywordDataMap.get(id);
                if(data.getId()!=id){
                    logger.error("line id:"+id+"\n"+data.toString());
                    return ;
                }
                line=line+"\t"+data.getKeyword();

                Set<Integer> relationSet  = data.getRelationMap().keySet();
                for(int relationId:relationSet){
                    int weight = data.getRelationMap().get(relationId);
                    String writeLine = data.getId()+"\t"+relationId+"\t"+weight;
                    FileOperation.appendWrite(networkPath,writeLine+"\n");
                    System.out.println(writeLine);
                }
            }
            FileOperation.appendWrite(mapPath,line+"\n");
        }

    }

}
