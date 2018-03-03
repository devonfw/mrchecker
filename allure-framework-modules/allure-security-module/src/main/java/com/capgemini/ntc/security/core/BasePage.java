package com.capgemini.ntc.security.core;

import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.BaseTestWatcher;
import com.capgemini.ntc.test.core.ITestObserver;
import com.capgemini.ntc.test.core.ModuleType;
import com.capgemini.ntc.test.core.analytics.IAnalytics;
import com.capgemini.ntc.test.core.base.environment.EnvironmentModule;
import com.capgemini.ntc.test.core.base.environment.IEnvironmentService;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.Guice;

abstract public class BasePage implements ITestObserver {
	
	private static IEnvironmentService	environmentService;
	private final static IAnalytics		analytics;
	public final static String			analitycsCategoryName	= "Security-Module";
	
	static {
		// Get analytics instance created in BaseTets
		analytics = BaseTest.getAnalytics();
		
		// Read Environment variables either from environmnets.csv or any other input data.
		setEnvironmetInstance();
	}
	
	public static IAnalytics getAnalytics() {
		return BasePage.analytics;
	}
	
	public BasePage() {
		this.addObserver();
	}
	
	@Override
	public void addObserver() {
		BaseTestWatcher.addObserver(this);
	}
	
	@Override
	public void onTestFailure() {
		BFLogger.logDebug("BasePage.onTestFailure    " + this.getClass()
				.getSimpleName());
	}
	
	@Override
	public void onTestSuccess() {
		// All actions needed while test method is success
		BFLogger.logDebug("BasePage.onTestSuccess    " + this.getClass()
				.getSimpleName());
	}
	
	@Override
	public void onTestFinish() {
		// All actions needed while test class is finishing
		BFLogger.logDebug("BasePage.onTestFinish   " + this.getClass()
				.getSimpleName());
		BaseTestWatcher.removeObserver(this);
	}
	
	@Override
	public void onTestClassFinish() {
		BFLogger.logDebug("BasePage.onTestClassFinish   " + this.getClass()
				.getSimpleName());
	}
	
	@Override
	public ModuleType getModuleType() {
		return ModuleType.SECURITY;
	}
	
	private static void setEnvironmetInstance() {
		environmentService = Guice.createInjector(new EnvironmentModule())
				.getInstance(IEnvironmentService.class);
	}
	
}
