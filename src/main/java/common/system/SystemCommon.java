package common.system;
import common.http.HtmlInfo;
import common.http.SimpleHttp;
import org.apache.http.Header;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SystemCommon {
	private static final Logger logger = Logger.getLogger(SystemCommon.class);


	public static void sleeps(int s){
		Random random = new Random();
		try {
			Thread.sleep((s+random.nextInt(5))*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void sleepsSecond(int s){
		Random random = new Random();
		try {
			Thread.sleep((s+random.nextInt(5))*1000);
		} catch (InterruptedException e) {
			logger.info(e);
		}
	}
	public static String urlEncode(String str,String charset){
		try {
			return URLEncoder.encode(str, charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void printLog(Object o){
		String s = o+"";
		Date date = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.print(dateFormat.format(date)+" : ");
		System.out.println(s);
		
	}

	public static void printLog(Object t,Object o){
		String s = o+"";
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.print(dateFormat.format(date)+" "+
				t.getClass().getSimpleName()+"  : ");
		System.out.println(s);

	}
	public static void printList(List<? extends Object> list){
		int count = 0;
		for (Object obj:list) {
			printLog((++count)+"\t"+obj);
		}
	}
	public static void printMap(Object obj){
		Map<? extends Object,? extends Object> map = (HashMap<? extends Object,? extends Object>)obj;
		Set<? extends Object>  keySet = map.keySet();
		for (Object key :keySet) {
			printLog(key+"\t:\t"+map.get(key));
		}
	}
	public static void printHeaders(Header []heads){
        for (Header header : heads) {
        	printLog(header.getName()+"\t:\t"+header.getValue());
		}
	}
	public static int getMonth(String month){
		String []months = {"January",//1
							"February",//2
							"March",//3
							"April",//4
							"May",//5
							"June",//6
							"July",//7
							"August",//8
							"September",//9
							"October",//10
							"November",//11
							"December"};//12
		for (int i = 0; i < months.length; i++) {
			if(months[i].contains(month)||months[i].equals(month)){
				return i+1;
			}
		}
		return 0;
	}
	public static String decodeUnicode(String str) {
		Charset set = Charset.forName("UTF-16");
		Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
		Matcher m = p.matcher(str);
		int start = 0;
		int start2 = 0;
		StringBuffer sb = new StringBuffer();
		while (m.find(start)) {
			start2 = m.start();
			if (start2 > start) {
				String seg = str.substring(start, start2);
				sb.append(seg);
			}
			String code = m.group(1);
			int i = Integer.valueOf(code, 16);
			byte[] bb = new byte[4];
			bb[0] = (byte) ((i >> 8) & 0xFF);
			bb[1] = (byte) (i & 0xFF);
			ByteBuffer b = ByteBuffer.wrap(bb);
			sb.append(String.valueOf(set.decode(b)).trim());
			start = m.end();
		}
		start2 = str.length();
		if (start2 > start) {
			String seg = str.substring(start, start2);
			sb.append(seg);
		}
		return sb.toString();
	}
	public static String regex(String content,String pattern){
		//String line = "http://www11.drugfuture.com/cnpat/SecurePdf.aspx";
	    //  String pattern = "http://www(\\d*).drugfuture.com/cnpat/SecurePdf.aspx";
	      // 创建 Pattern 对象
	      Pattern r = Pattern.compile(pattern);
	      // 现在创建 matcher 对象
	      Matcher m = r.matcher(content);
	      StringBuffer sb = new StringBuffer();
	      while (m.find( )) {
	    	  if(sb.length()>0){
	    		  sb.append("\n");
	    	  }
	    	  sb.append(m.group());
	      }
	      return sb.toString();
	}

	public static String  getProxyFromWeb(){
		String url = "http://117.132.15.89:8090/proxy/";
		HtmlInfo html = new HtmlInfo();
		html.setOrignUrl(url);
		SimpleHttp simpleHttp = new SimpleHttp();
		simpleHttp.simpleGet(html);
		String proxy = html.getContent();
		logger.info("curr proxy : "+proxy);
		return proxy;
	}


}
