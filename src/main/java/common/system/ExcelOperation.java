package common.system;


import jxl.*;
import jxl.read.biff.BiffException;
import org.apache.log4j.Logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelOperation {


    private  static Logger logger = Logger.getLogger(ExcelOperation.class);


    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
    public static List readExcel(File file) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                List<List> outerList=new ArrayList<List>();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);

                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList=new ArrayList();
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();



                        Cell c = sheet.getCell(j, i);
                        if (c.getType() == CellType.DATE) {//手动填写模板文件时为 date 类型，其他情况有可能不是date类型
                            DateCell dc = (DateCell) c;
                            Date date = dc.getDate();
                            TimeZone zone = TimeZone.getTimeZone("GMT");
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            sdf.setTimeZone(zone);
                            cellinfo = sdf.format(date);
                        }

                        if(cellinfo==null||cellinfo.length()<=0){
                            //logger.info("cellinfo:"+cellinfo);
                        }
                        innerList.add(cellinfo);
                    }
                    outerList.add(i, innerList);
                }
                return outerList;
            }
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (BiffException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }


    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
    public static Map<String,List> readExcel2Map(File file) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            Map<String,List> map = new HashMap<>();
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                List<List> outerList=new ArrayList<List>();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList=new ArrayList();
                    // sheet.getColumns()返回该页的总列数
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
                        if(cellinfo==null||cellinfo.length()<=0){
                            //logger.info("cellinfo:"+cellinfo);
                        }

                        Cell c = sheet.getCell(j, i);
                        if (c.getType() == CellType.DATE) {//手动填写模板文件时为 date 类型，其他情况有可能不是date类型
                            DateCell dc = (DateCell) c;
                            Date date = dc.getDate();
                            TimeZone zone = TimeZone.getTimeZone("GMT");
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            sdf.setTimeZone(zone);
                            cellinfo = sdf.format(date);
                        }

                        innerList.add(cellinfo);
                    }
                    outerList.add(i, innerList);
                }
                map.put(sheet.getName(),outerList);
            }
            return map;
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (BiffException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
