package com.qa.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.pojo.CreateUser;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PutUserApiTest extends TestBase {

	String baseUrl, serviceUrl, accessToken;

	public PutUserApiTest() {

		super();
	}

	@BeforeMethod
	public void setUp() {

		baseUrl = prop.getProperty("baseUrl");
		serviceUrl = prop.getProperty("getUserListServiceUrl");
		accessToken = prop.getProperty("accessToken");

	}

	@Test
	public void updateUserApiTest() {

		CreateUser createUser = new CreateUser();
		createUser.setFirst_name("Peter");
		createUser.setLast_name("Johnson");
		createUser.setGender("male");
		createUser.setEmail("peterjohnson@zmail.com");
		createUser.setStatus("active");

		Response postResponse = RestClient.PostCall(baseUrl, "json", true, "access-token", accessToken, "POST",
				serviceUrl, createUser);
		JsonPath jPath = postResponse.jsonPath();
		String createdUserId = jPath.get("result.id");
		createUser.setEmail("peter@newmail.com");

		Response putResponse = RestClient.PutCall(baseUrl, "json", true, "access-token", accessToken, "PUT",
				serviceUrl + "/" + createdUserId, createUser);
		putResponse.prettyPrint();

	}

}
