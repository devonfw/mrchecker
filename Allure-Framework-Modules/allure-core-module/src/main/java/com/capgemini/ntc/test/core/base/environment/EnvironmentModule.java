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
		SpreadsheetEnvironmentService.init(path);
		return SpreadsheetEnvironmentService.getInstance();
	}
	
}
