package com.example.selenium.tests.tests.pages.demo.main.navigationbar;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.core.logger.BFLogger;
import com.example.core.tests.core.BaseTest;
import com.example.selenium.pages.features.MainPage;
import com.example.selenium.pages.features.navigationbar.home.NavBarHome;

public class NavigationBarTest extends BaseTest {

	@Test
	public void test() {

		BFLogger.logInfo("[Step 1] Open main page");
		MainPage mainPage = new MainPage();

		BFLogger.logInfo("[Step 2] Click navigation bar - Home");
		mainPage.getNavigationBar().clickNavBarHome();

		BFLogger.logInfo("[Step 3] Verify if main page is opened");
		assertTrue(new NavBarHome(mainPage).isLoaded());
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}

}
