package com.capgemini.ntc.webapi.core.base.driver;

public interface IMockDriver {
	
	/**
	 * @return Http port number
	 */
	int port();
	
	/**
	 * @return Https port number
	 */
	int httpsPort();
	
	/**
	 * @return Start Mock sever
	 */
	void start();
	
	/**
	 * Stop Mock server
	 */
	void stop();
	
}
