package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsIE;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectY.HorizontalSliderPage;
import com.capgemini.mrchecker.selenium.pages.projectY.InteractionType;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;

@TestsSelenium
@TestsChrome
@TestsFirefox
@TestsIE
public class SliderTest extends TheInternetBaseTest {
	
	private static HorizontalSliderPage horizontalSliderPage;
	
	BigDecimal sliderMinValue;
	BigDecimal sliderMiddleValue;
	BigDecimal sliderMaxValue;
	
	@BeforeAll
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
		
		sliderMinValue = horizontalSliderPage.getSliderMinValue();
		sliderMiddleValue = horizontalSliderPage.getSliderMiddleValue();
		sliderMaxValue = horizontalSliderPage.getSliderMaxValue();
	}
	
	@Test
	public void shouldHorizontalSliderMoveWhenKeyboardArrowButtonsArePressed() {
		BigDecimal sliderValue;
		logStep("Move slider to minimal position: " + sliderMinValue);
		horizontalSliderPage.setSliderValue(sliderMinValue, InteractionType.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", sliderMinValue, horizontalSliderPage.getSliderValue());
		
		logStep("Move slider to middle position: " + sliderMiddleValue);
		horizontalSliderPage.setSliderValue(sliderMiddleValue, InteractionType.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", horizontalSliderPage.correctSliderValue(sliderMiddleValue), horizontalSliderPage.getSliderValue());
		
		logStep("Move slider to maximal position: " + sliderMaxValue);
		horizontalSliderPage.setSliderValue(sliderMaxValue, InteractionType.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", sliderMaxValue, horizontalSliderPage.getSliderValue());
		
		sliderValue = sliderMinValue.subtract(BigDecimal.ONE);
		logStep("Move slider to value lower than minimal: " + sliderValue);
		horizontalSliderPage.setSliderValue(sliderValue, InteractionType.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", sliderMinValue, horizontalSliderPage.getSliderValue());
		
		sliderValue = sliderMaxValue.add(BigDecimal.ONE);
		logStep("Move slider to value greater than maximal: " + sliderValue);
		horizontalSliderPage.setSliderValue(sliderValue, InteractionType.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", sliderMaxValue, horizontalSliderPage.getSliderValue());
		
		sliderValue = sliderMiddleValue.divide(new BigDecimal(2));
		logStep("Move slider to improperly defined position: " + sliderValue);
		horizontalSliderPage.setSliderValue(sliderValue, InteractionType.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", horizontalSliderPage.correctSliderValue(sliderValue), horizontalSliderPage.getSliderValue());
		
		sliderValue = new BigDecimal(new BigInteger("233234"), 5);
		logStep("Move slider to incorrect value: " + sliderValue);
		horizontalSliderPage.setSliderValue(sliderValue, InteractionType.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", horizontalSliderPage.correctSliderValue(sliderValue), horizontalSliderPage.getSliderValue());
		
		logStep("Move slider back to minimal value: " + sliderMinValue);
		horizontalSliderPage.setSliderValue(sliderMinValue, InteractionType.KEYBOARD);
		assertEquals("Fail to set horizontal sliders position", sliderMinValue, horizontalSliderPage.getSliderValue());
	}
	
	@Test
	public void shouldHorizontalSliderMoveWhenMouseButtonIsPressedAndMouseIsMoving() {
		BigDecimal sliderValue;
		logStep("Move slider to minimal position: " + sliderMinValue);
		horizontalSliderPage.setSliderValue(sliderMinValue, InteractionType.MOUSE);
		assertEquals("Fail to set horizontal sliders position", sliderMinValue, horizontalSliderPage.getSliderValue());
		
		logStep("Move slider to middle position: " + sliderMiddleValue);
		horizontalSliderPage.setSliderValue(sliderMiddleValue, InteractionType.MOUSE);
		assertEquals("Fail to set horizontal sliders position", horizontalSliderPage.correctSliderValue(sliderMiddleValue), horizontalSliderPage.getSliderValue());
		
		logStep("Move slider to maximal position: " + sliderMaxValue);
		horizontalSliderPage.setSliderValue(sliderMaxValue, InteractionType.MOUSE);
		assertEquals("Fail to set horizontal sliders position", sliderMaxValue, horizontalSliderPage.getSliderValue());
		
		sliderValue = sliderMinValue.subtract(BigDecimal.ONE);
		logStep("Move slider to value lower than minimal: " + sliderValue);
		horizontalSliderPage.setSliderValue(sliderValue, InteractionType.MOUSE);
		assertEquals("Fail to set horizontal sliders position", sliderMinValue, horizontalSliderPage.getSliderValue());
		
		sliderValue = sliderMaxValue.add(BigDecimal.ONE);
		logStep("Move slider to value greater than maximal: " + sliderValue);
		horizontalSliderPage.setSliderValue(sliderValue, InteractionType.MOUSE);
		assertEquals("Fail to set horizontal sliders position", sliderMaxValue, horizontalSliderPage.getSliderValue());
		
		sliderValue = sliderMiddleValue.divide(new BigDecimal(2));
		logStep("Move slider to improperly defined position: " + sliderValue);
		horizontalSliderPage.setSliderValue(sliderValue, InteractionType.MOUSE);
		assertEquals("Fail to set horizontal sliders position", horizontalSliderPage.correctSliderValue(sliderValue), horizontalSliderPage.getSliderValue());
		
		sliderValue = new BigDecimal(new BigInteger("212348"), 5);
		logStep("Move slider to incorrect value: " + sliderValue);
		horizontalSliderPage.setSliderValue(sliderValue, InteractionType.MOUSE);
		assertEquals("Fail to set horizontal sliders position", horizontalSliderPage.correctSliderValue(sliderValue), horizontalSliderPage.getSliderValue());
		
		logStep("Move slider back to minimal value: " + sliderMinValue);
		horizontalSliderPage.setSliderValue(sliderMinValue, InteractionType.MOUSE);
		assertEquals("Fail to set horizontal sliders position", sliderMinValue, horizontalSliderPage.getSliderValue());
	}
	
}
