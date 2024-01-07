package test;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.APIHelper;
import core.APIRequest;
import core.JsonProcessor;
import io.restassured.response.Response;
import pojo.listResources.ListResources;
import pojo.listUsers.ListUsers;
import pojo.singleResource.SingleResource;
import pojo.singleUser.SingleUser;

@SuppressWarnings("unused")
public class TestGet {
	
	//GET request (Get all Users) with hard-coded values
	@Test(enabled=true)
	public void verifyGetAllUsersFunctionalityWhenDataIsHardCoded() {
		
		//Creating the Request object
		APIRequest apiRequest = new APIRequest("get", "/api/users?page=2");
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
		
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
		
		
		//Validating the Response body
		
		ListUsers listUsers = null;
		ObjectMapper objectMapper = new ObjectMapper();
		
		//Converting the response object into string & then into json object
		JSONObject jsonObj = JsonProcessor.stringToJsonObject(response.asString());

		//Converting the json object into string & then into pojo object
		try {		
			
			listUsers = objectMapper.readValue(jsonObj.toString(), ListUsers.class);
			
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}

		//Asserting various components of response body
		
		Assert.assertEquals(listUsers.getPage(), 2);
		Assert.assertEquals(listUsers.getPer_page(), 6);
		Assert.assertEquals(listUsers.getTotal(),12);
		Assert.assertEquals(listUsers.getTotal_pages(),2);
		
		Assert.assertEquals(listUsers.getData().get(0).getFirst_name(),"Michael");
		Assert.assertEquals(listUsers.getData().get(5).getEmail(),"rachel.howell@reqres.in");
		
		Assert.assertEquals(listUsers.getSupport().getText(), "To keep ReqRes free, contributions towards server costs are appreciated!");
		Assert.assertEquals(listUsers.getSupport().getUrl(), "https://reqres.in/#support-heading");
		
		
	}
	
	//GET request (Get Single User Details) with details passed from json file
	@Test(enabled=true)
	public void verifyGetSingleUserDetailsWhenDataIsPassedFromJson() {
		
		//Creating the JSON file path
		String jsonFileName="SingleUser.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
		
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(filePath);
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		 
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
		
		
		//Validating the Response body
		
		SingleUser singleUser = null;
		ObjectMapper objectMapper = new ObjectMapper();
				
		//Converting the response object into string & then into json object
		JSONObject jsonObj = JsonProcessor.stringToJsonObject(response.asString());

		//Converting the json object into string & then into pojo object
		try {		
					
			singleUser = objectMapper.readValue(jsonObj.toString(), SingleUser.class);
					
		} 
				
		catch (Exception e) {
			e.printStackTrace();
		}

		//Asserting various components of response body
		
		Assert.assertEquals(singleUser.getData().getId(),2);
		Assert.assertEquals(singleUser.getData().getFirst_name(),"Janet");
		Assert.assertEquals(singleUser.getData().getLast_name(),"Weaver");
		Assert.assertEquals(singleUser.getData().getEmail(),"janet.weaver@reqres.in");
		Assert.assertEquals(singleUser.getData().getAvatar(),"https://reqres.in/img/faces/2-image.jpg");
		
		Assert.assertEquals(singleUser.getSupport().getUrl(),"https://reqres.in/#support-heading");
		Assert.assertEquals(singleUser.getSupport().getText(), "To keep ReqRes free, contributions towards server costs are appreciated!");
		
	}
	
	 
	//GET request (Get all Users) with details passed from json file
	@Test(enabled = true)
	public void verifyGetAllUsersFunctionalityWhenDataIsPassedFromJson() {
		
		//Creating the JSON file path
		String jsonFileName="ListUsers.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
		
		//Obtaining the values into jsonObject from the json file
		JSONObject jsonObject = JsonProcessor.readFromJsonFile(filePath);
		
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(jsonObject.get("requestType").toString(),
				jsonObject.get("requestApiPath").toString());
		
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
		  
		//Validating the Response body
		
		ListUsers listUsers = null;
		ObjectMapper objectMapper = new ObjectMapper();
						
		//Converting the response object into string & then into json object
		JSONObject jsonObj = JsonProcessor.stringToJsonObject(response.asString());

		//Converting the json object into string & then into pojo object
		try {		
							
			listUsers = objectMapper.readValue(jsonObj.toString(), ListUsers.class);
							
		} 
						
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//Asserting various components of response body
		
		Assert.assertEquals(listUsers.getPage(), 2);
		Assert.assertEquals(listUsers.getPer_page(), 6);
		Assert.assertEquals(listUsers.getTotal(),12);
		Assert.assertEquals(listUsers.getTotal_pages(),2);
				
		Assert.assertEquals(listUsers.getData().get(0).getFirst_name(),"Michael");
		Assert.assertEquals(listUsers.getData().get(5).getEmail(),"rachel.howell@reqres.in");
		
		Assert.assertEquals(listUsers.getSupport().getText(), "To keep ReqRes free, contributions towards server costs are appreciated!");
		Assert.assertEquals(listUsers.getSupport().getUrl(), "https://reqres.in/#support-heading");

	}
	
	 
	//GET request (Single User Not Found) with details passed from json file
	@Test(enabled = true)
	public void verifyGetSingleUserNotFoundWhenDataIsPassedFromJson() {
		
		//Creating the JSON file path
		String jsonFileName="SingleUserNotFound.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
		
		//Obtaining the values into jsonObject from the json file
		JSONObject jsonObject = JsonProcessor.readFromJsonFile(filePath);
		
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(jsonObject.get("requestType").toString(),
				jsonObject.get("requestApiPath").toString());
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
		
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(),404);
		Assert.assertEquals(response.body().asString(),"{}");
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 404 Not Found");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
	
	}
	
	
	//GET request (Get all Resources) with details passed from json file
	@Test(enabled = true)
	public void verifyGetAllResourcesFunctionalityWhenDataIsPassedFromJson() {
	
		//Creating the JSON file path
		String jsonFileName="ListResources.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
		
		//Obtaining the values into jsonObject from the json file
		JSONObject jsonObject = JsonProcessor.readFromJsonFile(filePath);
		
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(jsonObject.get("requestType").toString(),
				jsonObject.get("requestApiPath").toString());
		
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
		
		//Validating the Response body
		
		ListResources listResources = null;
		ObjectMapper objectMapper = new ObjectMapper();
						
		//Converting the response object into string & then into json object
		JSONObject jsonObj = JsonProcessor.stringToJsonObject(response.asString());

		//Converting the json object into string & then into pojo object
		try {		
							
			listResources = objectMapper.readValue(jsonObj.toString(), ListResources.class);
							
		} 
						
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//Asserting various components of response body
		
		Assert.assertEquals(listResources.getPage(), 1);
		Assert.assertEquals(listResources.getPer_page(),6);
		Assert.assertEquals(listResources.getTotal(),12);
		Assert.assertEquals(listResources.getTotal_pages(),2);
		
		Assert.assertEquals(listResources.getData().get(3).getName(),"aqua sky");
		Assert.assertEquals(listResources.getData().get(0).getColor(),"#98B2D1");
		
		Assert.assertEquals(listResources.getSupport().getUrl(),"https://reqres.in/#support-heading");
		Assert.assertEquals(listResources.getSupport().getText(), 
				"To keep ReqRes free, contributions towards server costs are appreciated!");
		
		
	}

	
	//GET request (Get Single Resource) with details passed from json file
	@Test(enabled=true)
	public void verifyGetSingleResourceFunctionalityWhenDataIsPassedFromJson() {
		
		//Creating the JSON file path
		String jsonFileName="SingleResource.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
		
		//Obtaining the values into jsonObject from the json file
		JSONObject jsonObject = JsonProcessor.readFromJsonFile(filePath);
				
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(jsonObject.get("requestType").toString(),
				jsonObject.get("requestApiPath").toString());
				
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
				
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
		
		//Asserting the response details
		Assert.assertEquals(200, response.getStatusCode());
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);	
		
		//Validating the Response body
		
		SingleResource singleResource = null;
		ObjectMapper objectMapper = new ObjectMapper();
						
		//Converting the response object into string & then into json object
		JSONObject jsonObj = JsonProcessor.stringToJsonObject(response.asString());

		//Converting the json object into string & then into pojo object
		try {		
			singleResource = objectMapper.readValue(jsonObj.toString(), SingleResource.class);
							
		} 
						
		catch (Exception e) {
			e.printStackTrace();
		}

		//Asserting various components of response body
		
		Assert.assertEquals(singleResource.getData().getName(),"fuchsia rose");
		Assert.assertEquals(singleResource.getData().getYear(),2001);
		Assert.assertEquals(singleResource.getData().getId(),2);
		Assert.assertEquals(singleResource.getData().getPantone_value(),"17-2031");
		
		Assert.assertEquals(singleResource.getSupport().getUrl(), "https://reqres.in/#support-heading");
		Assert.assertEquals(singleResource.getSupport().getText(),
				"To keep ReqRes free, contributions towards server costs are appreciated!");

		
	}
	
	//GET request (Single Resource Not Found) with details passed from json file
	@Test(enabled=true)
	public void verifyGetSingleResourceNotFoundFunctionalityWhenDataIsPassedFromJson() {

		//Creating the JSON file path
		String jsonFileName="GetSingleResourceNotFound.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
				
		//Obtaining the values into jsonObject from the json file
		JSONObject jsonObject = JsonProcessor.readFromJsonFile(filePath);
				
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(jsonObject.get("requestType").toString(),
						jsonObject.get("requestApiPath").toString());
				
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
				
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
				
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(),404);
		Assert.assertEquals(response.body().asString(),"{}");
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 404 Not Found");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
		
	}
	
	
	//GET request (Getting User Details with a delay in getting response) with details passed from json file
	@Test(enabled=true) 
	public void verifyGetDelayedResponseFunctionalityWhenDataIsPassedFromJson() {
			
		//Creating the JSON file path
		String jsonFileName="DelayedResponse.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
			
		//Creating the Request object
		APIRequest apiRequest = new APIRequest(filePath);
			
		//Creating object of APIHelper & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
			
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
			
		//Asserting the response details
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)>3000);
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(response.getContentType(),"application/json; charset=utf-8");
			
			
	}


	
}


