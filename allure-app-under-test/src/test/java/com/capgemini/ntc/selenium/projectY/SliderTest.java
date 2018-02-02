package com.capgemini.ntc.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.projectY.HorizontalSliderPage;
import com.capgemini.ntc.selenium.pages.projectY.TheInternetPage;
import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class SliderTest extends BaseTest {
	
	private static TheInternetPage		theInternetPage;
	private static HorizontalSliderPage	horizontalSliderPage;
	
	BigDecimal	startPosition;
	BigDecimal	middlePosition;
	BigDecimal	endPosition;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		
		BFLogger.logInfo("Step 1: Open the Url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		theInternetPage.load();
		
		BFLogger.logInfo("Step 2: Verify if Url http://the-internet.herokuapp.com/ is opened");
		assertTrue("The Internet Page was not open", theInternetPage.isLoaded());
		
		BFLogger.logInfo("Step 3: Click Horizontal Slider link");
		horizontalSliderPage = theInternetPage.clickHorizontalSliderLink();
		
		BFLogger.logInfo("Step 4: Verify if Horizontal Slider Page is opened");
		assertTrue("The Horizontal Slider Page was not open", horizontalSliderPage.isLoaded());
		
		BFLogger.logInfo("Step 5: Verify if horizontal slider element is visible");
		assertTrue("Horizontal slider is not visible", horizontalSliderPage.isElementHorizontalSliderVisible());
	}
	
	@Override
	public void setUp() {
		startPosition = horizontalSliderPage.getStartPosition();
		middlePosition = horizontalSliderPage.getMiddlePosition();
		endPosition = horizontalSliderPage.getEndPosition();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		BFLogger.logInfo("Step 10: Navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
	@Test
	public void moveHorizontalSliderUsingKeyboardTest() {
		
		BFLogger.logInfo("Step 6: Move slider to start position: " + startPosition);
		horizontalSliderPage.setSliderPositionTo(startPosition, HorizontalSliderPage.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", startPosition, horizontalSliderPage.getCurrentPosition());
		
		BFLogger.logInfo("Step 7: Move slider to middle position: " + middlePosition);
		horizontalSliderPage.setSliderPositionTo(middlePosition, HorizontalSliderPage.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", middlePosition, horizontalSliderPage.getCurrentPosition());
		
		BFLogger.logInfo("Step 8: Move slider to end position: " + endPosition);
		horizontalSliderPage.setSliderPositionTo(endPosition, HorizontalSliderPage.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", endPosition, horizontalSliderPage.getCurrentPosition());
		
		BFLogger.logInfo("Step 9: Move slider back to start position: " + startPosition);
		horizontalSliderPage.setSliderPositionTo(startPosition, HorizontalSliderPage.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", startPosition, horizontalSliderPage.getCurrentPosition());
	}
	
	@Test
	public void moveHorizontalSliderUsingMouseTest() {
		
		BFLogger.logInfo("Step 6: Move slider to start position: " + startPosition);
		horizontalSliderPage.setSliderPositionTo(startPosition, HorizontalSliderPage.MOUSE);
		assertEquals("Fail to set horizontal sliders position", startPosition, horizontalSliderPage.getCurrentPosition());
		
		BFLogger.logInfo("Step 7: Move slider to middle position: " + middlePosition);
		horizontalSliderPage.setSliderPositionTo(middlePosition, HorizontalSliderPage.MOUSE);
		assertEquals("Fail to set horizontal sliders position", middlePosition, horizontalSliderPage.getCurrentPosition());
		
		BFLogger.logInfo("Step 8: Move slider to end position: " + endPosition);
		horizontalSliderPage.setSliderPositionTo(endPosition, HorizontalSliderPage.MOUSE);
		assertEquals("Fail to set horizontal sliders position", endPosition, horizontalSliderPage.getCurrentPosition());
		
		BFLogger.logInfo("Step 9: Move slider back to start position: " + startPosition);
		horizontalSliderPage.setSliderPositionTo(startPosition, HorizontalSliderPage.MOUSE);
		assertEquals("Fail to set horizontal sliders position", startPosition, horizontalSliderPage.getCurrentPosition());
	}
	
	@Override
	public void tearDown() {
		
	}
	
}
