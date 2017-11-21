package com.capgemini.ntc.test.core;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;

import com.capgemini.ntc.test.core.base.environment.EnvironmentModule;
import com.capgemini.ntc.test.core.base.environment.EnvironmentService;
import com.capgemini.ntc.test.core.base.runtime.RuntimeParameters;
import com.capgemini.ntc.test.core.base.runtime.RuntimeParametersModule;
import com.capgemini.ntc.test.core.testRunners.ParallelTestClassRunner;
import com.google.inject.Guice;

@RunWith(ParallelTestClassRunner.class)
public abstract class BaseTest implements IBaseTest {
	
	@ru.yandex.qatools.allure.annotations.Parameter("Width")
	public static int windowWidth;
	
	@ru.yandex.qatools.allure.annotations.Parameter("Height")
	public static int windowHeight;
	
	@ru.yandex.qatools.allure.annotations.Parameter("Username")
	private String defaultUsername_lastUsedInTest;
	
	private static EnvironmentService environmentService;
	
	public BaseTest() {
		
		RuntimeParameters runtimeParametersInstance = setRuntimeParameters();
		setEnvironmetInstance(runtimeParametersInstance);
		
	}
	
	public static EnvironmentService getEnvironmentService() {
		return environmentService;
	}
	
	public static void setEnvironmentService(EnvironmentService environmentService) {
		BaseTest.environmentService = environmentService;
	}
	
	@BeforeClass
	public static final void setUpClass() throws MalformedURLException {
	}
	
	@AfterClass
	public static final void tearDownClass() {
		// DriverManager.stop();
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
	
	
	private void setEnvironmetInstance(RuntimeParameters runtimeParametersInstance) {
		// Environment variables either from environmnets.csv or any other input data.
		EnvironmentService environmetInstance = Guice.createInjector(new EnvironmentModule())
				.getInstance(EnvironmentService.class);
		environmetInstance.setEnvironment(runtimeParametersInstance.getEnv());
		BaseTest.setEnvironmentService(environmetInstance);
	}
	
	private RuntimeParameters setRuntimeParameters() {
		// Read System or maven parameters
		RuntimeParameters runtimeParametersInstance = Guice.createInjector(new RuntimeParametersModule())
				.getInstance(RuntimeParameters.class);
		return runtimeParametersInstance;
	}
}
