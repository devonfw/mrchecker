package com.capgemini.ntc.security;

import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.exceptions.BFInputDataException;

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
						.getServiceAddress(this.name());
		
	}
	
	@Override
	public String toString() {
		return getValue();
	}
	
}
