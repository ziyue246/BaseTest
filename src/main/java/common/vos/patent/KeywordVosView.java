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
public class KeywordVosView {


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


    private static Logger logger = Logger.getLogger(KeywordVosView.class);

    @Test
    public void getnetWork(){
        String path="file/vos/data/patent/keyword/dataSrc";
        String keywordPath  = "file/vos/data/patent/keyword/keywordRecord.txt";
        String []lines = FileOperation.read(path).split("\n");


        HashMap<String,Integer> keywordRecord = new HashMap<>();
        int index=1;
        List<KeywordData> keywordDataList = new ArrayList<>();
        List<PatentData> patentDataList = new ArrayList<>();

        for(String line:lines){
            line = line.trim();
            String []strs = line.split("##");
            PatentData patentData = new PatentData();
            patentDataList.add(patentData);
            patentData.setId(StringProcess.str2Int(strs[0].trim()));
            patentData.setPatentName(strs[1].trim());
            patentData.setPatentCode(strs[2].trim());
            patentData.setCategory(strs[3].trim());
            List<String> keywordList = new ArrayList<>();
            patentData.setKeywords(keywordList);
            for(int i=4;i<strs.length;i++){
                String keyword=strs[i].trim();
                keywordList.add(keyword);
                KeywordData keywordData = new KeywordData();
                keywordDataList.add(keywordData);
                if(keywordRecord.get(keyword)==null){
                    String keywordRecordLine = index+"\t"+keyword;
                    keywordRecord.put(keyword,index++);

                    FileOperation.appendWrite(keywordPath,keywordRecordLine+"\n");
                }
                keywordData.setId(keywordRecord.get(keyword));
                keywordData.setKeyword(keyword);
                keywordData.setPatentId(patentData.getId());
            }
        }


        for(KeywordData datai:keywordDataList){
            for(KeywordData dataj:keywordDataList){
                if(!datai.equals(dataj)&&datai.getPatentId()==dataj.getPatentId()
                        &&datai.getId()!=dataj.getId()){
                    datai.getRelationMap().put(dataj.id,1);
                    dataj.getRelationMap().put(datai.id,1);
                }
            }
        }

        for(KeywordData datai:keywordDataList){
            for(KeywordData dataj:keywordDataList){
                if(datai.getId()>=0&&!datai.equals(dataj)
                        &&datai.getId()==dataj.getId()){
                    Map<Integer,Integer> map1 = datai.getRelationMap();
                    Map<Integer,Integer> map2 = dataj.getRelationMap();
                    Set<Integer> keySet2 = map2.keySet();
                    for(int j:keySet2){
                        if(map1.get(j)==null){
                            map1.put(j,map2.get(j));
                        }else{
                            map1.put(j,map1.get(j)+map2.get(j));
                        }
                    }
                    dataj.setId(-1);
                }
            }
        }


        path = "file/vos/data/patent/keyword/network_sparse.txt";


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
        String mapPath = "file/vos/data/patent/keyword/map_notLabel.txt";
        if(FileOperation.exist(mapPath))
        {
            getLabelMap(KeywordDataMap);
        }

    }

    public void getLabelMap(Map<Integer,KeywordData> KeywordDataMap){

        String path = "file/vos/data/patent/keyword/map_notLabel.txt";
        String []lines = FileOperation.read(path).split("\n");

        String mapPath = "file/vos/data/patent/keyword/map.txt";
        String networkPath = "file/vos/data/patent/keyword/network.txt";

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
