package com.capgemini.mrchecker.test.core.base.environment;

import java.nio.file.Paths;

import com.capgemini.mrchecker.test.core.base.environment.providers.SpreadsheetEnvironmentService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class EnvironmentModule extends AbstractModule {
	
	@Override
	protected void configure() {
		
	}
	
	@Provides
	IEnvironmentService provideSpreadsheetEnvironmentService() {
		String path = System.getProperty("user.dir") + Paths.get("/src/resources/enviroments/environments.csv");
		
		String environment = "DEV";
		SpreadsheetEnvironmentService.init(path, environment);
		return SpreadsheetEnvironmentService.getInstance();
	}
	
}
