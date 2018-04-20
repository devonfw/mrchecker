package com.capgemini.ntc.selenium.core.newDrivers.elementType;

import java.math.BigDecimal;
import java.math.MathContext;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class HorizontalSliderElement extends BasicElement {
	
	public static final int	KEYBOARD	= 0;
	public static final int	MOUSE		= 1;
	
	private static final int	MOUSE_MOVE_LEFT		= 0;
	private static final int	MOUSE_MOVE_RIGHT	= 1;
	
	private BigDecimal	minRange;
	private BigDecimal	maxRange;
	private BigDecimal	step	= BigDecimal.ONE;
	private int			maxNumberOfSteps;
	
	private By	sliderSelector;
	private By	valueSelector;
	
	public HorizontalSliderElement(By sliderContainerSelector) {
		super(ElementType.HORIZONTAL_SLIDER, sliderContainerSelector);
	}
	
	public HorizontalSliderElement(By sliderContainerSelector, By sliderSelector, By valueSelector) {
		this(sliderContainerSelector);
		this.sliderSelector = sliderSelector;
		this.valueSelector = valueSelector;
	}
	
	public HorizontalSliderElement(By sliderContainerSelector, By sliderSelector, By valueSelector, BigDecimal minRange, BigDecimal maxRange, BigDecimal step) {
		this(sliderContainerSelector);
		this.sliderSelector = sliderSelector;
		this.valueSelector = valueSelector;
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.step = step;
		this.maxNumberOfSteps = calculateMaxNumberOfSteps();
	}
	
	/**
	 * Returns actual value of horizontal slider's position.
	 * 
	 * @return BigDecimal representing position's value.
	 * @throws NumberFormatException
	 */
	
	public BigDecimal getCurrentSliderValue() throws NumberFormatException {
		@SuppressWarnings("deprecation")
		WebElement currentValueElement = this.getElement()
						.findElement(this.valueSelector);
		String value = currentValueElement.getText();
		if (value == null || value.isEmpty()) {
			value = getValueAttributeOfWebElement(currentValueElement);
		}
		return new BigDecimal(value);
	}
	
	/**
	 * Returns the lowest possible value of horizontal slider's position.
	 * 
	 * @return BigDecimal representing minimum value from slider's range.
	 */
	public BigDecimal getMinRange() {
		return minRange;
	}
	
	/**
	 * Sets the lowest possible value of horizontal slider's position.
	 * 
	 * @param minRange
	 *            BigDecimal
	 */
	public void setMinRange(BigDecimal minRange) {
		this.minRange = minRange;
	}
	
	/**
	 * Returns the highest possible value of horizontal slider's position.
	 * 
	 * @return BigDecimal representing maximum value from slider's range.
	 */
	public BigDecimal getMaxRange() {
		return maxRange;
	}
	
	/**
	 * Sets the highest possible value of horizontal slider's position.
	 * 
	 * @param minRange
	 *            BigDecimal
	 */
	public void setMaxRange(BigDecimal maxRange) {
		this.maxRange = maxRange;
	}
	
	/**
	 * Returns value which represents a distance between each two following slider's positions.
	 * 
	 * @return BigDecimal representing step's value.
	 */
	public BigDecimal getStep() {
		return step;
	}
	
	/**
	 * Sets the value of horizontal slider's step.
	 * 
	 * @param step
	 *            BigDecimal
	 */
	public void setStep(BigDecimal step) {
		this.step = step;
	}
	
	/**
	 * Returns a number of steps required to move horizontal slider
	 * between minimum and maximum value of horizontal slider's range.
	 * 
	 * @return A number of steps.
	 */
	public int getMaxNumberOfSteps() {
		return maxNumberOfSteps;
	}
	
	/**
	 * Returns a Dimension object describing size of horizontal slider on the web page.
	 * 
	 * @return Horizontal slider's size.
	 * @see Dimension
	 */
	public Dimension getDimensions() {
		return this.getElement()
						.findElement(sliderSelector)
						.getSize();
	}
	
	/**
	 * Returns horizontal slider's width.
	 * 
	 * @return Horizontal slider's width in pixels.
	 */
	public Integer getWidth() {
		return getDimensions().getWidth();
	}
	
	/**
	 * Returns a distance in pixels between each two following slider's positions.
	 * 
	 * @return Step's width.
	 */
	public BigDecimal getStepWidth() {
		BigDecimal rangeDiff = maxRange.subtract(minRange);
		BigDecimal numberOfSteps = rangeDiff.setScale(1)
						.divide(step.setScale(1));
		return new BigDecimal(getWidth()).divide(numberOfSteps);
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
		BasePage.getDriver()
						.mouseLeftClick(getElement().findElement(sliderSelector));
		position = verifyAndCorrectPositionValue(position);
		while (position.compareTo(getCurrentSliderValue()) != 0 || counter > getMaxNumberOfSteps()) {
			if (position.compareTo(getCurrentSliderValue()) > 0) {
				switch (method) {
					case KEYBOARD:
						performKeypress(Keys.ARROW_RIGHT);
						break;
					case MOUSE:
						performMouseMove(MOUSE_MOVE_RIGHT);
						break;
					default:
						BFLogger.logDebug(message + method);
						break;
				}
			} else if (position.compareTo(getCurrentSliderValue()) < 0) {
				switch (method) {
					case KEYBOARD:
						performKeypress(Keys.ARROW_LEFT);
						break;
					case MOUSE:
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
		if (position.compareTo(getMinRange()) == -1) {
			BFLogger.logInfo("Position value: " + position + " will be set to minimum value: " + getMinRange());
			position = getMinRange();
		} else if (position.compareTo(getMaxRange()) == 1) {
			BFLogger.logInfo("Position value: " + position + " will be set to maximum value: " + getMaxRange());
			position = getMaxRange();
		}
		BigDecimal step = getStep();
		BigDecimal rest = position.remainder(step);
		if (rest.compareTo(BigDecimal.ZERO) != 0) {
			BigDecimal oldPosition = position;
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
			if (position.scale() > 0)
				position = position.stripTrailingZeros();
			BFLogger.logInfo("Position value: " + oldPosition + " must be a multiply of " + step + ". It will be rounded to: " + position);
		}
		return position;
	}
	
	private String getValueAttributeOfWebElement(WebElement element) {
		return element.getAttribute("value");
	}
	
	private int calculateMaxNumberOfSteps() {
		return getMaxRange().subtract(getMinRange())
						.divide(getStep())
						.intValueExact();
	}
	
	private void performKeypress(CharSequence key) {
		BasePage.getAction()
						.sendKeys(key)
						.build()
						.perform();
	}
	
	private void performMouseMove(int side) {
		int offset = getStepWidth()
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
