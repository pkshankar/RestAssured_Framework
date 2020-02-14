package com.qa.test;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.pojo.CreateUser;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PutUserApiTest extends TestBase {

	String baseUrl, serviceUrl, authorization;
	HashMap<String, String> map;

	public PutUserApiTest() {

		super();
	}

	@BeforeMethod
	public void setUp() {

		baseUrl = prop.getProperty("baseUrl");
		serviceUrl = prop.getProperty("getUserListServiceUrl");
		authorization = prop.getProperty("authorization");
		map = new HashMap<>();
		map.put("AUTHORIZATION", authorization);

	}

	@Test
	public void updateUserApiTest() {

		CreateUser createUser = new CreateUser();
		createUser.setFirst_name("Peter2");
		createUser.setLast_name("Johnson2");
		createUser.setGender("male");
		createUser.setEmail("peterjohnson2@zmail.com");
		createUser.setStatus("active");

		Response postResponse = RestClient.PostCall(baseUrl, "json", false, "POST", serviceUrl, map, createUser);
		JsonPath jPath = postResponse.jsonPath();
		String createdUserId = jPath.get("result.id");
		createUser.setEmail("peter@newmail.com");

		Response putResponse = RestClient.PutCall(baseUrl, "json", false, "PUT", serviceUrl + "/" + createdUserId, map,
				createUser);
		putResponse.prettyPrint();

	}

}
