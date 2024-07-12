package com.capgemini.framework.actions;

import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.logger.Logger;
import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Fail;

import java.util.List;
import java.util.function.Consumer;

import static com.capgemini.framework.playwright.PlaywrightFactory.getBrowserContext;
import static com.capgemini.framework.playwright.PlaywrightFactory.getPage;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public final class ActionGui {
	@Step("Fill text field \"{fieldName}\" with \"{inputValue}\"")
	public static void fillTextBox(String fieldName, String inputValue, Locator locator) {
		locator.click();
		locator.fill(inputValue);
		AllureStepLogger.info(inputValue + " in textbox " + fieldName + " is typed");
	}
	
	public static void fillTextBox(String fieldName, String inputValue, String selector) {
		fillTextBox(fieldName, inputValue, getPage().locator(selector));
	}
	
	@Step("Set radiobutton \"{fieldName}\"")
	public static void setRadioButton(String fieldName, Locator locator) {
		locator.click();
		AllureStepLogger.info(fieldName + " is selected");
	}
	
	public static void setRadioButton(String fieldName, String selector) {
		setRadioButton(fieldName, getPage().locator(selector));
	}
	
	@Step("Click \"{fieldName}\"")
	public static void click(String fieldName, Locator locator) {
		if(!locator.isVisible()){
			Fail.fail(fieldName + " is not visible and cannot be clicked.");
		}
		if(!locator.isEnabled()){
			Fail.fail(fieldName + " is not enabled and cannot be clicked.");
		}
		try {
			locator.click(new Locator.ClickOptions().setTimeout(20000));
		} catch (Exception e) {
			Logger.logError("Error while clicking on " + fieldName + ": " + e.getMessage());
			Fail.fail(fieldName + " cannot be clicked.");
		}
		AllureStepLogger.info(fieldName + " is clicked");
	}
	
	public static void click(String fieldName, Locator locator, double timeout) {
		if(!locator.isVisible()){
			Fail.fail(fieldName + " is not visible and cannot be clicked.");
		}
		if(!locator.isEnabled()){
			Fail.fail(fieldName + " is not enabled and cannot be clicked.");
		}
		try {
			locator.click(new Locator.ClickOptions().setTimeout(timeout));
		} catch (Exception e) {
			Logger.logError("Error while clicking on " + fieldName + ": " + e.getMessage());
			Fail.fail(fieldName + " cannot be clicked. " + e.getMessage());
		}
		AllureStepLogger.info(fieldName + " is clicked");
	}
	
	public static void click(String fieldName, Locator locator, boolean waitForPageLoadingToFinish) {
		click(fieldName, locator);
		if (waitForPageLoadingToFinish) {
			ActionGui.waitForPageLoadingFinish();
		}
	}
	
	public static void pressEnter(Locator locator) {
		locator.press("Enter");
	}
	@Step("Select value \"{value}\" in Dropdown \"{fieldName}\"")
	public static void selectDropdownValue(String value, String fieldName, Locator locator) {
		locator.click();
		locator.selectOption(value);
		AllureStepLogger.info(value + " in Dropdown " + fieldName + " is selected");
	}
	@Step("Verify {fieldName} is visible")
	public static void verifyElementVisible(String fieldName, Locator locator) {
		Assertions.assertThat(locator.isVisible()).as(fieldName + " visible").withFailMessage(fieldName + " is not visible").isTrue();
	}
	
	public static void verifyElementVisible(String fieldName, String selector) {
		verifyElementVisible(fieldName, getPage().locator(selector));
	}
	@Step("Wait for {fieldName} to disappear")
	public static void waitForElementToDisappear(String fieldName, Locator locator) {
		locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	@Step("Open page {url}")
	public static void navigate(String url, int pageLoadingTimeout) {
		getPage().navigate(url, new Page.NavigateOptions().setTimeout(pageLoadingTimeout));
		getPage().onLoad(p -> AllureStepLogger.info("Page loaded!"));
	}
	@Step("Check {fieldName} checkbox")
	public static void checkCheckBox(String fieldName, Locator locator) {
		locator.check();
		AllureStepLogger.info(fieldName + " checkbox is checked");
	}
	
	public static void clickIfVisible(String fieldName, Locator locator, boolean b) {
		if (locator.isVisible()) {
			click(fieldName, locator, b);
		}
	}

	@Step("Check Banner has text {bannerText}")
	public static void checkBannerText(String bannerText, Locator locator) {
		AllureStepLogger.info("Banner text is: " + locator.textContent());
		assertThat(locator.textContent()).withFailMessage("Text on banner is " + locator.textContent() + " but it should be " + bannerText)
				.contains(bannerText);
	}

	@Step("Get Banner text")
	public static String getBannerText() {
		Locator banner;
		try{ banner = getPage().locator("div[class*='PageControllerStatusLine']");}
		catch(Exception e){
			return "";
		};
		AllureStepLogger.info("Banner text is: " + banner.textContent());
		return banner.textContent();
	}
	@Step("Check that {element} has text {expectedText}")
	public static void verifyElementHasText(String element, String expectedText, Locator locator) {
		AllureStepLogger.info(element + " text is: " + locator.textContent());
		assertThat(locator.textContent()).withFailMessage("Text is " + locator.textContent() + " but it should be " + expectedText)
				.contains(expectedText);
	}

	public static boolean isElementPresent(Locator locator) {
		return locator.isVisible();
	}

	public void waitForPageToLoad() {
		getPage().waitForLoadState(LoadState.NETWORKIDLE);
	}
	
	public String getPageTitle() {
		return getPage().title();
	}
	
	public static void waitMilliseconds(Integer milliseconds) {
		Logger.logDebug("[waitMilliseconds] " + milliseconds);
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			Logger.logError(e.getMessage());
			Thread.currentThread().interrupt();
		}
	}
	
	@Step("Wait for page loading to finish")
	public static void waitForPageLoadingFinish(){
		String loadingAnimation = "div[id^='RadAjaxLoadingPanel']";
		waitForElementToDisappear("Loading animation", getPage().locator(loadingAnimation).first());
		//second version: getPage().waitForSelector(loadingAnimation).waitForElementState(ElementState.HIDDEN);
	}


	@Step("Check on column name {columnName} for first row text {searchText}")
	public static boolean isTextPresentInFirstRow(String columnName, String searchText) {
		int columnIndex = getColumnIndex(columnName);
		List<ElementHandle> rows = getPage().querySelectorAll("table tr");
		if (!rows.isEmpty()) {
			ElementHandle firstRow = rows.get(0);
			String cellText = firstRow.querySelectorAll("td span").get(columnIndex).textContent();
			return cellText.contains(searchText);
		} else {
			return false;
		}
	}

	@Step("Find index for column name {columnName}")
	private static int getColumnIndex(String columnName) {
		List<ElementHandle> headers = getPage().querySelectorAll("table th a span");
		for (int i = 0; i < headers.size(); i++) {
			String headerText = headers.get(i).textContent();
			if (headerText.equals(columnName)) {
				return i;
			}
		}
		return -1;
	}

	@Step("Create a dialog handler that will check message text and press OK/Cancel")
	public static void checkPopupMessageTextAndPressOKCancel(boolean pressOK, String messageText) {
		Consumer<Dialog> handler = dialog -> {
			Logger.logInfo("Popup message: " + dialog.message());
			Logger.logInfo("Popup message will be OK: " + pressOK);
			// Which option you want to click
			if (dialog.message().contains(messageText)) {
				if (pressOK) {
					// Click on button "OK"
					dialog.accept();
					Logger.logInfo("Clicked on 'OK'");
				} else {
					// Click on button "Cancel"
					dialog.dismiss();
					Logger.logInfo("Clicked on 'Cancel'");
				}
			}else {
				fail("Popup message is not as expected " + dialog.message() + " but it should be " + messageText);
			}
		};
		getPage().onDialog(handler);
		getPage().offDialog(handler);
	}
	@Step("Check that {fieldName} has text {expectedText}")
	public static void checkText(String fieldName, String expectedText, Locator locator) {
		assertThat(locator.textContent())
				.as(fieldName)
				.isEqualTo(expectedText);
	}


}
