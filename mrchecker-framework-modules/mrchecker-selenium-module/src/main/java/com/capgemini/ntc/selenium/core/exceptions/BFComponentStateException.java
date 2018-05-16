package com.capgemini.ntc.selenium.core.exceptions;

import com.capgemini.ntc.test.core.logger.BFLogger;

public class BFComponentStateException extends AssertionError {
	
	private static final long serialVersionUID = 2453124693849726543L;
	private static String exceptionMessage;
	
	/**
	 * This exception should be thrown when component state is not as expected, which prevents performing certain
	 * action. Example: User has to to click on a Button, but nothing happens, button is disabled. Calling "throw new
	 * BFComponentStateException("Button", "click", "disabled")" is going to create error message: "Unable to [click]
	 * [Button]. Component is [disabled]."
	 * 
	 * @param componentName
	 * @param action
	 * @param actualState
	 */
	public BFComponentStateException(String componentName, String action, String actualState) {
		super(generateExceptionMessage(componentName, action, actualState));
		BFLogger.logError(exceptionMessage);
	}
	
	private static String generateExceptionMessage(String componentName, String action, String actualState) {
		exceptionMessage = "Unable to [" + action + "] [" + componentName + "]. Component is [" + actualState + "].";
		return exceptionMessage;
	}
}
