package com.qa.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.codes.HttpResponseCode;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetUserApiTest extends TestBase {

	String baseUrl, serviceUrl, authorization;
	int pageCountExpected = 84;
	Response response;

	public GetUserApiTest() {

		super();
	}

	@BeforeMethod
	public void setUp() {

		baseUrl = prop.getProperty("baseUrl");
		serviceUrl = prop.getProperty("getUserListServiceUrl");
		authorization = prop.getProperty("authorization");
		HashMap<String, String> map = new HashMap<>();
		map.put("AUTHORIZATION", authorization);
		response = RestClient.GetCall(baseUrl, "json", false, "GET", serviceUrl, map);

	}

	// Validate status code
	@Test
	public void getUserApiStatusCodeTest() {

		Assert.assertEquals(RestClient.getStatusCode(response), HttpResponseCode.HTTP_RESPONSE_CODE_200);

	}

	// Validate page count
	@Test
	public void getUserApiPageCountTest() {

		JsonPath jPath = RestClient.getJsonPath(response);
		String pageCount = jPath.getString("_meta.pageCount");
		Assert.assertEquals(Integer.parseInt(pageCount), pageCountExpected);

	}

}
