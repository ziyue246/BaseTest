package xpath;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import org.junit.Test;

import java.net.URLEncoder;

public class HttpTest {
	
	
	
	@Test
	public void simplehttp() throws Exception{
		HtmlInfo html = new HtmlInfo();
		SimpleHttp http = new SimpleHttp();
		
		String url = "http://weixin.sogou.com/weixin?query=%E9%9D%92%E5%B2%9B%E6%97%A9%E6%8A%A5&type=1";
		
		url="http://mp.weixin.qq.com/profile?src=3&timestamp=1510207883&ver=1&signature=XGB6OHukAekuXRA0XvrdAcEg3-nZH0di99WHbpGCv4Q1XaqA6hrYy6vrQbziwXJpATUTkLT*HSGNPRnr3RE0Ng==";
		
		url="http://mp.weixin.qq.com/profile?src=3&timestamp=1510215133&ver=1&signature=XGB6OHukAekuXRA0XvrdAcEg3-nZH0di99WHbpGCv4Q1XaqA6hrYy6vrQbziwXJpFsK83jB3ggktsh3QWGbGGQ==";
		
		url="http://mp.weixin.qq.com/profile?src=3&timestamp=1510215133&ver=1&signature=XGB6OHukAekuXRA0XvrdAcEg3-nZH0di99WHbpGCv4Q1XaqA6hrYy6vrQbziwXJpFsK83jB3ggktsh3QWGbGGQ==";
		url="https://mp.weixin.qq.com/s?timestamp=1510215251&src=3&ver=1&signature=qmn1IsRtGkSXORGKr9DZzBmO8rfgF6v3YONiukfoOQXOuwedUUb9IOjhbMq*-MLn5INK-VaAg6ipZzCaH5OZr-0IMzFTrTH0zA82lq-onOAtbICBjdEMRNp-XeG-uNVswHnkO0Y*2Chx7LoaFeLd85mHSAftrTzL4Le9s4sp9L0=";
		url="https://mp.weixin.qq.com/s?timestamp=1510216288&src=3&ver=1&signature=qmn1IsRtGkSXORGKr9DZzNciXWyx7GMtkWy4PIkca7sflAfEEIPJnPrIsSD72MoHkCWwINUXfOhD2J0WK8QyROAZpQCRwiVVcbpZp5HegChsljFF7KQAXdraxlKdQwadKA*RVivuVTbnvVsRCn1ORL3H2m64hWwdh2QA6tehZrk=";

		url="http://kns.cnki.net/kns/request/SearchHandler.ashx?action=&NaviCode=*&ua=1.21&PageName=ASP.brief_result_aspx&DbPrefix=SCDB&DbCatalog=%e4%b8%ad%e5%9b%bd%e5%ad%a6%e6%9c%af%e6%96%87%e7%8c%ae%e7%bd%91%e7%bb%9c%e5%87%ba%e7%89%88%e6%80%bb%e5%ba%93&ConfigFile=SCDB.xml&db_opt=CJFQ%2CCJRF%2CCDFD%2CCMFD%2CCPFD%2CIPFD%2CCCND%2CCCJD&expertvalue=AF=%27%E4%B8%AD%E5%9B%BD%E7%A7%91%E5%AD%A6%E9%99%A2%E8%87%AA%E5%8A%A8%E5%8C%96%E7%A0%94%E7%A9%B6%E6%89%80%27%20%20and%20(%20AU%20%%20%27%E6%9B%B9%E5%BF%97%E5%BC%BA%27%202B%%20%27%E6%88%B4%E6%B1%9D%E4%B8%BA%27%202B%%20%27%E4%BE%AF%E5%A2%9E%E5%B9%BF%27%202B%%20%27%E8%BE%B9%E6%A1%82%E5%BD%AC%27%202B%%20%27%E6%9B%B9%E5%BF%97%E5%86%AC%27%202B%%20%27%E6%9B%BE%E5%A4%A7%E5%86%9B%27%202B%%20%27%E6%9B%BE%E5%B8%85%27%202B%%20%27%E7%A8%8B%E9%BE%99%27%202B%%20%27%E8%91%A3%E8%A5%BF%E6%9D%BE%27%202B%%20%27%E6%9D%9C%E6%B4%8B%27%20%20)&publishdate_from=2005-01-01&publishdate_to=2019-01-01&his=0&__=Mon%20Mar%2005%202018%2014%3A32%3A05%20GMT%2B0800%20(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)";

		url ="http://kns.cnki.net/kns/request/SearchHandler.ashx?RecordsPerPage=50&action=&NaviCode=*&ua=1.21&PageName=ASP.brief_result_aspx&DbPrefix=SCDB&DbCatalog=%e4%b8%ad%e5%9b%bd%e5%ad%a6%e6%9c%af%e6%96%87%e7%8c%ae%e7%bd%91%e7%bb%9c%e5%87%ba%e7%89%88%e6%80%bb%e5%ba%93&ConfigFile=SCDB.xml&db_opt=CJFQ%2CCJRF%2CCDFD%2CCMFD%2CCPFD%2CIPFD%2CCCND%2CCCJD&expertvalue=AF=%27%E4%B8%AD%E5%9B%BD%E7%A7%91%E5%AD%A6%E9%99%A2%E8%87%AA%E5%8A%A8%E5%8C%96%E7%A0%94%E7%A9%B6%E6%89%80%27%20%20and%20(%20AU%20%%20%27%E6%9B%B9%E5%BF%97%E5%BC%BA%27%202B%%20%27%E6%88%B4%E6%B1%9D%E4%B8%BA%27%202B%%20%27%E4%BE%AF%E5%A2%9E%E5%B9%BF%27%202B%%20%27%E8%BE%B9%E6%A1%82%E5%BD%AC%27%202B%%20%27%E6%9B%B9%E5%BF%97%E5%86%AC%27%202B%%20%27%E6%9B%BE%E5%A4%A7%E5%86%9B%27%202B%%20%27%E6%9B%BE%E5%B8%85%27%202B%%20%27%E7%A8%8B%E9%BE%99%27%202B%%20%27%E8%91%A3%E8%A5%BF%E6%9D%BE%27%202B%%20%27%E6%9D%9C%E6%B4%8B%27%20%20)&publishdate_from=2005-01-01&publishdate_to=2019-01-01&his=0&__=Mon%20Mar%2005%202018%2014%3A32%3A05%20GMT%2B0800%20(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)";

		//url = URLEncoder.encode(url,"utf-8");



		html.setOrignUrl(url);

		//html.setReferUrl("http://kns.cnki.net/kns/brief/result.aspx?dbprefix=SCDB");
		//http://kns.cnki.net/kns/brief/result.aspx?dbprefix=SCDB
		String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";




		//html.setUa(ua);



		http.simpleGet(html);
		
	
		System.out.println(html.getContent());
		
		
		
		
		
	}

}
