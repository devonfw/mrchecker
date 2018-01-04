package com.capgemini.ntc.selenium.core.newDrivers.elementType;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.exceptions.BFElementNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BasicElement implements IBasicElement {
	
	private ElementType type;
	private By cssSelector;
	
	public BasicElement(ElementType type, By cssSelector) {
		this.type = type;
		this.setCssSelector(cssSelector);
		
		this.load();
	}
	
	@Override
	public WebElement load() {
		return getElement();
	}
	
	@Override
	public String getElementTypeName() {
		return this.type.toString();
	}
	
	public WebElement getElement() throws BFElementNotFoundException {
		return getElement(0);
	}
	
	public WebElement getElement(int timeOutInSec) throws BFElementNotFoundException {
		return BasePage.getDriver()
				.findElementDynamic(getCssSelector());
	}
	
	public String getClassName() {
		return getElement().getAttribute("class");
	}
	
	public String getValue() {
		return getElement().getAttribute("value");
	}
	
	public String getText() {
		return getElement().getText();
	}
	
	public Boolean isDisplayed() {
		return BasePage.isElementDisplayed(this.getCssSelector());
	}
	
	private By getCssSelector() {
		return cssSelector;
	}
	
	private void setCssSelector(By cssSelector) {
		this.cssSelector = cssSelector;
	}
	
}
