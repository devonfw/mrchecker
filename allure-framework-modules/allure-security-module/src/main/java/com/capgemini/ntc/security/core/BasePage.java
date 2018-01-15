package com.capgemini.ntc.security.core;

import java.util.Arrays;

import com.capgemini.ntc.security.core.base.properties.PropertiesFileSettings;
import com.capgemini.ntc.security.core.base.runtime.RuntimeParameters;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.BaseTestWatcher;
import com.capgemini.ntc.test.core.ITestObserver;
import com.capgemini.ntc.test.core.analytics.IAnalytics;
import com.capgemini.ntc.test.core.base.environment.EnvironmentModule;
import com.capgemini.ntc.test.core.base.environment.IEnvironmentService;
import com.capgemini.ntc.test.core.base.properties.PropertiesSettingsModule;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.Guice;

abstract public class BasePage implements ITestObserver {
	
	private final static PropertiesFileSettings	propertiesFileSettings;
	private final static IAnalytics				analytics;
	public final static String					analitycsCategoryName	= "Security-Module";
	private static IEnvironmentService			environmentInstance;
	
	static {
		analytics = BaseTest.getAnalytics();
		propertiesFileSettings = createPropertiesSettings();
		setRuntimeParametersSelenium();
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
		BFLogger.logDebug(Arrays.asList(RuntimeParameters.values())
						.toString());
	}
	
	private static void setEnvironmetInstance() {
		environmentInstance = Guice.createInjector(new EnvironmentModule())
						.getInstance(IEnvironmentService.class);
	}
	
	public static String getEnvValue(String key) {
		return environmentInstance.getServiceAddress(key);
	}
}
