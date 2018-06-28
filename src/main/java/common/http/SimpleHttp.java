package common.http;



import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLHandshakeException;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author rzy 2016/12/19
 */
public class SimpleHttp {
	private static final Logger logger = Logger.getLogger(SimpleHttp.class);

	private HashMap<String, String> cookieMap = new HashMap<String, String>();

	public HashMap<String, String> getCookieMap() {
		return cookieMap;
	}

	public void setCookieMap(HashMap<String, String> cookieMap) {
		this.cookieMap = cookieMap;
	}

	RequestConfig defaultRequestConfig = RequestConfig.custom()
			.setSocketTimeout(1000 * 60 )
			.setConnectTimeout(1000 * 60 )
			.setConnectionRequestTimeout(1000 * 60 )
			.setStaleConnectionCheckEnabled(true)
			.setRedirectsEnabled(false)
			.build();

	public String updateCookieMapByHeader(Header[] heads) {
		if(heads!=null)
		{
			for (Header head : heads) {
				String lineCookie = head.getValue().split(";")[0];
				String cookieKey = lineCookie.split("=")[0].trim();
				String cookieValue = lineCookie.replace(cookieKey, "").trim();
				if (!cookieValue.equals("=")) {
					cookieMap.put(cookieKey, cookieValue);
				}
			}
		}
		String cookie = "";
		if(cookieMap!=null)
		{
			for (String key : cookieMap.keySet()) {
				if (cookie == "") {
					cookie = key + cookieMap.get(key);
				} else {
					cookie += "; " + key + cookieMap.get(key);
				}
			}
		}
		return cookie;
	}

	public void updateCookieMapByString(String cookie) {
		if (cookie == null || cookie.length() == 0) return;
		String[] cookies = cookie.split(";");
		for (String c : cookies) {
			if (c.contains("=") && c.split("=").length == 2) {
				String key = c.split("=")[1];
				String value = c.replace(c, key);
				cookieMap.put(key, value);
			}
		}
	}

	public void getCookieByWebClient(common.http.HtmlInfo html) {
		WebClient webclient = new WebClient(BrowserVersion.CHROME);
		webclient.getCookieManager().setCookiesEnabled(true);
		webclient.getOptions().setTimeout(10000);

		webclient.getOptions().setCssEnabled(false);
		webclient.getOptions().setJavaScriptEnabled(false);
		if (false) {
			ProxyConfig proxyConfig = webclient.getOptions().getProxyConfig();
			String proxy = "127.0.0.1:8580";
			// proxy = "47.91.154.245:8080";
			// proxy = "194.58.103.23:8118";
			String host = proxy.split(":")[0];
			int port = Integer.parseInt(proxy.split(":")[1]);
			proxyConfig.setProxyHost(host);
			proxyConfig.setProxyPort(port);
			//218.247.161.37  80
		}
		try {
			String url = html.getOrignUrl();
			HtmlPage htmlPage;
			htmlPage = webclient.getPage(url);
			Set<Cookie> cookies = webclient.getCookieManager().getCookies();
			for (Cookie cookie : cookies) {
				//logger.trace("cookie:"+cookie.getName()+"="+cookie.getValue());
				cookieMap.put(cookie.getName(), "=" + cookie.getValue());
			}
			html.setCookie(updateCookieMapByHeader(null));
			html.setContent(htmlPage.asXml());
		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getCookieByPython(common.http.HtmlInfo html) {

		String url = "http://www.cnblogs.com/dabiao/archive/2010/03/07/1680096.html";
		url = "http://httpbin.org/get?show_env=1";
		url = "http://www.realtor.ca";

		url = html.getOrignUrl();
		url = "http://www.domain.com.au/";
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
		//userAgent = html.getUa();
		String pythonPath = "D:\\Users\\ziyue\\PycharmProjects\\hello\\PyHttp.py";
		userAgent = userAgent.replace(" ", "##");
		Process proc = null;
		try {
			proc = Runtime.getRuntime().exec("python " + pythonPath + " " + url + " " + userAgent);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		try {
//			proc.waitFor();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		try {
			InputStream fis = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer();
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				sb.append(line + "\n");
			}
			String[] messages = sb.toString().split("&&");
			;
			for (String message : messages) {
				if (message.startsWith("page_source##")) {
					String content = message.replace("page_source##", "");
					//logger.trace(content);
					html.setContent(content);
				} else if (message.startsWith("cookie##")) {
					String cookie = message.replace("cookie##", "");
					//logger.trace(cookie);
					html.setCookie(cookie);
					updateCookieMapByString(cookie);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getCookie(common.http.HtmlInfo html) {
		HttpClient client = HttpClients.createDefault();
		// html.setOrignUrl(loginUrl);

		// html.setOrignUrl("http://epub.cnki.net/kns/brief/result.aspx?dbprefix=scdb&action=scdbsearch&db_opt=SCDB");
		HttpGet get = new HttpGet(html.getOrignUrl());

		if (html.getProxy() != null) {
			RequestConfig config = setProxy(client, html.getProxy());
			get.setConfig(config);
		}
		get.setHeader("User-Agent", html.getUa());
		get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		get.setHeader("Accept-Encoding", "gzip, deflate, sdch");
		get.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		get.setHeader("Host", "acad.cnki.net");
		get.setHeader("Proxy-Connection", "keep-alive");
		get.setHeader("Upgrade-Insecure-Requests", "1");

		HttpResponse response = null;
		try {
			response = client.execute(get);
			html.setResponseStatus(response.getStatusLine().getStatusCode());
			logger.trace("获取Cookie get status:" + response.getStatusLine());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (response.containsHeader("Set-Cookie")) {
			html.setCookie(updateCookieMapByHeader(response.getHeaders("Set-Cookie")));
		}
		if (response.containsHeader("Location")) {
			try {
				String locationUrl = response.getFirstHeader("Location").getValue();
				html.setOrignUrl(locationUrl);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private RequestConfig setProxy(HttpClient client, String proxyString) {
		String[] strings = proxyString.split(":");
		if (strings.length != 2) return null;
		String proxyHost = strings[0].trim();
		int proxyPort = Integer.parseInt(strings[1].trim());
		HttpHost proxy = new HttpHost(proxyHost, proxyPort);
		//client.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
		RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
		return config;
	}

	public void simpleGetbyHtmlUnit(common.http.HtmlInfo html) {

		html.setContent(null);
		html.setResponseStatus(-1);
		WebClient webclient = new WebClient(BrowserVersion.CHROME);
		webclient.getCookieManager().setCookiesEnabled(true);
		webclient.getOptions().setTimeout(10000);
		HtmlPage htmlPage;
		webclient.getOptions().setCssEnabled(true);
		webclient.getOptions().setJavaScriptEnabled(true);
		

		String host=null;
		int port=0;
		if (html.getProxy() != null) {
			ProxyConfig proxyConfig = webclient.getOptions().getProxyConfig();
			String proxy = html.getProxy().trim();
			host = proxy.split(":")[0];
			port = Integer.parseInt(proxy.split(":")[1]);
			proxyConfig.setProxyHost(host);
			proxyConfig.setProxyPort(port);
		}
		try {
			String url = html.getOrignUrl();
			WebRequest request = new WebRequest(new URL(url));
			
			if (html.getAccept() != null) {
				request.setAdditionalHeader("Accept", html.getAccept());
			}
			if (html.getUa() != null) {
				request.setAdditionalHeader("User-Agent", html.getUa());
			}
			request.setAdditionalHeader("Connection", "keep-alive");
			if (html.getHost() != null) {
				request.setAdditionalHeader("Host", html.getHost());
			}
			if (html.getCookie() != null) {
				request.setAdditionalHeader("Cookie", html.getCookie());
			}
			if(html.getProxy()!=null&&host!=null) {
				request.setProxyHost(host);
				request.setProxyPort(port);
			}
			htmlPage = webclient.getPage(request);
			html.setResponseStatus(htmlPage.getWebResponse().getStatusCode());
			html.setContent(htmlPage.asXml());

			Set<Cookie> cookies = webclient.getCookieManager().getCookies();
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), "=" + cookie.getValue());
			}
			html.setCookie(updateCookieMapByHeader(null));


		} catch (FailingHttpStatusCodeException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void simpleGet(HtmlInfo html) {
		//可以关闭的http请求客户端对象
		HttpClient client = null;
		//创建httpget请求对象
		HttpGet get = null;
		try {
			//清空上次请求
			html.setContent(null);
			//将响应码设置为-1，如果整个过程能够顺利执行，会被设置为200,302等有效响应码
			html.setResponseStatus(-1);

			//http请求客户端设置为默认类型
			client = HttpClients.custom()
					.setDefaultRequestConfig(defaultRequestConfig)
					.build();

			get = new HttpGet(html.getOrignUrl().startsWith("http") ? html.getOrignUrl() : "https://" + html.getOrignUrl());
			if (html.getProxy() != null) {
				//设置get请求代理
				logger.trace("proxy : "+html.getProxy());
				RequestConfig config = setProxy(client, html.getProxy());
				get.setConfig(config);
			}
			html.setContent(null);
			if (html.getAccept() != null) {
				//设置请求头 accept
				get.setHeader("Accept", html.getAccept());
			}
			if (html.getUa() != null) {
				//设置请求头 user agent
				get.setHeader("User-Agent", html.getUa());
			}
			//设置请求头 connection
			get.setHeader("Connection", "keep-alive");
			if (html.getHost() != null) {
				//设置请求头 host
				get.setHeader("Host", html.getHost());
			}
			if (html.getCookie() != null) {
				//设置请求头 cookie
				get.setHeader("Cookie", html.getCookie());
			}
			if (html.getReferUrl() != null) {
				//设置请求头 referer
				get.setHeader("Referer", html.getReferUrl());
			}
			if (html.getAcceptEncoding() != null) {
				//设置请求头 referer  Upgrade-Insecure-Requests: 1
				get.setHeader("Accept-Encoding", html.getAcceptEncoding());
			}

			//:
			get.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-us;q=0.6,en;q=0.5;q=0.4");
			if(html.getHeadMap()!=null&&html.getHeadMap().size()>0){
				Set<String> keys = html.getHeadMap().keySet();
				for(String key:keys){
					get.setHeader(key,html.getHeadMap().get(key));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn(e.getMessage());
		}
		// 创建请求响应的可关闭对象（手动关闭）
		HttpResponse response = null;
		logger.trace("response = client.execute(get);  before");
		try {
			logger.trace(get.toString());
			//执行get请求，并返回响应对象
			response = client.execute(get);

			logger.trace("response = client.execute(get);  after");

			if (response == null)
				logger.trace("client.execute(get)出错！：response=null");

			//记录响应码 如200，302 400
			html.setResponseStatus(response.getStatusLine().getStatusCode());

			logger.trace("Get statusList:\t" + response.getStatusLine());

			if (response.containsHeader("Set-Cookie")) {
				logger.trace("get update cookie");
				try {
					//更新cookie
					updateCookieMapByString(html.getCookie());
					//记录cookie
					html.setCookie(updateCookieMapByHeader(response.getHeaders("Set-Cookie")));
				} catch (Exception e) {
					logger.trace("get update cookie 出错！", e);
				}

				logger.trace("get update cookie END");
			}
			if (response.containsHeader("Location")) {
				//如果响应头中包含了location，一般为302跳转，跳转链接提取方式如下
				String realUrl = response.getFirstHeader("Location").getValue();
				//记录302跳转的链接
				html.setRealUrl(realUrl);
			}
			logger.trace("before if (response != null && response.getStatusLine().getStatusCode() == 200) {");
			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				logger.trace(" if (response != null && response.getStatusLine().getStatusCode() == 200) {");
				//获取http get请求的实体对象
				HttpEntity httpEntity = response.getEntity();
				logger.trace(" HttpEntity httpEntity = response.getEntity();");
				String result = null;
				try {
					//获取http 请求的内容，并指定编码格式
					result = EntityUtils.toString(httpEntity, html.getEncode());
				} catch (Exception e) {
					logger.warn(e);
				}
				response.getEntity().getContent().close();
				//记录http get请求获得的内容
				html.setContent(result);
			}
			logger.trace("after if (response != null && response.getStatusLine().getStatusCode() == 200) {");
		} catch(HttpHostConnectException e)	{
			logger.warn("get : " + e.getMessage() + "  \t  代理问题，连接超时:"+html.getProxy());
			logger.warn(e);
		}catch(ClientProtocolException e)	{
			logger.warn("get : " + e.getMessage() + "  \t  代理问题，连接超时:"+html.getProxy());
			logger.warn(e);
		}

		catch(Exception e)		{
			logger.trace("client.execute(get)出错："+html.getProxy(), e);
		}
		logger.trace("simpleGet() END!");
	}

	public void simpleGetClose(common.http.HtmlInfo html) {
		//可以关闭的http请求客户端对象
		CloseableHttpClient client = null;
		//创建httpget请求对象
		HttpGet get = null;
		try {
			//清空上次请求
			html.setContent(null);
			//将响应码设置为-1，如果整个过程能够顺利执行，会被设置为200,302等有效响应码
			html.setResponseStatus(-1);

			//http请求客户端设置为默认类型
			client = HttpClients.custom()
					.setDefaultRequestConfig(defaultRequestConfig)
					.build();

			logger.trace("get url:" + html.getOrignUrl());
			logger.trace("get ua:" + html.getUa());
			get = new HttpGet(html.getOrignUrl().startsWith("http") ? html.getOrignUrl() : "https://" + html.getOrignUrl());
			if (html.getOrignUrl().contains("baidu")) {
				RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();//设置请求和传输超时时间
				get.setConfig(requestConfig);
			}
			if (html.getProxy() != null) {
				//设置get请求代理
				RequestConfig config = setProxy(client, html.getProxy());
				get.setConfig(config);
			}
			html.setContent(null);
			if (html.getAccept() != null) {
				//设置请求头 accept
				get.setHeader("Accept", html.getAccept());
			}
			if (html.getUa() != null) {
				//设置请求头 user agent
				get.setHeader("User-Agent", html.getUa());
			}
			//设置请求头 connection
			get.setHeader("Connection", "keep-alive");
			if (html.getHost() != null) {
				//设置请求头 host
				get.setHeader("Host", html.getHost());
			}
			if (html.getCookie() != null) {
				//设置请求头 cookie
				get.setHeader("Cookie", html.getCookie());
			}
			if (html.getReferUrl() != null) {
				//设置请求头 referer
				get.setHeader("Referer", html.getReferUrl());
			}
		} catch (Exception e) {
			logger.trace(e);
		}
		// 创建请求响应的可关闭对象（手动关闭）
		CloseableHttpResponse response = null;
		logger.trace("response = client.execute(get);  before");
		try {
			logger.trace(get.toString());
			//执行get请求，并返回响应对象
			response = client.execute(get);

			logger.trace("response = client.execute(get);  after");

			if (response == null)
				logger.trace("client.execute(get)出错！：response=null");

			//记录响应码 如200，302 400
			html.setResponseStatus(response.getStatusLine().getStatusCode());

			logger.trace("Get statusList:\t" + response.getStatusLine());

			if (response.containsHeader("Set-Cookie")) {
				logger.trace("get update cookie");
				try {
					//更新cookie
					updateCookieMapByString(html.getCookie());
					//记录cookie
					html.setCookie(updateCookieMapByHeader(response.getHeaders("Set-Cookie")));
				} catch (Exception e) {
					logger.trace("get update cookie 出错！", e);
				}

				logger.trace("get update cookie END");
			}
			if (response.containsHeader("Location")) {
				//如果响应头中包含了location，一般为302跳转，跳转链接提取方式如下
				String realUrl = response.getFirstHeader("Location").getValue();
				//记录302跳转的链接
				html.setRealUrl(realUrl);
			}
			logger.trace("before if (response != null && response.getStatusLine().getStatusCode() == 200) {");
			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				logger.trace(" if (response != null && response.getStatusLine().getStatusCode() == 200) {");
				//获取http get请求的实体对象
				HttpEntity httpEntity = response.getEntity();
				logger.trace(" HttpEntity httpEntity = response.getEntity();");
				String result = null;
				try {
					//获取http 请求的内容，并指定编码格式
					result = EntityUtils.toString(httpEntity, html.getEncode());
				} catch (Exception e) {
					logger.trace(e);
				}
				response.getEntity().getContent().close();
				//记录http get请求获得的内容
				html.setContent(result);
			}
			logger.trace("after if (response != null && response.getStatusLine().getStatusCode() == 200) {");
		} catch(HttpHostConnectException|ClientProtocolException e)	{
			logger.trace("get : " + e.getMessage() + "  \t  代理问题，连接超时:"+html.getProxy());
			logger.trace(e);
		} catch(Exception e)		{
			logger.trace("client.execute(get)出错："+html.getProxy(), e);
		}finally {
			try {
				if (response != null)
					response.close();
				logger.trace("before client.close();");
				client.close();
				logger.trace("after client.close();");
			}catch (Exception e){
				logger.trace("response . client close出错：", e);
			}
		}
		logger.trace("simpleGet() END!");
	}


	public HttpURLConnection getHttpURLConnection(common.http.HtmlInfo html)
			{
		String pathUrl = html.getOrignUrl();
		// 建立连接
		URL url = null;
		try {
			url = new URL(pathUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		try {
			if (html.getProxy() != null) {
				//添加代理
				String proxyString = html.getProxy();
				if (proxyString.split(":").length == 2) {
					String proxyHost = proxyString.split(":")[0].trim();
					int proxyPort = Integer.parseInt(proxyString.split(":")[1].trim());
					Proxy proxy = new Proxy(Proxy.Type.HTTP,
							new InetSocketAddress(proxyHost, proxyPort));
					//获取http代理对象

					return  (HttpURLConnection) url.openConnection(proxy);

				}else{
					logger.trace("proxy 出错 ："+proxyString);
				}
			} else {
				//获取http代理对象
				return  (HttpURLConnection) url.openConnection();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
    public void urlConnectionGet(HtmlInfo html) {

		html.setResponseStatus(-1);
        html.setContent(null);
        try {
			logger.trace("httpUrlConnectiondown get url:" + html.getOrignUrl());
			HttpURLConnection httpConn = getHttpURLConnection(html);
			if(httpConn==null){
				logger.trace("httpConn is null");
				return;
			}
            // //设置连接属性
            httpConn.setDoOutput(true);// 使用 URL 连接进行输出
            httpConn.setDoInput(true);// 使用 URL 连接进行输入
            httpConn.setUseCaches(true);// 忽略缓存
            httpConn.setRequestMethod("GET");// 设置URL请求方法
			httpConn.setConnectTimeout(5 * 1000);
			if(html.getUa()!=null) {
				httpConn.setRequestProperty("User-Agent", html.getUa());
			}
            httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
			if(html.getContentType()!=null) {
				httpConn.setRequestProperty("Content-Type", html.getContentType());
			}
			if(html.getAccept()!=null) {
				httpConn.setRequestProperty("Accept", html.getAccept());
			}
			if(html.getEncode()!=null) {
				httpConn.setRequestProperty("Charset", html.getEncode());
			}
			if(html.getAcceptEncoding()!=null) {
				httpConn.setRequestProperty("Accept-Encoding", html.getAcceptEncoding());
			}
            
            
            if (html.getCookie() != null) {
				httpConn.setRequestProperty("Cookie", html.getCookie());
			}
            if (html.getReferUrl() != null) {
				httpConn.setRequestProperty("Referer", html.getReferUrl());
			}

            int responseCode = httpConn.getResponseCode();
			html.setResponseStatus(responseCode);
            if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				InputStream inStream = httpConn.getInputStream();
				while( (len=inStream.read(buffer)) != -1 ){
					outStream.write(buffer, 0, len);
				}
				html.setContent(new String(outStream.toByteArray(), html.getEncode()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void urlConnectionPost(common.http.HtmlInfo html, ArrayList<String> params) {
    	//清空上次请求的内容
        html.setContent(null);
        //清空上次的请求状态吗
		html.setResponseStatus(-1);
		//创建http链接对象
		HttpURLConnection httpConn = getHttpURLConnection(html);
		if(httpConn==null){
			logger.trace("httpConn is null");
			return;
		}
        try {
            String pathUrl = html.getOrignUrl();
            logger.trace("httpUrlConnectionPost: url=" + pathUrl);
            logger.trace("url.openConnection();  before");

            
            logger.trace("url.openConnection();  after");
            if (httpConn == null) {
                logger.trace("httpConn is null proxy:" + html.getProxy() + "   url" + html.getOrignUrl());
                html.setResponseStatus(-1);
                return ;
            }
            //设置超时
            httpConn.setConnectTimeout(60000);
            httpConn.setReadTimeout(60000);
            
            httpConn.setDoOutput(true);// 使用 URL 连接进行输出
            httpConn.setDoInput(true);// 使用 URL 连接进行输入
            httpConn.setUseCaches(true);// 忽略缓存
            httpConn.setRequestMethod("POST");// 设置URL请求方法,设置为post请求方式


			//设置请求头的相关信息
            if (html.getUa() != null)
                httpConn.setRequestProperty("User-Agent", html.getUa());
            httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            if (html.getAccept() != null)
                httpConn.setRequestProperty("Accept", html.getAccept());
            if (html.getContentType() != null)
                httpConn.setRequestProperty("Content-Type", html.getContentType());
            if (html.getCookie() != null)
                httpConn.setRequestProperty("Cookie", html.getCookie());
            if (html.getReferUrl() != null)
                httpConn.setRequestProperty("Referer", html.getReferUrl());

            //设置http post 的from    也就是请求所携带的表单
            StringBuffer form = new StringBuffer();
            for (String line : params) {
                String[] items = line.split("##");
                if (form.length() > 0) {
                    form.append("&");
                }
                if (items.length == 1) {
                    form.append(items[0]).append("=");
                } else if (items.length == 2) {
                    form.append(items[0]).append("=").
                            append(URLEncoder.encode(items[1], html.getEncode()));
                }
            }
            
            byte[] bypes = form != null ? form.toString().getBytes() : "".getBytes();
            //请求头长度
            httpConn.setRequestProperty("Content-length", "" + bypes.length);

            //获取httpconn的输出流，也就是向服务器发送数据的流（写数据）
            OutputStream outputStream = httpConn.getOutputStream();
            outputStream.write(bypes);
            //发送(写)完关闭
            outputStream.close();
            
            // 获得响应状态
            int responseCode = httpConn.getResponseCode();
            html.setResponseStatus(responseCode);
            
            logger.trace("Post statusCode:\t" + responseCode);
            if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
                // 当正确响应时处理数据
				// 获取响应得到的数据内容，并记录
                StringBuffer sb = new StringBuffer();
                String readLine;
                BufferedReader responseReader;
                // 处理响应流，必须与服务器响应流输出的编码一致
                responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), html.getEncode()));
                while ((readLine = responseReader.readLine()) != null) {
                    
                    sb.append(readLine).append("\n");
                }
                //关闭响应流
                responseReader.close();
                //记录内容
                html.setContent(sb.toString());
            }
        } catch (SocketTimeoutException | ConnectException e) {
            logger.trace("httpUrlConnectionPost  SocketTimeoutException|ConnectException : " + e.getMessage() + "  \t  代理问题，连接超时");
        } catch (Exception e) {
            logger.trace("httpUrlConnectionPost Exception: " + e.getMessage() + "");
            logger.trace("proxy:" + html.getProxy() + "   url:" + html.getOrignUrl());
            logger.trace(params);
        } finally {
			//关闭响链接
            httpConn.disconnect();
        }
    }
    
    /**
     * @param html
     * @param params
     * @return
     */
    public String simplePost(HtmlInfo html, ArrayList<String> params) {

		//清空上次请求的内容
		html.setContent(null);
		//清空上次的请求状态吗
		html.setResponseStatus(-1);

		//创建自动关闭的http客户端
        HttpClient client = HttpClients.custom()
                        .setDefaultRequestConfig(defaultRequestConfig)
                        .build();
        logger.trace("post url:" + html.getOrignUrl());
        HttpPost post = new HttpPost(html.getOrignUrl());
        if (html.getProxy() != null) {
        	//设置代理服务器
            RequestConfig config = setProxy(client, html.getProxy());
            post.setConfig(config);
        }
        post.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);
        if (html.getAccept() != null) {
            post.setHeader("Accept", html.getAccept());
        }
        if (html.getUa() != null) {
            post.setHeader("User-Agent", html.getUa());
        }
        post.setHeader("Connection", "keep-alive");
        if (html.getHost() != null) {
            post.setHeader("Host", html.getHost());
        }
        if (html.getContentType() != null) {
            post.setHeader("Content-Type", html.getContentType());
        }
        if (html.getCookie() != null) {
            post.setHeader("Cookie", html.getCookie());
        }
        if (html.getReferUrl() != null) {
            post.setHeader("Referer", html.getReferUrl());
        }
        //设置post 的form  也就是请求所携带的表单
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (String param : params) {
            if (param.split("##").length == 1) {
                String key = param.split("##")[0];
                list.add(new BasicNameValuePair(key, ""));
            } else if (param.split("##").length == 2) {
                String key = param.split("##")[0];
                String value = param.split("##")[1];
                list.add(new BasicNameValuePair(key, value));
            }
        }
        try {
			//设置post 的form  也就是请求所携带的表单 ，写入表单
            post.setEntity(new UrlEncodedFormEntity(list));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        HttpResponse response = null;
        try {
            
            logger.trace("response = client.execute(post);  before");
            logger.trace(post);
            response = client.execute(post);
            
            logger.trace("response = client.execute(post);  after");
            if (response == null)
                logger.trace("client.execute(post)出错！：response=null");
            html.setResponseStatus(response.getStatusLine().getStatusCode());
            logger.info("Post statusList:\t" + response.getStatusLine());
        } catch (ClientProtocolException | NoHttpResponseException | SSLHandshakeException | SocketTimeoutException e) {
            logger.error("simplePost : " + e.getMessage() + "  \t  代理问题，连接超时");
            return null;
        } catch (HttpHostConnectException e) {
            logger.error("simplePost : " + e.getMessage() + "  \t  代理问题，连接超时");
            return null;
        } catch (Exception e1) {
            logger.error("client.execute(post)出错：", e1);
        } finally {
            post.abort();
        }
        String realUrl = null;
        try {
            if (response.containsHeader("Set-Cookie")) {
                logger.trace("get update cookie");
                try {
                	//更新cookie ，记录cookie
                    updateCookieMapByString(html.getCookie());
                    html.setCookie(updateCookieMapByHeader(response.getHeaders("Set-Cookie")));
                } catch (Exception e) {
                    logger.trace("simplePost() get update cookie 出错！",e);
                }
            }
            if (response.containsHeader("Location")) {
            	//获取302的跳转地址
                realUrl = response.getFirstHeader("Location").getValue();
                html.setRealUrl(realUrl);
                logger.trace("Location 302 url:" + realUrl);
                return null;
            }
            
        } catch (Exception e) {
            logger.trace(e);
        }
        //一般情况post不返回内容，但是有时候也返回内容，
		//这段代码一般在返回内容时会获取到内容，获取的内容最大长度在64k，
		//如果不返回内容时，一般会出错，
		//所以这段代码挺尴尬的，写了一个if (true)
        if (true) {
            try {
                HttpEntity httpEntity = response.getEntity();
                if (httpEntity != null) {
                	//获取http post的请求到的内容
                    String content = EntityUtils.toString(httpEntity, html.getEncode());
                    html.setContent(content);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return realUrl;
    }
    
}
