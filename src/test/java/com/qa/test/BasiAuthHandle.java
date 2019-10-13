package com.qa.test;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BasiAuthHandle {

	@Test
	public void handleBasicAuth() {

		RestAssured.baseURI = "http://the-internet.herokuapp.com";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.auth().basic("admin", "admin");
		Response response = httpRequest.get("/basic_auth");
		response.prettyPrint();
	}

}
