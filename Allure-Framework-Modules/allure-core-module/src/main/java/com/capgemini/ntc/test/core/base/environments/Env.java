package com.capgemini.ntc.test.core.base.environments;

public enum Env {
	
	INSTANCE;
	
	private EnvironmentService environmentService;
	
	public EnvironmentService getEnvironmentService() {
		return environmentService;
	}
	
	private EnvironmentService build(SingletonBuilder builder) {
		environmentService = builder.environmentService;
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
			return Env.INSTANCE.build(this);
		}
		
	}
	
}
