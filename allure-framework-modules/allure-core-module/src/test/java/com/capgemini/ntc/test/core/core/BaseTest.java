package com.capgemini.ntc.test.core.tests.core;

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
import com.capgemini.ntc.test.core.tests.testRunners.ParallelTestClassRunner;

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

	public static INewWebDriver getDriver() {
		return DriverManager.getDriver();
	}

	@BeforeClass
	public static final void setUpClass() throws MalformedURLException {
	}

	@AfterClass
	public static final void tearDownClass() {
		BFLogger.logInfo(String.format("All clicks took %.2fs", 1.0 * NewRemoteWebElement.dropClickTimer() / 1000));
		DriverManager.closeDriver();
	}

	@After
	public void tearDownTestLast() {
		defaultUsername_lastUsedInTest = BasePage.getDefaultUsername();
	}

	@Override
	abstract public void setUp();

	@Override
	abstract public void tearDown();

	@Rule
	public TestWatcher testWatcher = new BaseTestWatcher(this);

	/**
	 * Changes resolution of browser to resolution specified by parameter
	 * 
	 * @param resolution
	 */
	protected static void setResolution(Dimension resolution) {

		BFLogger.logInfo("Trying to set requested resolution: " + resolution.width + "x" + resolution.height + ".");
		getDriver().manage().window().setSize(resolution);

		BFLogger.logInfo("Resolution readed from device: " + getResolution().width + "x" + getResolution().height);

		Dimension dimensionWithOffset = new Dimension(getOffsetWidth(), resolution.height);
		BFLogger.logInfo("Trying to set increased resolution for 'View port': " + dimensionWithOffset.width + "x"
				+ dimensionWithOffset.height + ".");
		getDriver().manage().window().setSize(dimensionWithOffset);

		BFLogger.logInfo("Resolution readed from device: " + getResolution().width + "x" + getResolution().height);
	}

	/* Setting and getting used dymension */

	public static Dimension getUsedDimension() {
		return usedDimension;
	}

	public static void setUsedDimension(Dimension defaultDimension) {
		BaseTest.usedDimension = defaultDimension;
	}

	/**
	 * Changes resolution of browser to width specified by parameter and height of 900
	 * 
	 * @param resolution
	 * @author
	 */
	protected static void setResolution(int width) {
		Dimension resolution = new Dimension(width, 900);
		setResolution(resolution);
	}

	/**
	 * Changes resolution of browser to width and height taken from test
	 * 
	 * @author
	 */
	protected void setResolution() {
		Dimension resolution = new Dimension(windowWidth, windowHeight);
		setResolution(resolution);
	}

	/**
	 * Changes resolution of browser to width and height specified by parameter
	 * 
	 * @param width
	 *            window width
	 * @param height
	 *            window height
	 * @author
	 */
	protected void setResolution(int width, int height) {
		Dimension resolution = new Dimension(width, height);
		setResolution(resolution);
	}

	/**
	 * Changes resolution of browser to width and height taken from {@link ResolutionEnum}
	 */
	protected void setResolution(ResolutionEnum resolutionEnum) {
		Dimension resolution = new Dimension(resolutionEnum.getWidth(), resolutionEnum.getHeight());
		setResolution(resolution);
	}

	private static Dimension getResolution() {
		int width = getDriver().manage().window().getSize().getWidth();
		int height = getDriver().manage().window().getSize().getHeight();
		return new Dimension(width, height);
	}

	private static int getOffsetWidth() {
		int widthScreen = WindowUtils.getScreenWidth(getDriver());
		int offset = getDriver().manage().window().getSize().getWidth() - widthScreen;
		return getDriver().manage().window().getSize().getWidth() + offset;

	}

}
