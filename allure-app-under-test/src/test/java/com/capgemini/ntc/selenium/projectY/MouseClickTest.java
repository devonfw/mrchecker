package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsLocal;
import com.capgemini.ntc.selenium.pages.projectY.ContextMenuPage;
import com.capgemini.ntc.test.core.BaseTest;

@Category({ TestsLocal.class, TestsFirefox.class })
public class MouseClickTest extends BaseTest {
	
	private ContextMenuPage contextMenuPage;
	
	@Override
	public void setUp() {
		contextMenuPage = new ContextMenuPage();
		assertTrue("The-internet page is not loaded", contextMenuPage.isLoaded());
	}
	
	@Test
	public void testShouldRightClickOnHotSpotArea() {
		// given
		// when
		contextMenuPage.rightClickOnHotSpotArea();
		contextMenuPage.chooseTheInternetOptionFromContextMenu();
		
		// then
		assertTrue("Alert text is not valid", contextMenuPage.isAlertTextValid());
	}
	
	@Override
	public void tearDown() {
		contextMenuPage.clickOnAgreeAtAlert();
	}
	
}
