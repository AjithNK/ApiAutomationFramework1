package test;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import assignments.JsonProcessor;
import assignments.Root;
import core.APIHelper;
import core.APIRequest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.ListUser;

public class Test1 {
	@Test
	public void f() {
		
		APIRequest apiRequest = new APIRequest("get", "/api/users?page=2");

		APIHelper apiHelper = new APIHelper();
		
		Response response = apiHelper.hitAPI(apiRequest);
		
		JSONObject jsonObj = JsonProcessor.stringToJsonObject(response.asString());

		// JsonPath - 1st method (suport.data)
		// use pojo classes -2nd method

		ListUser listUser = null;

		ObjectMapper om = new ObjectMapper();
		try {
			listUser = om.readValue(jsonObj.toString(), ListUser.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(listUser.getSupport().getUrl());

	}

}
