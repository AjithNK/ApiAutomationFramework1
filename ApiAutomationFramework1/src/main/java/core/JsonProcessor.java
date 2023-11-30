package core;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonProcessor {
	
	
	public static String jsonObjectToString(JSONObject jsonObj) {
		
		return jsonObj.toJSONString();
	

		
	}
	
	
	
	
	public static JSONObject stringToJsonObject(String inputString) {
		JSONObject jsonObj= new JSONObject();
		
		JSONParser parser = new JSONParser();
		try {
			 jsonObj = (JSONObject) parser.parse(inputString);
			 System.out.println(jsonObj.getClass());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return jsonObj;
		
	}

}
