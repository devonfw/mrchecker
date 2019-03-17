package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.HorizontalSliderElement;
import com.capgemini.mrchecker.selenium.pages.projectY.HorizontalSliderPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;

@Category({ TestsSelenium.class, TestsChrome.class, TestsFirefox.class, TestsIE.class })
public class SliderTest extends TheInternetBaseTest {
	
	private static HorizontalSliderPage horizontalSliderPage;
	
	BigDecimal	startPosition;
	BigDecimal	middlePosition;
	BigDecimal	endPosition;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		logStep("Open the Url http://the-internet.herokuapp.com/");
		theInternetPage = new TheInternetPage();
		theInternetPage.load();
		
		logStep("Verify if Url http://the-internet.herokuapp.com/ is opened");
		assertTrue("Unable to load The Internet Page", theInternetPage.isLoaded());
	}
	
	@Override
	public void setUp() {
		logStep("Click Horizontal Slider link");
		horizontalSliderPage = theInternetPage.clickHorizontalSliderLink();
		
		logStep("Verify if Horizontal Slider page is opened");
		assertTrue("Unable to load Horizontal Slider page", horizontalSliderPage.isLoaded());
		
		logStep("Verify if horizontal slider element is visible");
		assertTrue("Horizontal slider is not visible", horizontalSliderPage.isElementHorizontalSliderVisible());
		
		startPosition = horizontalSliderPage.getStartPosition();
		middlePosition = horizontalSliderPage.getMiddlePosition();
		endPosition = horizontalSliderPage.getEndPosition();
	}
	
	@Test
	public void shouldHorizontalSliderMoveWhenKeyboardArrowButtonsArePressed() {
		BigDecimal position;
		logStep("Move slider to start position: " + startPosition);
		horizontalSliderPage.setSliderPositionTo(startPosition, HorizontalSliderElement.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", startPosition, horizontalSliderPage.getCurrentPosition());
		
		logStep("Move slider to middle position: " + middlePosition);
		horizontalSliderPage.setSliderPositionTo(middlePosition, HorizontalSliderElement.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", horizontalSliderPage.verifyAndCorrectPositionValue(middlePosition), horizontalSliderPage.getCurrentPosition());
		
		logStep("Move slider to end position: " + endPosition);
		horizontalSliderPage.setSliderPositionTo(endPosition, HorizontalSliderElement.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", endPosition, horizontalSliderPage.getCurrentPosition());
		
		position = startPosition.subtract(BigDecimal.ONE);
		logStep("Move slider to position before start position: " + position);
		horizontalSliderPage.setSliderPositionTo(position, HorizontalSliderElement.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", startPosition, horizontalSliderPage.getCurrentPosition());
		
		position = endPosition.add(BigDecimal.ONE);
		logStep("Move slider to position after end position: " + position);
		horizontalSliderPage.setSliderPositionTo(position, HorizontalSliderElement.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", endPosition, horizontalSliderPage.getCurrentPosition());
		
		position = middlePosition.divide(new BigDecimal(2));
		logStep("Move slider to improperly defined position: " + position);
		horizontalSliderPage.setSliderPositionTo(position, HorizontalSliderElement.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", horizontalSliderPage.verifyAndCorrectPositionValue(position), horizontalSliderPage.getCurrentPosition());
		
		position = new BigDecimal(new BigInteger("233234"), 5);
		logStep("Move slider to improperly defined random position: " + position);
		horizontalSliderPage.setSliderPositionTo(position, HorizontalSliderElement.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", horizontalSliderPage.verifyAndCorrectPositionValue(position), horizontalSliderPage.getCurrentPosition());
		
		logStep("Move slider back to start position: " + startPosition);
		horizontalSliderPage.setSliderPositionTo(startPosition, HorizontalSliderElement.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", startPosition, horizontalSliderPage.getCurrentPosition());
	}
	
	@Test
	public void shouldHorizontalSliderMoveWhenMouseButtonIsPressedAndMouseIsMoving() {
		BigDecimal position;
		logStep("Move slider to start position: " + startPosition);
		horizontalSliderPage.setSliderPositionTo(startPosition, HorizontalSliderElement.MOUSE);
		assertEquals("Fail to set horizontal sliders position", startPosition, horizontalSliderPage.getCurrentPosition());
		
		logStep("Move slider to middle position: " + middlePosition);
		horizontalSliderPage.setSliderPositionTo(middlePosition, HorizontalSliderElement.MOUSE);
		assertEquals("Fail to set horizontal sliders position", horizontalSliderPage.verifyAndCorrectPositionValue(middlePosition), horizontalSliderPage.getCurrentPosition());
		
		logStep("Move slider to end position: " + endPosition);
		horizontalSliderPage.setSliderPositionTo(endPosition, HorizontalSliderElement.MOUSE);
		assertEquals("Fail to set horizontal sliders position", endPosition, horizontalSliderPage.getCurrentPosition());
		
		position = startPosition.subtract(BigDecimal.ONE);
		logStep("Move slider to position before start position: " + position);
		horizontalSliderPage.setSliderPositionTo(position, HorizontalSliderElement.MOUSE);
		assertEquals("Fail to set horizontal sliders position", startPosition, horizontalSliderPage.getCurrentPosition());
		
		position = endPosition.add(BigDecimal.ONE);
		logStep("Move slider to position after end position: " + position);
		horizontalSliderPage.setSliderPositionTo(position, HorizontalSliderElement.MOUSE);
		assertEquals("Fail to set horizontal sliders position", endPosition, horizontalSliderPage.getCurrentPosition());
		
		position = middlePosition.divide(new BigDecimal(2));
		logStep("Move slider to improperly defined position: " + position);
		horizontalSliderPage.setSliderPositionTo(position, HorizontalSliderElement.MOUSE);
		assertEquals("Fail to set horizontal sliders position", horizontalSliderPage.verifyAndCorrectPositionValue(position), horizontalSliderPage.getCurrentPosition());
		
		position = new BigDecimal(new BigInteger("212348"), 5);
		logStep("Move slider to improperly defined random position: " + position);
		horizontalSliderPage.setSliderPositionTo(position, HorizontalSliderElement.MOUSE);
		assertEquals("Fail to set horizontal sliders position", horizontalSliderPage.verifyAndCorrectPositionValue(position), horizontalSliderPage.getCurrentPosition());
		
		logStep("Move slider back to start position: " + startPosition);
		horizontalSliderPage.setSliderPositionTo(startPosition, HorizontalSliderElement.MOUSE);
		assertEquals("Fail to set horizontal sliders position", startPosition, horizontalSliderPage.getCurrentPosition());
	}
	
}
