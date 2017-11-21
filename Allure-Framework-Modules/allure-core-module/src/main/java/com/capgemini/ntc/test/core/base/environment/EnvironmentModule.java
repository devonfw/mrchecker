package com.capgemini.ntc.test.core.base.environment;

import com.capgemini.ntc.test.core.base.environment.providers.SpreadsheetEnvironmentService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class EnvironmentModule extends AbstractModule {
	
	@Override
	protected void configure() {
		
	}
	
	@Provides
	EnvironmentService provideSpreadsheetEnvironmentService() {
		String path = getClass().getClassLoader()
				.getResource("")
				.getPath() + "/enviroments/environments.csv";
		String environment = "DEV";
		SpreadsheetEnvironmentService.init(path, environment);
		return SpreadsheetEnvironmentService.getInstance();
	}
	
}
