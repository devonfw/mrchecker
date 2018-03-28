package com.capgemini.ntc.webapi.core;

import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.BaseTestWatcher;
import com.capgemini.ntc.test.core.ITestObserver;
import com.capgemini.ntc.test.core.ModuleType;
import com.capgemini.ntc.test.core.analytics.IAnalytics;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.base.driver.DriverManager;

abstract public class BaseWebApi implements ITestObserver {
	
	private static DriverManager	driver					= null;
	private final static IAnalytics	analytics;
	public final static String		analitycsCategoryName	= "WebApi-Module";
	
	static {
		// Get analytics instance created in BaseTets
		analytics = BaseTest.getAnalytics();
		
		// Read Environment variables either from environmnets.csv or any other input data.
		setEnvironmetInstance();
	}
	
	public static IAnalytics getAnalytics() {
		return BaseWebApi.analytics;
	}
	
	public BaseWebApi() {
		this(getDriver());
	}
	
	public BaseWebApi(DriverManager driver) {
		this.addObserver();
	}
	
	@Override
	public void addObserver() {
		BaseTestWatcher.addObserver(this);
	}
	
	@Override
	public void onTestFailure() {
		BFLogger.logDebug("BaseWebApi.onTestFailure    " + this.getClass()
				.getSimpleName());
	}
	
	@Override
	public void onTestSuccess() {
		BFLogger.logDebug("BaseWebApi.onTestSuccess    " + this.getClass()
				.getSimpleName());
	}
	
	@Override
	public void onTestFinish() {
		BFLogger.logDebug("BaseWebApi.onTestFinish   " + this.getClass()
				.getSimpleName());
		BaseTestWatcher.removeObserver(this);
	}
	
	@Override
	public void onTestClassFinish() {
		BFLogger.logDebug("BaseWebApi.onTestClassFinish   " + this.getClass()
				.getSimpleName());
		BFLogger.logDebug("driver: " + getDriver().toString());
		DriverManager.closeDriver();
	}
	
	@Override
	public ModuleType getModuleType() {
		return ModuleType.WEBAPI;
	}
	
	public static DriverManager getDriver() {
		if (driver == null) {
			driver = new DriverManager();
		}
		return driver;
	}
	
	private static void setEnvironmetInstance() {
		/*
		 * Environment variables either from environmnets.csv or any other input data. For now there is no properties
		 * settings file for Selenium module. In future, please have a look on Core Module IEnvironmentService
		 * environmetInstance = Guice.createInjector(new EnvironmentModule()) .getInstance(IEnvironmentService.class);
		 */
	}
	
}
