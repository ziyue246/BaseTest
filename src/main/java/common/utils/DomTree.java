package common.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.html.dom.HTMLDocumentImpl;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import org.apache.xpath.XPathAPI;
import org.cyberneko.html.parsers.DOMFragmentParser;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;


import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomTree {
	
	private static final Logger logger = Logger.getLogger(DomTree.class);

	public static void printNode(String mark, Node node) {
		String rNodeName = node.getNodeName();// ��ǰ����Ԫ������
		 if(node.getNodeType()==Node.ELEMENT_NODE){ //Ϊ�ڵ����ͣ�����ڵ�����
		 System.out.print("<"+rNodeName+">");
		 }
		if (node.getNodeType() == Node.TEXT_NODE) { // �ı����ͣ�����Ėc
			String s = ((Text) node).getWholeText();
			//s = s.replace("\\s*", "");
			System.out.println(s);
		}

		NodeList allNodes = node.getChildNodes();// ��ȡ��Ҫ�����ڵ���ӽڞ�
		int size = allNodes.getLength();
		if (size > 0) {
			for (int j = 0; j < size; j++) {
				Node childNode = allNodes.item(j);
				printNode(mark, childNode);
				if (childNode.getNodeType() == Node.ELEMENT_NODE) {
					// ÿ������د����ǩ�����������ǩ
					String s = childNode.getTextContent();
					//s = s.replace("\\s*", "");
					System.out.println("</" + childNode.getNodeName() + ">" + s);
				}
			}
		}
	}

	public static String getHtmlContent(String url) throws Exception {

		GetMethod getMethod = new GetMethod(url);
		try {
			HttpClient client = new HttpClient();
			int statusCode = client.executeMethod(getMethod);
			System.out.println("statusCode:" + statusCode);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed:" + getMethod.getStatusLine());
			}

			InputStream is = getMethod.getResponseBodyAsStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));

			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				sb.append(line).append("\r\n");
			}
			reader.close();
			return sb.toString();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static DocumentFragment getNode(String content, String charset) {
		if (content == null)
			return null;
		charset = charset == null ? "utf-8" : charset;
		byte[] byt = null;
		try {
			byt = content.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "").getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			logger.warn(e.getMessage());
			byt = null;
			return null;
		}
		InputSource source = new InputSource(new ByteArrayInputStream(byt));
		source.setEncoding(charset);
		DOMFragmentParser parser = new DOMFragmentParser();
		DocumentFragment domtree = new HTMLDocumentImpl().createDocumentFragment();

		try {
			parser.setFeature("http://cyberneko.org/html/features/balance-tags", true);
		} catch (SAXNotRecognizedException | SAXNotSupportedException e) {
			e.printStackTrace();
		}
		// �Ƿ����<script>Ԫ���е�<!-- -->��ע�ͷ�
		try {
			parser.setFeature("http://cyberneko.org/html/features/scanner/script/strip-comment-delims", true);
		} catch (SAXNotRecognizedException | SAXNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			parser.parse(source, domtree);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return domtree;

	}
	
	public static NodeList commonList(String xpath, Node domtree) {
		if (xpath == null || xpath.equals("") || xpath.startsWith("${"))
			return null;
		NodeList list = null;
		try {
			list = XPathAPI.selectNodeList(domtree, xpath);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return list;
	}


	public static String trimInnerSpaceStr(String str) {
		str = str.trim();
		while (str.startsWith(" ")) {
			str = str.substring(1, str.length()).trim();
		}
		while (str.endsWith(" ")) {
			str = str.substring(0, str.length() - 1).trim();
		}
		return str;
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
	//LI##1=2
	public static List<Node> parserXpathGetListNode(Node node,String xpath) {
		String name = null;
		String attName = null;
		String attValue = null;
		if (xpath.contains("##")) {
			name = xpath.split("##")[0];
			String att = xpath.split("##")[1];
			if (att.contains("=")) {
				attName = att.split("=")[0];
				attValue = att.split("=")[1];

			} else {
				attName = att;
			}
		} else {
			name = xpath;
		}
		List<Node> result = new ArrayList<Node>();
		parserXpath(node,name,attName,attValue,result);
		return result;
	}
	public static Node parserXpathGetFirstNode(Node node,String xpath) {
		String name = null;
		String attName = null;
		String attValue = null;
		if (xpath.contains("##")) {

			name = xpath.split("##")[0];
			String att = xpath.split("##")[1];
			if (att.contains("=")) {
				attName = att.split("=")[0];
				attValue = att.split("=")[1];
			} else {
				attName = att;
			}
		} else {
			name = xpath;
		}
		List<Node> result = new ArrayList<Node>();
		parserXpath(node,name,attName,attValue,result);
		if(result.size()>0){
			return result.get(0);
		}
		return null;
	}
	public static void parserXpath(Node node,String name,String attName,String attValue,List<Node> result) {
		if(node==null)return ;

		if(node.getNodeName().toString().equals(name)){
			if(attName==null){
				result.add(node);
			}else {
				NamedNodeMap nodemap = node.getAttributes();
				for (int i = 0; i < nodemap.getLength(); i++) {
					Node att = nodemap.item(i);
					if (att.getNodeName().toString().equals(attName)
							&& att.getNodeValue().toString().equals(attValue)) {
						result.add(node);
						break;
					}
				}
			}
		}
		NodeList nl = node.getChildNodes();
		if(nl==null)return ;
		for(int i=0;i<nl.getLength();i++){
			parserXpath(nl.item(i),name,attName,attValue,result);
		}
	}

	public static List<String> parserNodeByXpathGetList(Node node,String xpath) {
		NodeList nl = DomTree.commonList(xpath,node);
		if(nl==null||nl.getLength()==0)return null;
		List<String> list = new ArrayList<String>();
		for(int i=0;i<nl.getLength();i++){
			list.add(nl.item(i).getTextContent().trim());
		}
		return list;
	}
	public static String parserNodeByXpathGetString(Node node,String xpath) {
		NodeList nl = DomTree.commonList(xpath,node);
		if(nl==null||nl.getLength()==0){
			logger.info("nl==null||nl.getLength()==0:xpath:"+xpath);
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<nl.getLength();i++){
			sb.append(nl.item(i).getTextContent().trim());
		}
		return sb.toString();
	}
	public static String parserNodeByXpathGetStringSplitByMark(Node node,String xpath,String mark) {
		NodeList nl = DomTree.commonList(xpath,node);
		if(nl==null||nl.getLength()==0){
			logger.warn("nl==null||nl.getLength()==0:xpath:"+xpath);
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<nl.getLength();i++){
			sb.append(nl.item(i).getTextContent().trim()+mark);
		}
		return sb.toString();
	}
	
	public static String parserContentByXpathGetStringSplitByMark(String content,String charset,
			String xpath,String mark){
		return parserNodeByXpathGetStringSplitByMark(getNode(content, charset),xpath,mark);
	}
	public static int parserNodeByXpathGetLength(Node node,String xpath) {
		NodeList nl = DomTree.commonList(xpath,node);
		if(nl==null||nl.getLength()==0)return 0;

		return nl.getLength();
	}

	public static String parserNodeByXpathGetString(Node node,String xpath,String separator) {
		NodeList nl = DomTree.commonList(xpath,node);
		if(nl==null||nl.getLength()==0)return null;
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<nl.getLength();i++){
			if(separator.equals("count")){
				if(sb.length()==0){
					sb.append("\n"+(i+1)+"\t:\t"+nl.item(i).getTextContent().trim());
				}else {
					sb.append("\n"+(i+1)+"\t:\t"+nl.item(i).getTextContent().trim());
				}
			}else{
				if(sb.length()==0){
					sb.append(nl.item(i).getTextContent().trim());
				}else{
					sb.append(separator+nl.item(i).getTextContent().trim());
				}
			}
		}
		return sb.toString();
	}
	
	
	public static String parserNodeByXpathGetIndexContent(Node node,String xpath,int index) {
		NodeList nl = DomTree.commonList(xpath,node);
		if(nl==null||nl.getLength()==0)return null;
		if(index<nl.getLength()){
			return nl.item(index).getTextContent().trim();
		}
		return null;
	}
}