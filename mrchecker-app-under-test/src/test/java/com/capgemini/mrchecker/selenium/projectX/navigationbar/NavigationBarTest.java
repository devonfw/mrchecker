package com.capgemini.mrchecker.selenium.projectX.navigationbar;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.capgemini.mrchecker.selenium.pages.projectX.MainPage;
import com.capgemini.mrchecker.selenium.pages.projectX.navigationbar.home.NavBarHome;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class NavigationBarTest extends BaseTest {
	
	@Test
	public void test() {
		
		BFLogger.logInfo("[Step 1] Open main page");
		MainPage mainPage = new MainPage();
		
		BFLogger.logInfo("[Step 2] Click navigation bar - Home");
		mainPage.getNavigationBar()
				.clickNavBarHome();
		
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
