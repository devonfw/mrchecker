package com.capgemini.ntc.selenium.tests.tests.pages.demo.main;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;

import com.example.core.tests.core.BaseTest;
import com.capgemini.ntc.selenium.pages.features.MainPage;
import com.capgemini.ntc.selenium.tests.testSuites.testType.TestsSelenium;

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
