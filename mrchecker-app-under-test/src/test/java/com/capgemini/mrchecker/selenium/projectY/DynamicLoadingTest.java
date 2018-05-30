package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.DynamicLoadingPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class DynamicLoadingTest extends BaseTest {
	
	private static final int	EXAMPLE_WAITING_TIME	= 30;
	private static final String	EXAMPLE_TEXT			= "Hello World!";
	
	private static TheInternetPage		theInternetPage;
	private static DynamicLoadingPage	dynamicLoadingPage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		
		BFLogger.logInfo("Step 1: Open the Url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		theInternetPage.load();
		
		BFLogger.logInfo("Step 2: Verify if Url http://the-internet.herokuapp.com/ is opened");
		assertTrue("The Internet Page was not open", theInternetPage.isLoaded());
		
		BFLogger.logInfo("Step 3: Click Dynamic Loading link");
		dynamicLoadingPage = theInternetPage.clickDynamicLoadingLink();
		
		BFLogger.logInfo("Step 4: Verify if Dynamic Loading Page is opened");
		assertTrue("The Dynamic Loading Page was not open", dynamicLoadingPage.isLoaded());
		
		BFLogger.logInfo("Step 5: Verify if dynamic loading message is visible");
		assertTrue("Dynamic loading message is not visible", dynamicLoadingPage.isDynamicLoadingMessageVisible());
	}
	
	@Override
	public void setUp() {
		
	}
	
	@Test
	public void shouldExampleTextBeDisplayedAterRunExampleOne() {
		BFLogger.logInfo("Step 6: Click Example 1 link");
		dynamicLoadingPage.clickExampleOneLink();
		
		BFLogger.logInfo("Step 7: Verify if Example 1 link opened content");
		assertTrue("Fail to load Example 1 content", dynamicLoadingPage.isStartButtonVisible());
		
		BFLogger.logInfo("Step 8: Click Start button");
		dynamicLoadingPage.clickStartButton();
		
		BFLogger.logInfo("Step 9: Verify if expected text is displayed on the screen");
		assertEquals("Fail to display example text", EXAMPLE_TEXT, dynamicLoadingPage.getExampleOneDynamicText(EXAMPLE_WAITING_TIME));
	}
	
	@Test
	public void shouldExampleTextBeDisplayedAterRunExampleTwo() {
		BFLogger.logInfo("Step 6: Click Example 2 link");
		dynamicLoadingPage.clickExampleTwoLink();
		
		BFLogger.logInfo("Step 7: Verify if Example 2 link opened content");
		assertTrue("Fail to load Example 2 content", dynamicLoadingPage.isStartButtonVisible());
		
		BFLogger.logInfo("Step 8: Click Start button");
		dynamicLoadingPage.clickStartButton();
		
		BFLogger.logInfo("Step 9: Verify if expected text is displayed on the screen");
		assertEquals("Fail to display example text", EXAMPLE_TEXT, dynamicLoadingPage.getExampleTwoDynamicText(EXAMPLE_WAITING_TIME));
	}
	
	@Override
	public void tearDown() {
		BFLogger.logInfo("Step 10: Click back to reset Dynamic Loading page");
		BasePage.navigateBack();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		BFLogger.logInfo("Step 11: Navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
}
