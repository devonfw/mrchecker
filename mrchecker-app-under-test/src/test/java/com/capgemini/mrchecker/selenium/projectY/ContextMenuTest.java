package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsLocal;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsNONParallel;
import com.capgemini.mrchecker.selenium.pages.projectY.ContextMenuPage;

@Category({ TestsLocal.class, TestsNONParallel.class, TestsFirefox.class })
public class ContextMenuTest extends TheInternetBaseTest<ContextMenuPage> {
	
	private static ContextMenuPage	contextMenuPage;
	private final String			expectedAlertText	= "You selected a context menu";
	
	@BeforeClass
	public static void setUpBeforeClass() {
		contextMenuPage = new ContextMenuPage();
		shouldTheInternetSubpageBeOpened(contextMenuPage);
	}
	
	@Test
	public void shouldOpenAlertWithProperMessageWhenClickContextMenuOption() {
		logStep("Right click the hot spot area");
		contextMenuPage.rightClickHotSpotArea();
		
		logStep("Choose 'the-internet' option from context menu");
		contextMenuPage.chooseTheInternetOptionFromContextMenu();
		
		logStep("Verify if displayed message is equal to expected one");
		assertTrue("Displayed message is different than expected", contextMenuPage.isAlertTextValid(expectedAlertText));
		
		logStep("Close alert window");
		contextMenuPage.clickAlertsOkButton();
	}
}
