package test;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import core.APIHelper;
import core.APIRequest;
import io.restassured.response.Response;

public class TestDelete {
	
	//DELETE request (Delete user) with hard-coded values
	@Test(enabled=true)
	public void verifyDeleteUserFunctionalityWhenDataIsHardCoded() {
		
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
		Assert.assertEquals(response.getBody().asPrettyString(),"");
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 204 No Content");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);  
		
	}
	
	
	//DELETE request (Delete user) with details passed from json file
	@Test(enabled=true)
	public void verifyDeleteUserFunctionalityWhenDataIsPassedFromJson() {
		
		
		//Creating the JSON file path
		String jsonFileName="DeleteUser.json";
		String filePath=Paths.get(System.getProperty("user.dir"),"src","main","resources","apis",jsonFileName).toString();
		
		//Creating Request object
		APIRequest apiRequest = new APIRequest(filePath);
		
		//Creating the object of APIHelper class & calling the hitAPI() method using this object and obtaining the response
		APIHelper apiHelper = new APIHelper();
		Response response = apiHelper.hitAPI(apiRequest);
		
		//Console output of the response body and headers
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getHeaders());
		
		//Assertions on the response
		Assert.assertEquals(response.getStatusCode(),204);
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 204 No Content");
		Assert.assertEquals(response.getContentType(), "");
		Assert.assertEquals(response.getBody().asPrettyString(),"");
		Assert.assertTrue(response.getTimeIn(TimeUnit.MILLISECONDS)<3000);
		
		
	}
	
	

}
