package com.capgemini.ntc.test.core;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.openqa.selenium.Dimension;

import com.example.selenium.core.BasePage;
import com.example.selenium.core.newDrivers.DriverManager;
import com.example.selenium.core.newDrivers.INewWebDriver;
import com.example.selenium.core.newDrivers.NewRemoteWebElement;
import com.example.selenium.core.utils.WindowUtils;
import com.capgemini.ntc.test.core.enums.ResolutionEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.test.core.testRunners.ParallelTestClassRunner;

@RunWith(ParallelTestClassRunner.class)
public abstract class BaseTest implements IBaseTest {
	static String browser;
	static String browserVersion;
	static String os;
	public static String env;
	public static boolean maintenanceMode = false;

	private final static ResolutionEnum defaultResolution = ResolutionEnum.w1600;
	private static Dimension usedDimension = new Dimension(defaultResolution.getWidth(), defaultResolution.getHeight());

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
		BFLogger.logInfo(String.format("All clicks took %.2fs", 1.0 * NewRemoteWebElement.dropClickTimer() / 1000));
		DriverManager.stop();
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

	/* Setting and getting used dymension */

	public static Dimension getUsedDimension() {
		return usedDimension;
	}

	public static void setUsedDimension(Dimension defaultDimension) {
		BaseTest.usedDimension = defaultDimension;
	}

	

}
