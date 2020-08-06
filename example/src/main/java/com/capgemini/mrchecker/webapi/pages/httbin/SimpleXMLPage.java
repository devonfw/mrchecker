package com.capgemini.mrchecker.webapi.pages.httbin;

import com.capgemini.mrchecker.webapi.core.BasePageWebAPI;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.capgemini.mrchecker.webapi.pages.environment.GetEnvironmentParam;

import io.restassured.response.Response;

public class SimpleXMLPage extends BasePageWebAPI {
	
	private final static String	HOSTNAME	= GetEnvironmentParam.HTTPBIN.getValue();
	private final static String	PATH		= "/xml";
	private final static String	ENDPOINT	= HOSTNAME + PATH;
	
	public Response getXMLDocument() {
		return DriverManager.getDriverWebAPI()
				.get(ENDPOINT);
	}
	
	@Override
	public String getEndpoint() {
		return ENDPOINT;
	}
	
	/**
	 * Elements of XML that can be used in searches
	 */
	public enum XML_ELEMENT {
		SLIDESHOW("slideshow"),
		ITEM("item");
		
		private String element;
		
		XML_ELEMENT(String element) {
			this.element = element;
		}
		
		public String getElementKey() {
			return element;
		}
	}
	
	/**
	 * Attribute keys that can be used in tests
	 */
	public enum XML_ATTRIBUTE {
		DATE("date");
		
		private String attribute;
		
		XML_ATTRIBUTE(String attribute) {
			this.attribute = attribute;
		}
		
		public String getAttributeKey() {
			return attribute;
		}
	}
}