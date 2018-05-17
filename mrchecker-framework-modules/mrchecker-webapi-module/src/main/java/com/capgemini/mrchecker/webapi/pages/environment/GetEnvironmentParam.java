package com.capgemini.mrchecker.webapi.pages.environment;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.exceptions.BFInputDataException;

public enum GetEnvironmentParam {
	
	// Name if enum must be in line with cell name in /src/resources/vironments/environment.csv
	HTTPBIN;
	
	public String getValue() {
		
		if (null == BaseTest.getEnvironmentService()) {
			throw new BFInputDataException("Environment Parameters class wasn't initialized properly");
		}
		
		return BaseTest.getEnvironmentService()
						.getValue(this.name());
		
	}
	
	@Override
	public String toString() {
		
		return this.getValue();
		
	}
	
}
