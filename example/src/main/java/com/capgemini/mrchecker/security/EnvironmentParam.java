package com.capgemini.mrchecker.security;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.exceptions.BFInputDataException;

/**
 * This enumeration holds all environment variables relevant for the security test suite.
 *
 * @author Marek Puchalski, Capgemini
 */
public enum EnvironmentParam {
	SECURITY_CLIENT_ORIGIN,
	SECURITY_SERVER_ORIGIN,
	SECURITY_USER1_NAME,
	SECURITY_USER1_PASSWD;
	
	public String getValue() {
		
		if (null == BaseTest.getEnvironmentService()) {
			throw new BFInputDataException("Environment Parameters class wasn't initialized properly");
		}
		
		return BaseTest.getEnvironmentService()
		        .getValue(this.name());
		
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
}
