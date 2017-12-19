package xpath;

import common.system.FileOperation;
import common.utils.JsonParser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.Date;

public class JsonTest {

	
	
	@Test
	public void testWeixinSogou() throws Exception{
		
		
		String path  = "C:/Users/Administrator/Desktop/123.html";
		
		String content = FileOperation.read(path);
		
		if(content.contains("var msgList = ")){
			
			content = content.split("var msgList = ")[1];
			if(content!=null&&content.contains(";\n")){
				content = content.split(";\n")[0];
			}
			System.out.println(content);
			
			
			JSONArray jsonArray = (JSONArray)JsonParser.content2Obj(content, "list");
			
			for(Object o:jsonArray){
				
				
				JSONObject jsonObject =(JSONObject)o;
				JSONObject app_msg_ext_info = (JSONObject)JsonParser.obj2Obj(jsonObject, "app_msg_ext_info");
				
				String author = (String)JsonParser.obj2Obj(app_msg_ext_info, "author");
				String content_url = (String)JsonParser.obj2Obj(app_msg_ext_info, "content_url");
				if(!content_url.startsWith("http")){
					content_url="https://mp.weixin.qq.com"+content_url;
				}
				content_url=content_url.replace("amp;", "");
				String brief = (String)JsonParser.obj2Obj(app_msg_ext_info, "digest");
				String title = (String)JsonParser.obj2Obj(app_msg_ext_info, "title");
				
				JSONObject comm_msg_info = (JSONObject)JsonParser.obj2Obj(jsonObject, "comm_msg_info");
				int timeStamp = (int)JsonParser.obj2Obj(comm_msg_info, "datetime");;
				Date pubdate = new Date(timeStamp*1000L);
					
				System.out.println("\n\n\n\n########################");
				System.out.println(author);
				System.out.println(content_url);
				System.out.println(brief);
				System.out.println(title);
				System.out.println(pubdate.toLocaleString());

				JSONArray multi_app_msg_item_list = (JSONArray)JsonParser.obj2Obj(app_msg_ext_info, "multi_app_msg_item_list");
				if(multi_app_msg_item_list==null){
					continue;
				}
				for(Object o2:multi_app_msg_item_list){
					JSONObject jsonObject2 =(JSONObject)o2;
					
					author = (String)JsonParser.obj2Obj(jsonObject2, "author");
					content_url = (String)JsonParser.obj2Obj(jsonObject2, "content_url");
					if(!content_url.startsWith("http")){
						content_url="https://mp.weixin.qq.com"+content_url;
					}
					content_url=content_url.replace("amp;", "");
					brief = (String)JsonParser.obj2Obj(jsonObject2, "digest");
					title = (String)JsonParser.obj2Obj(jsonObject2, "title");
					
					System.out.println("\n\n\n\n########################");
					System.out.println(author);
					System.out.println(content_url);
					System.out.println(brief);
					System.out.println(title);
					System.out.println(pubdate.toLocaleString());
					
				}
		
			}
			
			
		}
		
		
	}	
}
