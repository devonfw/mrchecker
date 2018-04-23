package com.capgemini.ntc.webapi.core;

public interface IWebAPI {
	
	/**
	 * @return Generate SOAP response in String format
	 */
	public String getMessage();
	
	/**
	 * @return URL Endpoint in String format
	 */
	public String getEndpoint();
}
