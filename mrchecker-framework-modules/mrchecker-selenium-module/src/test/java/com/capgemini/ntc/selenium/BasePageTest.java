
package com.capgemini.ntc.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.test.core.BaseTest;

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
