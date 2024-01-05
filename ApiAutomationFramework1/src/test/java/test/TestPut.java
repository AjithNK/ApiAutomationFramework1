package test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import core.APIHelper;
import core.APIRequest;
import core.JsonProcessor;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;
import requestPojo.RequestPojoPut;

@SuppressWarnings({"unused", "unchecked"})
public class TestPut {
	
	//PUT request (Update user) with hard-coded values
	@Test(enabled=true)
	public void updateUser() {    //validateUpdateUserFunctionalityWhenDataIsHardCoded
		
		//Header
		HashMap<String, String> headers = new HashMap<String,String>();
		headers.put("Content-Type","application/json");
		
		//Request body
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", "Rajesh");
		requestBody.put("job", "Automation Engineer");
		
		//Creating the Request object
		APIRequest apiRequestPut = new APIRequest("put","https://reqres.in/api/users/2",headers,requestBody);
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequestPut);
		
		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
		
		//Asserting the response details
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.contentType().toString(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
	
		
	}
	
	
	//PUT request (Update user) with details passed from json file
	@Test(enabled=true)
	public void updateUser2() { //validateUpdateUserFunctionalityWhenDataIsPassedFromJson
	
		
		//Creating the JSON file path
		String jsonFileName="UpdateUserDetails.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
		
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(filePath);
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		
		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
		
		//Asserting the response details		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.contentType().toString(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
	}
	
	
	//PUT request (Update user) with details passed from json file and request is dynamic
	@Test(enabled=true)
	public void updateUser3() {    //validateUpdateUserFunctionalityWhenDataPassedFromJson&DynamicRequestBody
			
		//Creating the JSON file path
		String jsonFileName="UpdateUserDetails3.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
			
		//
		HashMap<String, String> metaInfo = new HashMap<String, String>();
		metaInfo.put("name", "John");
		metaInfo.put("job", "admin");
			
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(filePath, metaInfo);
			
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
			
		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
			
		//Asserting the response details		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.contentType().toString(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
	}
			
	
	//1 TEST PENDING
	@Test(enabled=false)		 //validateUpdateUserFunctionalityWhenDataPassedFromJson&RequestBodyAsPojo
	public void updateUser4() {   //testPutOrchestrationUsingRequestPojo   -and request is dynamic 
			
		//Creating the JSON file path
		String jsonFileName="UpdateUserDetails4.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
		
		
		//Reading the data from JSON file and obtaining the jsonObject 
		JSONObject jsonObj = JsonProcessor.readFromJsonFile(filePath);
		
		//Headers
		HashMap<String,String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "json");
		
		//Creating object of request pojo
		RequestPojoPut requestPojoPut = new RequestPojoPut();
		requestPojoPut.setName("John");
		requestPojoPut.setJob("Team Lead");
		
		//Obtaining the request as JSON Object
		JSONObject requestJSONObject = JsonProcessor.stringToJsonObject(requestPojoPut.toString());
				
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(filePath, requestJSONObject);
		
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
