package com.capgemini.mrchecker.webapi.example.base;

import java.util.Objects;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.ModuleType;
import com.capgemini.mrchecker.test.core.Page;
import com.capgemini.mrchecker.test.core.analytics.IAnalytics;
import com.capgemini.mrchecker.test.core.base.properties.PropertiesSettingsModule;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.core.IWebAPI;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.capgemini.mrchecker.webapi.core.base.properties.PropertiesWebAPI;
import com.capgemini.mrchecker.webapi.core.base.runtime.RuntimeParameters;
import com.google.inject.Guice;

public abstract class BasePageWebAPI extends Page implements IWebAPI {
	
	private static DriverManager driver = null;
	
	private final static PropertiesWebAPI	propertiesFileSettings;
	private final static IAnalytics			ANALYTICS;
	
	static {
		// Get analytics instance created in BaseTets
		ANALYTICS = BaseTest.getAnalytics();
		
		// Get and then set properties information from selenium.settings file
		propertiesFileSettings = setPropertiesSettings();
		
		// Read System or maven parameters
		setRuntimeParametersWebApi();
	}
	
	public static IAnalytics getAnalytics() {
		return ANALYTICS;
	}
	
	public BasePageWebAPI() {
		getDriver();
	}
	
	@Override
	public ModuleType getModuleType() {
		return ModuleType.WEBAPI;
	}
	
	public static DriverManager getDriver() {
		if (Objects.isNull(BasePageWebAPI.driver)) {
			// Create module driver
			BasePageWebAPI.driver = new DriverManager(propertiesFileSettings);
		}
		return BasePageWebAPI.driver;
	}
	
	private static PropertiesWebAPI setPropertiesSettings() {
		// Get and then set properties information from settings.properties file
		return Guice.createInjector(PropertiesSettingsModule.init())
				.getInstance(PropertiesWebAPI.class);
	}
	
	private static void setRuntimeParametersWebApi() {
		// Read System or maven parameters
		BFLogger.logDebug(java.util.Arrays.asList(RuntimeParameters.values())
				.toString());
		
	}
	
}
