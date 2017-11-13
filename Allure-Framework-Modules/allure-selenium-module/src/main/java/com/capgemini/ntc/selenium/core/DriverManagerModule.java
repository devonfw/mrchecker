package com.capgemini.ntc.selenium.core;

import com.capgemini.ntc.test.core.base.environments.EnvironmentService;
import com.capgemini.ntc.test.core.base.environments.SpreadsheetEnvironmentServiceProvider;
import com.google.inject.AbstractModule;


public class DriverManagerModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(EnvironmentService.class).toProvider(SpreadsheetEnvironmentServiceProvider.class);
		
		
	}
	
}
