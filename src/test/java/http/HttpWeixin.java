package http;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.system.FileOperation;
import org.apache.log4j.Logger;
import org.junit.Test;

public class HttpWeixin {



	private Logger logger = Logger.getLogger(HttpWeixin.class);

	@Test
	public void testLogger(){

		logger.info("hello ");
		logger.info("world ");
		logger.info("!!! ");
		logger.info("!!! ");


	}

	
	@Test
	public void test(){


		String path="file/weixin";

		String []urls = FileOperation.read(path).split("\n");
		
		HtmlInfo htmlInfo = new HtmlInfo();


		for(String url:urls){
			String realUrl = url.split("####")[1];
			if(realUrl.equals("null")){
				url = url.split("####")[0];
			}else{
				System.out.println(url);
			}


			url=url.trim();
			String url_tail="&key=013493bc2ee2dcf9555c781910c94f2788c686e7e48d351c9ba19adac8185507fa13a3901c4fe4714fd1896a77a768426af84c5a90248bc32bfe4859ae73eb6cc3cc5521fa0c6940087ea017b8526c6d&ascene=1&uin=MTU4MTM4MzI2Mg%3D%3D&devicetype=Windows+10&version=62060038&pass_ticket=RYThfzZ0g%2FzBEWKdBnKn%2BtPY6AoNfYt42sxAbN3%2FjtMlFYk6ktYmQt%2F%2FZd1pDu8r&winzoom=1";

			url_tail="&key=013493bc2ee2dcf94bb5185d446cfd0f47d47faec36efdcb4e8e680f5ee82bf2bc33cd7ec324bc3fd5a1beae6bf5cedc2ce582d67e8c61b2b39ad948ad4d7c2e7831aced0686a2670e18136306cd3cf3&ascene=1&uin=MTU4MTM4MzI2Mg%3D%3D&devicetype=Windows+10&version=62060038&pass_ticket=RYThfzZ0g%2FzBEWKdBnKn%2BtPY6AoNfYt42sxAbN3%2FjtMlFYk6ktYmQt%2F%2FZd1pDu8r&winzoom=1";


//			if(url.endsWith("new=1")){
//
//				url_tail="&key=6571080d88416c72656307dfa1b53c02408886015a6b9617e65cfac222a8bb320b1f67a2b4db35251b2da3f1f479f370a6c067310cb9591970872994565a14bcccc65a425ca19d8c3ddfe09df1b711c0&ascene=1&uin=MTU4MTM4MzI2Mg%3D%3D&devicetype=Windows+10&version=62060038&pass_ticket=RYThfzZ0g%2FzBEWKdBnKn%2BtPY6AoNfYt42sxAbN3%2FjtMlFYk6ktYmQt%2F%2FZd1pDu8r&winzoom=1";
//
//				url_tail="&key=6571080d88416c72c6af2704cba03cb64b5099c61e321a7871f159190f1d9c9fd789dae33185466aeb445240fece1f31cf15c522deceb2275c99e67b0771e4708012d69f0277c1d2f0b00e48134d826c&ascene=1&uin=MTU4MTM4MzI2Mg%3D%3D&devicetype=Windows+10&version=62060038&pass_ticket=RYThfzZ0g%2FzBEWKdBnKn%2BtPY6AoNfYt42sxAbN3%2FjtMlFYk6ktYmQt%2F%2FZd1pDu8r&winzoom=1";
//			}
			htmlInfo.setOrignUrl(url+url_tail);


			htmlInfo.setEncode("utf-8");
			htmlInfo.setHost("mp.weixin.qq.com");

			String cookie = "wxtokenkey=781a35d51f154e6b8817c135f4055917a1f90d2d076c68983f22aa347bbb98d4; wxuin=1581383262; devicetype=Windows10; version=62060038; lang=zh_CN; pass_ticket=rVhcy3DduOTpg2QffkHhDIJrIJp7xwf6F34B4JFd9p6ijWhTuOPTfXypkbqLvIWr; wap_sid2=CN78h/IFElx1dGd1TWRYenJvV3ZnOHRwSVFxVnNxejVSVDhuS0ktUG1MeEhnZzlONzFaWnh5TnBoX2R0NGJXcE5IWnU3TXdUZ0RUNDlOclBjTFFFNklya09iZG9vcVlEQUFBfjCb9rjRBTgNQAE=";

			cookie ="wxtokenkey=943e45c6f59a4d6eb2ea06c887fbb0daab038e7a07d77a54f46003067d5ed752; wxuin=1581383262; devicetype=Windows10; version=62060038; lang=zh_CN; pass_ticket=RYThfzZ0g/zBEWKdBnKn+tPY6AoNfYt42sxAbN3/jtMlFYk6ktYmQt//Zd1pDu8r; wap_sid2=CN78h/IFElxiWXA2dUVMRXZQVnNLU3BpRk1JbEFyalhIekN5aThIbUxQWVhFdmJVUUFidzRobGxjNll2SnhKWTY3WnJiUUZLLVR0dVY0VlNTTEtuS2ctXy0tNzV0NllEQUFBfjCN6L3RBTgNQAE=";
			cookie ="wxtokenkey=77aa2dcfbdf82f10b3eba98f8543ec8318130c1408700d0edcae2efdb18ff52a; wxuin=1581383262; devicetype=Windows10; version=62060038; lang=zh_CN; pass_ticket=RYThfzZ0g/zBEWKdBnKn+tPY6AoNfYt42sxAbN3/jtMlFYk6ktYmQt//Zd1pDu8r; wap_sid2=CN78h/IFElxKdmwySjRRM2hteDJ1bHQ0cmpyLVhSNUFaU3FIUl93YUxYeC1hQTNxTEpXMGp5TEpkOGJqeUVfdnNGRFA0dDFUZWVSY2NRTDY1SDI0ZVdPUFk3VWdHYVlEQUFBfjCbg77RBTgNQAE=";
			cookie ="wxtokenkey=248b34d7bcbe825020ba96f484b52d8692918b349b10fb49a0401b496d3be03b; wxuin=1581383262; devicetype=Windows10; version=62060038; lang=zh_CN; pass_ticket=RYThfzZ0g/zBEWKdBnKn+tPY6AoNfYt42sxAbN3/jtMlFYk6ktYmQt//Zd1pDu8r; wap_sid2=CN78h/IFElxVeDR1a196R19QMzdPWW9WcVJ0ei16TGJiQnhreERXb1Z5R05YVF93bXhGc1lWNjYydGJaVzdDUUcwVlhkMTdFd2Mtc21ISS1CUnpzc19GWmg3WmtGS1lEQUFBfjDai77RBTgNQAE=";
			String ua="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400";
			htmlInfo.setUa(ua);
			htmlInfo.setAccept("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			htmlInfo.setAcceptEncoding("gzip, deflate");
			htmlInfo.setCookie(cookie);
			SimpleHttp simpleHttp = new SimpleHttp();

			simpleHttp.simpleGet(htmlInfo);


			System.out.print(url);
			System.out.print("####");
			System.out.println(htmlInfo.getRealUrl());

		}
		
	}


	@Test
	public void test02(){


		HtmlInfo htmlInfo = new HtmlInfo();
		String url = "http://weixin.sogou.com/weixin?query=%E4%B8%AD%E5%9B%BD%E6%99%BA%E8%83%BD%E8%BD%A6%E6%9C%AA%E6%9D%A5%E6%8C%91%E6%88%98%E8%B5%9B&type=2";


		url="https://mp.weixin.qq.com/s?src=11&timestamp=1513015415&ver=568&signature=UvyiJ6*EPL*Xsm9dKhQVlEpykX0hMyNxr8JZZ7n87pnq2JGZsu-LyIYLqKJYLVw1xdScHG6W*XtSGOqU2iaAocZ2SJ2H24-ozs6V1v9dvFEGDlHxn9Xlj6yOeuE1-qXB";
		//url="https://mp.weixin.qq.com/s?src=11&timestamp=1513020940&ver=568&signature=NdQjtbbythHXrWO6qX5WP3jjxSYIK1n*DWdgmf8vbiCSBDGeVSByNhs0X256BCKwxpusgkPowx7LuWS6aznv2XRt0lEWbLfbHmCoNQqvJVAv3Qo4woAUEzGQVFHiKpko";

		String url_tail="&key=013493bc2ee2dcf9555c781910c94f2788c686e7e48d351c9ba19adac8185507fa13a3901c4fe4714fd1896a77a768426af84c5a90248bc32bfe4859ae73eb6cc3cc5521fa0c6940087ea017b8526c6d&ascene=1&uin=MTU4MTM4MzI2Mg%3D%3D&devicetype=Windows+10&version=62060038&pass_ticket=RYThfzZ0g%2FzBEWKdBnKn%2BtPY6AoNfYt42sxAbN3%2FjtMlFYk6ktYmQt%2F%2FZd1pDu8r&winzoom=1";
		url_tail="&key=57525b5230b75ef7f84a8daf0edf7db5aa235b4a2977bace571ec915c0a4590d21528578c8dbe3d12ded47b4301cbdc3aae889ace21ca1e2543a889fadf9aebe45a8f730a75ddec7c0b21afd0b007843&ascene=1&uin=MTU4MTM4MzI2Mg%3D%3D&devicetype=Windows+10&version=62060038&pass_ticket=RYThfzZ0g%2FzBEWKdBnKn%2BtPY6AoNfYt42sxAbN3%2FjtMlFYk6ktYmQt%2F%2FZd1pDu8r&winzoom=1";

		url_tail="&key=05115eb1639ab3eb278b24c999f40a58fd893252ef015e1cc1624b00e01f0bf4ba7927fe48e365a09c2f0fe87e1bb1218396acacda1cc1275a15d170e7052840bda7d75e8e55afe670277ab9ca2220ee&ascene=1&uin=MTU4MTM4MzI2Mg%3D%3D&devicetype=Windows+10&version=62060038&pass_ticket=RYThfzZ0g%2FzBEWKdBnKn%2BtPY6AoNfYt42sxAbN3%2FjtMlFYk6ktYmQt%2F%2FZd1pDu8r&winzoom=1";


		url_tail="&key=57525b5230b75ef71d30668949c6c04cb49162a1a4ac8f392d2b9aba07fd075d953846fae4adf5f2feb2123d1e860536cbe92c4bb9bf4736f97fabd3a0d777379959d65e6164697bb82eda104e54e552&ascene=1&uin=MTU4MTM4MzI2Mg%3D%3D&devicetype=Windows+10&version=62060038&pass_ticket=RYThfzZ0g%2FzBEWKdBnKn%2BtPY6AoNfYt42sxAbN3%2FjtMlFYk6ktYmQt%2F%2FZd1pDu8r&winzoom=1";

		htmlInfo.setOrignUrl(url+url_tail);


		htmlInfo.setEncode("utf-8");
		htmlInfo.setHost("mp.weixin.qq.com");

		String cookie = "wxtokenkey=781a35d51f154e6b8817c135f4055917a1f90d2d076c68983f22aa347bbb98d4; wxuin=1581383262; devicetype=Windows10; version=62060038; lang=zh_CN; pass_ticket=rVhcy3DduOTpg2QffkHhDIJrIJp7xwf6F34B4JFd9p6ijWhTuOPTfXypkbqLvIWr; wap_sid2=CN78h/IFElx1dGd1TWRYenJvV3ZnOHRwSVFxVnNxejVSVDhuS0ktUG1MeEhnZzlONzFaWnh5TnBoX2R0NGJXcE5IWnU3TXdUZ0RUNDlOclBjTFFFNklya09iZG9vcVlEQUFBfjCb9rjRBTgNQAE=";


		cookie="wxtokenkey=31b2acc7ab7400a29d5fca6338b3de93cf77ba35e85a18218e959398ddd9df72; wxuin=1581383262; devicetype=Windows10; version=62060038; lang=zh_CN; pass_ticket=RYThfzZ0g/zBEWKdBnKn+tPY6AoNfYt42sxAbN3/jtMlFYk6ktYmQt//Zd1pDu8r; wap_sid2=CN78h/IFEnBVeDR1a196R19QMzdPWW9WcVJ0ei14MDJzajRzQVpsQzQtUm03WFZDWnN0ZWhtV1J3X1hqRVQ1ZkVsRjVDcEp3a3E3SFBKY0ZrWDNJV2xFNEYycW5uUHlUdzh0SEM2MnFYMm9WTFVoc29GR21Bd0FBMLqWvtEFOA1AAQ==";

		cookie="wxtokenkey=fa1599f3b1692bf0915848d34b14045ce407c9384438ae17271dd9dd33d05ca2; wxuin=1581383262; devicetype=Windows10; version=62060038; lang=zh_CN; pass_ticket=RYThfzZ0g/zBEWKdBnKn+tPY6AoNfYt42sxAbN3/jtMlFYk6ktYmQt//Zd1pDu8r; wap_sid2=CN78h/IFEnBVeDR1a196R19QMzdPWW9WcVJ0ei16Y3FiWmRMb2Z6QnRBM0dpMFYwME1nSE1uek1XM2hjalJHOS1ER2tMTm1XLVNtQzlldVJFZjMyUnpMQkNXVXVTZWtHcEpfOGlVYUR0RGtORnZid3E1Nm1Bd0FBMMCWvtEFOA1AAQ==";



		String ua="Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat QBCore/3.43.691.400 QQBrowser/9.0.2524.400";



		htmlInfo.setUa(ua);
		htmlInfo.setAccept("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		htmlInfo.setAcceptEncoding("gzip, deflate");
		htmlInfo.setCookie(cookie);
		SimpleHttp simpleHttp = new SimpleHttp();
		simpleHttp.simpleGet(htmlInfo);
		System.out.println(htmlInfo.getContent());
		System.out.println(htmlInfo.getRealUrl());

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
