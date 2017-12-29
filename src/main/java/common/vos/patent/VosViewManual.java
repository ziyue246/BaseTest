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
public class VosViewManual {


    private static Logger logger = Logger.getLogger(VosViewManual.class);

    @Test
    public void getnetWork(){

        Map<String,Set<String>> map1 = new HashMap<>();
        Map<String,Set<String>> map2 = new HashMap<>();
        Map<String,Set<String>> map3 = new HashMap<>();
        readKeyPoint(map1,map2,map3);




        String path="file/vos/data/patent/paper/manual/dataSrc";
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

        //1-18     18
        //19-49    30
        //50-63    14

        int threshold1=40;
        int threshold2=35;
        double threshold3=0.4;

        double threshold4=0.3;
        int threshold5=30;

        double threshold6=0.8;
        double threshold7=0.3;
        for(int i=0;i<patentDataList.size();i++){
            PatentData data1=patentDataList.get(i);

            for(int k=i+1;k<patentDataList.size();k++) {
                PatentData data2=patentDataList.get(k);
                //{
                int mark=0;
                if(map1.keySet().contains(data1.getPatentCode())){                    mark++;                }
                if(map2.keySet().contains(data1.getPatentCode())){                    mark++;                }
                if(map3.keySet().contains(data1.getPatentCode())){                    mark++;                }
                if(map1.keySet().contains(data2.getPatentCode())){                    mark++;                }
                if(map2.keySet().contains(data2.getPatentCode())){                    mark++;                }
                if(map3.keySet().contains(data2.getPatentCode())){                    mark++;                }


                if(mark==1 ||Math.random() < threshold3){
                    if ((Math.random() < threshold3 &&k<18&&k>=49)||Math.random() < threshold3/1.8)
                    {
                        data1.getRelationMap().put(data2.getId(),//+ (int) (Math.random() * (57 - k)
                                (int) (threshold6 * threshold2));//Math.random()
                    }
                }
            }
            for(int j=i+1;j<18;j++){
                PatentData data2=patentDataList.get(j);
                if(map1.keySet().contains(data1.getPatentCode())&&!map1.keySet().contains(data2.getPatentCode())){
                    data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold4));
                    if(map1.get(data1.getPatentCode()).contains(data2.getPatentCode())){
                        data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold5));
                    }
                }else if(map1.keySet().contains(data2.getPatentCode())&&!map1.keySet().contains(data1.getPatentCode())){
                    data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold4));
                    if(map1.get(data2.getPatentCode()).contains(data1.getPatentCode())){
                        data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold5));
                    }
                }else {
                    data1.getRelationMap().put(data2.getId(), (int) (threshold7 * threshold1));
                }
            }
            if(i<18)continue;
            for(int j=i+1;j<49;j++){
                PatentData data2=patentDataList.get(j);
                if(map2.keySet().contains(data1.getPatentCode())&&!map2.keySet().contains(data2.getPatentCode())){
                    data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold4));
                    if(map2.get(data1.getPatentCode()).contains(data2.getPatentCode())){
                        data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold5));
                    }

                }else if(map2.keySet().contains(data2.getPatentCode())&&!map2.keySet().contains(data1.getPatentCode())){
                    data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold4));
                    if(map2.get(data2.getPatentCode()).contains(data1.getPatentCode())){
                        data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold5));
                    }
                }else {
                    data1.getRelationMap().put(data2.getId(), (int) (threshold7 * threshold1));
                }
            }
            if(i<49)continue;
            for(int j=i+1;j<patentDataList.size();j++){
                PatentData data2=patentDataList.get(j);
                if(map3.keySet().contains(data1.getPatentCode())&&!map3.keySet().contains(data2.getPatentCode())){
                    data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold4));
                    if(map3.get(data1.getPatentCode()).contains(data2.getPatentCode())){
                        data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold5));
                    }

                }else if(map3.keySet().contains(data2.getPatentCode())&&!map3.keySet().contains(data1.getPatentCode())){
                    data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold4));
                    if(map3.get(data2.getPatentCode()).contains(data1.getPatentCode())){
                        data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold5));
                    }
                }else {
                    data1.getRelationMap().put(data2.getId(), (int) (threshold7 * threshold1));
                }
            }
        }

        path = "file/vos/data/patent/paper/manual/network_sparse.txt";
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

    public void readKeyPoint(Map<String,Set<String>> map1,
                             Map<String,Set<String>> map2,
                             Map<String,Set<String>> map3){
        String path="file/vos/data/patent/paper/manual/keyPoint";
        String []lines = FileOperation.read(path).split("\n");
//        Map<String,Set<String>> map1 = new HashMap<>();
//        Map<String,Set<String>> map2 = new HashMap<>();
//        Map<String,Set<String>> map3 = new HashMap<>();

        Map<String,Set<String>> map = map1;

        int mark = 1; // 1 key ,2 value

        String key = "";
        for(String line:lines){
            line = line.trim();
            if(line.startsWith("key1")){
                map = map1;
            }else if(line.startsWith("key2")){
                map = map2;
            }else if(line.startsWith("key3")){
                map = map3;
            }else if(line.startsWith("##")){

                key = line.split("£¬")[1].trim();
                Set<String> set = new HashSet<>();
                map.put(key,set);
            }else{
                String value = line.split("£¬")[1].trim();
                map.get(key).add(value);
            }
        }
    }

    @Test
    public void getLabelMap(){


        String postfix="3";


        String path="file/vos/data/patent/paper/manual/dataSrc";
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

        path="file/vos/data/patent/paper/manual/network_sparse"+postfix+".txt";
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

        path = "file/vos/data/patent/paper/manual/map_notLabel"+postfix+".txt";
        lines = FileOperation.read(path).split("\n");


        String mapPath = "file/vos/data/patent/paper/manual/map"+postfix+".txt";
        String networkPath = "file/vos/data/patent/paper/manual/network"+postfix+".txt";

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

    @Test
    public void getLabelMapOneByOne(){
        String path="file/vos/data/patent/paper/manual/network_sparse.txt";
        String []lines = FileOperation.read(path).split("\n");


        List<PatentData> patentDataList = new ArrayList<>();

        String networkSparsePaht1 = "file/vos/data/patent/paper/manual/network_sparse1.txt";
        String networkSparsePaht2 = "file/vos/data/patent/paper/manual/network_sparse2.txt";
        String networkSparsePaht3 = "file/vos/data/patent/paper/manual/network_sparse3.txt";

        FileOperation.delete(networkSparsePaht1);
        FileOperation.delete(networkSparsePaht2);
        FileOperation.delete(networkSparsePaht3);

        //1-18
        //19-49
        //50-63
        for(String line:lines){

            line = line.trim();
            int id1 = StringProcess.str2Int(line.split("\t")[0]);
            int id2 = StringProcess.str2Int(line.split("\t")[1]);
            int weight = StringProcess.str2Int(line.split("\t")[2]);

            weight=weight/5;
            line = id1+"\t"+id2+"\t"+weight;
            if(id1>=1&&id1<=18&&id2>=1&&id2<=18){
                FileOperation.appendWrite(networkSparsePaht1,line+"\n");
            }
            if(id1>=19&&id1<=49&&id2>=19&&id2<=49){
                FileOperation.appendWrite(networkSparsePaht2,line+"\n");
            }
            if(id1>=50&&id1<=63&&id2>=50&&id2<=63){
                FileOperation.appendWrite(networkSparsePaht3,line+"\n");
            }

        }

    }





    @Test
    public void getnetWorkOneByOne(){

        Map<String,Set<String>> map1 = new HashMap<>();
        Map<String,Set<String>> map2 = new HashMap<>();
        Map<String,Set<String>> map3 = new HashMap<>();
        readKeyPoint(map1,map2,map3);




        String path="file/vos/data/patent/paper/manual/dataSrc";
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

        //1-18
        //19-49
        //50-63

        int threshold1=40;
        int threshold2=35;
        double threshold3=0.3;

        int threshold4=30;
        int threshold5=60;
        for(int i=0;i<patentDataList.size();i++){
            PatentData data1=patentDataList.get(i);

            for(int k=i;k<patentDataList.size();k++) {
                PatentData data2=patentDataList.get(k);
                if (Math.random() < threshold3) {
                    data1.getRelationMap().put(data2.getId(),//+ (int) (Math.random() * (57 - k)
                            (int) (Math.random() * threshold2));
                }
            }
            for(int j=i;j<18;j++){
                PatentData data2=patentDataList.get(j);
                if(map1.keySet().contains(data1.getPatentCode())){
                    data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold4));
                    if(map1.get(data1.getPatentCode()).contains(data2.getPatentCode())){
                        data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold5));
                    }
                }else {
                    data1.getRelationMap().put(data2.getId(), (int) (Math.random() * threshold1));
                }
            }
            if(i<18)continue;
            for(int j=i;j<49;j++){
                PatentData data2=patentDataList.get(j);
                if(map2.keySet().contains(data1.getPatentCode())){
                    data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold4));
                    if(map2.get(data1.getPatentCode()).contains(data2.getPatentCode())){
                        data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold5));
                    }

                }else {
                    data1.getRelationMap().put(data2.getId(), (int) (Math.random() * threshold1));
                }
            }
            if(i<49)continue;
            for(int j=i;j<63;j++){
                PatentData data2=patentDataList.get(j);
                if(map3.keySet().contains(data1.getPatentCode())){
                    data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold4));
                    if(map3.get(data1.getPatentCode()).contains(data2.getPatentCode())){
                        data1.getRelationMap().put(data2.getId(), (int) (threshold1+threshold5));
                    }

                }else {
                    data1.getRelationMap().put(data2.getId(), (int) (Math.random() * threshold1));
                }
            }
        }

        path = "file/vos/data/patent/paper/manual/network_sparse.txt";
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
}
