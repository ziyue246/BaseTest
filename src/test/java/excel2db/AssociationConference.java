package excel2db;

import common.pojo.AssociationConferenceAllCountData;
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


                String title_tmp = titleList.get(j).toString();
                String currStr_tmp = list.get(j).toString();
                if(title_tmp==null||title_tmp.length()==0)continue;
                if(currStr_tmp==null||currStr_tmp.length()==0)continue;

                title_tmp=title_tmp.trim();
                for(int k=0;k<10;k++) {
                    dataList_tmp.add(new AssociationConferenceData());
                }
                if(title_tmp.equals("单位名称")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setCompany_name(currStr_tmp);
                    }
                }
                if(title_tmp.startsWith("学会单位")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setInstitutional_name(currStr_tmp);
                    }
                }
                if(title_tmp.equals("联系人")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setContact_name(currStr_tmp);
                    }
                }
                if(title_tmp.equals("职务")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setContact_position(currStr_tmp);
                    }
                }
                if(title_tmp.equals("联系方式")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setContact_phone(currStr_tmp);
                    }
                }
                if(title_tmp.equals("推荐专家")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setRecommended_experts(currStr_tmp);
                    }
                }
                if(title_tmp.startsWith("会议名称")){//会议名称1
                    int index  = StringProcess.str2Int(title_tmp.replace("会议名称",""));
                    dataList_tmp.get(index-1).setConference_name(currStr_tmp);

                }
                if(title_tmp.startsWith("会议时间")){
                    int index  = StringProcess.str2Int(title_tmp.replace("会议时间",""));
                    dataList_tmp.get(index-1).setHolding_time(currStr_tmp);
                }
                if(title_tmp.startsWith("会议地点")){
                    int index  = StringProcess.str2Int(title_tmp.replace("会议地点",""));
                    dataList_tmp.get(index-1).setHolding_address(currStr_tmp);
                }
                if(title_tmp.startsWith("所属领域")){
                    int index  = StringProcess.str2Int(title_tmp.replace("所属领域",""));
                    dataList_tmp.get(index-1).setField(currStr_tmp);
                }
                if(title_tmp.startsWith("会议领域")){
                    int index  = StringProcess.str2Int(title_tmp.replace("会议领域",""));
                    dataList_tmp.get(index-1).setField(currStr_tmp);
                }

                if(title_tmp.startsWith("主办机构")){
                    int index  = StringProcess.str2Int(title_tmp.replace("主办机构",""));
                    dataList_tmp.get(index-1).setOrganizer(currStr_tmp);
                }
                if(title_tmp.startsWith("会议规模")){
                    int index  = StringProcess.str2Int(title_tmp.replace("会议规模",""));
                    dataList_tmp.get(index-1).setScale(currStr_tmp);
                }
                if(title_tmp.startsWith("连续召开次数")){
                    int index  = StringProcess.str2Int(title_tmp.replace("连续召开次数",""));
                    dataList_tmp.get(index-1).setTimes(currStr_tmp);
                }
                if(title_tmp.startsWith("连续召开届次")){
                    int index  = StringProcess.str2Int(title_tmp.replace("连续召开届次",""));
                    dataList_tmp.get(index-1).setTimes(currStr_tmp);
                }

                if(title_tmp.startsWith("是否征集论文")){
                    int index  = StringProcess.str2Int(title_tmp.replace("是否征集论文",""));
                    dataList_tmp.get(index-1).setCollection_paper(currStr_tmp);
                }
                if(title_tmp.startsWith("推荐理由")){
                    int index  = StringProcess.str2Int(title_tmp.replace("推荐理由",""));
                    dataList_tmp.get(index-1).setRecommended_reason(currStr_tmp);
                }
                if(title_tmp.equals("GUID")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setGUID(currStr_tmp);
                    }
                }
                if(title_tmp.startsWith("审核状态")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setApproval_status(currStr_tmp);
                    }
                }
                if(title_tmp.startsWith("单位编号")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setCompany_number(currStr_tmp);
                    }
                }
                if(title_tmp.startsWith("填报日期")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setSignUp_time(currStr_tmp);
                    }
                }
                if(title_tmp.startsWith("模板编号")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setTemplate_number(currStr_tmp);
                    }
                }
                if(title_tmp.startsWith("学会编号")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setAssociation_number(currStr_tmp);
                    }
                }
                if(title_tmp.startsWith("审核人")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setReviewer(currStr_tmp);
                    }
                }
                if(title_tmp.startsWith("审核人编号")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setReviewer_number(currStr_tmp);
                    }
                }
                if(title_tmp.startsWith("审核时间")){
                    for(int k=0;k<10;k++) {
                        dataList_tmp.get(k).setReview_time(currStr_tmp);
                    }
                }

                if(title_tmp.startsWith("会议主题")){
                    int index  = StringProcess.str2Int(title_tmp.replace("会议主题",""));
                    dataList_tmp.get(index-1).setConference_topic(currStr_tmp);
                }
                if(title_tmp.startsWith("创建年份")){
                    int index  = StringProcess.str2Int(title_tmp.replace("创建年份",""));
                    dataList_tmp.get(index-1).setCreate_year(currStr_tmp);
                }
                if(title_tmp.startsWith("检索情况")){
                    int index  = StringProcess.str2Int(title_tmp.replace("检索情况",""));
                    dataList_tmp.get(index-1).setSearch_situation(currStr_tmp);
                }
                if(title_tmp.startsWith("会议截止日期")){
                    int index  = StringProcess.str2Int(title_tmp.replace("会议截止日期",""));
                    dataList_tmp.get(index-1).setClosing_date(currStr_tmp);
                }
                if(title_tmp.startsWith("会议网址")){
                    int index  = StringProcess.str2Int(title_tmp.replace("会议网址",""));
                    dataList_tmp.get(index-1).setWeb_address(currStr_tmp);
                }


            }
            int count=0;
            for(AssociationConferenceData data:dataList_tmp){
                if(data.getConference_name()!=null&&data.getConference_name().length()>3
                        &&data.getApproval_status().equals("审核通过")){
                    dataList.add(data);
                    ++count;
                }
            }
            //logger.info(i+" line valid datas size :"+count);

        }
        logger.info("total valid data sizes :"+dataList.size());
        return dataList;
    }




    public  List<AssociationConferenceAllCountData> getExcelAllCountDatas(String path ) {


        File file = new File(path);
        List excelList = ExcelOperation.readExcel(file);

        List<AssociationConferenceAllCountData> dataList = new ArrayList<>();

        List titleList = (List)excelList.get(0);
        for (int i = 1; i < excelList.size(); i++) {
            List list = (List) excelList.get(i);


            AssociationConferenceAllCountData data = new AssociationConferenceAllCountData();


            for (int j = 0; j < list.size(); j++) {


                String title_tmp = titleList.get(j).toString();
                String currStr_tmp = list.get(j).toString();
                if(title_tmp==null||title_tmp.length()==0)continue;
                if(currStr_tmp==null||currStr_tmp.length()==0)continue;

                title_tmp=title_tmp.trim();

                if(title_tmp.equals("单位名称")) {
                    data.setCompany_name(currStr_tmp);
                }
                if(title_tmp.equals("联系人")) {
                    data.setContacts(currStr_tmp);
                }
                if(title_tmp.equals("职务")) {
                    data.setJob_title(currStr_tmp);
                }
                if(title_tmp.equals("联系方式")) {
                    data.setContact_information(currStr_tmp);
                }
                if(title_tmp.equals("举办次数（国内）")) {
                    data.setMeetings_times_domestic(currStr_tmp);
                }
                if(title_tmp.equals("举办次数（国际）")) {
                    data.setMeetings_times_international(currStr_tmp);
                }
                if(title_tmp.equals("举办次数（港澳台）")) {
                    data.setMeetings_times_held_hK_mc_tw(currStr_tmp);
                }
                if(title_tmp.equals("参加人数（国内）")) {
                    data.setParticipants_number_domestic(currStr_tmp);
                }
                if(title_tmp.equals("参加人数（国际）")) {
                    data.setParticipants_number_international(currStr_tmp);
                }
                if(title_tmp.equals("参加人数（港澳台）")) {
                    data.setParticipants_number_hK_mc_tw(currStr_tmp);
                }
                if(title_tmp.equals("交流论文会议数（国内）")) {
                    data.setExchange_papers_meeting_domestic(currStr_tmp);
                }
                if(title_tmp.equals("交流论文会议数（国际）")) {
                    data.setExchange_paper_conferences_international(currStr_tmp);
                }
                if(title_tmp.equals("交流论文会议数（港澳台）")) {
                    data.setExchange_paper_conferences_hK_mc_tw(currStr_tmp);
                }
                if(title_tmp.equals("举办次数（专业性）")) {
                    data.setNumber_of_times_professional(currStr_tmp);
                }
                if(title_tmp.equals("举办次数（综合性）")) {
                    data.setNumber_of_times_comprehensive(currStr_tmp);
                }
                if(title_tmp.equals("参加人数（专业性）")) {
                    data.setParticipants_number_professional(currStr_tmp);
                }
                if(title_tmp.equals("参加人数（综合性）")) {
                    data.setParticipants_number_comprehensive(currStr_tmp);
                }
                if(title_tmp.equals("交流论文会议数（专业性）")) {
                    data.setExchange_papers_conference_professional(currStr_tmp);
                }
                if(title_tmp.equals("交流论文会议数（综合性）")) {
                    data.setExchange_paper_conference_number_comprehensive(currStr_tmp);
                }
                if(title_tmp.equals("举办次数（大型）")) {
                    data.setMeetings_times_large(currStr_tmp);
                }
                if(title_tmp.equals("举办次数（中型）")) {
                    data.setMeetings_times_medium(currStr_tmp);
                }
                if(title_tmp.equals("举办次数（小型）")) {
                    data.setMeetings_times_small(currStr_tmp);
                }
                if(title_tmp.equals("参加人数（大型）")) {
                    data.setParticipants_number_large(currStr_tmp);
                }
                if(title_tmp.equals("参加人数（中型）")) {
                    data.setParticipants_number_medium(currStr_tmp);
                }
                if(title_tmp.equals("参加人数（小型）")) {
                    data.setParticipants_number_small(currStr_tmp);
                }
                if(title_tmp.equals("交流论文会议数（大型）")) {
                    data.setExchange_papers_conference_large(currStr_tmp);
                }
                if(title_tmp.equals("交流论文会议数（中型）")) {
                    data.setExchange_papers_meeting_medium(currStr_tmp);
                }
                if(title_tmp.equals("交流论文会议数（小型）")) {
                    data.setExchange_papers_meeting_small(currStr_tmp);
                }
                if(title_tmp.equals("举办次数（一次性）")) {
                    data.setMeetings_times_one_time(currStr_tmp);
                }
                if(title_tmp.equals("举办次数（年度性）")) {
                    data.setNumber_of_times_held_annual(currStr_tmp);
                }
                if(title_tmp.equals("举办次数（系列性）")) {
                    data.setNumber_of_events_series(currStr_tmp);
                }
                if(title_tmp.equals("参加人数（一次性）")) {
                    data.setMeetings_times_one_time(currStr_tmp);
                }
                if(title_tmp.equals("参加人数（年度性）")) {
                    data.setParticipants_number_annual(currStr_tmp);
                }
                if(title_tmp.equals("参加人数（系列性）")) {
                    data.setParticipants_number_series(currStr_tmp);
                }
                if(title_tmp.equals("交流论文会议数（一次性）")) {
                    data.setExchange_paper_conferences_one_time(currStr_tmp);
                }
                if(title_tmp.equals("交流论文会议数（年度性）")) {
                    data.setExchange_papers_meeting_annual(currStr_tmp);
                }
                if(title_tmp.equals("交流论文会议数（系列性）")) {
                    data.setExchange_papers_meeting_series(currStr_tmp);
                }
                if(title_tmp.equals("GUID")) {
                    data.setGuid(currStr_tmp);
                }
                if(title_tmp.equals("审核状态")) {
                    data.setApproval_status(currStr_tmp);
                }
                if(title_tmp.equals("单位编号")) {
                    data.setCompany_number(currStr_tmp);
                }
                if(title_tmp.equals("填报日期")) {
                    data.setDate_of_completion(currStr_tmp);
                }
                if(title_tmp.equals("模板编号")) {
                    data.setTemplate_number(currStr_tmp);
                }
                if(title_tmp.equals("学会编号")) {
                    data.setInstitutional_number(currStr_tmp);
                }
                if(title_tmp.equals("审核人")) {
                    data.setReviewer(currStr_tmp);
                }
                if(title_tmp.equals("审核人编号")) {
                    data.setReviewer_number(currStr_tmp);
                }
                if(title_tmp.equals("审核时间")) {
                    data.setReview_time(currStr_tmp);
                }
                if(title_tmp.equals("学会单位")) {
                    data.setInstitutional_name(currStr_tmp);
                }

            }
            if(data.getCompany_name()!=null&&data.getCompany_name().length()>3
                    &&data.getApproval_status().equals("审核通过"))
                dataList.add(data);


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
        String path  = "file/conference/excel/20180420/international1.xls";
        List<AssociationConferenceData> dataList = getExcelDatas(path);
        for(AssociationConferenceData data:dataList){
           Systemconfig.associationConferenceService.saveAssociationConferenceDate(
                   data,AssociationConferenceData.DBSELECT.INTERNATIONAL);
        }
        logger.info("save success 01");
        path  = "file/conference/excel/20180420/national1.xls";
        dataList = getExcelDatas(path);
        for(AssociationConferenceData data:dataList){
            Systemconfig.associationConferenceService.saveAssociationConferenceDate(
                    data,AssociationConferenceData.DBSELECT.NATIONAL);
        }
        logger.info("save success 02");
    }


    @Test
    public void test01(){
        String path  = "file/conference/excel/20180420/allCount1.xls";


        List<AssociationConferenceAllCountData> dataList = getExcelAllCountDatas(path);

        for(AssociationConferenceAllCountData data:dataList){
            Systemconfig.associationConferenceService.saveAssociationConferenceAllCountDate(
                    data);
        }
        logger.info("save success ");
    }
}
