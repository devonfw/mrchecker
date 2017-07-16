package com.capgemini.ntc.exceptions;

import com.capgemini.ntc.logger.BFLogger;

public class BFInputDataException extends AssertionError {

	private static final long serialVersionUID = 6054714830892676552L;
	private static String exceptionMessage;

	/**
	 * This exception should be thrown when test data contains false information, e.g.: invalid USER, USER without
	 * necessary Account, which prevents test from succeeding.
	 * 
	 * @param message
	 */
	public BFInputDataException(String message) {
		super(generateExceptionMessage(message));
		BFLogger.logError(exceptionMessage);
	}

	private static String generateExceptionMessage(String message) {
		exceptionMessage = "For user with USER [" +  "]: " + message;
		return exceptionMessage;
	}
}
