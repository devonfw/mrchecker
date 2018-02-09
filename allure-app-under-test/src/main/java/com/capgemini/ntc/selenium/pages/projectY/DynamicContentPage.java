package com.capgemini.ntc.selenium.pages.projectY;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.ListElements;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class DynamicContentPage extends BasePage {
	
	private static final By	selectorImages			= By.cssSelector("div#content > div.row img");
	private static final By	selectorDescriptions	= By.cssSelector("div#content > div.row div.large-10");
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("The DynamicContent page is loaded.");
		return getDriver().getCurrentUrl()
						.equals(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.DYNAMIC_CONTENT.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("load");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.DYNAMIC_CONTENT.getValue());
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public void refreshPage() {
		getDriver().navigate()
						.refresh();
	}
	
	public List<String> getDescriptions() {
		ListElements descriptions = new ListElements(selectorDescriptions);
		return descriptions.getTextList();
	}
	
	public List<String> getImages() {
		ListElements images = new ListElements(selectorImages);
		List<String> imagesLink = new ArrayList<String>();
		
		for (WebElement element : images.getList()) {
			imagesLink.add(element.getAttribute("src"));
		}
		return imagesLink;
	}
}
