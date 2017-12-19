package common.system;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringProcess {



    private static Logger logger = Logger.getLogger(StringProcess.class);
    public static String  upCaseFirstLetter(String s){
        if(s==null)return null;
        if(s.length()==0)return s;
        return s.substring(0,1).toUpperCase()+s.substring(1);
    }
    public static String regex2StrSplitByMark(String content,String pattern,String mark){
        //String line = "http://www11.drugfuture.com/cnpat/SecurePdf.aspx";
        //  String pattern = "http://www(\\d*).drugfuture.com/cnpat/SecurePdf.aspx";
        // ���� Pattern ����
        Pattern r = Pattern.compile(pattern);
        // ���ڴ��� matcher ����
        Matcher m = r.matcher(content);
        StringBuffer sb = new StringBuffer();
        while (m.find( )) {
            if(sb.length()>0){
                sb.append(mark);
            }
            sb.append(m.group());
        }
        return sb.toString();
    }

    public static List<String> regex2List(String content, String pattern){
        //String line = "http://www11.drugfuture.com/cnpat/SecurePdf.aspx";
        //  String pattern = "http://www(\\d*).drugfuture.com/cnpat/SecurePdf.aspx";
        // ���� Pattern ����
        Pattern r = Pattern.compile(pattern);
        // ���ڴ��� matcher ����
        Matcher m = r.matcher(content);
        List<String> list = new ArrayList<String>();
        while (m.find( )) {
            list.add(m.group());
        }
        return list;
    }




    /**
     * ������תΪ����ƴ��
     * @param ChineseLanguage Ҫת��ƴ��������
     */
    public static String toHanyuPinyin(String ChineseLanguage){
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// ���ƴ��ȫ��Сд
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// ��������
        //defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V) ;
        try {
            for (int i=0; i<cl_chars.length; i++){
                if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")){// ����ַ�������,������תΪ����ƴ��
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
                } else {// ����ַ���������,��ת��
                    hanyupinyin += cl_chars[i];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            logger.info("�ַ�����ת�ɺ���ƴ��");
        }
        hanyupinyin = hanyupinyin.replace("u:","v");
        return hanyupinyin;
    }

    public static int str2Int(String str){

        try {
            return Integer.parseInt(str);
        }catch (Exception e){

            logger.info("str :" +str+"\n exception : "+e.getMessage());
            return 0;
        }

    }
    public static boolean isChinese(String str){

        String regEx = "[\\u4e00-\\u9fa5]+";

        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(str);

        if(m.find())
            return true;
        else
            return false;

    }
}
