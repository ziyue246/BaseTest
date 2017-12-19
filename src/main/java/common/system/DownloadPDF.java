package common.system;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import common.http.HtmlInfo;





public class DownloadPDF {
	
	public static void main(String[] args) throws Exception {
		HtmlInfo html = new HtmlInfo();
		String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2824.2 Safari/537.36";
		String cookie = "kc_cnki_net_uid=fb5098db-c0b1-99dd-d7e4-fc15d2724431; Ecp_ClientId=2161115085400693876; LID=WEEvREcwSlJHSldRa1FhcEE0L01SRzBvU3dtTnJkU1BJbzlyVGpzK0FNUT0=$9A4hF_YAuvQ5obgVAqNKPCYcEjKensW4ggI8Fm4gTkoUKaID8j8gFw!!; ASPSESSIONIDSASSQRCR=AALGCAFCLOIMEBHDAHINCDNO; SID=088001";
		html.setUa(ua);
		//html.setCookie(cookie);
		
		String startUrl = "http://www.aclweb.org/anthology/P/P17/";
		String path = "C:/Users/Administrator/Desktop/1ttt.html";
		String content = FileOperation.read(path);
		
		String []lines = content.split("\n");
		for(String line:lines){
			line = line.trim();
			if(!(line.contains("pdf")))continue;
			if(!line.contains("last"))continue;
			if(!line.contains("first"))continue;
			String pdf = line.split("href=\"")[1].split(".pdf")[0]+".pdf";
			String title = line.split("<i>")[1].split("</i>")[0];
			String pdfUrl = startUrl+pdf;
			System.out.println(pdfUrl+"\t"+title);
			String saveFile = "file/"+title+".pdf";
			html.setReferUrl(startUrl);
			html.setOrignUrl(pdfUrl);
			downloadPDF(html, saveFile);
			SystemCommon.printLog(title+"  �������");
			Thread.sleep(1000*5);
		}
		System.out.println("Over");
	}
	
	public static void downloadPDF(HtmlInfo html ,String saveFile) {
		
		HttpClient client = new HttpClient();
		HttpMethod getMethod = new GetMethod(html.getOrignUrl());

		getMethod.setRequestHeader("User-Agent", html.getUa());
		getMethod.setRequestHeader("Connection", "keep-alive");
		if(html.getCookie()!=null){
			getMethod.setRequestHeader("Cookie", html.getCookie());
		}
		if(html.getReferUrl()!=null)
			getMethod.setRequestHeader("Referer",html.getReferUrl());
		
		try {
			client.executeMethod(getMethod);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] buffer = new byte[1024];
		byte[] byteArray = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int count = -1;
		InputStream responseBodyAsStream = null;
		
		if (getMethod != null && getMethod.getStatusCode() == 200) {
			try {
				responseBodyAsStream = getMethod.getResponseBodyAsStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//写入页面真实链接的url
			try {
				while ((count = responseBodyAsStream.read(buffer, 0, buffer.length)) > -1) {
					baos.write(buffer, 0, count);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buffer = null;
			try {
				baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				responseBodyAsStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getMethod.releaseConnection();
			byteArray = baos.toByteArray();
		}
		try {
			FileOutputStream fos = new FileOutputStream(saveFile);
			try {
				fos.write(byteArray);
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
