package xpath;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.system.FileOperation;
import common.system.StringProcess;
import common.utils.DomTree;
import common.utils.StringUtil;
import org.junit.Test;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NodeList;

import java.util.Calendar;
import java.util.Date;

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



		xpath="//DIV[@class='flex-column']/DIV[@class='card-box']/DIV[0]/DIV[contains(@style,'width')]";
		xpath="//DIV[@class='flex-column']//DIV[@style='width: 115px;']//SPAN[@class='large-number']";
		xpath="//DIV[@class='flex-column']//DIV[@style='width: 100px;']//SPAN[@class='large-number']";
		NodeList nl = DomTree.commonList(xpath, node);
	    
	    System.out.println(nl.getLength());
	    //System.out.println(nl.item(0).getTextContent());
	    String result = DomTree.parserNodeByXpathGetString(node, xpath,"##");
		System.out.println(result);
		    
	    
		result = DomTree.parserNodeByXpathGetIndexContent(node, xpath,0);
		System.out.println(result);



	}


//\{.*?\}



	@Test
	public void testMian_jinritoutiao(){


		String path  = "C:/Users/lenovo/Desktop/1.html";
		String content = FileOperation.read(path);


		String xpathContent = "//DIV[contains(@class,'content')]//P|"
				+ "//ARTICLE//P|//DIV[contains(@id,'ext')]//P|" + "//FIGURE/FIGCAPTION";
		DocumentFragment node = DomTree.getNode(content, "utf-8");

		String xpath = "//TBODY/TR//TH//A[@class='s xst']/@href";//"//DIV/P";

		xpath="//DIV[@class='sourinfo']/P/A[contains(.,'20')][contains(.,'10')]";

		xpath="//DIV[@class='sourinfo']/P/A[contains(.,'20')][contains(.,'10')]";


		xpath = "//DIV[@class='gs_rs']";  //href cites=
		xpath = "//DIV[@class='gs_fl']//A[contains(@href,'cites=')]";
		//xpath="//DIV[@class='gs_r gs_or gs_scl']";
		//		//<div class="gs_r gs_or gs_scl"
		//<div class="gs_rs">

		xpath = "//TR[@align='center']//TD[@align='left']/A/@href";
		xpath = "//H3[@class='gs_rt']";//lass="gs_rt"
		xpath = "//DIV[@class='gs_a']/A|//DIV[@class='gs_a']/A/@href";
		xpath = "//DIV[@class='gs_a']/A";
		xpath = "//DIV[@class='gs_fl']//A[contains(@href,'cites=')]";

		xpath = "//DIV[@id='gs_ab_md']/DIV[@class='gs_ab_mdw']";
		xpath = "//DIV[@class='gs_a']";

		NodeList nl = DomTree.commonList(xpath, node);



		for(int i=0;i<nl.getLength();i++){
			String item = nl.item(i).getTextContent();

//			String itemhref = nl.item(i).getAttributes().getNamedItem("href").getTextContent();
//			//String year = StringUtil.extractOne(item,"(19|20)\\d{2}");  /
//			String citeNum = StringUtil.extractOne(item,"\\d*\\d");

			System.out.println((i+1)+":"+item);

//			System.out.println((i+1)+":"+itemhref);
//			System.out.println((i+1)+":"+citeNum);

			//System.out.println(item);
			//System.out.println(year);
			//System.out.println(citeNum);

		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		System.out.println("year"+calendar.get(Calendar.YEAR));



		String sql = "The complexities of various search algorithms are " +
				"considered in terms of time, space, and cost of solution path. " +
				"It is known that breadth-first search requires too much space and depth-first " +
				"search can use too much time and doesn't always find a cheapest path. A depth-first";


		System.out.println(sql.replace("'","\\'"));
	}
}
