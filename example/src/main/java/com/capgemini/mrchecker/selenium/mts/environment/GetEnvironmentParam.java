package com.capgemini.mrchecker.selenium.mts.environment;

import java.util.Objects;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.exceptions.BFInputDataException;

public enum GetEnvironmentParam {
	MY_THAI_STAR_URL;
	
	public String getValue() {
		
		if (Objects.isNull(BaseTest.getEnvironmentService())) {
			throw new BFInputDataException("Environment Parameters class wasn't initialized properly");
		}
		
		return BaseTest.getEnvironmentService()
				.getValue(name());
	}
	
	@Override
	public String toString() {
		return getValue();
	}
}
