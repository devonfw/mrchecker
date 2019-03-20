package com.capgemini.mrchecker.selenium.projectX;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectX.MainPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category({ TestsSelenium.class })
public class MainPageTest extends BaseTest {

	@Test
	public void test() {

		new MainPage();
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
