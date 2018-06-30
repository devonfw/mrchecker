package com.capgemini.mrchecker.database.core;

import com.capgemini.mrchecker.database.core.base.properties.PropertiesFileSettings;
import com.capgemini.mrchecker.database.core.base.runtime.RuntimeParameters;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.BaseTestWatcher;
import com.capgemini.mrchecker.test.core.ITestObserver;
import com.capgemini.mrchecker.test.core.ModuleType;
import com.capgemini.mrchecker.test.core.analytics.IAnalytics;
import com.capgemini.mrchecker.test.core.base.environment.IEnvironmentService;
import com.capgemini.mrchecker.test.core.base.properties.PropertiesSettingsModule;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.google.inject.Guice;
import lombok.Getter;

import javax.persistence.EntityManager;

abstract public class BaseDatabase implements ITestObserver, IDatabasePrefixHolder {

	private final static PropertiesFileSettings propertiesFileSettings;
	private static       IEnvironmentService    environmentService;
	private final static IAnalytics             analytics;

	@Getter
	protected EntityManager entityManager = null;

	public final static String analyticsCategoryName = "Database-Module";

	static {
		// Get analytics instance created in BaseTest
		analytics = BaseTest.getAnalytics();

		// Get and then set properties information from selenium.settings file
		propertiesFileSettings = setPropertiesSettings();

		// Read System or maven parameters
		setRuntimeParametersDatabase();

		// Read Environment variables either from environments.csv or any other input data.
		setEnvironmentInstance();
	}

	public BaseDatabase() {
		assignEntityManager();
	}

	public static IAnalytics getAnalytics() {
		return BaseDatabase.analytics;
	}

	@Override
	public void addObserver() {
		BaseTestWatcher.addObserver(this);
	}

	@Override
	public void onTestFailure() {
		BFLogger.logDebug("BasePage.onTestFailure    " + this.getClass()
				.getSimpleName());
		closeSession();
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
		closeSession();
		BFLogger.logDebug("Session for connection: [" + getDatabaseUnitName() + "] closed.");
	}

	@Override
	public ModuleType getModuleType() {
		return ModuleType.DATABASE;
	}

	private static PropertiesFileSettings setPropertiesSettings() {
		// Get and then set properties information from settings.properties file
		PropertiesFileSettings propertiesFileSettings = Guice.createInjector(PropertiesSettingsModule.init())
				.getInstance(PropertiesFileSettings.class);
		return propertiesFileSettings;
	}

	private static void setRuntimeParametersDatabase() {
		// Read System or maven parameters
		BFLogger.logDebug(java.util.Arrays.asList(RuntimeParameters.values())
				.toString());

	}

	private void closeSession() {
		if (entityManager != null) {
			entityManager.close();
		}
	}

	private void assignEntityManager() {
		if (entityManager == null) {
			this.entityManager = DriverManager.createEntityManager(getDatabaseUnitName());
		}
	}

	private static void setEnvironmentInstance() {
		/*
		 * Environment variables either from environmnets.csv or any other input data. For now there is no properties
		 * settings file for Selenium module. In future, please have a look on Core Module IEnvironmentService
		 * environmetInstance = Guice.createInjector(new EnvironmentModule()) .getInstance(IEnvironmentService.class);
		 */

	}

}