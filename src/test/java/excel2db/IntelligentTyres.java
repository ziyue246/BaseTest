package excel2db;

import common.pojo.AssociationConferenceAllCountData;
import common.pojo.AssociationConferenceData;
import common.pojo.IntelligentTyresData;
import common.system.AppContext;
import common.system.ExcelOperation;
import common.system.StringProcess;
import common.system.Systemconfig;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.*;

/**
 * Created by ziyue on 2018/4/8.
 */
public class IntelligentTyres {

    private  static Logger logger = Logger.getLogger(IntelligentTyres.class);


    public  List<IntelligentTyresData> getExcelDatas(String path) {


        File file = new File(path);
        Map<String,List> excelMap  = ExcelOperation.readExcel2Map(file);


        List<IntelligentTyresData> dataList = new ArrayList<>();

        Set<String > keySet = new HashSet<>();

        keySet.add("自动充气");
        keySet.add("TPMS");
        keySet.add("爆胎");
        keySet.add("智能轮胎");
        keySet.add("轮胎模型");
        keySet.add("里程可追溯");
        keySet.add("轮胎压力");
        keySet.add("轮胎传感器");
        keySet.add("轮胎状态估计");
        //keySet.add("疑似无效文章");
        keySet.add("轮胎温度");
        keySet.add("轮胎振动");
        keySet.add("轮胎摩擦");
        keySet.add("轮胎新材料");



        List<IntelligentTyresData> dataList_tmp = new ArrayList<>();
        for(Object key_obj : excelMap.keySet()) {


            logger.info("sheet name : "+key_obj.toString());
            if(!keySet.contains(key_obj.toString())){
                continue;
            }
            List excelList = excelMap.get(key_obj);

            if(excelList==null || excelList.size()==0)continue;


            List titleList = (List) excelList.get(0);
            if(!(titleList.contains("标题") || titleList.contains("title"))){
                continue;
            }
            for (int i = 1; i < excelList.size(); i++) {
                List list = (List) excelList.get(i);
                IntelligentTyresData data = new IntelligentTyresData();
                for (int j = 0; j < list.size(); j++) {

                    //序号	排除原因	新类别及原因	ID	标题	作者	地址	发表时间	期刊	关键词	摘要	引用数	被引数	搜索词
                    //序号	排除原因	新类别及原因	ID	标题	作者	地址	发表时间	期刊	关键词	摘要	引用数	被引数	搜索词
                    //序号	排除原因	新类别及原因	ID	标题	作者	地址	发表时间	期刊	关键词	摘要	引用数	被引数	搜索词
                    //序号	排除原因	新类别及原因	ID	垃圾词	标题	作者	地址	发表时间	期刊	关键词	摘要	引用数	被引数	搜索词
                    //排除原因	新类别及原因	id	title	下载	author	db	journal	summary	keywords	address	berefer_num
                    // down_num	category	en_author	fund	searchkey
                    //序号	排除原因	新类别及原因	ID	标题	下载	作者	类型	发表时间	期刊/会议	关键词	摘要	下载数	被引数	搜索词
                    //序号	排除原因	新类别及原因	ID	标题	下载	作者	类型	发表时间	期刊/会议	关键词	摘要	下载数	被引数	搜索词
                    //排除原因	新类别及原因	ID	标题	申请号	申请人	地址	申请时间	公开时间	发明人
                    //序号	排除原因	新类别及原因	ID	标题	作者	地址	发表时间	期刊	关键词	摘要	引用数	被引数	搜索词

                    String title_tmp = titleList.get(j).toString().replace(" ","").trim();
                    if(title_tmp.contains("序号")||title_tmp.contains("ID")||
                            title_tmp.equals("id")){
                        data.setExcel_id(list.get(j).toString());
                    }else if(title_tmp.equals("排除原因")){
                        data.setRemove_reason(list.get(j).toString());
                    }else if(title_tmp.equals("新类别及原因")){
                        data.setNew_category_reason(list.get(j).toString());
                    }else if(title_tmp.equals("标题")||title_tmp.equals("title")){
                        data.setTitle(list.get(j).toString());
                    }else if(title_tmp.equals("下载")){
                        data.setDownload(list.get(j).toString());
                    }else if(title_tmp.equals("申请号")){
                        data.setApplication_num(list.get(j).toString());
                    }else if(title_tmp.equals("申请人")){
                        data.setApplicant(list.get(j).toString());
                    }else if(title_tmp.equals("申请时间")){
                        data.setApplication_time(list.get(j).toString());
                    }else if(title_tmp.equals("公开时间")){
                        data.setPubtime(list.get(j).toString());
                    }else if(title_tmp.equals("发明人")){
                        data.setInventor(list.get(j).toString());
                    }else if(title_tmp.equals("db")||title_tmp.equals("类型")){
                        data.setCategory_db(list.get(j).toString());
                    }else if(title_tmp.equals("作者")||title_tmp.equals("author")){
                        data.setAuthor(list.get(j).toString());
                    }else if(title_tmp.equals("地址")||title_tmp.equals("address")){
                        data.setAddress(list.get(j).toString());
                    }else if(title_tmp.equals("发表时间")){
                        data.setPubtime(list.get(j).toString());
                    }else if(title_tmp.equals("期刊")||title_tmp.equals("期刊/会议")){
                        data.setJournal(list.get(j).toString());
                    }else if(title_tmp.equals("关键词")||title_tmp.equals("keywords")){
                        data.setKeywords(list.get(j).toString());
                    }else if(title_tmp.equals("摘要")||title_tmp.equals("summary")){
                        data.setSummary(list.get(j).toString());
                    }else if(title_tmp.equals("引用数")){
                        data.setRefer_num(list.get(j).toString());
                    }else if(title_tmp.equals("被引数")||title_tmp.equals("berefer_num")){
                        data.setCite_num(list.get(j).toString());
                    }else if(title_tmp.equals("搜索词")||title_tmp.equals("searchkey")){
                        data.setSearchkey(list.get(j).toString());
                    }else if(title_tmp.equals("category")){
                        data.setLib_category(list.get(j).toString());
                    }else if(title_tmp.equals("fund")){
                        data.setFund(list.get(j).toString());
                    }else if(title_tmp.equals("en_author")){
                        data.setEn_author(list.get(j).toString());
                    }

                }
                data.setCategory(key_obj.toString());
                if(data.getTitle()!=null&&data.getTitle().length()>3){
                    dataList.add(data);
                }
            }
        }
        logger.info("total valid data sizes :"+dataList.size());
        return dataList;
    }


    @Before
    public void start(){
        AppContext.initial();
    }


    @Test
    public void test(){

        final  String lang_type_ch = "ch";
        final  String lang_type_en = "en";

        //1
        String path  = "file/conference/excel/20180423/conference-en.xls";
        List<IntelligentTyresData> dataList = getExcelDatas(path);
        for(IntelligentTyresData data:dataList){
            data.setLanguage(lang_type_en);
            data.setDbselect(IntelligentTyresData.DBSELECT.PAPER);
            Systemconfig.intelligentTyresService.saveDate(data);
        }
        logger.info("conference-en save success");
        //2
        path  = "file/conference/excel/20180423/journal-en.xls";
        dataList = getExcelDatas(path);
        for(IntelligentTyresData data:dataList){
            data.setLanguage(lang_type_en);
            data.setDbselect(IntelligentTyresData.DBSELECT.PAPER);
            Systemconfig.intelligentTyresService.saveDate(data);
        }
        logger.info("journal-en save success");
        //3
        path  = "file/conference/excel/20180423/paper-ch.xls";
        dataList = getExcelDatas(path);
        for(IntelligentTyresData data:dataList){
            data.setLanguage(lang_type_ch);
            data.setDbselect(IntelligentTyresData.DBSELECT.PAPER);
            Systemconfig.intelligentTyresService.saveDate(data);
        }
        logger.info("paper-ch save success");
        //4
        path  = "file/conference/excel/20180423/patent-ch.xls";
        dataList = getExcelDatas(path);
        for(IntelligentTyresData data:dataList){
            data.setLanguage(lang_type_ch);
            data.setDbselect(IntelligentTyresData.DBSELECT.PATENT);
            Systemconfig.intelligentTyresService.saveDate(data);
        }
        logger.info("patent-en save success");
        //5
        path  = "file/conference/excel/20180423/patent-en.xls";
        dataList = getExcelDatas(path);
        for(IntelligentTyresData data:dataList){
            data.setLanguage(lang_type_en);
            data.setDbselect(IntelligentTyresData.DBSELECT.PATENT);
            Systemconfig.intelligentTyresService.saveDate(data);
        }
        logger.info("patent-en save success");
        logger.info("save success");
    }

    @Test
    public void test2(){

        final  String lang_type_ch = "ch";
        final  String lang_type_en = "en";

        //1
        String path  = "file/conference/excel/20180423/conference-en.xls";




        path  = "file/conference/excel/20180423/patent-en.xls";
        List<IntelligentTyresData> dataList = getExcelDatas(path);
        for(IntelligentTyresData data:dataList){
            data.setLanguage(lang_type_en);
            data.setDbselect(IntelligentTyresData.DBSELECT.PATENT);
            Systemconfig.intelligentTyresService.saveDate(data);
        }

        logger.info("save success");
    }


    @Test
    public void print(){
        String str = "中华       人 民    共 和国    hello Word";
        str = str.replaceAll("(\\w) +(\\w)","$1@$2");
        System.out.println(str);
        str = str.replaceAll(" ","").replaceAll("@"," ");
        System.out.println(str);
    }
}
