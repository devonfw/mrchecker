package com.capgemini.ntc.test.core.base.environment;

import com.capgemini.ntc.test.core.base.environment.providers.SpreadsheetEnvironmentService;
import com.google.inject.AbstractModule;

public class EnvironmentModule extends AbstractModule {
	
	@Override
	protected void configure() {
		bind(EnvironmentService.class).to(SpreadsheetEnvironmentService.class);
	}
}
