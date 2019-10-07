package com.qa.test;

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

	String baseUrl, serviceUrl, accessToken;

	public PostUserApiTest() {

		super();
	}

	@BeforeMethod
	public void setUp() {

		baseUrl = prop.getProperty("baseUrl");
		serviceUrl = prop.getProperty("getUserListServiceUrl");
		accessToken = prop.getProperty("accessToken");

	}

	@DataProvider
	public String[][] getData() {

		return TestUtil.getDataExcel(FileLocation.CREATE_USER_FILE_LOCATION, "UserData");

	}

	@Test(dataProvider = "getData")
	public void createUserApiTest(String first_name, String last_name, String gender, String email, String status) {

		CreateUser userObj = new CreateUser(first_name, last_name, gender, email, status);
		Response response = RestClient.PostCall(baseUrl, "json", true, "access-token", accessToken, "POST", serviceUrl, userObj);
		response.prettyPrint();
	}

}
