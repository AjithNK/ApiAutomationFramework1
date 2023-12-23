package test;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.hamcrest.object.HasEqualValues;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import core.APIHelper;
import core.APIRequest;
import io.restassured.response.Response;

public class TestPatch {
	
	@Test
	public void testPatch() {
		
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

}
