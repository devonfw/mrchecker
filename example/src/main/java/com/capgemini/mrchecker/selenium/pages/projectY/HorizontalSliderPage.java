package com.capgemini.mrchecker.selenium.pages.projectY;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.HorizontalSliderElement;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import org.openqa.selenium.By;

import java.math.BigDecimal;

public class HorizontalSliderPage extends BasePage {

	private static final By selectorHorizontalSlider = By.cssSelector("div.sliderContainer");
	private static final By sliderSelector           = By.cssSelector("input");
	private static final By valueSelector            = By.cssSelector("#range");

	private HorizontalSliderElement horizontalSlider;

	public HorizontalSliderPage() {
		horizontalSlider = getDriver().elementHorizontalSlider(selectorHorizontalSlider, sliderSelector, valueSelector, BigDecimal.ZERO, new BigDecimal(5), new BigDecimal(0.5));
	}

	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
				.contains(PageSubURLsProjectYEnum.HORIZONTAL_SLIDER.getValue());
	}

	@Override
	public void load() {
		BFLogger.logDebug("Load 'Horizontal Slider' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.HORIZONTAL_SLIDER.getValue());
		getDriver().waitForPageLoaded();
	}

	@Override
	public String pageTitle() {
		return getActualPageTitle();
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
	 * Returns the value of slider's minimal value.
	 *
	 * @return BigDecimal representing the lowest possible value of slider.
	 */
	public BigDecimal getSliderMinValue() {
		return horizontalSlider.getMinRange();
	}

	/**
	 * Returns the value of slider's middle position.
	 *
	 * @return BigDecimal representing the average value between start and end position.
	 */
	public BigDecimal getSliderMiddleValue() {
		return horizontalSlider.getMaxRange()
				.subtract(horizontalSlider.getMinRange())
				.divide(new BigDecimal(2));
	}

	/**
	 * Returns the value of slider's end position.
	 *
	 * @return BigDecimal representing the highest possible value of slider.
	 */
	public BigDecimal getSliderMaxValue() {
		return horizontalSlider.getMaxRange();
	}

	/**
	 * Returns current value of slider's position.
	 *
	 * @return BigDecimal representing current value of slider.
	 */
	public BigDecimal getSliderValue() {
		return horizontalSlider.getCurrentSliderValue();
	}



	/**
	 * Sets horizontal slider using one of available methods: using keyboard
	 * or using mouse move.
	 *
	 * @param value
	 * @param interactionType
	 */
	public void setSliderValue(BigDecimal value, InteractionType interactionType) {
		horizontalSlider.setSliderPositionTo(value, interactionType.getMethodValue());
	}

	/**
	 * Verifies the correctness of given position value and round it when necessary.
	 *
	 * @param position
	 * @return Correct value of horizontal slider's position.
	 */
	public BigDecimal correctSliderValue(BigDecimal position) {
		return horizontalSlider.verifyAndCorrectPositionValue(position);
	}

}
