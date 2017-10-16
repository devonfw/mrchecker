package com.example.selenium.core.newDrivers.elementType;

import com.example.core.logger.BFLogger;
import com.example.selenium.core.BasePage;

import org.openqa.selenium.By;

public class IFrame extends BasicElement implements IBasicElement {

	private By cssSelector;

	public IFrame(By cssSelector) {
		super(ElementType.IFRAME, cssSelector);
		this.cssSelector = cssSelector;
		
		BasePage.getDriver().switchTo().frame(getElement());
		BFLogger.logInfo("Switching to iFrame");
	}

	public void switchToDefaultContent() {
		BasePage.getDriver().switchTo().defaultContent();
	}
}
