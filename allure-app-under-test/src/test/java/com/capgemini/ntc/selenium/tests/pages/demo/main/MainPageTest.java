package com.capgemini.ntc.selenium.tests.pages.demo.main;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.ntc.selenium.pages.features.MainPage;
import com.capgemini.ntc.selenium.testSuites.testType.TestsSelenium;
import com.capgemini.ntc.test.core.BaseTest;

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
