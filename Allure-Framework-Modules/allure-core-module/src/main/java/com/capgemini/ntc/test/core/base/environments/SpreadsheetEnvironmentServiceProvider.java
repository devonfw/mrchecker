package com.capgemini.ntc.test.core.base.environments;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.Provider;

public class SpreadsheetEnvironmentServiceProvider implements Provider<EnvironmentService> {
	
	public SpreadsheetEnvironmentService get() {
		String path = getClass().getClassLoader()
				.getResource("")
				.getPath() + "/enviroments/environments.csv";
		
		new SpreadsheetEnvironmentService.SingletonBuilder(path).build();
		BFLogger.logDebug("Reading environment file: " + path);
		return SpreadsheetEnvironmentService.INSTANCE;
	}
}