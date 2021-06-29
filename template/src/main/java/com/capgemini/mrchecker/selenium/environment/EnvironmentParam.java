package com.capgemini.mrchecker.selenium.environment;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.exceptions.BFInputDataException;

import java.util.Objects;

/**
 * @author lucst Takes values saved in /src/resources/environments/environment.csv When -Denv is not set, then it takes
 *         default DEV
 */
public enum EnvironmentParam {
	
	// Name if enum must be in line with cell name in /src/resources/environments/environment.csv
	WWW_FONT_URL;
	
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
