package com.capgemini.ntc.test.core.base.environment;

import java.nio.file.Paths;

import com.capgemini.ntc.test.core.base.environment.providers.SpreadsheetEnvironmentService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class EnvironmentModule extends AbstractModule {
	
	@Override
	protected void configure() {
		
	}
	
	@Provides
	IEnvironmentService provideSpreadsheetEnvironmentService() {
		String path = System.getProperty("user.dir") + Paths.get("/src/resources/enviroments/environments.csv");
		String secretPath = System.getProperty("user.dir") + Paths.get("/src/resources/enviroments/secret.txt");
		
		String environment = "DEV";
		SpreadsheetEnvironmentService.init(path, environment, secretPath);
		return SpreadsheetEnvironmentService.getInstance();
	}
	
}
