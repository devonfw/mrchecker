package com.capgemini.mrchecker.selenium.pages.projectY;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class NotificationMessagesPage extends BasePage {

	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
				.contains(PageSubURLsProjectYEnum.NOTIFICATION_MESSAGE.getValue());
	}

	@Override
	public void load() {
		BFLogger.logDebug("Load 'Notification Message' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.NOTIFICATION_MESSAGE.getValue());
		getDriver().waitForPageLoaded();
	}

	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}

}
