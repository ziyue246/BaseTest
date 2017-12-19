package xpath;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import org.junit.Test;

public class HttpTest {
	
	
	
	@Test
	public void simplehttp(){
		HtmlInfo html = new HtmlInfo();
		SimpleHttp http = new SimpleHttp();
		
		String url = "http://weixin.sogou.com/weixin?query=%E9%9D%92%E5%B2%9B%E6%97%A9%E6%8A%A5&type=1";
		
		url="http://mp.weixin.qq.com/profile?src=3&timestamp=1510207883&ver=1&signature=XGB6OHukAekuXRA0XvrdAcEg3-nZH0di99WHbpGCv4Q1XaqA6hrYy6vrQbziwXJpATUTkLT*HSGNPRnr3RE0Ng==";
		
		url="http://mp.weixin.qq.com/profile?src=3&timestamp=1510215133&ver=1&signature=XGB6OHukAekuXRA0XvrdAcEg3-nZH0di99WHbpGCv4Q1XaqA6hrYy6vrQbziwXJpFsK83jB3ggktsh3QWGbGGQ==";
		
		url="http://mp.weixin.qq.com/profile?src=3&timestamp=1510215133&ver=1&signature=XGB6OHukAekuXRA0XvrdAcEg3-nZH0di99WHbpGCv4Q1XaqA6hrYy6vrQbziwXJpFsK83jB3ggktsh3QWGbGGQ==";
		url="https://mp.weixin.qq.com/s?timestamp=1510215251&src=3&ver=1&signature=qmn1IsRtGkSXORGKr9DZzBmO8rfgF6v3YONiukfoOQXOuwedUUb9IOjhbMq*-MLn5INK-VaAg6ipZzCaH5OZr-0IMzFTrTH0zA82lq-onOAtbICBjdEMRNp-XeG-uNVswHnkO0Y*2Chx7LoaFeLd85mHSAftrTzL4Le9s4sp9L0=";
		url="https://mp.weixin.qq.com/s?timestamp=1510216288&src=3&ver=1&signature=qmn1IsRtGkSXORGKr9DZzNciXWyx7GMtkWy4PIkca7sflAfEEIPJnPrIsSD72MoHkCWwINUXfOhD2J0WK8QyROAZpQCRwiVVcbpZp5HegChsljFF7KQAXdraxlKdQwadKA*RVivuVTbnvVsRCn1ORL3H2m64hWwdh2QA6tehZrk=";
		
		html.setOrignUrl(url);
		
		String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
		html.setUa(ua);
		
		http.simpleGet(html);
		
	
		System.out.println(html.getContent());
		
		
		
		
		
	}

}
