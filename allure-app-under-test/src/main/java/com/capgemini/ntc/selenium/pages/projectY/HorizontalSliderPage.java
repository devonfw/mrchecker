package com.capgemini.ntc.selenium.pages.projectY;

import java.math.BigDecimal;
import java.math.MathContext;

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
	 * Sets slider to the position given by user using one of following methods:
	 * keyboard button press or mouse move.
	 * <p>
	 * If position chosen by user is less than the minimum or more than the maximum possible value,
	 * then position is automatically limited to minimum or maximum value.
	 * </p>
	 * <p>
	 * Also, if position chosen by user has a value which is not a multiple of step defined for
	 * horizontal slider, then it is rounded to nearest proper value.
	 * </p>
	 * 
	 * @param position
	 *            A destination value of slider's position.
	 * @param method
	 *            The way to perform slider's movement:
	 *            <ul>
	 *            <li>0 - KEYBOARD
	 *            <li>1 - MOUSE
	 *            </ul>
	 * @see HorizontalSliderElement
	 */
	public void setSliderPositionTo(BigDecimal position, int method) {
		String message = "Chosen method doesn't exist.";
		int counter = 0;
		performLeftMouseClickOnWebElement();
		position = verifyAndCorrectPositionValue(position);
		while (position.compareTo(horizontalSlider.getCurrentSliderValue()) != 0 || counter > horizontalSlider.getMaxNumberOfSteps()) {
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
			counter++;
		}
	}
	
	/**
	 * Verifies if given value can be a proper position of horizontal slider and correct it if necessary.
	 * <p>
	 * If given value is less then the minimum possible value then returned value is the minimum.<br>
	 * If given value is more than the maximum possible value then returned value is the maximum.<br>
	 * </p>
	 * <p>
	 * If given value is not a multiply of step defined for horizontal slider, then it is rounded to
	 * nearest multiply of step.
	 * <ul>
	 * <li>If difference between step and scaled value is more than or equal to half of step value, then
	 * position value is rounded up, e.g. step=0.5, position=1.25 is rounded to 1.5.
	 * <li>Otherwise position value is rounded down, e.g. step=0.5, position=1.13 is rounded to 1.
	 * </ul>
	 * </p>
	 * 
	 * @param position
	 *            Given value of slider's position.
	 * @return BigDecimal representing correct value of slider's position.
	 * @see HorizontalSliderElement
	 * @see BigDecimal
	 */
	public BigDecimal verifyAndCorrectPositionValue(BigDecimal position) {
		if (position.compareTo(getStartPosition()) == -1) {
			position = getStartPosition();
		} else if (position.compareTo(getEndPosition()) == 1) {
			position = getEndPosition();
		}
		BigDecimal step = horizontalSlider.getStep();
		BigDecimal rest = position.remainder(step);
		if (rest.compareTo(BigDecimal.ZERO) != 0) {
			BigDecimal sub = step.subtract(rest);
			int result = sub.compareTo(rest);
			switch (result) {
				case -1:
				case 0:
					position = position.add(sub, new MathContext(step.scale() + 1));
					break;
				case 1:
					position = position.subtract(rest, new MathContext(step.scale() + 1));
					break;
				default:
					break;
			}
		}
		if (position.scale() > 0)
			position = position.stripTrailingZeros();
		return position;
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
