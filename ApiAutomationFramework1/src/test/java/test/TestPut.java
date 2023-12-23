package test;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import core.APIHelper;
import core.APIRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

public class TestPut {
	
	
	//PUT request (Update user) with hard-coded values
	@SuppressWarnings("unchecked")
	@Test
	public void updateUser() {
		
		//Header
		HashMap<String, String> header = new HashMap<String,String>();
		
		//Request body
		JSONObject requestBody = new JSONObject();
		requestBody.put("name", "Rajesh");
		requestBody.put("job", "Automation Engineer");
		
		//Creating the Request
		APIRequest apiRequestPut = new APIRequest("put","https://reqres.in/api/users/2",header,requestBody);
		
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
	

}
