package com.capgemini.ntc.test.core;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;

import com.capgemini.ntc.test.core.BaseTestWatcher.TestClassRule;
import com.capgemini.ntc.test.core.analytics.AnalyticsProvider;
import com.capgemini.ntc.test.core.analytics.IAnalytics;
import com.capgemini.ntc.test.core.base.environment.EnvironmentModule;
import com.capgemini.ntc.test.core.base.environment.IEnvironmentService;
import com.capgemini.ntc.test.core.base.properties.PropertiesCoreTest;
import com.capgemini.ntc.test.core.base.properties.PropertiesSettingsModule;
import com.capgemini.ntc.test.core.base.runtime.RuntimeParametersCore;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.test.core.testRunners.ParallelTestClassRunner;
import com.google.inject.Guice;

@RunWith(ParallelTestClassRunner.class)
public abstract class BaseTest implements IBaseTest {
	
	private static IEnvironmentService	environmentService;
	private final static IAnalytics		analytics;
	
	private final static PropertiesCoreTest setPropertiesSettings;
	static {
		setPropertiesSettings = setPropertiesSettings();
		setRuntimeParametersCore();
		setEnvironmetInstance();
		analytics = setAnalytics(setPropertiesSettings.isAnalyticsEnabled());
	}
	
	public BaseTest() {
		getAnalytics().sendClassName();
	}
	
	public static IEnvironmentService getEnvironmentService() {
		return BaseTest.environmentService;
	}
	
	public static IAnalytics getAnalytics() {
		return BaseTest.analytics;
	}
	
	public static void setEnvironmentService(IEnvironmentService environmentService) {
		BaseTest.environmentService = environmentService;
	}
	
	@BeforeClass
	public static final void setUpClass() throws MalformedURLException {
	}
	
	@AfterClass
	public static final void tearDownClass() {
		System.out.println("BaseTest.tearDownTestLast()");
		// Run observers
		TestClassRule.classObservers.forEach(ITestObserver::onTestFinish);
		BaseTestWatcher.observers.forEach(ITestObserver::onTestFinish);
		
		// Clear observers for all tests
		BaseTestWatcher.observers.clear();
		TestClassRule.classObservers.clear();
		
	}
	
	@After
	public void tearDownTestLast() {
		
	}
	
	@Override
	abstract public void setUp();
	
	@Override
	abstract public void tearDown();
	
	@Rule
	public TestWatcher testWatcher = new BaseTestWatcher(this);
	
	@ClassRule
	public static TestClassRule classRule = new TestClassRule();
	
	private static void setEnvironmetInstance() {
		// Environment variables either from environmnets.csv or any other input data.
		IEnvironmentService environmetInstance = Guice.createInjector(new EnvironmentModule())
						.getInstance(IEnvironmentService.class);
		environmetInstance.setEnvironment(RuntimeParametersCore.ENV.getValue());
		BaseTest.setEnvironmentService(environmetInstance);
	}
	
	private static void setRuntimeParametersCore() {
		// Read System or maven parameters
		BFLogger.logDebug(RuntimeParametersCore.ENV.toString());
	}
	
	private static PropertiesCoreTest setPropertiesSettings() {
		/*
		 * For now there is no properties settings file for Core module. In future, please have a look on Selenium
		 * Module PropertiesSelenium propertiesSelenium = Guice.createInjector(PropertiesSettingsModule.init())
		 * .getInstance(PropertiesSelenium.class);
		 */
		
		// Get and then set properties information from settings.properties file
		PropertiesCoreTest propertiesCoreTest = Guice.createInjector(PropertiesSettingsModule.init())
						.getInstance(PropertiesCoreTest.class);
		return propertiesCoreTest;
	}
	
	private static IAnalytics setAnalytics(Boolean isAnalyticsEnabled) {
		BFLogger.logAnalytics("Is analytics enabled:" + isAnalyticsEnabled);
		return isAnalyticsEnabled ? AnalyticsProvider.GOOGLE : AnalyticsProvider.DISABLED;
		
	}
	
}
