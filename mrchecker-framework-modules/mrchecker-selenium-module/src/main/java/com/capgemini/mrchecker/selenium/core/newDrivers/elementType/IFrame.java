package com.capgemini.mrchecker.selenium.core.newDrivers.elementType;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class IFrame extends BasicElement implements IBasicElement {
	
	private By cssSelector;
	
	public IFrame(By cssSelector) {
		super(ElementType.IFRAME, cssSelector);
		this.cssSelector = cssSelector;
		
		BasePage.getDriver()
				.switchTo()
				.frame(getElement());
		BFLogger.logInfo("Switching to iFrame");
	}
	
	public void switchToDefaultContent() {
		BasePage.getDriver()
				.switchTo()
				.defaultContent();
	}
}
