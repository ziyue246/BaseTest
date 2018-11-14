package common.system;


import lombok.val;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileUtils
{
    static Logger logger = Logger.getLogger(FileUtils.class);
    public static String getResourcePath(String filePath) {
        //test absolute or relative path
        File file = new File(filePath);
        if (!file.isAbsolute()) {
            URL url = FileUtils.class.getClassLoader().getResource(filePath);
            if (url == null)
                throw new IllegalArgumentException(String.format("%s is not a valid format", filePath));
            file = new File(url.getFile());
        }
        if (!file.exists())
            throw new IllegalArgumentException(String.format("%s is not a valid file: %s", filePath, file.getPath()));
        logger.info(String.format("File %s has the complex path : %s", filePath, file.getPath()));
        return file.getPath();
    }

    public static List<String> getFileLines(String filePath)  {
        try(InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(filePath)) {
            try(InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                BufferedReader reader = new BufferedReader(streamReader);
                return reader.lines().collect(Collectors.toList());
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static Map mergeEntry(Map mapStreams, Map.Entry entry) {
        if(entry == null || mapStreams == null)return mapStreams;
        if (!mapStreams.containsKey(entry.getKey())) {
            mapStreams.put(entry.getKey(), entry.getValue());
        } else if (mapStreams.get(entry.getKey()).getClass().isArray()) {
            val list = new ArrayList<Object>();
            for (val o : (Object[]) mapStreams.get(entry.getKey())) {
                list.add(o);
            }
            if (entry.getValue().getClass().isArray()) {
                for (val o : (Object[]) entry.getValue()) {
                    list.add(o);
                }
            } else {
                list.add(entry.getValue());
            }

            mapStreams.put(entry.getKey(), list.toArray());
        } else {
            val list = new ArrayList();
            list.add(mapStreams.get(entry.getKey()));
            list.add(entry.getValue());
            mapStreams.put(entry.getKey(), list.toArray());

        }
        return mapStreams;
    }

    public static Map mergeMap(Map ori, Map<String, Object> curr) {
        if(curr == null || ori == null)return ori;
        for(Map.Entry<String, Object> entry: curr.entrySet()) {
            if(entry.getValue() != null) {
                ori = mergeEntry(ori, entry);
            }
        }
        return ori;
    }

    public static String readFile(String path) {
        String encoding = "utf-8";
        File file = new File(path);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void writeStringToFile(String text, String filePath) {
        try
        {
            createFile(filePath);
            FileWriter writer=new FileWriter(filePath);
            writer.write(text);
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if(file.exists()) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if(!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            System.out.println("目标文件所在目录不存在，准备创建它！");
            if(!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                System.out.println("创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                System.out.println("创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }
    }


    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }
    }
}
