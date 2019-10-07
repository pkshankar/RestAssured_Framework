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

	String baseUrl, serviceUrl, accessToken;

	public GetUserApiTest() {

		super();
	}

	@BeforeMethod
	public void setUp() {

		baseUrl = prop.getProperty("baseUrl");
		serviceUrl = prop.getProperty("getUserListServiceUrl");
		accessToken = prop.getProperty("accessToken");

	}

	@Test
	public void getUserApiTest() {

		Response response = RestClient.GetCall(baseUrl, "json", true, "access-token", accessToken, "GET", serviceUrl);
		Assert.assertEquals(RestClient.getStatusCode(response), HttpResponseCode.HTTP_RESPONSE_CODE_200);
		
		// VALIDATE totalCount
		JsonPath jPath = RestClient.getJsonPath(response);
//		String totalCount = jPath.getString("_meta.totalCount");
//		Assert.assertEquals(totalCount, "1893");
		System.out.println("**********************************");
		ArrayList al = jPath.get("result");
		HashMap<String, Object> hMap = (HashMap<String, Object>) al.get(0);
		//System.out.println(hMap.get("first_name"));
		for(Map.Entry<String, Object> map : hMap.entrySet()) {
			
			System.out.println("KEY = " +map.getKey() +" VALUE IS = " + map.getValue());
		}
	}

}
