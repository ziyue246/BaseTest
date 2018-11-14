package common.main.rich;


import common.system.TextReplaceConverter;
import common.system.WordConverter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mark4FTest {


    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";


    private static ArrayList<String> pathList = new ArrayList<>();



    public String rootpath = "/Users/ziyue/Documents/saohei/";
    public String lableexcelFilePath = "./标注笔录.xlsx";
    public String filename = "张氏案电子版";

    public static void  main(String []args){


        Mark4FTest mark4FTest = new Mark4FTest();
        if(args.length==3){
            mark4FTest.rootpath = args[0].trim();
            mark4FTest.filename = args[1].trim();
            mark4FTest.lableexcelFilePath = args[2].trim();
            mark4FTest.bilu2excel4label();
        }else{
            System.out.println("args length error!");
        }
    }




    public  void bilu2excel4label() {



        traverseFolder(rootpath+filename);



        List<Map<String,String>>  datalist = new ArrayList<>();
        int countSentence = 0;
        for (String filepath : pathList) {

            if (filepath.contains("~$") || filepath.contains("辨认")
                    || filepath.split(filename + "/")[1].startsWith("._")) continue;
            if(filepath.contains("总证据册")||!(filepath.endsWith(".doc")||filepath.endsWith(".docx"))){
                continue;
            }
            System.out.println(filepath);
            String text = WordConverter.converter(filepath);
            //TextReplaceConverter textReplaceConverter = new TextReplaceConverter("gongan/wordDicts/replace_words");
            //text = textReplaceConverter.converter(text);

            String relativePath = filepath.split(filename + "/")[1];
            String relativePathMd5 = stringToMD5(relativePath);

            for (String sentence : text.split("。|\\?|？|\n")) {
                sentence = sentence.trim();
                sentence = sentence.replace("　","");
                if(sentence.length()<3)continue;
                countSentence+=1;
                Map<String,String> data = new HashMap<String,String>();
                data.put("id",countSentence+"");
                data.put("fileMd5",relativePathMd5);
                data.put("relativePath",relativePath);
                data.put("sententMd5",stringToMD5(sentence));
                data.put("sentence",sentence);
                datalist.add(data);
            }
        }
        writeExcel(datalist,datalist.size()+1,
                lableexcelFilePath);//rootpath+filename+"/"+
    }

    /**
     * 判断Excel的版本,获取Workbook
     * @param
     * @param
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

    public static void writeExcel(List<Map<String,String>> dataList, int cloumnCount,String finalXlsxPath){
        OutputStream out = null;
        try {
            // 获取总列数
            int columnNumCount = cloumnCount;
            // 读取Excel文档
            File finalXlsxFile = new File(finalXlsxPath);
            Workbook workBook = getWorkbok(finalXlsxFile);
            // sheet 对应一个工作页
            Sheet sheet = workBook.getSheetAt(0);
            /**
             * 删除原有数据，除了属性列
             */
            int rowNumber = sheet.getLastRowNum();    // 第一行从0开始算
            System.out.println("原始数据总行数，除属性列：" + rowNumber);
            for (int i = 1; i <= rowNumber; i++) {
                Row row = sheet.getRow(i);
                sheet.removeRow(row);
            }
            // 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
            /**
             * 往Excel中写新数据
             */
            for (int j = 0; j < dataList.size()+1; j++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(j);


                //for (int k = 0; k <= 1; k++)
                {

                    int tmp_index=0;
                    if(j==0){
                        row.createCell(tmp_index++).setCellValue("id");
                        row.createCell(tmp_index++).setCellValue("文件Md5");
                        row.createCell(tmp_index++).setCellValue("句子Md5");
                        row.createCell(tmp_index++).setCellValue("文件");
                        row.createCell(tmp_index++).setCellValue("句子");
                        row.createCell(tmp_index++).setCellValue("经济特征(是标注1，不是标注0)");
                        row.createCell(tmp_index++).setCellValue("控制特征(是标注1，不是标注0)");
                        row.createCell(tmp_index++).setCellValue("行为特征(是标注1，不是标注0)");
                        row.createCell(tmp_index++).setCellValue("组织特征(是标注1，不是标注0)");
                    }else {

                        Map dataMap = dataList.get(j-1);
                        String id = dataMap.get("id").toString();
                        String fileMd5 = dataMap.get("fileMd5").toString();
                        String sententMd5 = dataMap.get("sententMd5").toString();
                        String sentence = dataMap.get("sentence").toString();
                        String relativePath = dataMap.get("relativePath").toString();
                        row.createCell(tmp_index++).setCellValue(id);
                        row.createCell(tmp_index++).setCellValue(fileMd5);
                        row.createCell(tmp_index++).setCellValue(sententMd5);
                        row.createCell(tmp_index++).setCellValue(relativePath);
                        row.createCell(tmp_index++).setCellValue(sentence);
                        row.createCell(tmp_index++).setCellValue(0);
                        row.createCell(tmp_index++).setCellValue(0);
                        row.createCell(tmp_index++).setCellValue(0);
                        row.createCell(tmp_index++).setCellValue(0);
                        //break;
                    }

                }
            }
            // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
            out =  new FileOutputStream(finalXlsxPath);
            workBook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(out != null){
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



    public  void appendWrite(String filePath, String conent) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath, true)));
            out.write(conent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private  void traverseFolder(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                ///System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        //System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        pathList.add(file2.getAbsolutePath());
                        //System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            //System.out.println("文件不存在!");
        }
    }

    public  String stringToMD5(String plainText) {


        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
