package com.qa.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.pojo.CreateUser;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteUserApiTest extends TestBase {
	
	String baseUrl, serviceUrl, accessToken;

	public DeleteUserApiTest() {

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
		createUser.setFirst_name("Loky");
		createUser.setLast_name("Mers");
		createUser.setGender("female");
		createUser.setEmail("loky@mers.com");
		createUser.setStatus("active");

		Response postResponse = RestClient.PostCall(baseUrl, "json", true, "access-token", accessToken, "POST",
				serviceUrl, createUser);
		JsonPath jPath = postResponse.jsonPath();
		String createdUserId = jPath.get("result.id");
		Response deleteResponse = RestClient.PutCall(baseUrl, "json", true, "access-token", accessToken, "DELETE",
				serviceUrl + "/" + createdUserId, createUser);
		deleteResponse.prettyPrint();

	}

}



