package core;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIHelper {
	
	public Response hitAPI(APIRequest apiRequest) {
		
		RestAssured.baseURI = "https://reqres.in";

		RequestSpecification request = RestAssured.given();
		Response response = null;
		
		if(apiRequest.getRequestType().equalsIgnoreCase("get")) {
			 response = request.get(apiRequest.getApiPath());
		}
		
		else if(apiRequest.getRequestType().equalsIgnoreCase("post")) {
		
			//request.post(null);
		}
		return response;
	}

}
