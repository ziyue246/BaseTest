package wos_export;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.pojo.WosExportData;
import common.system.*;
import common.test.FunClass;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by ziyue on 2018/5/25.
 */
public class WosExportMain {


    private static Logger logger = Logger.getLogger(WosExport.class);


    @Before
    public void start(){
        AppContext.initial();
    }


    public void testMain_downData() throws  Exception{


        SimpleHttp http = new SimpleHttp();
        HtmlInfo html = new HtmlInfo();
        String cookie="__guid=115478998.2335628301381164000.1507705557442.3503; SID=\"8Ansu1eznYSjtx42P2Q\"; CUSTOMER=\"CAS Institute of Automation\"; E_GROUP_NAME=\"CAS Institute of Automation\"; ak_bmsc=DACB0C7EF932964CFCD2B62E86B2474F172B30649A6000002E5A075BFEE34150~pl2A7OGkJXOwpxKraFal28scmPi29BYPq8Xp27c3A6zrvQ6BEwuWBEOYZqKCfprTwQDbUdKNYCwAnbKKSlM/yDlw/vcSabwlrt7VYhn4DhKd3npakiPJ5WMdgn9+NHj7/YNgJy8DWRYY9AK3spzbn6GLNwQOWNEsi8cBgRVLCvqUnarE8KqvwVCFoGhm8JNPeL7x3uQPcrnxgo812ZmCt4hKBhhpGcvafZhjPI72/Dj1HeCp+qWlH2w89VF/GuLSvX; JSESSIONID=10FB79A44AD8EE6A6D62C5710D4EC0DE; bm_sv=C39DEB4A2333512275E582A037904E34~gclkAzTmml5YAS58ZyCHEhK7A0WQNxi0WG4+8r5h5B7U1q8WJecGpgdCmZYdTLQvZar+BEeDmf93btRFzZCsOGfzIGpaqNQ6ajiDMEjyEEFQte8S7d6+E0tjoIwJ40sFTdkN99J4k8+x0icRM8skdd5k34L0cBTC2Hrq3COZA6o=";

        cookie="__guid=115478998.2335628301381164000.1507705557442.3503; SID=\"8Ansu1eznYSjtx42P2Q\"; CUSTOMER=\"CAS Institute of Automation\"; E_GROUP_NAME=\"CAS Institute of Automation\"; ak_bmsc=545B05A78D148DBCA3C933F48038794417D2D725AA6C0000E4F9075B81091025~plkoe98talA3JBDSZSGOqP4z+4Uah58sIh11A8sBP/PVkQFbeikHDgIhl5c4JAowHUykBA+72jynk6f+msyTn3Z4zZFinRuBrIJ6oudNinc+nv+mt4EUQanMMo+xZ47tYH1wzQmj5Zg7LCegSe5BrJNvDCJBklR8SJTnvuouAvZ8DRWnRaZ9CE0g81Mw2JKd5OKbBtIyAl7Z9LlBcg6J74rKU/gqt8MwE2mEbIekXZGnpt4dzvCQGdy055+lVRtnco; JSESSIONID=5A91FA70E06E375B254574C9829DE825; bm_sv=D48C90B4AE6A0E1273CD98A3C4D36F23~+5A00+9Tzt6VL4Al0n/L4/nZOKq8ks6eLmX0OC5avdoNqoKMb6yPMpQkuQpZKRoEDNQiuW9mWfoyXHWz+hj4Fs5RVwd1v0Saa5qEkeaPoYwXbo5MDtSoIRRms2dB4grdx7iyC49rcSsgBAtvvXe6xcVb5idkqx77fk2r2VAU85Y=";
        html.setCookie(cookie);
        html.setEncode("utf-8");
        //Host:
        html.setHost("apps.webofknowledge.com");
        String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
        html.setUa(ua);



        int Max_Record=6106;


        for(int i=35501;i<=Max_Record;i+=500) {
            int endRecord = i+500-1;
            if(endRecord>Max_Record){
                endRecord=Max_Record;
            }
            ArrayList<String> params = new ArrayList<>();
            System.out.println("page:"+i+"-"+endRecord);
            params.add("selectedIds##");
            params.add("displayCitedRefs##true");
            params.add("displayTimesCited##true");
            params.add("displayUsageInfo##true");
            params.add("viewType##summary");
            params.add("product##WOS");

            params.add("rurl##http%3A%2F%2Fapps.webofknowledge.com%2Fsummary.do%3Fproduct%3DWOS%26search_mode%3DAdvancedSearch%26colName%3DWOS%26page%3D1%26qid%3D14%26SID%3D8Ansu1eznYSjtx42P2Q%26parentProduct%3DWOS");

            params.add("mark_id##WOS");
            params.add("colName##WOS");
            params.add("search_mode##AdvancedSearch");
            params.add("locale##zh_CN");
            params.add("view_name##WOS-summary");
            params.add("sortBy##PY.D;LD.D;SO.A;VL.D;PG.A;AU.A");
            params.add("mode##OpenOutputService");
            params.add("qid##13");
            params.add("SID##8Ansu1eznYSjtx42P2Q");
            params.add("format##saveToFile");
            params.add("filters##HIGHLY_CITED HOT_PAPER OPEN_ACCESS PMID USAGEIND AUTHORSIDENTIFIERS ACCESSION_NUM FUNDING SUBJECT_CATEGORY JCR_CATEGORY LANG IDS PAGEC SABBR CITREFC ISSN PUBINFO KEYWORDS CITTIMES ADDRS CONFERENCE_SPONSORS DOCTYPE ABSTRACT CONFERENCE_INFO SOURCE TITLE AUTHORS  ");
            params.add("mark_from##" + i);
            params.add("mark_to##" + (endRecord));
            params.add("queryNatural##IS=(0360-0300 or 1529-3785 or 0734-2071 or 0362-5915 or 0730-0301 or 1046-8188 or 1551-6857 or 1049-331X or 1049-250X or 1523-9829 or 0946-2171 or 0004-3702 or 0005-1098 or 1387-2532 or 0340-1200 or 0142-9612 or 1617-7959 or 1387-2176 or 0169-7439 or 0001-0782 or 0891-2017 or 1384-5810 or 1364-8152 or 1063-6560 or 0957-4174 or 1615-3375 or 0737-0024 or 0018-8646 or 1545-5963 or 1063-6692 or 0163-6804 or 1066-033X or 0741-3106 or 1089-7801 or 0018-9197 or 0733-8716 or 1077-260X or 0018-9200 or 0272-1732 or 1070-986X or 0890-8044 or 1536-1268 or 1041-1135 or 1053-5888 or 0018-9286 or 1051-8215 or 0018-9383 or 1089-778X or 1063-6706 or 0196-2892 or 1057-7149 or 0278-0046 or 0018-9448 or 1524-9050 or 1041-4347 or 0278-0062 or 0018-9480 or 1536-1233 or 1520-9210 or 1045-9227 or 0162-8828 or 0885-8993 or 1552-3098 or 0098-5589 or 1083-4419 or 1077-2626 or 1536-1284 or 0378-7206 or 0020-0255 or 0920-5691 or 0925-5273 or 0278-3649 or 0004-5411 or 1067-5027 or 0021-9290 or 1532-0464 or 1549-3296 or 1083-3668 or 1549-9596 or 0920-654X or 0021-9991 or 0933-2790 or 1063-8016 or 0733-8724 or 1532-4435 or 1057-7157 or 1093-3263 or 1741-2560 or 0272-6963 or 0022-4065 or 1742-5689 or 0909-0495 or 1570-8268 or 1863-8880 or 0885-6125 or 0025-1909 or 0340-6253 or 0025-5610 or 1361-8415 or 0276-7783 or 1748-0132 or 1749-4885 or 0028-0836 or 0899-7667 or 1539-2791 or 1094-4087 or 0146-9592 or 0018-9219 or 0027-8424 or 0031-3203 or 0031-9155 or 1050-2947 or 1059-1478 or 1559-8985 or 0079-6727 or 1611-020X or 1533-7146 or 1746-0751 or 0034-4257 or 0036-8075 or 0167-6911 or 0965-8564 or 0191-2615 or 0041-1655 or 1066-8888)");
            params.add("count_new_items_marked##0");
            params.add("use_two_ets##false");
            params.add("IncitesEntitled##no");
            params.add("value(record_select_type)##range");
            params.add("markFrom##" + i);
            params.add("markTo##" + (endRecord));
            params.add("fields_selection##HIGHLY_CITED HOT_PAPER OPEN_ACCESS PMID USAGEIND AUTHORSIDENTIFIERS ACCESSION_NUM FUNDING SUBJECT_CATEGORY JCR_CATEGORY LANG IDS PAGEC SABBR CITREFC ISSN PUBINFO KEYWORDS CITTIMES ADDRS CONFERENCE_SPONSORS DOCTYPE ABSTRACT CONFERENCE_INFO SOURCE TITLE AUTHORS  ");
            params.add("save_options##tabWinUTF8");

            html.setOrignUrl("http://apps.webofknowledge.com/OutboundService.do?action=go&&");

            http.simplePost(html, params);

            if(html.getRealUrl()!=null){

                String url_tmp = html.getRealUrl();
                html.setRealUrl(null);
                System.out.println("postLocalUrl_1:"+url_tmp);
                //url_tmp= URLEncoder.encode(url_tmp,"utf-8");
                url_tmp = url_tmp.replace(" ","+");
                System.out.println("postLocalUrl_2:"+url_tmp);
                html.setOrignUrl(url_tmp);

                http.urlConnectionGet(html);
                //extractDatas(html.getContent());
            }else{
                System.out.println("html.getRealUrl()==null");
                break;
            }
            SystemCommon.sleepsSecond(30);
        }

    }


    @Test
    //public void extractDatas() {
    public void extractDatas() {


        //String content;
        String  filePath = "file/wos_export/wos_export_6w-2018-5-25.txt";

        String content = FileOperation.read(filePath);
        logger.info("read data content");
        String []lines=content.split("\n");


        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<WosExportData> wosExports = new ArrayList<>();

        for(int i=0;i<lines.length;++i){

            String line = lines[i];
            line  = line.trim();
            if(line.length()<5||
                    (line.contains("PT")&&line.contains("AU")
                            &&line.contains("BA")&&line.contains("BE"))){
                continue;
            }

            String []words = line.split("\t");
            if(i==0){
                for(int j=0;j<words.length;j++){
                    String word = words[j].trim();
                    titleList.add(word);
                }
            }else{

                WosExportData data = new WosExportData();
                wosExports.add(data);
                for(int j=0;j<words.length;j++){
                    String title = titleList.get(j);
                    String word = words[j].trim();
                    if(!title.equals(word)) {
                        try {

                            Method method = data.getClass().getMethod("set" + title, String.class);
                            method.invoke(data, word);
                        }catch (Exception e){
                            logger.error(e.getMessage());
                        }


                    }
                }
            }
        }
        logger.info("data size:"+wosExports.size());


        ArrayList<WosExportData> wosExports_tmp = new ArrayList<>();
        for(WosExportData WosExportData :wosExports){
            wosExports_tmp.add(WosExportData);
            if(wosExports_tmp.size()>1000){
                Systemconfig.wosExportService.saveDates(wosExports_tmp);
                wosExports_tmp.clear();
            }
            //Systemconfig.wosExportService.saveDate(WosExportData);
        }
        Systemconfig.wosExportService.saveDates(wosExports_tmp);
        logger.info("write db success");
    }


    @After
    public void end(){

        AppContext.close();


    }
    @Test
    public void testMain_dany() {








    }




}

