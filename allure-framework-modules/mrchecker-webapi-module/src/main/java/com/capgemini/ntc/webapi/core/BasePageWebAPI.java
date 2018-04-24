package com.capgemini.ntc.webapi.core;

import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.BaseTestWatcher;
import com.capgemini.ntc.test.core.ITestObserver;
import com.capgemini.ntc.test.core.ModuleType;
import com.capgemini.ntc.test.core.analytics.IAnalytics;
import com.capgemini.ntc.test.core.base.properties.PropertiesSettingsModule;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.base.driver.DriverManager;
import com.capgemini.ntc.webapi.core.base.properties.PropertiesFileSettings;
import com.capgemini.ntc.webapi.core.base.runtime.RuntimeParameters;
import com.google.inject.Guice;

abstract public class BasePageWebAPI implements ITestObserver, IWebAPI {
	
	private static DriverManager driver = null;
	
	private final static PropertiesFileSettings	propertiesFileSettings;
	private final static IAnalytics				analytics;
	public final static String					analitycsCategoryName	= "WebAPI-Module";
	
	static {
		// Get analytics instance created in BaseTets
		analytics = BaseTest.getAnalytics();
		
		// Get and then set properties information from selenium.settings file
		propertiesFileSettings = setPropertiesSettings();
		
		// Read System or maven parameters
		setRuntimeParametersWebApi();
		
		// Read Environment variables either from environmnets.csv or any other input data.
		setEnvironmetInstance();
	}
	
	public static IAnalytics getAnalytics() {
		return BasePageWebAPI.analytics;
	}
	
	public BasePageWebAPI() {
		this(getDriver());
	}
	
	public BasePageWebAPI(DriverManager driver) {
		// Add given module to Test core Observable list
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
		BFLogger.logDebug("driver:" + getDriver().toString());
	}
	
	@Override
	public ModuleType getModuleType() {
		return ModuleType.WEBAPI;
	}
	
	public static DriverManager getDriver() {
		if (BasePageWebAPI.driver == null) {
			// Create module driver
			BasePageWebAPI.driver = new DriverManager(propertiesFileSettings);
		}
		return BasePageWebAPI.driver;
	}
	
	private static PropertiesFileSettings setPropertiesSettings() {
		// Get and then set properties information from settings.properties file
		PropertiesFileSettings propertiesFileSettings = Guice.createInjector(PropertiesSettingsModule.init())
						.getInstance(PropertiesFileSettings.class);
		return propertiesFileSettings;
	}
	
	private static void setRuntimeParametersWebApi() {
		// Read System or maven parameters
		BFLogger.logDebug(java.util.Arrays.asList(RuntimeParameters.values())
						.toString());
		
	}
	
	private static void setEnvironmetInstance() {
		/*
		 * Environment variables either from environmnets.csv or any other input data. For now there is no properties
		 * settings file for Selenium module. In future, please have a look on Core Module IEnvironmentService
		 * environmetInstance = Guice.createInjector(new EnvironmentModule()) .getInstance(IEnvironmentService.class);
		 */
		
	}
	
}
