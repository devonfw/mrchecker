package com.capgemini.mrchecker.selenium.projectX;

import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.selenium.pages.projectX.MainPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

@TestsChrome
public class MainPageTest extends BaseTest {
	
	@Test
	public void test() {
		PageFactory.getPageInstance(MainPage.class);
	}
}
