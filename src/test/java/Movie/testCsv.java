package Movie;

import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * Created by ziyue on 2018/7/2.
 */
public class testCsv {


    @Test
    public void test(){
        List<Person> dataList = new ArrayList<Person>();
        Person person1 = new Person();
        person1.setName("張三,3");
        person1.setSex(1);
        person1.setAge(55);
        person1.setPhone("13911111111");
        person1.setAddress("北京海淀区");
        dataList.add(person1);
        Person person2 = new Person();
        person2.setName("小美");
        person2.setSex(0);
        person2.setAge(20);
        person2.setPhone("13911112222");
        person2.setAddress("北京,西城区");
        dataList.add(person2);
        Person person3 = new Person();
        person3.setName("小明");
        person3.setSex(1);
        person3.setAge(25);
        person3.setPhone("13933333333");
        person3.setAddress("北京,海淀区");
        dataList.add(person3);

        writeCSV(dataList,"file/test.csv");
    }




    public void writeCSV(List<Person> dataList, String finalPath) {
        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            File finalCSVFile = new File(finalPath);
            out = new FileOutputStream(finalCSVFile);
            osw = new OutputStreamWriter(out, "UTF-8");
            // 手动加上BOM标识
            osw.write(new String(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}));
            bw = new BufferedWriter(osw);
            /**
             * 往CSV中写新数据
             */
            String title = "";
            title = "name,sex,age,phone num,address";
            bw.append(title).append("\r");

            if (dataList != null && !dataList.isEmpty()) {
                for (Person data : dataList) {
                    bw.append(data.getName() + ",");
                    if (data.getSex() == 1) {
                        bw.append("男,");
                    } else if (data.getSex() == 0) {
                        bw.append("女,");
                    } else {
                        bw.append(" ,");
                    }
                    bw.append(data.getAge() + ",");
                    bw.append(data.getPhone() + ",");
                    bw.append(data.getAddress());
                    bw.append("\r");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (bw != null) {
                try {
                    bw.close();
                    bw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                    osw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println(finalPath + "\n数据导出成功");

    }
}
class Person {

    private String name;

    private int age;

    private int sex;

    private String phone;

    private String address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}