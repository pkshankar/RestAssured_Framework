package com.qa.client;

import java.util.HashMap;
import java.util.List;

import com.qa.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	public static Response GetCall(String baseUri, String contentType, boolean log, String httpMethod,
			String serviceUri, HashMap<String, String> hMap) {

		setBaseUri(baseUri);
		RequestSpecification request = createRequest(contentType, log);
		addHeader(request, hMap);
		return getResponse(httpMethod, request, serviceUri);

	}

	public static Response PostCall(String baseUri, String contentType, boolean log, String httpMethod,
			String serviceUri, HashMap<String, String> hMap, Object obj) {

		setBaseUri(baseUri);
		RequestSpecification request = createRequest(contentType, log);
		addHeader(request, hMap);
		request.body(TestUtil.pojoToJson(obj));
		return getResponse(httpMethod, request, serviceUri);
	}

	public static Response PutCall(String baseUri, String contentType, boolean log, String httpMethod,
			String serviceUri, HashMap<String, String> hMap, Object obj) {

		setBaseUri(baseUri);
		RequestSpecification request = createRequest(contentType, log);
		addHeader(request, hMap);
		request.body(TestUtil.pojoToJson(obj));
		return getResponse(httpMethod, request, serviceUri);
	}

	public static Response DeleteCall(String baseUri, String contentType, boolean log, String httpMethod,
			String serviceUri, HashMap<String, String> hMap) {

		setBaseUri(baseUri);
		RequestSpecification request = createRequest(contentType, log);
		addHeader(request, hMap);
		return getResponse(httpMethod, request, serviceUri);
	}

	// PREPARE REQUEST

	public static void setBaseUri(String uri) {

		RestAssured.baseURI = uri;
	}

	public static RequestSpecification createRequest(String contentType, boolean log) {

		RequestSpecification request = null;

		if (log) {

			request = RestAssured.given().log().all();
		} else {

			request = RestAssured.given();
		}

		if (contentType.equalsIgnoreCase("JSON")) {

			request.contentType(ContentType.JSON);
		}

		else if (contentType.equalsIgnoreCase("XML")) {

			request.contentType(ContentType.XML);
		}

		else if (contentType.equalsIgnoreCase("TEXT")) {

			request.contentType(ContentType.TEXT);
		}

		return request;
	}

	public static void addHeader(RequestSpecification request, HashMap<String, String> headerMap) {

		request.headers(headerMap);
	}

	public static Response getResponse(String httpMethod, RequestSpecification request, String serviceUri) {

		return executeApi(httpMethod, request, serviceUri);

	}

	private static Response executeApi(String httpMethod, RequestSpecification request, String serviceUri) {

		Response response = null;

		switch (httpMethod) {
		case "GET":
			response = request.get(serviceUri);
			break;
		case "POST":
			response = request.post(serviceUri);
			break;
		case "PUT":
			response = request.put(serviceUri);
			break;
		case "DELETE":
			response = request.delete(serviceUri);
			break;

		default:
			System.out.println("PLEASE ENTER A VALID HTTP METHOD");
			break;
		}
		return response;
	}

	// RESPONSE

	public static int getStatusCode(Response response) {

		return response.getStatusCode();
	}

	public static String getHeaderValue(Response response, String headerName) {

		return response.getHeader(headerName);
	}

	public static List<Header> getAllResponseHeaders(Response response) {

		Headers headers = response.getHeaders();
		List<Header> listHeaders = headers.asList();
		return listHeaders;
	}

	public static int getResponseHeaderCount(Response response) {

		return response.getHeaders().size();
	}

	public static JsonPath getJsonPath(Response response) {

		return response.jsonPath();
	}
}
