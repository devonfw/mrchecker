package com.capgemini.mrchecker.selenium.projectX;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.mrchecker.selenium.pages.projectX.MainPage;
import com.capgemini.mrchecker.test.core.BaseTest;

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
