package xpath;

import common.http.HtmlInfo;
import common.http.SimpleHttp;
import common.main.researchGate.ResearchGateAuthorDataXpath;
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
	    
		//<div class="content-grid__columns--narrow">  type="button"
		xpath="//DIV[@class='content-grid__columns--narrow']//DIV[@class='nova-o-pack__item']|" +
				"//DIV[@class='content-grid__columns--narrow']//BUTTON[@type='button']";  //<em class="minor commafy last">
		NodeList nl = DomTree.commonList(xpath, node);
	    
	    System.out.println("nl.getLength():"+nl.getLength());
	    //System.out.println(nl.item(0).getTextContent());
	    String result = DomTree.parserNodeByXpathGetString(node, xpath,"");
		//Reads7,264Recommendations9Followers32Citations93

		System.out.println(result);

		// <div class="nova-e-text nova-e-text--size-m nova-e-text--family-sans-serif nova-e-text--spacing-none nova-e-text--color-inherit nova-c-nav__item-label">

		xpath = "//DIV[@class='nova-e-text nova-e-text--size-m nova-e-text--family-sans-serif nova-e-text--spacing-none nova-e-text--color-inherit nova-c-nav__item-label']";

		nl = DomTree.commonList(xpath, node);
		result = DomTree.parserNodeByXpathGetString(node, xpath,"##");
		//Overview##Comments (1)##Citations (93)##References (55)##Related research (10+)##Public Full-text (1)
		System.out.println(result);


		xpath = "//SPAN/A[@class='nova-e-link nova-e-link--color-inherit nova-e-link--theme-bare research-detail-author'][contains(@href,'profile')]/@href";
		nl = DomTree.commonList(xpath, node);
		result = DomTree.parserNodeByXpathGetString(node, xpath,"\n");
		//profile/Young-Jin_Cha?_sg=uMtx61daZ721D7VUqGD98ZYPmNo0Y4xXF6rnsYzmD-BNk_zgYVgz3ME1vfKy0NjObFGd2o0.KGtGRqGoW450vxRmNJuy9qyegrBErEpXxRBEnMK3Cj8HP5a4bAQofP4e0b0OxKPnRxcGnKf61vvUHhsD2CirjQ
		System.out.println("\n\n\nresult");
		System.out.println(result);
	}



//\{.*?\}



	@Test
	public void testMian_jinritoutiao(){


		String path  = "C:/Users/Administrator/Desktop/1.html";
		String content = FileOperation.read(path);


		String xpathContent = "//DIV[contains(@class,'content')]//P|"
				+ "//ARTICLE//P|//DIV[contains(@id,'ext')]//P|" + "//FIGURE/FIGCAPTION";
		DocumentFragment node = DomTree.getNode(content, "gbk");

		String xpath = "//TBODY/TR//TH//A[@class='s xst']/@href";//"//DIV/P";

		xpath = "//DIV[@class='comment-item'][20]//P";

		xpath = "//DIV[@class='js-items']/UL[@class='search-results clearfix']//DIV[@class='name']/A/@href";

		xpath = ResearchGateAuthorDataXpath.skills;

		NodeList nl = DomTree.commonList(xpath, node);



		for(int i=0;i<nl.getLength();i++){
			String item = nl.item(i).getTextContent();


			System.out.println((i+1)+":"+item);


		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		System.out.println("year"+calendar.get(Calendar.YEAR));



		String sql = "The complexities of various search algorithms are " +
				"considered in terms of time, space, and cost of solution path. " +
				"It is known that breadth-first search requires too much space and depth-first " +
				"search can use too much time and doesn't always find a cheapest path. A depth-first";


		System.out.println(sql.replace("'","\\'"));

		System.out.println("123:"+"123".substring(0,1));


		String strResult = "84,316Reads";
		strResult = strResult.replaceAll("[a-zA-Z|,]","");

		System.out.println("strResult:"+strResult);

		strResult = "https://www.researchgate.net/profile/Daniel_Dajun_Zeng?_sg%5B0%5D=VTV4H2MIghmwEZeiDycsbr_KbahKCWSMPZZPFlCceR5rIxzKjUmsB334x3hJXA6ZMRbCoIHvHEJaYbdiv-Q&_sg%5B1%5D=bQYds7BOoFqI86h2UVK9JRxUTV6j4OKtyiC-8bs3AbiSYOaJcgPdItpfkRoUQaL8iWOglMC_JYVrVxaO";
		strResult = strResult.split("profile\\/")[1].split("\\?")[0];
		System.out.println("strResult:"+strResult);


		System.out.println("strResult:"+"//\"count\":\"19\",".replaceAll("\"","").split("count:")[1].split(",")[0]);



	}
}
