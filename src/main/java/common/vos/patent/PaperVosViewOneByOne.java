package common.vos.patent;

import common.pojo.PatentData;
import common.system.FileOperation;
import common.system.StringProcess;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by ziyue on 2017/12/22.
 */
public class PaperVosViewOneByOne {


    private static Logger logger = Logger.getLogger(PaperVosViewOneByOne.class);

    @Test
    public void getnetWork(){


        String postfix="3";

        String path="file/vos/data/patent/paper/dataSrc"+postfix;
        String []lines = FileOperation.read(path).split("\n");


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
                keywordList.add(strs[i].trim());
            }
        }

        for(int i=0;i<patentDataList.size();i++){
            PatentData data1=patentDataList.get(i);
            for(int j=i;j<patentDataList.size();j++){
                PatentData data2=patentDataList.get(j);
                int weight = getWeight(data1,data2);
                if(weight>0){
                    data1.getRelationMap().put(data2.getId(),weight);
                    //data2.getRelationMap().put(data1.getId(),weight);
                }
            }
        }

        path = "file/vos/data/patent/paper/network_sparse"+postfix+".txt";
        FileOperation.delete(path);
        for(PatentData data:patentDataList){
            Set<Integer> relationSet  = data.getRelationMap().keySet();

            for(int id:relationSet){
                int weight = data.getRelationMap().get(id);
                String writeLine = data.getId()+"\t"+id+"\t"+weight;
                FileOperation.appendWrite(path,writeLine+"\n");
                System.out.println(writeLine);
            }
        }

    }



    @Test
    public void getLabelMap(){
        String postfix="3";

        String path="file/vos/data/patent/paper/dataSrc"+postfix;
        String []lines = FileOperation.read(path).split("\n");


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
                keywordList.add(strs[i].trim());
            }
        }
        for(int i=0;i<patentDataList.size();i++){
            PatentData data1=patentDataList.get(i);
            for(int j=i;j<patentDataList.size();j++){
                PatentData data2=patentDataList.get(j);
                int weight = getWeight(data1,data2);
                if(weight>0){
                    data1.getRelationMap().put(data2.getId(),weight);
                    //data2.getRelationMap().put(data1.getId(),weight);
                }
            }
        }
        path = "file/vos/data/patent/paper/map_notLabel"+postfix+".txt";
        lines = FileOperation.read(path).split("\n");


        String mapPath = "file/vos/data/patent/paper/map"+postfix+".txt";
        String networkPath = "file/vos/data/patent/paper/network"+postfix+".txt";

        FileOperation.delete(mapPath);
        FileOperation.delete(networkPath);
        for(String line:lines){
            line = line.trim();
            if(line.startsWith("id")){
                line=line+"\t"+"label";
            }else{
                int id = StringProcess.str2Int(line.split("\t")[0].trim());
                PatentData data = patentDataList.get(id-1);
                if(data.getId()!=id){
                    logger.error("line id:"+id+"\n"+data.toString());
                    return ;
                }
                line=line+"\t"+data.getPatentCode();

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
//    public PatentData getPatentById(List<PatentData> patentDataList,int id){
//
//        for(){
//
//        }
//        return null;
//    }


    public int getWeight(PatentData data1,PatentData data2){

        List<String> ks1 = data1.getKeywords();
        List<String> ks2 = data2.getKeywords();

        int count=0;
        for(String k1:ks1){
            for(String k2:ks2){
                if(k1.equals(k2)){
                    ++count;
                }
            }
        }
        return count;
    }
}
