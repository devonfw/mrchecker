package com.capgemini.ntc.test.core.base.environments;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.Provider;

public class SpreadsheetEnvironmentServiceProvider implements Provider<EnvironmentService> {
	
	public SpreadsheetEnvironmentService get() {
		String path = "C:\\Repo\\devonfw-testing\\Allure-Framework-Modules\\allure-selenium-module\\src\\resources\\enviroments\\environments.csv";
		
		// String path = getClass().getClassLoader()
		// .getPath() + "/enviroments/environments.csv";
		
		BFLogger.logDebug("Reading environment file: " + path);
		new SpreadsheetEnvironmentService.SingletonBuilder(path).build();
		return SpreadsheetEnvironmentService.INSTANCE;
	}
}