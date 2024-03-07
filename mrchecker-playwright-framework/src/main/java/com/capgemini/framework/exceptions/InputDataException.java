package com.capgemini.framework.exceptions;

import com.capgemini.framework.logger.Logger;

public class InputDataException extends AssertionError {
	
	private static final long   serialVersionUID = 6054714830892676552L;
	private static       String exceptionMessage;
	
	/**
	 * This exception should be thrown when test data contains false information, e.g.: invalid USER, USER without necessary Account, which prevents test from succeeding. Example: User with 12341212
	 * has to click on certain Account in AccountSelector but this account is not present. Calling "throw new BFInputDataException("Account is not present.")" is going to create error message: "For
	 * user with USER [12341212]: Account is not present."
	 *
	 * @param message
	 * 		message
	 */
	public InputDataException(String message) {
		super(generateExceptionMessage(message));
		Logger.logError(exceptionMessage);
	}
	
	private static String generateExceptionMessage(String message) {
		exceptionMessage = message;
		return exceptionMessage;
	}
}
