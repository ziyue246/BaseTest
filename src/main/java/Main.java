package main;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.system.Ua;

public class Main {

	public static void main(String[] args) {
		

		
		
		HtmlInfo htmlInfo = new HtmlInfo();
		String url = "http://weixin.sogou.com/weixin?query=%E4%B8%AD%E5%9B%BD%E6%99%BA%E8%83%BD%E8%BD%A6%E6%9C%AA%E6%9D%A5%E6%8C%91%E6%88%98%E8%B5%9B&type=2";
		htmlInfo.setOrignUrl(url);
		htmlInfo.setUa(Ua.randomGetUa());
		htmlInfo.setAccept("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		
		SimpleHttp simpleHttp = new SimpleHttp();
		
		simpleHttp.simpleGet(htmlInfo);
		
		htmlInfo.setReferUrl(url);
		
		
		//System.out.println(htmlInfo.getCookie());
		
		//System.out.println(htmlInfo.getContent());
		url="http://weixin.sogou.com/weixin?type=2&ie=utf8&query=%E4%B8%AD%E5%9B%BD%E6%99%BA%E8%83%BD%E8%BD%A6%E6%9C%AA%E6%9D%A5%E6%8C%91%E6%88%98%E8%B5%9B&tsn=4&ft=&et=&interation=&wxid=&usip=";
		
		
		htmlInfo.setOrignUrl(url);
	
		simpleHttp.simpleGet(htmlInfo);
		
		
		System.out.println(htmlInfo.getCookie());
		
		System.out.println(htmlInfo.getContent());
	}

}
