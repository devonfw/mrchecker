package com.capgemini.ntc.selenium.pages.projectY;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.HorizontalSliderElement;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class HorizontalSliderPage extends BasePage {
	
	public static final int	KEYBOARD	= 0;
	public static final int	MOUSE		= 1;
	
	private static final int	MOUSE_MOVE_LEFT		= 0;
	private static final int	MOUSE_MOVE_RIGHT	= 1;
	
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
	
	public boolean isElementHorizontalSliderVisible() {
		return getDriver().elementHorizontalSlider(selectorHorizontalSlider)
						.isDisplayed();
	}
	
	public BigDecimal getStartPosition() {
		return horizontalSlider.getMinRange();
	}
	
	public BigDecimal getMiddlePosition() {
		return (horizontalSlider.getMaxRange()
						.subtract(horizontalSlider.getMinRange())
						.setScale(1))
										.divide(new BigDecimal(2).setScale(1), BigDecimal.ROUND_DOWN);
	}
	
	public BigDecimal getEndPosition() {
		return horizontalSlider.getMaxRange();
	}
	
	public BigDecimal getCurrentPosition() {
		return horizontalSlider.getCurrentSliderValue();
	}
	
	public void setSliderPositionTo(BigDecimal position, int method) {
		String message = "Chosen method doesn't exist.";
		performLeftMouseClickOnWebElement();
		while (!position.equals(horizontalSlider.getCurrentSliderValue())) {
			if (position.compareTo(horizontalSlider.getCurrentSliderValue()) > 0) {
				switch (method) {
					case HorizontalSliderPage.KEYBOARD:
						performKeypress(Keys.ARROW_RIGHT);
						break;
					case HorizontalSliderPage.MOUSE:
						performMouseMove(MOUSE_MOVE_RIGHT);
						break;
					default:
						BFLogger.logDebug(message + method);
						break;
				}
			} else if (position.compareTo(horizontalSlider.getCurrentSliderValue()) < 0) {
				switch (method) {
					case HorizontalSliderPage.KEYBOARD:
						performKeypress(Keys.ARROW_LEFT);
						break;
					case HorizontalSliderPage.MOUSE:
						performMouseMove(MOUSE_MOVE_LEFT);
						break;
					default:
						BFLogger.logDebug(message + method);
						break;
				}
			}
		}
	}
	
	private void performLeftMouseClickOnWebElement() {
		BasePage.getAction()
						.click(horizontalSlider.getElement()
										.findElement(sliderSelector))
						.build()
						.perform();
	}
	
	private void performKeypress(CharSequence key) {
		BasePage.getAction()
						.sendKeys(key)
						.build()
						.perform();
	}
	
	private void performMouseMove(int side) {
		int offset = horizontalSlider.getStepWidth()
						.intValue();
		if (side == MOUSE_MOVE_LEFT)
			offset = -offset;
		BasePage.getAction()
						.clickAndHold()
						.moveByOffset(offset, 0)
						.release()
						.build()
						.perform();
	}
	
}
