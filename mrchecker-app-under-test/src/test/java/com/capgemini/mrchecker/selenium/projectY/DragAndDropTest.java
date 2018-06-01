package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.DragAndDropPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class DragAndDropTest extends BaseTest {
	
	private static final String	ELEMENT_A	= "A";
	private static final String	CONTAINER_A	= "A";
	private static final String	ELEMENT_B	= "B";
	private static final String	CONTAINER_B	= "B";
	
	private static TheInternetPage	theInternetPage;
	private static DragAndDropPage	dragAndDropPage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		
		BFLogger.logInfo("Step 1: Open the Url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		theInternetPage.load();
		
		BFLogger.logInfo("Step 2: Verify if Url http://the-internet.herokuapp.com/ is opened");
		assertTrue("The Internet Page was not open", theInternetPage.isLoaded());
		
		BFLogger.logInfo("Step 3: Click Drag And Drop link");
		dragAndDropPage = theInternetPage.clickDragAndDropLink();
		
		BFLogger.logInfo("Step 4: Verify if Drag And Drop Page is opened");
		assertTrue("The Drag And Drop Page was not open", dragAndDropPage.isLoaded());
		
		BFLogger.logInfo("Step 5: Verify if Drag And Drop message is visible");
		assertTrue("Drag And Drop message is not visible", dragAndDropPage.isDragAndDropMessageVisible());
	}
	
	@Override
	public void setUp() {
		
	}
	
	@Test
	public void shouldDraggableElementBeMovedAndDropped() {
		BFLogger.logInfo("Step 6: Verify if elements are placed in proper containers");
		assertTrue("Element A doesn't exist in container A", dragAndDropPage.isElementPlacedInCorrectContainer(ELEMENT_A));
		assertTrue("Element B doesn't exist in container B", dragAndDropPage.isElementPlacedInCorrectContainer(ELEMENT_B));
		
		BFLogger.logInfo("Step 7: Drag and drop element A into container B");
		dragAndDropPage.dragElementToPosition(ELEMENT_A, CONTAINER_A, CONTAINER_B);
		
		BFLogger.logInfo("Step 8: Verify if elements are placed in improper containers");
		assertFalse("Element A doesn't exist in container B", dragAndDropPage.isElementPlacedInCorrectContainer(ELEMENT_A));
		assertFalse("Element B doesn't exist in container A", dragAndDropPage.isElementPlacedInCorrectContainer(ELEMENT_B));
		
		BFLogger.logInfo("Step 9: Drag and drop element B back into container B");
		dragAndDropPage.dragElementToPosition(ELEMENT_A, CONTAINER_B, CONTAINER_A);
		
		BFLogger.logInfo("Step 10: Verify if elements are placed in proper containers");
		assertTrue("Element A doesn't exist in container A", dragAndDropPage.isElementPlacedInCorrectContainer(ELEMENT_A));
		assertTrue("Element B doesn't exist in container B", dragAndDropPage.isElementPlacedInCorrectContainer(ELEMENT_B));
	}
	
	@Override
	public void tearDown() {
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		BFLogger.logInfo("Step 11: Navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
}
