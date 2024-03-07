package com.capgemini.frameworkExamples;

import com.capgemini.framework.enums.PrjEpics;
import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.playwright.BaseTest;
import com.capgemini.framework.playwright.PlaywrightFactory;
import com.capgemini.framework.tags.Status;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.Geolocation;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.capgemini.framework.playwright.PlaywrightFactory.getPage;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Epic(PrjEpics.EXAMPLES)
@Story("Geolocation example")
public class GeolocationExample extends BaseTest {
	

	
	@Test
	@Description("Geolocation")
	@Tag(Status.DONE)
	void geolocationNewYork_test() {
		
		var geolocationNewYork = new Geolocation(40.730610, -73.935242);
		openMcDonaldUSAMap(geolocationNewYork);
		assertThat(getPage().locator("ol")).containsText("NY 11222");
		AllureStepLogger.info("Location for New York found");
	}
	
	@Test
	@Description("Geolocation")
	@Tag(Status.DONE)
	void geolocationLosAngeles_test() {
		
		var geolocationLosAngeles = new Geolocation(34.098907, -118.327759);
		openMcDonaldUSAMap(geolocationLosAngeles);
		assertThat(getPage().locator("ol")).containsText("Los Angeles");
		AllureStepLogger.info("Location for Los Angeles found");
	}
	
	@Step("Open McDonald map")
	private static void openMcDonaldUSAMap(Geolocation geolocation) {
		Browser.NewContextOptions options = new Browser.NewContextOptions().setGeolocation(geolocation)
				.setPermissions(List.of("geolocation"));
		
		//Be sure that at first you init BrowserContext with options and then do actions on pages.
		//In other way: First usage of page is creating default BrowserContext,
		//so the options will not be active in such case.
		
		PlaywrightFactory.initBrowserContext(options);
		var url = "https://www.mcdonalds.com/us/en-us/restaurant-locator.html";
		
		var PAGE_LOADING_TIMEOUT = 10000;
		Page page = getPage();
		page.navigate(url, new Page.NavigateOptions().setTimeout(PAGE_LOADING_TIMEOUT));
		
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(" Locate me"))
				.click();
		page.waitForLoadState(LoadState.NETWORKIDLE);
		
	}
}