package com.capgemini.mrchecker.selenium.core.base.runtime;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.mrchecker.selenium.core.base.runtime.RuntimeParametersSelenium;

public class RuntimeParametersTest {
	
	private Map<String, String> values = new HashMap<String, String>();
	
	@Before
	public void setUp() throws Exception {
		
		values.put("browser", "magicBrowser");
		values.put("browserVersion", "11.0");
		values.put("seleniumGrid", "smth");
		values.put("os", "linux");
		
		// values.forEach((String key, String value) -> System.setProperty(key, value));
		values.forEach(System::setProperty);
		
		RuntimeParametersSelenium.BROWSER.refreshParameterValue();
		RuntimeParametersSelenium.BROWSER_VERSION.refreshParameterValue();
		RuntimeParametersSelenium.OS.refreshParameterValue();
		RuntimeParametersSelenium.SELENIUM_GRID.refreshParameterValue();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetProperty() {
		
		assertEquals("System parameters for empty property 'browser' should be 'magicbrowser'", "magicbrowser", RuntimeParametersSelenium.BROWSER.getValue());
		assertEquals("System parameters for empty property 'browserVersion' should be '11.0'", "11.0", RuntimeParametersSelenium.BROWSER_VERSION.getValue());
		assertEquals("System parameters for empty property 'seleniumGrid' should be 'smth'", "smth", RuntimeParametersSelenium.SELENIUM_GRID.getValue());
		assertEquals("System parameters for empty property 'os' should be 'linux'", "linux", RuntimeParametersSelenium.OS.getValue());
		
	}
	
	@Test
	public void testUnknonwRuntimeVariable() throws Exception {
		
	}
	
	@Test
	public void testBrowserIE() throws Exception {
		System.setProperty("browser", "ie");
		RuntimeParametersSelenium.BROWSER.refreshParameterValue();
		
		assertEquals("System parameters for empty property 'browser' should be 'internet explorer'", "internet explorer", RuntimeParametersSelenium.BROWSER.getValue());
		
	}
	
	@Test
	public void testGetEmptyProperty() {
		values.forEach((String key, String value) -> System.clearProperty(key));
		
		RuntimeParametersSelenium.BROWSER.refreshParameterValue();
		RuntimeParametersSelenium.BROWSER_VERSION.refreshParameterValue();
		RuntimeParametersSelenium.OS.refreshParameterValue();
		RuntimeParametersSelenium.SELENIUM_GRID.refreshParameterValue();
		
		assertEquals("System parameters for empty property 'browser' should be 'chrome'", "chrome", RuntimeParametersSelenium.BROWSER.getValue());
		assertEquals("System parameters for empty property 'browserVersion' should be 'null'", "", RuntimeParametersSelenium.BROWSER_VERSION.getValue());
		assertEquals("System parameters for empty property 'seleniumGrid' should be 'false'", "false", RuntimeParametersSelenium.SELENIUM_GRID.getValue());
		assertEquals("System parameters for empty property 'os' should be 'null'", "", RuntimeParametersSelenium.OS.getValue());
		
	}
	
	@Test
	public void testParamsToString() throws Exception {
		
		RuntimeParametersSelenium.valueOf("BROWSER")
						.toString()
						.equals("browser=magicBrowser");
		
	}
	
}
