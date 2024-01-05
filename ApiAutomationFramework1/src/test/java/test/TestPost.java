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
import io.restassured.http.Headers;
import io.restassured.response.Response;
import requestPojo.RequestPojoPost;

@SuppressWarnings({"unused", "unchecked"})
public class TestPost {

	//POST request (Create User) with hard-coded values
	@Test(enabled=false)
	public void testPost()
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
	@Test (enabled=false)
	 public void testPost2() {
		
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
	@Test(enabled=false)
	public void testPost3() {
		 
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
	 
	 
	 //EDIT COMMENT
	//POST REQUEST 4 - Create User  
	//pass data from json file and request obtained using RequestPojo)
	@Test(enabled=false)

	public void testPostOrchestrationUsingRequestPojo() {
		 
		//Creating the JSON file path
		String jsonFileName="CreateUser4.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
					
		//Request headers	
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type","application/json");
		headers.put("Accept","*/*");
		headers.put("Connection","keep-alive"); 
		
		
		
		
		RequestPojoPost objRequest = new RequestPojoPost();
		
		objRequest.setName("Raj");
		objRequest.setJob("leader");
		
		System.out.println(objRequest);
		
		
		JSONObject requestObject=JsonProcessor.stringToJsonObject(objRequest.toString());
		
		JSONObject jsonObject = JsonProcessor.readFromJsonFile(filePath);
		
		
		
		
		
		
		
		//Creating the Request object
		APIRequest apiRequest= new APIRequest(jsonObject.get("requestType").toString(),
		jsonObject.get("requestApiPath").toString(),headers,requestObject) ;
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
		
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 201 Created");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
		
		 
	} 
	 

	//POST request (Register user) with details passed from json file
	@Test(enabled=true)
	public void testRegistrationSuccessful() {
		
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
	@Test(enabled=false) 
	public void testRegistrationUnSuccessful() {

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
	
	
	//do this using - reading from file and request pojo
	//POST request (Login) with details passed from json file
	@Test(enabled=false) 
	public void testLoginSuccessful() {
		
	}
	
	

	//POST request (Login UnSuccessful) with hard-coded values
	@Test(enabled=false) 
	public void testLoginUnSuccessful() {
			
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


