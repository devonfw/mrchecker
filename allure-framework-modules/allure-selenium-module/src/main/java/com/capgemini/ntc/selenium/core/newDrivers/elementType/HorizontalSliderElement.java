package com.capgemini.ntc.selenium.core.newDrivers.elementType;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

public class HorizontalSliderElement extends BasicElement {
	
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
	
	public BigDecimal getCurrentSliderValue() throws NumberFormatException {
		WebElement currentValueElement = this.getElement()
						.findElement(this.valueSelector);
		String value = currentValueElement.getText();
		if (value == null || value.isEmpty()) {
			value = getValueAttributeOfWebElement(currentValueElement);
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
	
	public int getMaxNumberOfSteps() {
		return maxNumberOfSteps;
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
	
	private String getValueAttributeOfWebElement(WebElement element) {
		return element.getAttribute("value");
	}
	
	private int calculateMaxNumberOfSteps() {
		return getMaxRange().subtract(getMinRange())
						.divide(getStep())
						.intValueExact();
	}
	
}
