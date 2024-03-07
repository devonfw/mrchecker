package com.capgemini.frameworkExamples;

import com.capgemini.framework.enums.PrjEpics;
import com.capgemini.framework.playwright.BaseTest;
import com.capgemini.framework.playwright.PlaywrightFactory;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.capgemini.framework.playwright.PlaywrightFactory.getBrowserContext;
import static com.capgemini.framework.playwright.PlaywrightFactory.getPage;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Epic(PrjEpics.EXAMPLES)
@Story("Storage example")
@TestMethodOrder(MethodOrderer.MethodName.class)
public class StorageExample extends BaseTest {
	
	/**
	 * see documentation: https://playwright.dev/java/docs/auth
	 *
	 * Here is template example without implementation.
	 */
	
	Path   loggedStateFile = Paths.get("target/auth/LoggedState.json");
	String urlLogin        = "your login url";
	String urlApp          = "your app url";
	
	
	@Test
	void authenticate_test() {
		
		Page page = getPage();
		
		//Implement your login
		//		page.navigate("urlLogin");
		//		// Interact with login form
		//		page.getByLabel("Username or email address")
		//				.fill("username");
		//		page.getByLabel("Password")
		//				.fill("password");
		//		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in"))
		//				.click();
		
		// Save logged state to file
		getBrowserContext().storageState(new BrowserContext.StorageStateOptions().setPath(loggedStateFile));
		
	}
	
	@Test
	void yourFirst_test() {
		
		// Reusing signed in state
		Browser.NewContextOptions options = new Browser.NewContextOptions().setStorageStatePath(loggedStateFile);
		PlaywrightFactory.initBrowserContext(options);
		//		page.navigate("urlApp");
		
		//Be sure that at first you init BrowserContext with options and then do actions on pages.
		//In other way: First usage of page is creating default BrowserContext,
		//so the options will not be active in such case.
		
	}
	
}