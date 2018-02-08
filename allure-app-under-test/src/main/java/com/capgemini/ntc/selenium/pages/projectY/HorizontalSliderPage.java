package com.capgemini.ntc.selenium.pages.projectY;

import java.math.BigDecimal;

import org.openqa.selenium.By;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.HorizontalSliderElement;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class HorizontalSliderPage extends BasePage {
	
	private static final By	selectorHorizontalSlider	= By.cssSelector("div.sliderContainer");
	private static final By	sliderSelector				= By.cssSelector("input");
	private static final By	valueSelector				= By.cssSelector("#range");
	
	private HorizontalSliderElement horizontalSlider;
	
	public HorizontalSliderPage() {
		horizontalSlider = getDriver().elementHorizontalSlider(selectorHorizontalSlider, sliderSelector, valueSelector, BigDecimal.ZERO, new BigDecimal(5), new BigDecimal(0.5));
	}
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains("horizontal_slider");
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load()");
		
	}
	
	@Override
	public String pageTitle() {
		return "The Internet";
	}
	
	/**
	 * Validates if WebElement representing horizontal slider is visible on the page.
	 * 
	 * @return true if horizontal slider is visible, false otherwise.
	 */
	public boolean isElementHorizontalSliderVisible() {
		return getDriver().elementHorizontalSlider(selectorHorizontalSlider)
						.isDisplayed();
	}
	
	/**
	 * Returns the value of slider's start position.
	 * 
	 * @return BigDecimal representing the lowest possible value of slider.
	 */
	public BigDecimal getStartPosition() {
		return horizontalSlider.getMinRange();
	}
	
	/**
	 * Returns the value of slider's middle position.
	 * 
	 * @return BigDecimal representing the average value between start and end position.
	 */
	public BigDecimal getMiddlePosition() {
		return horizontalSlider.getMaxRange()
						.subtract(horizontalSlider.getMinRange())
						.divide(new BigDecimal(2));
	}
	
	/**
	 * Returns the value of slider's end position.
	 * 
	 * @return BigDecimal representing the highest possible value of slider.
	 */
	public BigDecimal getEndPosition() {
		return horizontalSlider.getMaxRange();
	}
	
	/**
	 * Returns current value of slider's position.
	 * 
	 * @return BigDecimal representing current value of slider.
	 */
	public BigDecimal getCurrentPosition() {
		return horizontalSlider.getCurrentSliderValue();
	}
	
	/**
	 * Sets horizontal slider to given position using one of available methods: using keyboard
	 * or using mouse move.
	 * 
	 * @param position
	 * @param method
	 */
	public void setSliderPositionTo(BigDecimal position, int method) {
		horizontalSlider.setSliderPositionTo(position, method);
	}
	
	/**
	 * Verifies the correctness of given position value and round it when necessary.
	 * 
	 * @param position
	 * @return Correct value of horizontal slider's position.
	 */
	public BigDecimal verifyAndCorrectPositionValue(BigDecimal position) {
		return horizontalSlider.verifyAndCorrectPositionValue(position);
	}
	
}
