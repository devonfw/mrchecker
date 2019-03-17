package com.capgemini.mrchecker.selenium.pages.projectY;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.ListElements;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class DynamicContentPage extends BasePage {
	
	private static final By	imagesLinksSelector			= By.cssSelector("div#content > div.row img");
	private static final By	imagesDescriptionsSelector	= By.cssSelector("div#content > div.row div.large-10");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.DYNAMIC_CONTENT.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Dynamic Content' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.DYNAMIC_CONTENT.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	/**
	 * Returns list of picture descriptions being present on the web page.
	 * 
	 * @return List of String objects representing descriptions
	 */
	public List<String> getDescriptions() {
		return new ListElements(imagesDescriptionsSelector).getTextList();
	}
	
	/**
	 * Returns a list of image links being present on the web page.
	 * 
	 * @return List of String objects representing paths to pictures
	 */
	public List<String> getImageLinks() {
		return new ListElements(imagesLinksSelector)
						.getList()
						.stream()
						.map(element -> element.getAttribute("src"))
						.collect(Collectors.toList());
	}
}
