package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.pages.projectY.MultipleWindowsPage;
import com.capgemini.mrchecker.selenium.pages.projectY.NewWindowPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class MultipleWindowsTest extends BaseTest {
	
	private static TheInternetPage theInternetPage;
	private static MultipleWindowsPage multipleWindowsPage;
	private static NewWindowPage newWindowPage;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BFLogger.logInfo("Step 1: Open the Url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		
		BFLogger.logInfo("Step 2: Verify if Url http://the-internet.herokuapp.com/ opens");
		assertTrue("The Internet Page was not open", theInternetPage.isLoaded());
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	@Before
	public void setUp() {
		
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void verifyIfNewBrowserWindowOpen() {
		BFLogger.logInfo("Step 3: Click on the Multiple Windows link");
		multipleWindowsPage = theInternetPage.clickmultipleWindowsPageLink();
		
		BFLogger.logInfo("Step 4: Verify if the Url http://the-internet.herokuapp.com/windows");
		assertTrue("The Multiple Windows Page was not open", multipleWindowsPage.isLoaded());
		newWindowPage = multipleWindowsPage.clickHereLink();
		assertTrue("New browser window doesn't open", newWindowPage.isLoaded());
	}
}
