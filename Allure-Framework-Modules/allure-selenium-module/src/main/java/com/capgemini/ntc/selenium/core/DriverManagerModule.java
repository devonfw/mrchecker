package com.capgemini.ntc.selenium.core;

import com.capgemini.ntc.test.core.base.environment.EnvironmentService;
import com.capgemini.ntc.test.core.base.environment.providers.SpreadsheetEnvironmentServiceProvider;
import com.google.inject.AbstractModule;


public class DriverManagerModule extends AbstractModule {

	@Override
	protected void configure() {
//		bind(EnvironmentService.class).toProvider(SpreadsheetEnvironmentServiceProvider.class);
//		bind(RuntimeParameters.class).to(SystemRuntimeParameters.class);
		
	}
	
}
