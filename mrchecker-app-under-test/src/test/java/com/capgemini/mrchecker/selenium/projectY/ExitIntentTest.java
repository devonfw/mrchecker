package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsLocal;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.ExitIntentPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

@Category({ TestsLocal.class })
public class ExitIntentTest extends BaseTest {
	
	private static final String MODAL_WINDOW_TITLE = "This is a modal window";
	
	private static TheInternetPage	theInternetPage;
	private static ExitIntentPage	exitIntentPage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		
		BFLogger.logInfo("Step 1: Open the Url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		theInternetPage.load();
		
		BFLogger.logInfo("Step 2: Verify if Url http://the-internet.herokuapp.com/ is opened");
		assertTrue("The Internet Page was not open", theInternetPage.isLoaded());
		
		BFLogger.logInfo("Step 3: Click Exit Intent link");
		exitIntentPage = theInternetPage.clickExitIntentLink();
		
		BFLogger.logInfo("Step 4: Verify if Exit Intent Page is opened");
		assertTrue("The Exit Intent Page was not open", exitIntentPage.isLoaded());
		
		BFLogger.logInfo("Step 5: Verify if exit intent message is visible");
		assertTrue("Exit intent message is not visible", exitIntentPage.isIntentMessageVisible());
	}
	
	@Override
	public void setUp() {
		
	}
	
	@Test
	public void shouldModalWindowAppearWhenMouseMovedOutOfViewportTest() {
		BFLogger.logInfo("Step 6: Verify if modal window is hidden");
		assertTrue("Fail to hide modal window", exitIntentPage.isModalWindowHidden());
		
		BFLogger.logInfo("Step 7: Move mouse pointer out of viewport");
		exitIntentPage.moveMouseOutOfViewport();
		
		BFLogger.logInfo("Step 8: Verify if modal window showed up");
		assertTrue("Fail to show up modal window", exitIntentPage.isModalWindowVisible());
		
		BFLogger.logInfo("Step 9: Verify if modal window title displays properly");
		assertTrue("Fail to display modal window's title", exitIntentPage.verifyModalWindowTitle(MODAL_WINDOW_TITLE.toUpperCase()));
		
		BFLogger.logInfo("Step 10: Close modal window");
		exitIntentPage.closeModalWindow();
		
		BFLogger.logInfo("Step 11: Verify if modal window is hidden again");
		assertTrue("Fail to hide modal window", exitIntentPage.isModalWindowHidden());
	}
	
	@Override
	public void tearDown() {
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		BFLogger.logInfo("Step 12: Navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
}
