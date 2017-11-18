package com.capgemini.ntc.test.core.base.environment;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.Inject;
import com.google.inject.Singleton;



@interface ForMethod {}


/**
 * @author lucst
 *
 * HOW TO USE Dependency Injection: 
 * EnvironmentService environmentService = Guice.createInjector(new EnvironmentModule())
 * 			.getInstance(EnvironmentMain.init.class)
 *			.getEnvironmentService();
 *
 *
 */

@Singleton
public class EnvironmentParameters {
	
//	private static EnvironmentParameters instance;
	private static EnvironmentService environmentService;
	
	@Inject
	public EnvironmentParameters(EnvironmentService environmentService) {
		BFLogger.logDebug("Reading environment from: " + environmentService.dataSource());
		EnvironmentParameters.environmentService = environmentService;
	}
	
	public static EnvironmentService getEnvironmentService() {
		return EnvironmentParameters.environmentService;
	}
	
	
//	public static EnvironmentParameters getInstance() {
//		return EnvironmentParameters.instance;
//	}
	
//	public static void delInstance() {
//		EnvironmentParameters.instance = null;
//	}
	
	
//	@Inject
//	public static EnvironmentParameters init(@ForMethod EnvironmentService environmentService) {
//		if (instance == null) {
//			synchronized (EnvironmentParameters.class) {
//				if (instance == null) {
//					instance = new EnvironmentParameters(environmentService);
//				}
//			}
//		}
//		return instance;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	INSTANCE;
	
	
//	
//	private EnvironmentService environmentService;
//	
//	public EnvironmentService getEnvironmentService() {
//		return environmentService;
//	}
//	
//	private EnvironmentService build(SingletonBuilder builder) {
//		this.environmentService = builder.environmentService;
//		BFLogger.logDebug("Reading environment from: " + environmentService.dataSource());
//		return environmentService;
//	}
//	
//	public static class SingletonBuilder {
//		
//		private final EnvironmentService environmentService; // Mandatory
//		
//		@Inject
//		public SingletonBuilder(EnvironmentService environmentService) {
//			this.environmentService = environmentService;
//		}
//		
//		
//		public EnvironmentService build() {
//			BFLogger.logDebug("Reading environment from: " + environmentService.dataSource());
//			return EnvironmentParameters.INSTANCE.build(this);
//		}
//		
//	}
}
