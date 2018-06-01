package com.capgemini.mrchecker.selenium.core.exceptions;

import java.awt.AWTException;

public class BFRobotInitilizationException extends RuntimeException {
	
	public BFRobotInitilizationException(AWTException e) {
		System.err.println("Unable to execute Robot action. \n" + e.getStackTrace()
				.toString());
		
	}
}
