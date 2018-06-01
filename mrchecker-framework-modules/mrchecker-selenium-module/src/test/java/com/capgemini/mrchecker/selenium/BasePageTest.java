
package com.capgemini.mrchecker.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.BaseTest;

public class BasePageTest extends BaseTest {
	
	@Override
	public void setUp() {
		
	}
	
	@Override
	public void tearDown() {
		
	}
	
	@Test
	public void testGetDriver() {
		TestPage testPage = new TestPage();
		assertEquals("", "Google", testPage.pageTitle());
		
	}
	
	private class TestPage extends BasePage {
		
		@Override
		public boolean isLoaded() {
			
			return false;
		}
		
		@Override
		public void load() {
			getDriver().get("http://google.com");
			
		}
		
		@Override
		public String pageTitle() {
			return getDriver().getTitle();
		}
		
	}
	
}
