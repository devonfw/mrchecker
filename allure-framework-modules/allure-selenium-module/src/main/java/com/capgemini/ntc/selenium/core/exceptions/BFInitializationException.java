package com.capgemini.ntc.selenium.core.exceptions;

import com.capgemini.ntc.test.core.logger.BFLogger;

public class BFInitializationException extends RuntimeException {
	private static final long serialVersionUID = 257707912184824239L;
	
	public BFInitializationException() {
		this("");
	}
	
	public BFInitializationException(String message) {
		super(message);
		BFLogger.logDebug(message);
	}
	
	public BFInitializationException(Exception e, String message) {
		super(message, e);
		BFLogger.logError(message);
	}
}