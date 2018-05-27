package wos_export;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.system.FileOperation;
import common.system.StringProcess;
import common.system.SystemCommon;
import org.apache.log4j.Logger;
import org.json.HTTP;
import org.junit.Test;

import java.net.URLEncoder;
import java.util.*;

/**
 * Created by ziyue on 2018/5/25.
 */
public class WosExport {


    private static Logger logger = Logger.getLogger(WosExport.class);

    //@Test
    public void testMain1(){
        System.out.println("page:");

        String file = "file/wos_export/wos_export_6k-2018-5-25.txt";
        //file="D:\\Users\\Administrator\\ideaworkspace\\other\\BaseTest\\file\\wos_export\\wos_export_6k-2018-5-25.txt";


        FileOperation.write("123123123"+"\n",file,true);



        HtmlInfo html = new HtmlInfo();
        String url = "http://ets.webofknowledge.com/ETS/ets.do?mark_from=1&product=UA&colName=WOS&displayUsageInfo=true&parentQid=14&rurl=http%253A%252F%252Fapps.webofknowledge.com%252Fsummary.do%253Fproduct%253DWOS%2526search_mode%253DAdvancedSearch%2526colName%253DWOS%2526page%253D1%2526qid%253D14%2526SID%253D8Ansu1eznYSjtx42P2Q%2526parentProduct%253DWOS&mark_to=500&filters=HIGHLY_CITED+HOT_PAPER+OPEN_ACCESS+PMID+USAGEIND+AUTHORSIDENTIFIERS+ACCESSION_NUM+FUNDING+SUBJECT_CATEGORY+JCR_CATEGORY+LANG+IDS+PAGEC+SABBR+CITREFC+ISSN+PUBINFO+KEYWORDS+CITTIMES+ADDRS+CONFERENCE_SPONSORS+DOCTYPE+ABSTRACT+CONFERENCE_INFO+SOURCE+TITLE+AUTHORS++&qid=35&SID=8Ansu1eznYSjtx42P2Q&totalMarked=500&action=saveToFile&sortBy=PY.D;LD.D;SO.A;VL.D;PG.A;AU.A&displayTimesCited=true&displayCitedRefs=true&fileOpt=tabWinUTF8&UserIDForSaveToRID=null";
        html.setOrignUrl(url);
        html.setEncode("utf-8");
        html.setHost("apps.webofknowledge.com");
        String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
        html.setUa(ua);
        SimpleHttp http = new SimpleHttp();

        http.urlConnectionGet(html);

        FileOperation.write(html.getContent(),file,true);


    }



    public void testMain_downData() throws  Exception{


        SimpleHttp http = new SimpleHttp();

        HtmlInfo html = new HtmlInfo();
        String cookie="__guid=115478998.2335628301381164000.1507705557442.3503; SID=\"8Ansu1eznYSjtx42P2Q\"; CUSTOMER=\"CAS Institute of Automation\"; E_GROUP_NAME=\"CAS Institute of Automation\"; ak_bmsc=DACB0C7EF932964CFCD2B62E86B2474F172B30649A6000002E5A075BFEE34150~pl2A7OGkJXOwpxKraFal28scmPi29BYPq8Xp27c3A6zrvQ6BEwuWBEOYZqKCfprTwQDbUdKNYCwAnbKKSlM/yDlw/vcSabwlrt7VYhn4DhKd3npakiPJ5WMdgn9+NHj7/YNgJy8DWRYY9AK3spzbn6GLNwQOWNEsi8cBgRVLCvqUnarE8KqvwVCFoGhm8JNPeL7x3uQPcrnxgo812ZmCt4hKBhhpGcvafZhjPI72/Dj1HeCp+qWlH2w89VF/GuLSvX; JSESSIONID=10FB79A44AD8EE6A6D62C5710D4EC0DE; bm_sv=C39DEB4A2333512275E582A037904E34~gclkAzTmml5YAS58ZyCHEhK7A0WQNxi0WG4+8r5h5B7U1q8WJecGpgdCmZYdTLQvZar+BEeDmf93btRFzZCsOGfzIGpaqNQ6ajiDMEjyEEFQte8S7d6+E0tjoIwJ40sFTdkN99J4k8+x0icRM8skdd5k34L0cBTC2Hrq3COZA6o=";
        cookie="__guid=115478998.2335628301381164000.1507705557442.3503; SID=\"8Ansu1eznYSjtx42P2Q\"; CUSTOMER=\"CAS Institute of Automation\"; E_GROUP_NAME=\"CAS Institute of Automation\"; ak_bmsc=428FE2B22FB3B0C725D3971F8F8090B2170F011D871D0000CC76075B003FEF39~plFnGwXwCrjGk1TAF995+k4b6pRRhAaxOnmBrFRuDLFu9aewskB8MEhySgj6z/+kfG0cQQ4XqjddVqA3yo0+4rbp6ecIKp6OcS64uGYRnFctJEg/Yy3hN90iePCB28yKnIan8LJbvMuZrh8UrQjySSgLWxbFSQ0Zq4n4xTPva9ozejYj8FJuvgdMneuCq98HOtRU1FiPCkvYs34kwtsRfkJR1aIJZNVdYpnFdYlrqECsot2ORzmlMAZcVAfsSTmnde; JSESSIONID=AF9696E496A0ACB0258339343876EDF5; bm_sv=502A64E42D05F037C3FB455C02FCD01F~fJdavK34I0dEsFNofJZliMkGBz56xdqCw+9m/35SFJHOvRP7h7Svax7sJrG1luXvMcEJ4pq794bRKvYe5DxDeWbE1S50PFuECpg9I2W5GMTP9FxO3krQaPulG0MBgufpy0D3nqJWKLnuIgBt9BLZ/jLF3+ohopXfPOeYb0UvoeM=";
        cookie="__guid=115478998.2335628301381164000.1507705557442.3503; SID=\"8Ansu1eznYSjtx42P2Q\"; CUSTOMER=\"CAS Institute of Automation\"; E_GROUP_NAME=\"CAS Institute of Automation\"; ak_bmsc=6736EF70FDC3E912A79D4AAB41CDC275600737349E13000088A8075B3AE36C73~plg1VVz+HkLhevg5zKnIDuIXwi4CyA8oJelVDKw7B7DvAHAEYj3Lr7hAaHRCwZSaZtnzGa+bv01XZK5oD8m/X0PE+ztudhy/573hb21PGY2AQ8E9vKarEHQrKbP6zO0T0CxL3/4U/fBVeDZ5TvTVfR9hL/WgSE8bwawaRnr1pJrCTHn4wMilHUA/oZkkpiVcsjyKJpc4T0lkys0UWq2NCv8qNcAmDTqGELX87GJDLoEevgQk0XVXyxifXVETrXTdoh; JSESSIONID=8D9B1FDDF11799C6D088475086E38016; bm_sv=613D8B7D686CF97D9B69D465267C0AC0~wWVo2MgmP7kt495mdUzdf5qtzbQGY3EMb89wka041M99KioxR78x3WRuvTx1Rmu7V0ZxfmW5Bn68RzIgBNmb68qRS8VlrlGeYYwWyKhn2WoyWvrDLOdrdx72RTYIClImohAt4ksl7nn6VxImyw8vlf61L9Ow+Ltpta05f2XOpbQ=";
        cookie="__guid=115478998.2335628301381164000.1507705557442.3503; SID=\"8Ansu1eznYSjtx42P2Q\"; CUSTOMER=\"CAS Institute of Automation\"; E_GROUP_NAME=\"CAS Institute of Automation\"; ak_bmsc=6736EF70FDC3E912A79D4AAB41CDC275600737349E13000088A8075B3AE36C73~plg1VVz+HkLhevg5zKnIDuIXwi4CyA8oJelVDKw7B7DvAHAEYj3Lr7hAaHRCwZSaZtnzGa+bv01XZK5oD8m/X0PE+ztudhy/573hb21PGY2AQ8E9vKarEHQrKbP6zO0T0CxL3/4U/fBVeDZ5TvTVfR9hL/WgSE8bwawaRnr1pJrCTHn4wMilHUA/oZkkpiVcsjyKJpc4T0lkys0UWq2NCv8qNcAmDTqGELX87GJDLoEevgQk0XVXyxifXVETrXTdoh; JSESSIONID=3BF54EF7FA8006D30D8CAF3E88391208; bm_sv=613D8B7D686CF97D9B69D465267C0AC0~wWVo2MgmP7kt495mdUzdf5qtzbQGY3EMb89wka041M99KioxR78x3WRuvTx1Rmu7V0ZxfmW5Bn68RzIgBNmb68qRS8VlrlGeYYwWyKhn2WoyWvrDLOdrdx72RTYIClImZnu9ghNOQ5vTGWISQ09aid2X6yTZP8uoFBgyaJedn/s=";

        cookie="__guid=115478998.2335628301381164000.1507705557442.3503; SID=\"8Ansu1eznYSjtx42P2Q\"; CUSTOMER=\"CAS Institute of Automation\"; E_GROUP_NAME=\"CAS Institute of Automation\"; JSESSIONID=3CDC3E38B2097BEE587BDC336CC75989; ak_bmsc=16AA8FB37A5198963ECC6B71A80605E217C663DDB40500007EC6075B060E2D73~plpipxHo4hQiXvZNZ1CdlWnoJKFxTyw09vSD53j3OBh9xK4pr8VcA3F5v3GaZ+yr6y87RO9F6bLSnfCk5Ht73BfjXEkGQboly1HUKT0rCIHD9d4HAEq6w1THyqp6CBsx9M9G3Xs88ZNf0DmS8FniUIMor371Bi9UsNL6mXgdKb6sq2GQqIfFAgeUlMzgVz1e0ExTtC4SWu0wTV5nq/aYq0Rl3Ql7+EsVNy5vsKetWkGZNA4ct+v5eyXke+xSTLik35";

        cookie="__guid=115478998.2335628301381164000.1507705557442.3503; SID=\"8Ansu1eznYSjtx42P2Q\"; CUSTOMER=\"CAS Institute of Automation\"; E_GROUP_NAME=\"CAS Institute of Automation\"; ak_bmsc=16AA8FB37A5198963ECC6B71A80605E217C663DDB40500007EC6075B060E2D73~plpipxHo4hQiXvZNZ1CdlWnoJKFxTyw09vSD53j3OBh9xK4pr8VcA3F5v3GaZ+yr6y87RO9F6bLSnfCk5Ht73BfjXEkGQboly1HUKT0rCIHD9d4HAEq6w1THyqp6CBsx9M9G3Xs88ZNf0DmS8FniUIMor371Bi9UsNL6mXgdKb6sq2GQqIfFAgeUlMzgVz1e0ExTtC4SWu0wTV5nq/aYq0Rl3Ql7+EsVNy5vsKetWkGZNA4ct+v5eyXke+xSTLik35; JSESSIONID=015A80573F15B3BB8FA79CD35BF4BD43; bm_sv=584D5C4A68BFBE064BFF1F853FACEE74~CnYgg/CDP/5BmCReO6cWLbSbOKhYhRP7BmW+Q6BHy15dXl2XUs/FiWp0r2BFOyDeq4uuhofagN4eG817YMIJFSp1M2aC4gRQbv1aTFwqkryVfBZokTUCHpqgjp+zayShHoxh2mnb7LBrnDvRbv/2vow5E+F5c560Za8iuJifDmU=";

        cookie="__guid=115478998.2335628301381164000.1507705557442.3503; SID=\"8Ansu1eznYSjtx42P2Q\"; CUSTOMER=\"CAS Institute of Automation\"; E_GROUP_NAME=\"CAS Institute of Automation\"; ak_bmsc=545B05A78D148DBCA3C933F48038794417D2D725AA6C0000E4F9075B81091025~plkoe98talA3JBDSZSGOqP4z+4Uah58sIh11A8sBP/PVkQFbeikHDgIhl5c4JAowHUykBA+72jynk6f+msyTn3Z4zZFinRuBrIJ6oudNinc+nv+mt4EUQanMMo+xZ47tYH1wzQmj5Zg7LCegSe5BrJNvDCJBklR8SJTnvuouAvZ8DRWnRaZ9CE0g81Mw2JKd5OKbBtIyAl7Z9LlBcg6J74rKU/gqt8MwE2mEbIekXZGnpt4dzvCQGdy055+lVRtnco; JSESSIONID=5A91FA70E06E375B254574C9829DE825; bm_sv=D48C90B4AE6A0E1273CD98A3C4D36F23~+5A00+9Tzt6VL4Al0n/L4/nZOKq8ks6eLmX0OC5avdoNqoKMb6yPMpQkuQpZKRoEDNQiuW9mWfoyXHWz+hj4Fs5RVwd1v0Saa5qEkeaPoYwXbo5MDtSoIRRms2dB4grdx7iyC49rcSsgBAtvvXe6xcVb5idkqx77fk2r2VAU85Y=";
        html.setCookie(cookie);
        html.setEncode("utf-8");
        //Host:
        html.setHost("apps.webofknowledge.com");
        String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
        html.setUa(ua);



        String DATA_TYPE="6k";
        DATA_TYPE = "6w";

        int Max_Record=6106;

        if(DATA_TYPE=="6w"){
            Max_Record = 65936;
            System.out.println(Max_Record);
        }


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
            if(DATA_TYPE=="6k") {
                params.add("rurl##http%3A%2F%2Fapps.webofknowledge.com%2Fsummary.do%3Fproduct%3DWOS%26search_mode%3DAdvancedSearch%26colName%3DWOS%26page%3D1%26qid%3D14%26SID%3D8Ansu1eznYSjtx42P2Q%26parentProduct%3DWOS");
            }else if(DATA_TYPE=="6w"){
                System.out.println("params:"+Max_Record);
                params.add("rurl:http%3A%2F%2Fapps.webofknowledge.com%2Fsummary.do%3Fproduct%3DWOS%26search_mode%3DAdvancedSearch%26doc%3D1%26qid%3D13%26SID%3D8Ansu1eznYSjtx42P2Q");
            }
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
                String fileName = "file/wos_export/wos_export_"+DATA_TYPE+"-2018-5-25.txt";
                FileOperation.write(html.getContent()+"\n",fileName,true);


            }else{
                System.out.println("html.getRealUrl()==null");
                break;
            }

            SystemCommon.sleepsSecond(15);


        }








    }


    @Test
    public void testMain_processData() throws  Exception{



        String DATA_TYPE="6k";
        DATA_TYPE = "6w";
        String  filePath = "file/wos_export/wos_export_"+DATA_TYPE+"-2018-5-25.txt";

        String content = FileOperation.read(filePath);
         logger.info("read data content");
         String []lines=content.split("\n");


         ArrayList<String> titleList = new ArrayList<>();

         ArrayList<Map<String,String>> datas = new ArrayList<>();
         for(int i=0;i<lines.length;++i){

             String line = lines[i];
             line  = line.trim();
             if(line.length()<5||
                     (line.startsWith("PT")&&line.startsWith("AU")
                             &&line.startsWith("BA")&&line.startsWith("BE"))){
                 continue;
             }
             String []words = line.split("\t");

             if(i==0){
                 for(int j=0;j<words.length;j++){
                     String word = words[j].trim();
                     titleList.add(word);
                 }
             }else{
                 Map<String,String> data = new HashMap<>();
                 datas.add(data);
                 for(int j=0;j<words.length;j++){
                     String word = words[j].trim();
                     if(!titleList.get(j).equals(word)) {
                         data.put(titleList.get(j), word);
                     }
                 }
             }
         }
         logger.info("data count:"+datas.size());


//        AF（作者全称）  Araujo, Ricardo de A.; Oliveira, Adriano L. I.; Meira, Silvio
//        C1（机构）[Araujo, Ricardo de A.] Inst Fed Sertao Parnambucano, Petrolina, PE, Brazil; [Oliveira, Adriano L. I.; Meira, Silvio] Univ Fed Pernambuco, Ctr Informat, Recife, PE, Brazil
//        RP（通讯作者） Araujo, RD (reprint author), Inst Fed Sertao Parnambucano, Petrolina, PE, Brazil.
//        TC（被引)


        Map<String,Integer> authorPaperCountMap = new HashMap<>();
        Map<String,Integer> institutionPaperCountMap = new HashMap<>();
        Map<String,Integer> authorPaperCitesMap = new HashMap<>();
        Map<String,Integer> institutionPaperCitesMap = new HashMap<>();


        for(Map<String,String> data : datas){
            if(!data.containsKey("TI")) {
                continue;
            }


            String authorStr = data.get("AF");
            //logger.info("authorStr:"+authorStr);
            String institutionStr = data.get("C1");
            if(institutionStr!=null&&institutionStr.contains("Tsinghua Univ")){
                if(data.containsKey("TI")) {
                    System.out.println("Tsinghua Univ title:" + data.get("TI") + ",,,");
                }
            }

            if(institutionStr!=null&&institutionStr.split("Tsinghua Univ").length>2){
                if(data.containsKey("TI")) {
                    System.out.println("111Tsinghua Univ title:" + data.get("TI") + ",,,");
                }
            }
            //logger.info("institutionStr:"+institutionStr);
            String citeStr = data.get("TC");

            int citeNum = StringProcess.str2Int(citeStr);

            if(authorStr!=null) {
                String[] authors = authorStr.split(";");
                for (String author : authors) {
                    author = author.trim();
                    if (authorPaperCountMap.containsKey(author)) {
                        authorPaperCountMap.put(author, authorPaperCountMap.get(author) + 1);
                    } else {
                        authorPaperCountMap.put(author, 1);
                    }
                    if (authorPaperCitesMap.containsKey(author)) {
                        authorPaperCitesMap.put(author, authorPaperCitesMap.get(author) + citeNum);
                    } else {
                        authorPaperCitesMap.put(author, citeNum);
                    }
                }
            }
            if(institutionStr!=null) {
                Set<String> institutionList = institutionProcess(institutionStr);
                for (String institution : institutionList) {
                    institution = institution.trim();
                    if (institutionPaperCitesMap.containsKey(institution)) {
                        institutionPaperCitesMap.put(institution, institutionPaperCitesMap.get(institution) + citeNum);
                    } else {
                        institutionPaperCitesMap.put(institution, citeNum);
                    }
                    if (institutionPaperCountMap.containsKey(institution)) {
                        institutionPaperCountMap.put(institution, institutionPaperCountMap.get(institution) + 1);
                    } else {
                        institutionPaperCountMap.put(institution, 1);
                    }
                }
            }
        }
        logger.info("author count over");
        logger.info("institution count over");

        authorPaperCountMap= sortMapByValue(authorPaperCountMap);
        logger.info("authorPaperCountMap sort over");
        institutionPaperCountMap= sortMapByValue(institutionPaperCountMap);
        logger.info("institutionPaperCountMap sort over");
        authorPaperCitesMap= sortMapByValue(authorPaperCitesMap);
        logger.info("authorPaperCitesMap sort over");
        institutionPaperCitesMap= sortMapByValue(institutionPaperCitesMap);
        logger.info("institutionPaperCitesMap sort over");



        for(String key : authorPaperCountMap.keySet()){
            String line = key+"\t"+authorPaperCountMap.get(key)+"\n";
            String filePath_tmp = "file/wos_export/authorPaperCount_"+DATA_TYPE+".txt";
            FileOperation.write(line,filePath_tmp,true);
        }logger.info("authorPaperCount wirte file over");

        for(String key : institutionPaperCountMap.keySet()){
            String line = key+"\t"+institutionPaperCountMap.get(key)+"\n";
            String filePath_tmp = "file/wos_export/institutionPaperCount_"+DATA_TYPE+".txt";
            FileOperation.write(line,filePath_tmp,true);
        }logger.info("institutionPaperCount wirte file over");


        for(String key : authorPaperCitesMap.keySet()){
            String line = key+"\t"+authorPaperCitesMap.get(key)+"\n";
            String filePath_tmp = "file/wos_export/authorPaperCites_"+DATA_TYPE+".txt";
            FileOperation.write(line,filePath_tmp,true);
        }logger.info("authorPaperCites wirte file over");


        for(String key : institutionPaperCitesMap.keySet()){
            String line = key+"\t"+institutionPaperCitesMap.get(key)+"\n";
            String filePath_tmp = "file/wos_export/institutionPaperCites_"+DATA_TYPE+".txt";
            FileOperation.write(line,filePath_tmp,true);
        }logger.info("institutionPaperCites wirte file over");

    }



    private Set<String> institutionProcess(String institutionStr){
        //        C1（机构）[Araujo, Ricardo de A.] Inst Fed Sertao Parnambucano, Petrolina, PE, Brazil;
        // [Oliveira, Adriano L. I.; Meira, Silvio] Univ Fed Pernambuco, Ctr Informat, Recife, PE, Brazil

        Set<String> instituionList = new HashSet<>();
        String []institutions = institutionStr.split(";");
        for(String insti:institutions){
            //Chinese Acad Sci
//            if(insti.contains("Tsinghua Univ")){
//                System.out.print("111");
//                System.out.println(insti);
//            }

            if(!insti.contains("]"))continue;
            insti = insti.split("]")[1].trim();
            //Chinese Acad Sci, Inst Soil Sci,
            if(insti.startsWith("Chinese Acad Sci")){
                insti = "Chinese Acad Sci,"+insti.split(",")[1];
            }else{
                insti = insti.split(",")[0];
            }
            instituionList.add(insti);
        }
        return instituionList;

    }


    public Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(
                oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
        Map.Entry<String, Integer> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }
}
class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {

        return 0-me1.getValue().compareTo(me2.getValue());
    }
}