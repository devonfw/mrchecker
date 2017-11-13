package com.capgemini.ntc.test.core.base.environments;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.Provider;

public class SpreadsheetEnvironmentServiceProvider implements Provider<EnvironmentService> {
	
	public SpreadsheetEnvironmentService get() {
		SpreadsheetEnvironmentService environmentService = SpreadsheetEnvironmentService.INSTANCE;
		
		String path = getClass().getClassLoader()
				.getResource("")
				.getPath() + "/enviroments/environments.csv";
		
		environmentService.setPath(path);
		BFLogger.logDebug("Reading environment file: " + path);
		return environmentService;
	}
}