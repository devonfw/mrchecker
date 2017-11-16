package com.capgemini.ntc.test.core.base.environments;

import com.capgemini.ntc.test.core.logger.BFLogger;

public enum Env {
	
	INSTANCE;
	
	private EnvironmentService environmentService;
	
	

	public EnvironmentService getEnvironmentService() {
		return environmentService;
	}
	
	private EnvironmentService build(SingletonBuilder builder) {
		this.environmentService = builder.environmentService;
		return environmentService;
	}
	
	public static class SingletonBuilder {
		
		private final EnvironmentService environmentService; // Mandatory
		
		public SingletonBuilder() {
			this.environmentService = new SpreadsheetEnvironmentServiceProvider().get();
		}
		
		public SingletonBuilder(EnvironmentService environmentService) {
			this.environmentService = environmentService;
			
		}
		
		public EnvironmentService build() {
			BFLogger.logDebug("Reading environment from: " + environmentService.dataSource());
			return Env.INSTANCE.build(this);
		}
		
	}
	
}
