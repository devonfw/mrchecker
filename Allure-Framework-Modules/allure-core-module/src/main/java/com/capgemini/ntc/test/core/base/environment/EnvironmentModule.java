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
	EnvironmentService provideEnvironmentService() {
		String path = System.getProperty("user.dir") + Paths.get("/resources/enviroments/environments.csv");
		
		// String path = getClass().getClassLoader().getResource("").getPath() + "/enviroments/environments.csv";
		SpreadsheetEnvironmentService.init(path);
		return SpreadsheetEnvironmentService.getInstance();	
	}
	
}
