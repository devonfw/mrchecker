package com.capgemini.ntc.selenium.core.exceptions;

import com.capgemini.ntc.test.core.logger.BFLogger;

/**
 * Time out exection for multi windows in browser to open
 * 
 * @author
 */
public class BFWaittingForBrowserWindowsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5905831788790013840L;
	
	public BFWaittingForBrowserWindowsException(String message) {
		BFLogger.logError(message);
	}
}
