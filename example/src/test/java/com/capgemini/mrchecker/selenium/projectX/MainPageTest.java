package com.capgemini.mrchecker.selenium.projectX;

import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsChrome;
import com.capgemini.mrchecker.selenium.pages.projectX.MainPage;
import com.capgemini.mrchecker.test.core.BaseTest;

@TestsChrome
public class MainPageTest extends BaseTest {
	
	@Test
	public void test() {
		
		new MainPage().initialize();
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
