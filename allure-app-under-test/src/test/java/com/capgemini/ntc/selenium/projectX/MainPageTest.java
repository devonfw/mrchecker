package com.capgemini.ntc.selenium.projectX;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.ntc.selenium.pages.projectX.MainPage;
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
