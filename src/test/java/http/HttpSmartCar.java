package http;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.system.SystemCommon;
import common.system.Ua;
import org.junit.Test;

public class HttpSmartCar {
	
	
	@Test
	public void test(){
		
		
		HtmlInfo htmlInfo = new HtmlInfo();
		String url = "http://weixin.sogou.com/weixin?query=%E4%B8%AD%E5%9B%BD%E6%99%BA%E8%83%BD%E8%BD%A6%E6%9C%AA%E6%9D%A5%E6%8C%91%E6%88%98%E8%B5%9B&type=2";
		htmlInfo.setOrignUrl(url);
		htmlInfo.setEncode("utf-8");
		htmlInfo.setUa(Ua.randomGetUa());
		htmlInfo.setAccept("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		
		SimpleHttp simpleHttp = new SimpleHttp();
		
		simpleHttp.simpleGet(htmlInfo);
		System.out.println(htmlInfo.getContent());
		htmlInfo.setReferUrl(url);
		
		url="http://weixin.sogou.com/weixin?type=2&ie=utf8&query=%E4%B8%AD%E5%9B%BD%E6%99%BA%E8%83%BD%E8%BD%A6%E6%9C%AA%E6%9D%A5%E6%8C%91%E6%88%98%E8%B5%9B&tsn=4&ft=&et=&interation=&wxid=&usip=";

		htmlInfo.setOrignUrl(url);
		simpleHttp.simpleGet(htmlInfo);

		System.out.println(htmlInfo.getCookie());
		
		System.out.println(htmlInfo.getContent());

		
	}
	@Test
	public void test01(){



		HtmlInfo htmlInfo = new HtmlInfo();
		String url = "http://weixin.sogou.com/weixin?query=%E4%B8%AD%E5%9B%BD%E6%99%BA%E8%83%BD%E8%BD%A6%E6%9C%AA%E6%9D%A5%E6%8C%91%E6%88%98%E8%B5%9B&type=2";
		htmlInfo.setOrignUrl(url);
		htmlInfo.setProxy(SystemCommon.getProxyFromWeb());
		htmlInfo.setEncode("utf-8");
		htmlInfo.setUa(Ua.randomGetUa());
		htmlInfo.setAccept("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");

		SimpleHttp simpleHttp = new SimpleHttp();

		simpleHttp.simpleGet(htmlInfo);
		System.out.println(htmlInfo.getContent());
		htmlInfo.setReferUrl(url);

		url="http://weixin.sogou.com/weixin?type=2&ie=utf8&query=%E4%B8%AD%E5%9B%BD%E6%99%BA%E8%83%BD%E8%BD%A6%E6%9C%AA%E6%9D%A5%E6%8C%91%E6%88%98%E8%B5%9B&tsn=4&ft=&et=&interation=&wxid=&usip=";

		htmlInfo.setOrignUrl(url);
		htmlInfo.setProxy(SystemCommon.getProxyFromWeb());
		simpleHttp.simpleGet(htmlInfo);

		System.out.println(htmlInfo.getCookie());

		System.out.println(htmlInfo.getContent());



	}

	@Test
	public void test011(){



		HtmlInfo htmlInfo = new HtmlInfo();
		String url = "http://weixin.sogou.com/weixin?query=%E4%B8%AD%E5%9B%BD%E6%99%BA%E8%83%BD%E8%BD%A6%E6%9C%AA%E6%9D%A5%E6%8C%91%E6%88%98%E8%B5%9B&type=2";
		htmlInfo.setOrignUrl(url);
		htmlInfo.setProxy(SystemCommon.getProxyFromWeb());
		htmlInfo.setEncode("utf-8");
		htmlInfo.setUa(Ua.randomGetUa());
		htmlInfo.setAccept("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");

		SimpleHttp simpleHttp = new SimpleHttp();

		simpleHttp.simpleGet(htmlInfo);
		//System.out.println(htmlInfo.getContent());
		htmlInfo.setReferUrl(url);

		url="http://weixin.sogou.com/weixin?type=2&ie=utf8&query=%E4%B8%AD%E5%9B%BD%E6%99%BA%E8%83%BD%E8%BD%A6%E6%9C%AA%E6%9D%A5%E6%8C%91%E6%88%98%E8%B5%9B&tsn=4&ft=&et=&interation=&wxid=&usip=";

		url = "http://weixin.sogou.com/weixin?type=2&ie=utf8&query=%E4%B8%AD%E5%9B%BD%E6%99%BA%E8%83%BD%E8%BD%A6%E6%9C%AA%E6%9D%A5%E6%8C%91%E6%88%98%E8%B5%9B&tsn=5&ft=2017-11-07&et=2017-11-28&interation=&wxid=&usip=";

		url = "http://weixin.sogou.com/weixin?query=%E4%B8%AD%E5%9B%BD%E6%99%BA%E8%83%BD%E8%BD%A6%E6%9C%AA%E6%9D%A5%E6%8C%91%E6%88%98%E8%B5%9B&type=2&ie=utf8&tsn=5&ft=2017-11-07&et=2017-11-28&interation=&wxid=&usip=";
		htmlInfo.setOrignUrl(url);
		htmlInfo.setProxy(SystemCommon.getProxyFromWeb());
		simpleHttp.simpleGet(htmlInfo);

		System.out.println(htmlInfo.getCookie());

		System.out.println(htmlInfo.getContent());



	}


	@Test
	public void test02(){

		HtmlInfo htmlInfo = new HtmlInfo();//http://weixin.sogou.com/weixin?query=%E5%B8%B8%E7%86%9F%E5%9B%BD%E9%99%85%E6%99%BA%E8%83%BD%E6%B1%BD%E8%BD%A6%E5%91%A8&type=2
		String url = "http://weixin.sogou.com/weixin?query=%E4%B8%AD%E5%9B%BD%E6%99%BA%E8%83%BD%E8%BD%A6%E6%9C%AA%E6%9D%A5%E6%8C%91%E6%88%98%E8%B5%9B&type=2";
		htmlInfo.setOrignUrl(url);
		htmlInfo.setProxy(SystemCommon.getProxyFromWeb());
		htmlInfo.setEncode("utf-8");
		htmlInfo.setUa(Ua.randomGetUa());
		htmlInfo.setAccept("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");

		SimpleHttp simpleHttp = new SimpleHttp();

		simpleHttp.simpleGet(htmlInfo);
		System.out.println(htmlInfo.getContent());
		htmlInfo.setReferUrl(url);

		url="http://weixin.sogou.com/weixin?type=2&ie=utf8&query=%E4%B8%AD%E5%9B%BD%E6%99%BA%E8%83%BD%E8%BD%A6%E6%9C%AA%E6%9D%A5%E6%8C%91%E6%88%98%E8%B5%9B&tsn=4&ft=&et=&interation=&wxid=&usip=";

		htmlInfo.setOrignUrl(url);
		htmlInfo.setProxy(SystemCommon.getProxyFromWeb());
		simpleHttp.simpleGet(htmlInfo);

		System.out.println(htmlInfo.getCookie());

		System.out.println(htmlInfo.getContent());



	}




	@Test
	public void test0211(){

		HtmlInfo htmlInfo = new HtmlInfo();//http://weixin.sogou.com/weixin?query=%E5%B8%B8%E7%86%9F%E5%9B%BD%E9%99%85%E6%99%BA%E8%83%BD%E6%B1%BD%E8%BD%A6%E5%91%A8&type=2
		String url = "http://weixin.sogou.com/weixin?query=%E4%B8%AD%E5%9B%BD%E6%99%BA%E8%83%BD%E8%BD%A6%E6%9C%AA%E6%9D%A5%E6%8C%91%E6%88%98%E8%B5%9B&type=2";
		url="http://news.qtv.com.cn/system/2017/12/29/014723941.shtml";

		htmlInfo.setOrignUrl(url);
		htmlInfo.setProxy(SystemCommon.getProxyFromWeb());
		htmlInfo.setEncode("gb2312");
		htmlInfo.setUa(Ua.randomGetUa());

		SimpleHttp simpleHttp = new SimpleHttp();

		simpleHttp.simpleGet(htmlInfo);
		System.out.println(htmlInfo.getContent());

	}
	@Test
	public void  getProxyFromWeb(){
		String url = "http://117.132.15.89:8090/proxy/";
		HtmlInfo html = new HtmlInfo();
		html.setOrignUrl(url);
		SimpleHttp simpleHttp = new SimpleHttp();
		simpleHttp.simpleGet(html);
		System.out.println(html.getContent());
	}
}
