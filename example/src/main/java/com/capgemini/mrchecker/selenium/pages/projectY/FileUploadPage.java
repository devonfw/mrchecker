package com.capgemini.mrchecker.selenium.pages.projectY;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FileUploadPage extends BasePage {

	private final static By selectorSelectFileButton = By.cssSelector("#file-upload");
	private final static By selectorUploadButton     = By.cssSelector("#file-submit");
	private final static By selectorFileNameLabel    = By.cssSelector("#uploaded-files");

	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
				.contains(PageSubURLsProjectYEnum.UPLOAD.getValue());
	}

	@Override
	public void load() {
		BFLogger.logDebug("Load 'File Uploader' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.UPLOAD.getValue());
		getDriver().waitForPageLoaded();
	}

	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}

	public void selectFileToUploadByClickingSelectFileButton(String filePath) {
		WebElement element = getDriver().findElementDynamic(selectorSelectFileButton);
		element.sendKeys(filePath);
	}

	public void clickUploadButton() {
		Button elementButton = getDriver().elementButton(selectorUploadButton);
		elementButton.click();
	}

	public String getUploadedFileNameText() {
		WebElement elementLabelText = getDriver().findElementDynamic(selectorFileNameLabel);
		return elementLabelText.getText();
	}
}
