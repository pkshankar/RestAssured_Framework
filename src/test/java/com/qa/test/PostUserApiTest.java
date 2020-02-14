package com.qa.test;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.filelocation.FileLocation;
import com.qa.pojo.CreateUser;
import com.qa.util.TestUtil;

import io.restassured.response.Response;

public class PostUserApiTest extends TestBase {

	String baseUrl, serviceUrl, authorization;
	HashMap<String, String> map;

	public PostUserApiTest() {

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

	@DataProvider
	public String[][] getData() {

		return TestUtil.getDataExcel(FileLocation.CREATE_USER_FILE_LOCATION, "UserData");

	}

	@Test(dataProvider = "getData")
	public void createUserApiTest(String first_name, String last_name, String gender, String email, String status) {

		CreateUser userObj = new CreateUser(first_name, last_name, gender, email, status);
		Response response = RestClient.PostCall(baseUrl, "json", false, "POST", serviceUrl, map, userObj);
		response.prettyPrint();
	}

}
