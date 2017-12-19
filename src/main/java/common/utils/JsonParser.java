package common.utils;




import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

public class JsonParser {

	private static final Logger logger = Logger.getLogger(JsonParser.class);

	public static Object content2Obj(String content, String param){
		Object obj=null;
		try {
			JSONObject job01 = JSONObject.fromObject(content);

			if (!job01.containsKey(param)) {

				logger.warn("JsonParser content2Obj !job01.containsKey(param) :" + param);
				return null;
			}
			obj = job01.get(param);
		}catch (Exception e){
			logger.info("Exception : "+ e.getMessage());
		}
		return obj;
	}

	public static Object obj2Obj(JSONObject obj, String param){
		if(param==null){
			logger.error("JsonParser obj2Obj param :"+param);
			return  null;
		}
		if(obj==null){
	
			logger.error("JsonParser obj2Obj obj :"+obj.toString());
			return  null;
		}
		if(!obj.containsKey(param)){
			logger.error("obj not containsKey "+param);
			return null;
		}

		Object resultObj=null;
		try {
			resultObj = obj.get(param);
		}catch (Exception e){
			logger.info("Exception : "+ e.getMessage());
		}
		return resultObj;
	}
	public static String getContentByJson(String content,String node){
		try {
			node = "\"" + node + "\"";
			if (content.contains(node)) {
				node = content.split(node)[1];
				if (node.contains("\",")) {
					node = node.split("\",")[0];
				}
				if (node.contains("\"")) {
					node = node.replace("\"", "");
				}
				if (node.contains(":")) {
					node = node.replace(":", "");
				}
				return node;
			}
		}catch (Exception e){
			logger.info("Exception : "+ e.getMessage());
		}
		return null;
	}

	public static String getContentByJson(String content,String node,String splitMark){
		node = "\""+node+"\"";
		if(content.contains(node)){
			node = content.split(node)[1];
			if(node.contains(splitMark)){
				node = node.split(splitMark)[0];
			}
			if(node.contains("\"")){
				node  = node.replace("\"","");
			}
			if(node.contains(":")){
				node  = node.replace(":","");
			}
			return node;
		}
		return null;
	}
}


/*


*/