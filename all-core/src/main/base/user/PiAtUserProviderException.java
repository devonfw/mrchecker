package com.example.core.base.user;

import com.example.core.logger.BFLogger;

/**
 * Exception for USER provider related issues
 * 
 * @author
 *
 */
public class PiAtUserProviderException extends RuntimeException {

	private static final long serialVersionUID = 8578188391655275269L;

	public PiAtUserProviderException() {
		this("");
	}

	public PiAtUserProviderException(String message) {
		super(message);
		BFLogger.logDebug(message);
	}

	public PiAtUserProviderException(Exception e, String message) {
		super(message, e);
		BFLogger.logError(message);
	}
}