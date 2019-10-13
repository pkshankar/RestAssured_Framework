package com.qa.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class OAuthTwo {

	@BeforeMethod
	public void setUp() {

	}

	@Test
	public void OAuthTwoGetTest() {
		
		RestAssured.baseURI = "http://coop.apps.symfonycasts.com";
		RequestSpecification request = RestAssured.given().auth().oauth2("9789f07fdec2a4693a49b8c5da606ba25269e536");
		Response response = request.get("/api/me");
		response.prettyPrint();
	}
	
	@Test
	public void OAuthTwoPostUnlockBarnTest() {
		
		RestAssured.baseURI = "http://coop.apps.symfonycasts.com";
		RequestSpecification request = RestAssured.given().auth().oauth2("9789f07fdec2a4693a49b8c5da606ba25269e536");
		Response response = request.post("/api/469/barn-unlock");
		//response.prettyPrint();
		JsonPath jPath = response.jsonPath();
		System.out.println(jPath.getString("action"));
				
	}
}
