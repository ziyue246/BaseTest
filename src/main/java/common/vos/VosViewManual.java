package common.vos;

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
public class VosViewManual {


    private static Logger logger = Logger.getLogger(VosViewManual.class);

    @Test
    public void getnetWork(){
        String path="file/vos/dataSrc";
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

        //1-15
        //16-46
        //47-57

        int threshold1=50;
        int threshold2=35;
        double threshold3=0.3;
        for(int i=0;i<patentDataList.size();i++){
            PatentData data1=patentDataList.get(i);

            for(int k=i;k<patentDataList.size();k++) {
                if (Math.random() < threshold3) {
                    data1.getRelationMap().put(k,//+ (int) (Math.random() * (57 - k)
                            (int) (Math.random() * threshold2));
                }
            }
            for(int j=i;j<15;j++){
                data1.getRelationMap().put(j+1,(int)(Math.random()*threshold1));
            }
            if(i<15)continue;
            for(int j=i;j<46;j++){
                data1.getRelationMap().put(j+1,(int)(Math.random()*threshold1));
            }
            if(i<46)continue;
            for(int j=i;j<57;j++){
                data1.getRelationMap().put(j+1,(int)(Math.random()*threshold1));
            }
        }

        path = "file/vos/network_sparse.txt";
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
        String path="file/vos/dataSrc";
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

        path="file/vos/network_sparse.txt";
        lines = FileOperation.read(path).split("\n");



        for(String line:lines) {
            line = line.trim();
            int id = StringProcess.str2Int(line.split("\t")[0]);
            int relationId = StringProcess.str2Int(line.split("\t")[1]);
            int weight = StringProcess.str2Int(line.split("\t")[2]);
            PatentData patentData  = patentDataList.get(id-1);
            if(patentData.getId()!=id){
                logger.error("line id:"+id+"\n"+patentData.toString());
                return ;
            }
            patentData.getRelationMap().put(relationId,weight);
        }

        path = "file/vos/map_notLabel.txt";
        lines = FileOperation.read(path).split("\n");


        String mapPath = "file/vos/map.txt";
        String networkPath = "file/vos/network.txt";

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
