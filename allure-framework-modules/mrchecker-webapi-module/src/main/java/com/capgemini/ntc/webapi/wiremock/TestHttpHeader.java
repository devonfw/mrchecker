package com.capgemini.ntc.webapi.wiremock;

public class TestHttpHeader {
	
	private String	name;
	private String	value;
	
	public static TestHttpHeader withHeader(String name,
			String value) {
		return new TestHttpHeader(name, value);
	}
	
	public TestHttpHeader(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getValue() {
		return value;
	}
	
}
