package com.capgemini.ntc.example.core;

import com.capgemini.ntc.example.core.base.properties.PropertiesFileSettings;
import com.capgemini.ntc.example.core.base.runtime.RuntimeParameters;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.BaseTestWatcher;
import com.capgemini.ntc.test.core.ITestObserver;
import com.capgemini.ntc.test.core.analytics.IAnalytics;
import com.capgemini.ntc.test.core.base.environment.IEnvironmentService;
import com.capgemini.ntc.test.core.base.properties.PropertiesSettingsModule;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.Guice;

abstract public class BasePage implements ITestObserver {
	
	private final static PropertiesFileSettings	propertiesFileSettings;
	private static IEnvironmentService			environmentService;
	private final static IAnalytics				analytics;
	public final static String					analitycsCategoryName	= "NAME-OF-MODULE";	// Selenium-Module
	
	static {
		// Get analytics instance created in BaseTets
		analytics = BaseTest.getAnalytics();
		
		// Get and then set properties information from selenium.settings file
		propertiesFileSettings = createPropertiesSettings();
		
		// Read System or maven parameters
		setRuntimeParametersSelenium();
		
		// Read Environment variables either from environmnets.csv or any other input data.
		setEnvironmetInstance();
	}
	
	public static IAnalytics getAnalytics() {
		return BasePage.analytics;
	}
	
	@Override
	public void addObserver() {
		BaseTestWatcher.addObserver(this);
	}
	
	@Override
	public void onTestFailure() {
		BFLogger.logDebug("BasePage.onTestFailure");
	}
	
	@Override
	public void onTestSuccess() {
		BFLogger.logDebug("BasePage.onTestSuccess");
	}
	
	@Override
	public void onTestFinish() {
		BFLogger.logDebug("BasePage.onTestFinish");
		BaseTestWatcher.removeObserver(this);
	}
	
	private static PropertiesFileSettings createPropertiesSettings() {
		// Get and then set properties information from settings.properties file
		PropertiesFileSettings propertiesFileSettings = Guice.createInjector(PropertiesSettingsModule.init())
						.getInstance(PropertiesFileSettings.class);
		return propertiesFileSettings;
	}
	
	private static void setRuntimeParametersSelenium() {
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
