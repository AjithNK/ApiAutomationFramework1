package test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.hamcrest.object.HasEqualValues;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import core.APIHelper;
import core.APIRequest;
import core.JsonProcessor;
import io.restassured.response.Response;
import requestPojo.RequestPojoPatch;
import requestPojo.RequestPojoPut;

@SuppressWarnings({"unused", "unchecked"})
public class TestPatch {
	
	//PATCH request (Partial Update user) with hard-coded values
	@Test(enabled=true)
	public void verifyPartialUpdateUserFunctionalityWhenDataIsHardCoded() {
		
		//Headers
		HashMap<String,String> headers = new HashMap<String,String>();
		
		//RequestBody for updating the email id
		JSONObject requestBody = new JSONObject();
		requestBody.put("email", "charles.david@reqres.in");
		
		//Creating Request object
		APIRequest requestPatch= new APIRequest("patch","https://reqres.in/api/users/5",headers,requestBody);
		
		//Creating the object of APIHelper class & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper= new APIHelper();
		Response response = apiHelper.hitAPI(requestPatch);
		
		
		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
		
		//Assertions on the response
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK"); 
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<2000);
		
		
	}
	
	//PATCH request (Partial Update user) with details passed from json file
	@Test(enabled=true)
	public void verifyPartialUpdateUserFunctionalityWhenDataIsPassedFromJson() {
	
		//Creating the JSON file path
		String jsonFileName="PartialUpdateUserDetails2.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
	
		//Creating Request object
		APIRequest apiRequest = new APIRequest(filePath);
	
		//Creating the object of APIHelper class & calling the hitAPI() method using this object and obtaining the response
		APIHelper apihelper = new APIHelper();
		Response response = apihelper.hitAPI(apiRequest);
	
		//Console output of the response body and headers
		System.out.println(response.asPrettyString());
		System.out.println(response.getHeaders());
	
		//Assertions on the response
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
	
	}
	
	
	//PATCH request (Partial Update user) with details passed from json file and request is dynamic
	@Test(enabled=true)
	public void verifyPartialUpdateUserFunctionalityWhenDataPassedFromJsonAndDynamicRequestBody() {
		
		//Creating the JSON file path
		String jsonFileName="PartialUpdateUserDetails3.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
			
		HashMap<String, String> metaInfo = new HashMap<String, String>();
		metaInfo.put("id", "5");
		
		//Creating Request object
		APIRequest apiRequest = new APIRequest(filePath, metaInfo);
		
		//Creating the object of APIHelper class & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		
		//Console output of the response body and headers
		System.out.println(response.asPrettyString());
		System.out.println(response.getHeaders());
			
		//Assertions on the response
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
		

	}
	
	
	//PATCH request (Partial Update user) with details passed from json file and request is passed as a pojo
	@Test(enabled=true)
	public void verifyPartialUpdateUserFunctionalityWhenDataPassedFromJsonAndRequestBodyAsPojo() {
		
		//Creating the JSON file path
		String jsonFileName="PartialUpdateUserDetails4.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
						
		//Reading the data from JSON file and obtaining the jsonObject 
		JSONObject jsonObj = JsonProcessor.readFromJsonFile(filePath);
				
		//Headers
		HashMap<String,String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "json");
				
		//Creating object of request pojo
		RequestPojoPatch requestPojoPatch = new RequestPojoPatch();
		requestPojoPatch.setJob("QA Manager");
				
		//Obtaining the request as JSON Object
		JSONObject requestJSONObject = JsonProcessor.stringToJsonObject(requestPojoPatch.toString());
						
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(jsonObj.get("requestType").toString(),
				jsonObj.get("requestApiPath").toString(),headers, requestJSONObject);
				
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
				
		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Asserting the response details	
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
		
	}
		
		
}
