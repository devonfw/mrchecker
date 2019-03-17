package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.DragAndDropPage;

@Category({ TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class })
public class DragAndDropTest extends TheInternetBaseTest {
	
	private static final String	ELEMENT_A	= "A";
	private static final String	CONTAINER_A	= "A";
	private static final String	ELEMENT_B	= "B";
	private static final String	CONTAINER_B	= "B";
	
	private static DragAndDropPage dragAndDropPage;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		dragAndDropPage = shouldTheInternetPageBeOpened().clickDragAndDropLink();
		
		logStep("Verify if Drag And Drop page is opened");
		assertTrue("Unable to open Drag And Drop page", dragAndDropPage.isLoaded());
		
		logStep("Verify if Drag And Drop message is visible");
		assertTrue("Drag And Drop message is not visible", dragAndDropPage.isDragAndDropMessageVisible());
	}
	
	@Test
	public void shouldDraggableElementBeMovedAndDropped() {
		logStep("Verify if elements are placed in proper containers");
		assertTrue("Element A doesn't exist in container A", dragAndDropPage.isElementPlacedInCorrectContainer(ELEMENT_A));
		assertTrue("Element B doesn't exist in container B", dragAndDropPage.isElementPlacedInCorrectContainer(ELEMENT_B));
		
		logStep("Step 7: Drag and drop element A into container B");
		dragAndDropPage.dragElementToPosition(ELEMENT_A, CONTAINER_A, CONTAINER_B);
		
		logStep("Step 8: Verify if elements are placed in improper containers");
		assertFalse("Element A doesn't exist in container B", dragAndDropPage.isElementPlacedInCorrectContainer(ELEMENT_A));
		assertFalse("Element B doesn't exist in container A", dragAndDropPage.isElementPlacedInCorrectContainer(ELEMENT_B));
		
		logStep("Drag and drop element B back into container B");
		dragAndDropPage.dragElementToPosition(ELEMENT_A, CONTAINER_B, CONTAINER_A);
		
		logStep("Verify if elements are placed in proper containers");
		assertTrue("Element A doesn't exist in container A", dragAndDropPage.isElementPlacedInCorrectContainer(ELEMENT_A));
		assertTrue("Element B doesn't exist in container B", dragAndDropPage.isElementPlacedInCorrectContainer(ELEMENT_B));
	}
	
}
