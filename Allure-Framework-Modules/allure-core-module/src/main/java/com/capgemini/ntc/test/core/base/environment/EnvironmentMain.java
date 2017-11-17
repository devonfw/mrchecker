package com.capgemini.ntc.test.core.base.environment;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.Inject;


public enum EnvironmentMain {
	
	INSTANCE;
	
	/*HOW TO USE Dependency Injection: 
	EnvironmentService environmentService = Guice.createInjector(new EnvironmentModule())
				.getInstance(EnvironmentMain.SingletonBuilder.class)
				.build();*/
	
	
	private EnvironmentService environmentService;
	
	public EnvironmentService getEnvironmentService() {
		return environmentService;
	}
	
	private EnvironmentService build(SingletonBuilder builder) {
		this.environmentService = builder.environmentService;
		BFLogger.logDebug("Reading environment from: " + environmentService.dataSource());
		return environmentService;
	}
	
	public static class SingletonBuilder {
		
		private final EnvironmentService environmentService; // Mandatory
		
		@Inject
		public SingletonBuilder(EnvironmentService environmentService) {
			this.environmentService = environmentService;
		}
		
		
		public EnvironmentService build() {
			BFLogger.logDebug("Reading environment from: " + environmentService.dataSource());
			return EnvironmentMain.INSTANCE.build(this);
		}
		
	}
}
