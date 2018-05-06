package com.capgemini.ntc.webapi.pages.httbin;

import java.util.HashMap;
import java.util.Map;

import com.capgemini.ntc.webapi.core.BasePageWebAPI;
import com.capgemini.ntc.webapi.core.base.driver.DriverManager;
import com.capgemini.ntc.webapi.pages.environment.GetEnvironmentParam;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SimplePOSTPage extends BasePageWebAPI {
	
	private final static String					HOSTNAME	= GetEnvironmentParam.HTTPBIN.getValue();
	private final static String					PATH		= "/post";
	private final static String					ENDPOINT	= HOSTNAME + PATH;
	private final static Map<String, Object>	jsonAsMap	= new HashMap<>();
	
	static {
		jsonAsMap.put("comments", "Delivery Instruction");
		jsonAsMap.put("custemail", "email");
		jsonAsMap.put("custname", "Name");
		jsonAsMap.put("custtel", "Telephone");
		jsonAsMap.put("delivery", "Telephone");
		jsonAsMap.put("size", "large");
		jsonAsMap.put("topping", "bacon");
		jsonAsMap.put("toppiasdasdng", "baconasdads");
		
	}
	
	public Response sendPOSTQuery() {
		return DriverManager.getDriverWebAPI()
						.contentType(ContentType.URLENC)
						.formParams(jsonAsMap)
						.when()
						.post(ENDPOINT);
	}
	
	@Override
	public String getEndpoint() {
		return ENDPOINT;
	}
}