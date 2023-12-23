package test;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import core.APIHelper;
import core.APIRequest;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;

public class TestDelete {
	
	@Test
	public void testDelete() {
		
		//Creating Request object
		APIRequest apiRequest = new APIRequest("delete","https://reqres.in/api/users/10");
		
		//Creating the object of APIHelper class & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());

		//Assertions on the response
		Assert.assertEquals(response.getStatusCode(), 204);
		Assert.assertEquals(response.getContentType(), "");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 204 No Content");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MICROSECONDS)<3000000);  
		
		
		
		
	}

}
