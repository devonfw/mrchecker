package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.DisappearingElementsPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class DisappearingElementsTest extends BaseTest {
	
	private static TheInternetPage			theInternetPage;
	private static DisappearingElementsPage	disappearingElementsPage;
	
	private static int			numberOfMenuButtons			= 0;
	private static final int	totalNumberOfMenuButtons	= 5;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		
		BFLogger.logInfo("Step 1: Open the Url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		theInternetPage.load();
		
		BFLogger.logInfo("Step 2: Verify if Url http://the-internet.herokuapp.com/ is opened");
		assertTrue("The Internet Page was not open", theInternetPage.isLoaded());
		
		BFLogger.logInfo("Step 3: Click Disappearing Element link");
		disappearingElementsPage = theInternetPage.clickDisappearingElementsLink();
		
		BFLogger.logInfo("Step 4: Verify if Disappearing Element Page is opened");
		assertTrue("The Disappearing Element Page was not open", disappearingElementsPage.isLoaded());
		
		BFLogger.logInfo("Step 5: Verify if menu button elements are visible");
		numberOfMenuButtons = disappearingElementsPage.getNumberOfMenuButtons();
		assertTrue("Unable to display menu", numberOfMenuButtons > 0);
	}
	
	@Override
	public void setUp() {
		
	}
	
	@Test
	public void shouldMenuButtonElementAppearAndDisappearAfterRefreshTest() {
		BFLogger.logInfo("Step 6: Click refresh button until menu button appears");
		disappearingElementsPage.refreshPageUntilWebElementAppears(true);
		
		BFLogger.logInfo("Step 7: Verify if menu button element appeared");
		assertNotNull("Unable to disappear menu button element", disappearingElementsPage.getGalleryMenuElement());
		assertEquals("The number of button elements after refresh is incorrect", totalNumberOfMenuButtons, disappearingElementsPage.getNumberOfMenuButtons());
		
		BFLogger.logInfo("Step 8: Click refresh button until menu button disappears");
		disappearingElementsPage.refreshPageUntilWebElementAppears(false);
		
		BFLogger.logInfo("Step 9: Verify if menu button element disappeared");
		assertNull("Unable to appear menu button element", disappearingElementsPage.getGalleryMenuElement());
		assertTrue("The number of button elements after refresh is incorrect", totalNumberOfMenuButtons > disappearingElementsPage.getNumberOfMenuButtons());
	}
	
	@Override
	public void tearDown() {
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		BFLogger.logInfo("Step 10: Navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
}
