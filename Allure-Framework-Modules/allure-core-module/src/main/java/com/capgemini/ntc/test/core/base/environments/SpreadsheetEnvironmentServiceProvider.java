package com.capgemini.ntc.test.core.base.environments;

import java.nio.file.Paths;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.Provider;

public class SpreadsheetEnvironmentServiceProvider implements Provider<EnvironmentService> {
	
	public SpreadsheetEnvironmentService get() {
		String path = System.getProperty("user.dir") + Paths.get("/resources/enviroments/environments.csv");
		
//		String path = getClass().getClassLoader().getResource("").getPath() + "/enviroments/environments.csv";
		new SpreadsheetEnvironmentService.SingletonBuilder(path).build();
		return SpreadsheetEnvironmentService.INSTANCE;
	}
}