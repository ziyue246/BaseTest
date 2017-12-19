package xpath;

import common.system.FileOperation;
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
			while (result.indexOf("銆�")!=-1)
				result = result.replaceAll("銆�", "");
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
		    	String time  = result.split("... -")[1];
		    	time = time.replace(" ", "");
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
		    	String time  = result.split("... -")[1];
		    	time = time.replace(" ", "");
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
		xpath= "//DIV[@class='result'][@id='<index>']//SPAN[@class='c-info']/A[contains(.,'查看更多相关新闻')]/@href";
			
		
		
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
	
	
	
	
	public void testNews() throws Exception{
		
		
		String path  = "C:/Users/Administrator/Desktop/123.html";
		
		String content = FileOperation.read(path);
		DocumentFragment node = DomTree.getNode(content, "utf-8");
		String xpath = "//DIV[@class='fb']/CITE[contains(@id,'cacheresult_info_')]";
	    
	//<p style="text-align: right;">
		xpath= "//DIV[@id='js_content']//SPAN";
	
		
		NodeList nl = DomTree.commonList(xpath, node);
	    
	    System.out.println(nl.getLength());
	    //System.out.println(nl.item(0).getTextContent());
	    String result = DomTree.parserNodeByXpathGetString(node, xpath,"count");
		System.out.println(result);
		    
	    
		result = DomTree.parserNodeByXpathGetIndexContent(node, xpath,0);
		System.out.println(result);
	}	
}
