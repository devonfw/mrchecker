package com.capgemini.mrchecker.selenium;

import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

public class MainPageTest extends BaseTest {
	private final MainPage mainPage = PageFactory.getPageInstance(MainPage.class);
	
	@Test
	public void test() {
		mainPage.load();
		BFLogger.logInfo("test");
	}
}
