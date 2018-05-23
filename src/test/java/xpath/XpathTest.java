package xpath;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.system.FileOperation;
import common.system.StringProcess;
import common.utils.DomTree;
import org.junit.Test;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NodeList;

public class XpathTest {
	
	public String format(String s) {
		String result = s;
		if (s != null && s.trim().length()>0){
			while (result.indexOf("\r")!=-1)
				result = result.replaceAll("\r", "");
			while (result.indexOf("\n")!=-1)
				result = result.replaceAll("\n", "");
			while (result.indexOf("\t")!=-1)
				result = result.replaceAll("\t", "");
			while (result.indexOf("  ")!=-1)
				result = result.replaceAll("  ", " ");
			while (result.indexOf("�?")!=-1)
				result = result.replaceAll("�?", "");
			return result.trim();
		}
		return "";
	}
	//@Test
	public void testMian(){
		
		
		String path  = "C:/Users/Administrator/Desktop/11.html";
		
		String content = FileOperation.read(path);
		DocumentFragment node =   DomTree.getNode(content, "utf-8");
	    String xpath = "//P[@class='FR_field'][SPAN[contains(.,'Keyword List:')]]|"
	    		+ "//P[@class='FR_field'][SPAN[contains(.,'Author Keywords:')]]|"
	    		+ "//P[@class='FR_field'][SPAN[contains(.,'KeyWords Plus:')]]";
	    
	    //Keyword List   Author Keywords     KeyWords Plus
	    String result = DomTree.parserNodeByXpathGetString(node, xpath, "");
	    
	    System.out.println(result);
		if(result.contains("Keyword List:")){
			result = result.split("Keyword List:")[1];
			if(result.contains("Author Keywords:")){
				result = result.split("Author Keywords:")[0];
			}
			if(result.contains("KeyWords Plus:")){
				result = result.split("KeyWords Plus:")[0];
			}
		}
		if(result.contains("Author Keywords:")){
			result = result.split("Author Keywords:")[1];
			if(result.contains("KeyWords Plus:")){
				result = result.split("KeyWords Plus:")[0];
			}
		}
		if(result.contains("KeyWords Plus:")){
			result = result.split("KeyWords Plus:")[1];
		}
		System.out.println(format(result));
		
	}
	//@Test
	public void testMian01(){
		
		
		String path  = "C:/Users/Administrator/Desktop/123.html";
		
		String content = FileOperation.read(path);
		DocumentFragment node = DomTree.getNode(content, "utf-8");
		String xpath = "//DIV[@class='fb']/CITE[contains(@id,'cacheresult_info_')]";
	    //Keyword List   Author Keywords     KeyWords Plus
	    NodeList nl = DomTree.commonList(xpath, node);
	    for(int i=0;i<nl.getLength();i++){
	    	String xpath2 = "//DIV[@class='fb']/CITE[@id='cacheresult_info_"+i+"']";
	    	String result = DomTree.parserNodeByXpathGetString(node, xpath2);
	    	System.out.println(result);
	    	if(result.contains("-")){
		    	String time  = result.split("...?-")[1];
		    	time = time.replace("?", "");
		    	System.out.println(time.trim());
	    	}
	    }
	    
	}
	//@Test
	public void testMian02(){
		
		
		String path  = "C:/Users/Administrator/Desktop/123.html";
		
		String content = FileOperation.read(path);
		DocumentFragment node = DomTree.getNode(content, "utf-8");
		String xpath = "//DIV[@class='fb']/CITE[contains(@id,'cacheresult_info_')]";
		
		
		//<div class="tit_user"><strong><a
		xpath = "//DIV[@class='tit_user']/STRONG";;
	    //Keyword List   Author Keywords     KeyWords Plus
	    NodeList nl = DomTree.commonList(xpath, node);
	    for(int i=0;i<nl.getLength();i++){
	    	String xpath2 = "//DIV[@class='fb']/CITE[@id='cacheresult_info_"+i+"']";
	    	String result = DomTree.parserNodeByXpathGetString(node, xpath2);
	    	System.out.println(result);
	    	if(result.contains("-")){
		    	String time  = result.split("...?-")[1];
		    	time = time.replace("?", "");
		    	System.out.println(time.trim());
	    	}
	    }
	    
	}
	//@Test
	public void testMianContent01(){
		
		
		String path  = "C:/Users/Administrator/Desktop/123.html";
		
		String content = FileOperation.read(path);
		DocumentFragment node = DomTree.getNode(content, "gb2312");
		String xpath = "//DIV[@class='fb']/CITE[contains(@id,'cacheresult_info_')]";
		
		
		//<div class="tit_user"><strong><a   <div class="layer_l">   <div class="content">
		xpath = "//DIV[@class='content']/DIV[@class='layer_l']/DIV[@class='tit_user'][1]/STRONG";
	    //Keyword List   Author Keywords     KeyWords Plus
		
		// <div class="article_foot"> <span>   <div class="layer_l">
		xpath = "//DIV[@class='layer_c'][1]//DIV[@class='text']//@src";
	    NodeList nl = DomTree.commonList(xpath, node);
	    
	    System.out.println(nl.getLength());
	    System.out.println(nl.item(0).getTextContent());
	    String result = DomTree.parserNodeByXpathGetString(node, xpath,"\n@@\n");
		System.out.println(result);
	    
	}
	
	
	public void testMianWeibo(){
		
		
		String path  = "C:/Users/Administrator/Desktop/123.html";
		
		String content = FileOperation.read(path);
		DocumentFragment node = DomTree.getNode(content, "utf-8");
		String xpath = "//DIV[@class='fb']/CITE[contains(@id,'cacheresult_info_')]";
		
		
		//<div class="tit_user"><strong><a   <div class="layer_l">   <div class="content">
		xpath = "//DIV[@class='content']/DIV[@class='layer_l']/DIV[@class='tit_user'][1]/STRONG";
	    //Keyword List   Author Keywords     KeyWords Plus
		
		// <div class="article_foot"> <span>   <div class="layer_l">
		xpath = "//DIV[@class='layer_c'][1]//DIV[@class='text']//@src";
		
		
		xpath="//DIV[@action-type][not(@feedtype)]/@mid";
		xpath="//DIV[@action-type][not(@feedtype)][@mid][1]//DIV[@class='WB_detail']/DIV[@class='WB_from S_txt2']/A[@class='S_txt2'][1]";
		
		xpath="//DIV[@action-type][not(@feedtype)][@mid]//DIV[@class='WB_detail']/DIV[@class='WB_text W_f14']";
	    NodeList nl = DomTree.commonList(xpath, node);
	    
	    System.out.println(nl.getLength());
	    System.out.println(nl.item(0).getTextContent());
	    String result = DomTree.parserNodeByXpathGetString(node, xpath,"count");
		System.out.println(result);
	    
	}
	@Test
	public void testMianqingdaonews() throws Exception{
		
		
		String path  = "C:/Users/Administrator/Desktop/1.html";
		
		String content = FileOperation.read(path);
		
		//System.out.println(content);
		DocumentFragment node = DomTree.getNode(content, "utf-8");
		String xpath = "//DIV[@class='fb']/CITE[contains(@id,'cacheresult_info_')]";
	    
	//<p style="text-align: right;">  <div class="c-span18 c-span-last">
		xpath= "//DIV[@class='result'][@id='<index>']//SPAN[@class='c-info']/A[contains(.,'')]/@href";
			
		
		
		//<span class="c-info">
		
		//xpath="//SPAN";  <div class="result" id="1"
		
		NodeList nl = DomTree.commonList("//DIV[@class='result']", node);
	    
	    System.out.println(nl.getLength());
	    //System.out.println(nl.item(0).getTextContent());
	    //String result = DomTree.parserNodeByXpathGetString(node, xpath,"count");
	    
	    
	    for(int i=0;i<nl.getLength();++i){
	    	xpath = xpath.replace("<index>", ""+(i+1));
	    	
	    	//System.out.println(xpath);
	    	String result = DomTree.parserNodeByXpathGetString(node, xpath,"");
	    	
	    	System.out.println(i+"\t:\t"+result);
	    }
	    
	    
		
		    

	}	
	
	
	
	@Test
	public void testNews() throws Exception{
		
		
		String path  = "C:/Users/Administrator/Desktop/1.html";
		
		String content = FileOperation.read(path);
		DocumentFragment node = DomTree.getNode(content, "utf-8");
		String xpath = "//DIV[@class='fb']/CITE[contains(@id,'cacheresult_info_')]";
	    
		//<p style="text-align: right;">
		// <div class="totalRow">  <div class="tcPerYear"
		//style="vertical-align:top;"
		xpath= "//DIV[@class='tcPerYear'][@style='vertical-align:top;']|//DIV[@class='totalRow']//SPAN";
		// <div class="create-cite-report">  <div class="summary_CitCount">
		// <div id="view_citation_report_image_placeholder">

		//		<div id="view_citation_report_image">
		//		<div class="summary_CitCount">   <nobr>   <a
		xpath = //"//DIV[@class='create-cite-report']
				"//DIV[@id='view_citation_report_image']//DIV[@class='summary_CitCount']//A/@href";
		//summary_CitCount
		NodeList nl = DomTree.commonList(xpath, node);
	    
	    System.out.println(nl.getLength());
	    //System.out.println(nl.item(0).getTextContent());
	    String result = DomTree.parserNodeByXpathGetString(node, xpath,"##");
		System.out.println(result);
		    
	    
		result = DomTree.parserNodeByXpathGetIndexContent(node, xpath,0);
		System.out.println(result);



		//System.out.println("1,"+StringProcess.regex2StrSplitByMark(content,"\"raw_tc_data\".*?\n?.*?}","#"));

		//System.out.println("2,"+content.split("raw_tc_data")[1].split("}")[0]);
	}


//\{.*?\}



	@Test
	public void testMian_jinritoutiao(){
//		HtmlInfo html = new HtmlInfo();
//		SimpleHttp http = new SimpleHttp();
//
//		String url = "http://weixin.sogou.com/weixin?query=%E9%9D%92%E5%B2%9B%E6%97%A9%E6%8A%A5&type=1";
//
//		url="http://club.xihaiannews.com/forum-58-1.html";
//		html.setOrignUrl(url);
//
//		String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36";
//		html.setUa(ua);
//
//		http.simpleGet(html);

		String path  = "C:/Users/Administrator/Desktop/2.html";
		String content = FileOperation.read(path);

		//content = html.getContent();
		//System.out.println(content);
		//System.out.println("��������");
//
//		//String content = html.getContent();

		//article-content
		String xpathContent = "//DIV[contains(@class,'content')]//P|"
				+ "//ARTICLE//P|//DIV[contains(@id,'ext')]//P|" + "//FIGURE/FIGCAPTION";
		DocumentFragment node = DomTree.getNode(content, "gbk");
			//class="s xst"
		String xpath = "//TBODY/TR//TH//A[@class='s xst']/@href";//"//DIV/P";


		//<td class="fr_address_row2">  <p class="FR_field">

		//<span class="FR_label">Reprint Address:
		xpath="//SPAN[@class='FR_label'][contains(.,'Reprint Address')]";//[contains(.,'ͨѶ����')]/@href";
		//class="fr_address_row2">


		//<div class="sourinfo">
		xpath="//DIV[@class='sourinfo']/P[contains(.,'ISSN')]";//[contains(.,'ͨѶ����')]/@href";
		xpath="//DIV[@class='sourinfo']/P/A[contains(.,'20')][contains(.,'10')]";
		NodeList nl = DomTree.commonList(xpath, node);



		for(int i=0;i<nl.getLength();i++){
			//System.out.println((i+1)+":"+nl.item(i).getNextSibling().getTextContent());
			System.out.println((i+1)+":"+nl.item(i).getTextContent());
		}

	}
}
