package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.DynamicLoadingPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;

@Category({ TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class })
public class DynamicLoadingTest extends TheInternetBaseTest<DynamicLoadingPage> {
	
	private static final int	EXAMPLE_WAITING_TIME	= 30;
	private static final String	EXAMPLE_TEXT			= "Hello World!";
	
	private static TheInternetPage<DynamicLoadingPage>	theInternetPage;
	private static DynamicLoadingPage					dynamicLoadingPage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		logStep("Open the Url http://the-internet.herokuapp.com/");
		dynamicLoadingPage = new DynamicLoadingPage();
		theInternetPage = new TheInternetPage<>(dynamicLoadingPage);
		theInternetPage.load();
		
		logStep("Verify if Url http://the-internet.herokuapp.com/ is opened");
		assertTrue("The Internet Page was not open", theInternetPage.isLoaded());
		
		logStep("Click subpage link");
		theInternetPage.clickPageLink();
	}
	
	@Override
	public void setUp() {
		
		logStep("Verify if subpage is opened");
		assertTrue("The Internet subpage: DynamicLoadingPage was not open", dynamicLoadingPage.isLoaded());
		
		logStep("Verify if dynamic loading message is visible");
		assertTrue("Dynamic loading message is not visible", dynamicLoadingPage.isDynamicLoadingMessageVisible());
	}
	
	@Test
	public void shouldExampleTextBeDisplayedAterRunExampleOne() {
		logStep("Click Example 1 link");
		dynamicLoadingPage.clickExampleOneLink();
		
		logStep("Verify if Example 1 link opened content");
		assertTrue("Fail to load Example 1 content", dynamicLoadingPage.isStartButtonVisible());
		
		logStep("Click Start button");
		dynamicLoadingPage.clickStartButton();
		
		logStep("Verify if expected text is displayed on the screen");
		assertEquals("Fail to display example text", EXAMPLE_TEXT, dynamicLoadingPage.getExampleOneDynamicText(EXAMPLE_WAITING_TIME));
	}
	
	@Test
	public void shouldExampleTextBeDisplayedAterRunExampleTwo() {
		logStep("Click Example 2 link");
		dynamicLoadingPage.clickExampleTwoLink();
		
		logStep("Verify if Example 2 link opened content");
		assertTrue("Fail to load Example 2 content", dynamicLoadingPage.isStartButtonVisible());
		
		logStep("Click Start button");
		dynamicLoadingPage.clickStartButton();
		
		logStep("Verify if expected text is displayed on the screen");
		assertEquals("Fail to display example text", EXAMPLE_TEXT, dynamicLoadingPage.getExampleTwoDynamicText(EXAMPLE_WAITING_TIME));
	}
	
	@Override
	public void tearDown() {
		logStep("Click back to reset Dynamic Loading page");
		BasePage.navigateBack();
	}
	
}
