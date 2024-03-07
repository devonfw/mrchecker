package com.capgemini.framework.playwright;

import com.capgemini.framework.assertions.AssertJConfig;
import com.capgemini.framework.environment.AllureEnvironment;
import com.capgemini.framework.logger.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.capgemini.framework.playwright.PlaywrightFactory.*;

/**
 * Playwright variables (see PlaywrightFactory) will be created on first usage automatically, so they are not created here.
 * But you have to close them to finish test by yourself which is done here in after AfterEach and AfterAll functions.
 */
@ExtendWith(TestExecutionWatcher.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {
	
	
	static {
		AssertJConfig.initJAssert();
		PlaywrightConfig.init();
		}
	
	@BeforeAll()
	public void init() {
		AllureEnvironment allureEnvironment = new AllureEnvironment();
		allureEnvironment.write();
	}
	
	@BeforeEach()
	public void initTest(TestInfo testInfo) {
		Logger.logInfo("Starting test: " + getTestName(testInfo));
	}
	
	@AfterEach
	public void tearDownTest(TestInfo testInfo) {
		Logger.logInfo(getTestName(testInfo));
		closeBrowserContext(getTestName(testInfo));
		closeAPIRequestContext();
		closePage();
	}
	
	@AfterAll
	public void tearDown(TestInfo testInfo) {
		closeBrowser();
		closePlaywright(getTestName(testInfo));
		Logger.removeLogInstance();
		
	}
	
	protected String getTestName(TestInfo testInfo) {
		return testInfo.getTestClass()
				.toString()
				.replace("Optional[class ", "")
				.replace("]", "") + "#" + testInfo.getDisplayName()
				.replace("()", "")
				.replace(" ", "");
	}
}
