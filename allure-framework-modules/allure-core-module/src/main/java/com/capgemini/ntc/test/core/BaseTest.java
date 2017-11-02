package com.capgemini.ntc.test.core;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;

import com.capgemini.ntc.test.core.testRunners.ParallelTestClassRunner;

@RunWith(ParallelTestClassRunner.class)
public abstract class BaseTest implements IBaseTest {
	static String browser;
	static String browserVersion;
	static String os;
	public static String env;
	public static boolean maintenanceMode = false;


	@ru.yandex.qatools.allure.annotations.Parameter("Width")
	public static int windowWidth;

	@ru.yandex.qatools.allure.annotations.Parameter("Height")
	public static int windowHeight;

	@ru.yandex.qatools.allure.annotations.Parameter("Username")
	private String defaultUsername_lastUsedInTest;

	@BeforeClass
	public static final void setUpClass() throws MalformedURLException {
	}

	@AfterClass
	public static final void tearDownClass() {
//		DriverManager.stop();
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

	

}
