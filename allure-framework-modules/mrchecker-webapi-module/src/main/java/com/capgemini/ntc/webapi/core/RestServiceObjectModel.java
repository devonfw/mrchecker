package com.capgemini.ntc.webapi.core;

import java.net.URL;

import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;

public class RestServiceObjectModel extends BasePageWebAPI {
	
	private String	hostName;
	private String	path;
	private URL		url;
	private String	serviceUrl;
	
	private RequestSpecification requestSpecification;
	
	public RestServiceObjectModel(RequestSpecification requestSpecification, String hostname, String path) {
		this.requestSpecification = requestSpecification;
		this.hostName = hostname;
		this.path = path;
		this.serviceUrl = this.hostName + this.path;
		
	}
	
	protected void configureBasicLogin(String userName, String password) {
		this.requestSpecification.auth()
						.basic(userName, password);
	}
	
	protected RequestSpecification getRequestSpecyfication() {
		return requestSpecification;
	}
	
	public String getHostName() {
		return hostName;
	}
	
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getServiceUrl() {
		return serviceUrl;
	}
	
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}
	
	public void addParam(String name, String value) {
		Header header = new Header(name, value);
		getRequestSpecyfication().header(header);
	}
	
	@Override
	public String getMessage() {
		// TASK Auto-generated method stub
		return null;
	}
}
