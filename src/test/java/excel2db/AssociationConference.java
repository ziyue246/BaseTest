package excel2db;

import common.pojo.AssociationConferenceData;
import common.system.AppContext;
import common.system.ExcelOperation;
import common.system.StringProcess;
import common.system.Systemconfig;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ziyue on 2018/4/8.
 */
public class AssociationConference {

    private  static Logger logger = Logger.getLogger(AssociationConference.class);


    public  List<AssociationConferenceData> getExcelDatas(String path ) {


        File file = new File(path);
        List excelList = ExcelOperation.readExcel(file);

        List<AssociationConferenceData> dataList = new ArrayList<>();

        List titleList = (List)excelList.get(0);
        for (int i = 1; i < excelList.size(); i++) {
            List list = (List) excelList.get(i);
            List<AssociationConferenceData> dataList_tmp = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {


                String str_tmp = titleList.get(j).toString();
                if(str_tmp==null||str_tmp.length()==0)continue;
                str_tmp=str_tmp.trim();
                for(int k=0;k<10;k++) {
                    dataList_tmp.add(new AssociationConferenceData());
                }
                if(str_tmp.equals("单位名称")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setInstitutional_name(list.get(j).toString());
                    }
                }
                if(str_tmp.equals("联系人")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setContact_name(list.get(j).toString());
                    }
                }
                if(str_tmp.equals("职务")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setContact_position(list.get(j).toString());
                    }
                }
                if(str_tmp.equals("联系方式")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setContact_phone(list.get(j).toString());
                    }
                }
                if(str_tmp.equals("推荐专家")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setRecommended_experts(list.get(j).toString());
                    }
                }
                if(str_tmp.startsWith("会议名称")){//会议名称1
                    int index  = StringProcess.str2Int(str_tmp.replace("会议名称",""));
                    dataList_tmp.get(index-1).setConference_name(list.get(j).toString());

                }
                if(str_tmp.startsWith("会议时间")){
                    int index  = StringProcess.str2Int(str_tmp.replace("会议时间",""));
                    dataList_tmp.get(index-1).setHolding_time(list.get(j).toString());
                }
                if(str_tmp.startsWith("会议地点")){
                    int index  = StringProcess.str2Int(str_tmp.replace("会议地点",""));
                    dataList_tmp.get(index-1).setHolding_address(list.get(j).toString());
                }
                if(str_tmp.startsWith("所属领域")){
                    int index  = StringProcess.str2Int(str_tmp.replace("所属领域",""));
                    dataList_tmp.get(index-1).setField(list.get(j).toString());
                }
                if(str_tmp.startsWith("会议领域")){
                    int index  = StringProcess.str2Int(str_tmp.replace("会议领域",""));
                    dataList_tmp.get(index-1).setField(list.get(j).toString());
                }

                if(str_tmp.startsWith("主办机构")){
                    int index  = StringProcess.str2Int(str_tmp.replace("主办机构",""));
                    dataList_tmp.get(index-1).setInstitutional_name(list.get(j).toString());
                }
                if(str_tmp.startsWith("会议规模")){
                    int index  = StringProcess.str2Int(str_tmp.replace("会议规模",""));
                    dataList_tmp.get(index-1).setScale(list.get(j).toString());
                }
                if(str_tmp.startsWith("连续召开次数")){
                    int index  = StringProcess.str2Int(str_tmp.replace("连续召开次数",""));
                    dataList_tmp.get(index-1).setTimes(list.get(j).toString());
                }
                if(str_tmp.startsWith("连续召开届次")){
                    int index  = StringProcess.str2Int(str_tmp.replace("连续召开届次",""));
                    dataList_tmp.get(index-1).setTimes(list.get(j).toString());
                }

                if(str_tmp.startsWith("是否征集论文")){
                    int index  = StringProcess.str2Int(str_tmp.replace("是否征集论文",""));
                    dataList_tmp.get(index-1).setCollection_paper(list.get(j).toString());
                }
                if(str_tmp.startsWith("推荐理由")){
                    int index  = StringProcess.str2Int(str_tmp.replace("推荐理由",""));
                    dataList_tmp.get(index-1).setRecommended_reason(list.get(j).toString());
                }
                if(str_tmp.equals("GUID")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setGUID(list.get(j).toString());
                    }
                }
                if(str_tmp.startsWith("审核状态")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setApproval_status(list.get(j).toString());
                    }
                }
                if(str_tmp.startsWith("单位编号")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setCompany_number(list.get(j).toString());
                    }
                }
                if(str_tmp.startsWith("填报日期")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setSignUp_time(list.get(j).toString());
                    }
                }
                if(str_tmp.startsWith("模板编号")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setTemplate_number(list.get(j).toString());
                    }
                }
                if(str_tmp.startsWith("学会编号")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setCompany_number(list.get(j).toString());
                    }
                }
                if(str_tmp.startsWith("审核人")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setReviewer(list.get(j).toString());
                    }
                }
                if(str_tmp.startsWith("审核人编号")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setReviewer_number(list.get(j).toString());
                    }
                }
                if(str_tmp.startsWith("审核时间")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setReview_time(list.get(j).toString());
                    }
                }
                if(str_tmp.startsWith("学会单位")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setCompany_number(list.get(j).toString());
                    }
                }
                if(str_tmp.startsWith("会议主题")){
                    int index  = StringProcess.str2Int(str_tmp.replace("会议主题",""));
                    dataList_tmp.get(index-1).setConference_topic(list.get(j).toString());
                }
                if(str_tmp.startsWith("创建年份")){
                    int index  = StringProcess.str2Int(str_tmp.replace("创建年份",""));
                    dataList_tmp.get(index-1).setCreate_year(list.get(j).toString());
                }
                if(str_tmp.startsWith("检索情况")){
                    int index  = StringProcess.str2Int(str_tmp.replace("检索情况",""));
                    dataList_tmp.get(index-1).setSearch_situation(list.get(j).toString());
                }
                if(str_tmp.startsWith("会议截止日期")){
                    int index  = StringProcess.str2Int(str_tmp.replace("会议截止日期",""));
                    dataList_tmp.get(index-1).setClosing_date(list.get(j).toString());
                }
                if(str_tmp.startsWith("会议网址")){
                    int index  = StringProcess.str2Int(str_tmp.replace("会议网址",""));
                    dataList_tmp.get(index-1).setWeb_address(list.get(j).toString());
                }


            }
            int count=0;
            for(AssociationConferenceData data:dataList_tmp){
                if(data.getConference_name()!=null&&data.getConference_name().length()>3){
                    dataList.add(data);
                    ++count;
                }
            }
            //logger.info(i+" line valid datas size :"+count);

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
        String path  = "file/excel/international.xls";


        List<AssociationConferenceData> dataList = getExcelDatas(path);

        for(AssociationConferenceData data:dataList){
           Systemconfig.associationConferenceService.saveAssociationConferenceDate(
                   data,AssociationConferenceData.DBSELECT.INTERNATIONAL);
        }
        logger.info("save success 01");
        path  = "file/excel/national.xls";


        dataList = getExcelDatas(path);

        for(AssociationConferenceData data:dataList){
            Systemconfig.associationConferenceService.saveAssociationConferenceDate(
                    data,AssociationConferenceData.DBSELECT.NATIONAL);
        }
        logger.info("save success 02");
    }
}
