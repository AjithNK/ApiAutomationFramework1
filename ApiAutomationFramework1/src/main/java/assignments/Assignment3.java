package assignments;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException; 

public class Assignment3 {
	
	public static void createJSonObject() {
		JSONObject root = new JSONObject();
		JSONObject address = new JSONObject();
		
		//adding values to root object
		root.put("name", "Ajith");
		root.put("age", 29);
		root.put("isEmployed", true);
		root.put("Height", 176.0f);
		
		//adding values to address object
		address.put("housename","abcd" );
		address.put("city", "trivandrum");
		address.put("pincode", 695582);
		
		
		//adding address object to root object
		root.put("address", address);
		
		
		System.out.println(root.toJSONString());
		
		System.out.println(root.get("name"));
		
		System.out.println(root.get("address"));
		
		
		
		
		ObjectMapper om = new ObjectMapper();
		try {
			Root root2 = om.readValue(root.toString(), Root.class);
			
			System.out.println(root2.getAddress().getHousename());
			
			
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			
		}

	}
	
	
	public static void main(String[] args) {
		createJSonObject();
		
	}

}
