package assignmentTest;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import assignments.JsonProcessor;
import junit.framework.Assert;

public class JsonProcessorTester {
	
	
  @Test
  public void convertJsonObjToString() {
	  
	  
	  	JSONObject root = new JSONObject();
		JSONObject address = new JSONObject();
	  
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
	  
	  
		String actualJsonString = JsonProcessor.jsonObjectToString(root);
		System.out.println(actualJsonString);
		
		String expectedJsonString="{\"address\":{\"pincode\":695582,\"housename\":\"abcd\",\"city\":\"trivandrum\"},\"name\":\"Ajith\",\"Height\":176.0,\"isEmployed\":true,\"age\":29}";
		
		Assert.assertEquals(actualJsonString, expectedJsonString);
		
		
		
	  
  }
  
  
  @Test
  public void convertStringToJsonObj() {
	  
	  	JSONObject root = new JSONObject();
	//	JSONObject address = new JSONObject();
	  
		root.put("name", "Ajith");
		root.put("age", 29);
	/*	root.put("isEmployed", true);
		root.put("Height", 176.0f);
		
		//adding values to address object
		address.put("housename","abcd" );
		address.put("city", "trivandrum");
		address.put("pincode", 695582);
		
		
		//adding address object to root object
		root.put("address", address);   */
	  
		String jsonString=JsonProcessor.jsonObjectToString(root);
  
		
		//JSONObject expectedObj = root;
		JSONObject actualObj= JsonProcessor.stringToJsonObject(jsonString);
		
		System.out.println(actualObj.getClass());
		Assert.assertEquals(actualObj, root);
			  
  }
	  
  
  

  @Test
  public void convertJsonFileToJsonObject() {
	  JsonProcessor obj = new JsonProcessor();
	  
	//	JSONObject jsonObj =  obj.readFromJsonFilePath("D:\\AJITH\\API_TRAINING_GIT_REPO\\git\\ApiAutomationFramework1\\ApiAutomationFramework1\\src\\main\\resources\\apis\\api.json");
		
	  
  }
  
  
}
