package com.capgemini.ntc.selenium.core.newDrivers.elementType;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public class HorizontalSliderElement extends BasicElement {
	
	private BigDecimal	minRange;
	private BigDecimal	maxRange;
	private BigDecimal	step	= BigDecimal.ONE;
	
	private By	sliderSelector;
	private By	valueSelector;
	
	public HorizontalSliderElement(By cssSelector) {
		super(ElementType.HORIZONTAL_SLIDER, cssSelector);
	}
	
	public HorizontalSliderElement(By cssSelector, By sliderSelector, By valueSelector) {
		this(cssSelector);
		this.sliderSelector = sliderSelector;
		this.valueSelector = valueSelector;
	}
	
	public HorizontalSliderElement(By cssSelector, By sliderSelector, By valueSelector, BigDecimal minRange, BigDecimal maxRange, BigDecimal step) {
		this(cssSelector);
		this.sliderSelector = sliderSelector;
		this.valueSelector = valueSelector;
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.step = step;
	}
	
	public BigDecimal getCurrentSliderValue() throws NumberFormatException {
		WebElement currentValueElement = this.getElement()
						.findElement(this.valueSelector);
		String value = currentValueElement.getText();
		// when value is unreachable by getText(), then there is attempt to read "value" attribute instead
		if (value == null || value.isEmpty()) {
			value = currentValueElement.getAttribute("value");
		}
		return new BigDecimal(value);
	}
	
	public BigDecimal getMinRange() {
		return minRange;
	}
	
	public void setMinRange(BigDecimal minRange) {
		this.minRange = minRange;
	}
	
	public BigDecimal getMaxRange() {
		return maxRange;
	}
	
	public void setMaxRange(BigDecimal maxRange) {
		this.maxRange = maxRange;
	}
	
	public BigDecimal getStep() {
		return step;
	}
	
	public void setStep(BigDecimal step) {
		this.step = step;
	}
	
	public Dimension getDimensions() {
		return this.getElement()
						.findElement(sliderSelector)
						.getSize();
	}
	
	public Integer getWidth() {
		return getDimensions().getWidth();
	}
	
	public BigDecimal getStepWidth() {
		BigDecimal rangeDiff = maxRange.subtract(minRange);
		BigDecimal numberOfSteps = rangeDiff.setScale(1)
						.divide(step.setScale(1));
		return new BigDecimal(getWidth()).divide(numberOfSteps);
	}
	
}
