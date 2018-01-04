package com.capgemini.ntc.test.core.base.user;

import com.capgemini.ntc.test.core.logger.BFLogger;

/**
 * Exception for USER provider related issues
 * 
 * @author
 */
public class UserProviderException extends RuntimeException {
	
	private static final long serialVersionUID = 8578188391655275269L;
	
	public UserProviderException() {
		this("");
	}
	
	public UserProviderException(String message) {
		super(message);
		BFLogger.logDebug(message);
	}
	
	public UserProviderException(Exception e, String message) {
		super(message, e);
		BFLogger.logError(message);
	}
}