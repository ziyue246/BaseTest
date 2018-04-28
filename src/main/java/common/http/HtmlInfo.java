package common.http;

import java.util.Map;

/**
 *
 */
public class HtmlInfo {

	private String url;
	private String site;
	private String orignUrl;
	private String realUrl;
	private String encode;
	private String content;
	private boolean agent;
	private String crawlerType;
	private boolean addHead;
	private String referUrl;
	private String cookie;
	private boolean fixEncode;
	private String fileType = ".htm";
	private String ua;// user agent
	private String acceptEncoding;
	private int siteId;
	private String responseCookie;
	private String searchKeyword;
	private String host;
	private String proxy;
	private String contentType;
	private int responseStatus;
	private Map<String,String> headMap;
	
	
	
	public Map<String, String> getHeadMap() {
		return headMap;
	}

	public void setHeadMap(Map<String, String> headMap) {
		this.headMap = headMap;
	}

	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}

	public int getResponseStatus() {
		return responseStatus;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public String getProxy() {
		return proxy;
	}

	public void setProxy(String proxy) {
		this.proxy = proxy;
	}

	private String accept;

	public String getAccept() {
		return accept;
	}
	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getResponseCookie() {
		return responseCookie;
	}

	public void setResponseCookie(String responseCookie) {
		this.responseCookie = responseCookie;
	}

	public String getCrawlerType() {
		return crawlerType;
	}

	public void setCrawlerType(String crawlerType) {
		this.crawlerType = crawlerType;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public String getAcceptEncoding() {
		return acceptEncoding;
	}

	public void setAcceptEncoding(String acceptEncoding) {
		this.acceptEncoding = acceptEncoding;
	}

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getOrignUrl() {
		return orignUrl;
	}

	public void setOrignUrl(String orignUrl) {
		this.orignUrl = orignUrl;
	}

	public String getRealUrl() {
		return realUrl;
	}

	public void setRealUrl(String realUrl) {
		this.realUrl = realUrl;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean getAgent() {
		return agent;
	}

	public void setAgent(boolean agent) {
		this.agent = agent;
	}

	public String getType() {
		return crawlerType;
	}

	public void setType(String type) {
		this.crawlerType = type;
	}

	public boolean getAddHead() {
		return addHead;
	}

	public void setAddHead(boolean addHead) {
		this.addHead = addHead;
	}

	public String getReferUrl() {
		return referUrl;
	}

	public void setReferUrl(String referUrl) {
		this.referUrl = referUrl;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public boolean getFixEncode() {
		return fixEncode;
	}

	public void setFixEncode(boolean changeEncode) {
		this.fixEncode = changeEncode;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

}
