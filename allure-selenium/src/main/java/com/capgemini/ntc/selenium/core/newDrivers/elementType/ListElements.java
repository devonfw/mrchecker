package com.capgemini.ntc.selenium.core.newDrivers.elementType;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;

public class ListElements extends BasicElement implements IBasicElement {

	private By cssSelector;

	public ListElements(By cssSelector) {
		super(ElementType.LIST, cssSelector);
		this.cssSelector = cssSelector;
	}

	public Integer getSize() {
		return BasePage.getDriver().findDynamicElements(this.cssSelector).size();

	}

	public List<WebElement> getList() {
		return BasePage.getDriver().findDynamicElements(this.cssSelector);		
	}
	
}
