package test;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import core.APIHelper;
import core.APIRequest;
import core.JsonProcessor;
import io.restassured.response.Response;
import requestPojo.RequestPojoPost;
import requestPojo.RequestPojoPut;

@SuppressWarnings({"unused", "unchecked"})
public class TestPost {

	//POST request (Create User) with hard-coded values
	@Test(enabled=true)
	public void verifyCreateUserFunctionalityWhenDataIsHardCoded()
	{					
	    //Request headers	
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive");
	
		//Request body
		JSONObject requestObject = new JSONObject();
		requestObject.put( "name","morpheus");
		requestObject.put(  "job", "leader");
	 
		//Creating the Request object
		APIRequest apiRequestPost = new APIRequest("post","/api/users",headers,requestObject);
	 
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequestPost);
	
		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		//System.out.println(response.getHeaders());
			
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(),201);
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 201 Created");
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
		
	}
	
	
	
	//POST request (Create User) with details passed from json file
	@Test (enabled=true)
	 public void verifyCreateUserFunctionalityWhenDataIsPassedFromJson() {
		
		//Creating the JSON file path
		String jsonFileName="CreateUser2.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
	
		//Request headers	
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive");
		
		//Creating the Request object
		APIRequest apiRequestPost=new APIRequest(filePath);
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response=apiHelper.hitAPI(apiRequestPost);
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
		
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(),201);
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 201 Created");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
		  
		 
	}
	 
	 
	//POST request (Create user) with details passed from json file and request is dynamic
	@Test(enabled=true)
	public void verifyCreateUserFunctionalityWhenDataPassedFromJsonAndDynamicRequestBody() {
		 
		//Creating the JSON file path
		String jsonFileName="CreateUser3.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
			
		//Request headers	
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive");
		
		//Creating Request body as a hashMap for dynamically replacing the existing request body 
		HashMap<String,String> metaInfo = new HashMap<String,String>();
		//Solving the data type issue
		metaInfo.put("name", "Ram");
		metaInfo.put("job", "QA");
	
		//Creating the Request object
		APIRequest apiRequestPost=new APIRequest(filePath,metaInfo);
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response=apiHelper.hitAPI(apiRequestPost);
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
		
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(),201);
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 201 Created");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
		  
		 
	}
	 
	
	//POST request (Create user) with details passed from json file and request is passed as a pojo
	@Test(enabled=true)

	public void verifyCreateUserFunctionalityWhenDataPassedFromJsonAndRequestBodyAsPojo() {
		 
		//Creating the JSON file path
		String jsonFileName="CreateUser4.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
		
		//Reading the data from JSON file and obtaining the jsonObject 
		JSONObject jsonObj = JsonProcessor.readFromJsonFile(filePath);
		
		//Request headers	
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive"); 
		
		//Creating object of request pojo
		RequestPojoPost requestPojoPost = new RequestPojoPost();
		requestPojoPost.setName("Raj");
		requestPojoPost.setJob("leader");
				
		//Obtaining the request as JSON Object
		JSONObject requestJSONObject = JsonProcessor.stringToJsonObject(requestPojoPost.toString());
						
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
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 201 Created");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);

		
	} 
	 

	//POST request (Register user) with details passed from json file
	@Test(enabled=true)
	public void verifyUserRegistrationSuccessfulWhenDataIsPassedFromJson() {
		
		//Request headers
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive");
	
		//Creating the JSON file path
		String jsonFileName="UserRegistrationSuccessful.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
		
		//Request body
		JSONObject requestBody = new JSONObject();
		requestBody.put("username", "cerulean");
		requestBody.put("email", "eve.holt@reqres.in");
		requestBody.put("password", "pistol@123");
		
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(filePath);
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		
		//Console output of the response body and headers
		System.out.println(response.body().asPrettyString());
		System.out.println(response.getHeaders());
		
		//Asserting the response details
		Assert.assertEquals(response.statusCode(), 201);
		Assert.assertEquals(response.contentType().toString(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 201 Created");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
		
				
	}
	
	
	//POST request (User Registration UnSuccessful) with details passed from json file and request is dynamic
	@Test(enabled=true) 
	public void verifyUserRegistrationUnsuccessfulWhenDataPassedFromJsonAndDynamicRequestBody() {

		//Request headers
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive");	
		
		//Creating the JSON file path
		String jsonFileName="UserRegistrationUnSuccessful.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
		
		//Creating Request body as a hashMap for dynamically replacing the existing request body 
		HashMap<String, String> metaInfo = new HashMap<String, String>();
		metaInfo.put("email", "sydney@fife");
		
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(filePath, metaInfo);
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
		
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(), 400);
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 400 Bad Request");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
		
		
	}
	
	

	//POST request (User Login UnSuccessful) with hard-coded values
	@Test(enabled=true) 
	public void verifyUserLoginUnsuccessfulWhenDataIsHardCoded() {
			
		//Request headers
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive");
		
		//Request body
		JSONObject requestBody = new JSONObject();
		requestBody.put("email","peter@klaven");
			
		//Creating the Request object
		APIRequest apiRequest = new APIRequest("post","https://reqres.in/api/login",headers,requestBody);
			
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
			
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
			
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(), 400);
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 400 Bad Request");
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
			
	}
	

}


