package com.capgemini.frameworkExamples;

import com.capgemini.framework.enums.PrjEpics;
import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.playwright.BaseTest;
import com.capgemini.framework.playwright.PlaywrightFactory;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;

import static com.capgemini.framework.playwright.PlaywrightFactory.getPage;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Epic(PrjEpics.EXAMPLES)
@Story("Locale example")
public class LocaleExample extends BaseTest {
	

	@Test
	void localeGermany_test() {
		
		initPlaywrightWithLocale("de_DE");
		openGoogle();
		assertThat(getPage().getByRole(AriaRole.SEARCH)).containsText("Auf gut Glück!");
		AllureStepLogger.info("Page is opened in German");
	}
	

	@Test
	void localeEnglish_test() {
		
		initPlaywrightWithLocale("en_AU"); //Australia
		openGoogle();
		assertThat(getPage().getByRole(AriaRole.SEARCH)).containsText("I'm Feeling Lucky");
		AllureStepLogger.info("Page is opened in English");
	}

	@Test
	void localePolish_test() {
		
		initPlaywrightWithLocale("pl-PL");
		openGoogle();
		assertThat(getPage().getByRole(AriaRole.SEARCH)).containsText("Szczęśliwy traf");
		AllureStepLogger.info("Page is opened in Polish");
	}
	@Step("Set locale: {locale}")
	private void initPlaywrightWithLocale( String locale) {
		
		//Be sure that at first you init BrowserContext with options and then do actions on pages.
		//In other way: First usage of page is creating default BrowserContext,
		//so the options will not be active in such case.
		
		Browser.NewContextOptions options = new Browser.NewContextOptions()
				.setLocale(locale);
		PlaywrightFactory.initBrowserContext(options);
		AllureStepLogger.info("Page is opened in Polish");
	}
	
	@Step("Open Google")
	private void openGoogle() {
		
		var url = "https://www.google.com/";
		
		var PAGE_LOADING_TIMEOUT = 10000;
		Page page = getPage();
		page.navigate(url, new Page.NavigateOptions().setTimeout(PAGE_LOADING_TIMEOUT));
		page.getByRole(AriaRole.BUTTON).last()
				.click();
		page.waitForLoadState(LoadState.LOAD);
	}
	
}