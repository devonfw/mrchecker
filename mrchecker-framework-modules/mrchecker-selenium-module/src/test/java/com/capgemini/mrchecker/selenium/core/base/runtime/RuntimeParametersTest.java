package com.capgemini.mrchecker.selenium.core.base.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RuntimeParametersTest {
	
	private Map<String, String> values = new HashMap<String, String>();
	
	@Before
	public void setUp() throws Exception {
		
		values.put("browser", "magicBrowser");
		values.put("browserVersion", "11.0");
		values.put("seleniumGrid", "smth");
		values.put("os", "linux");
		values.put("browserOptions", "headless;window-size=1200x600;--testMe");
		
		// values.forEach((String key, String value) -> System.setProperty(key, value));
		values.forEach(System::setProperty);
		
		RuntimeParametersSelenium.BROWSER.refreshParameterValue();
		RuntimeParametersSelenium.BROWSER_VERSION.refreshParameterValue();
		RuntimeParametersSelenium.OS.refreshParameterValue();
		RuntimeParametersSelenium.SELENIUM_GRID.refreshParameterValue();
		RuntimeParametersSelenium.BROWSER_OPTIONS.refreshParameterValue();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetProperty() {
		
		assertThat("System parameters for empty property 'browser' should be 'magicbrowser'", RuntimeParametersSelenium.BROWSER.getValue(), Matchers.equalTo("magicbrowser"));
		assertThat("System parameters for empty property 'browserVersion' should be '11.0'", RuntimeParametersSelenium.BROWSER_VERSION.getValue(), Matchers.equalTo("11.0"));
		assertThat("System parameters for empty property 'seleniumGrid' should be 'smth'", RuntimeParametersSelenium.SELENIUM_GRID.getValue(), Matchers.equalTo("smth"));
		assertThat("System parameters for empty property 'os' should be 'linux'", RuntimeParametersSelenium.OS.getValue(), Matchers.equalTo("linux"));
		assertThat("System parameters for empty property 'browserOptions' should be 'headless;window-size=1200x600;--testMe'", RuntimeParametersSelenium.BROWSER_OPTIONS.getValue(),
				Matchers.equalTo("headless;window-size=1200x600;--testMe"));
		
	}
	
	@Test
	public void testBrowserOptionsVariable() throws Exception {
		
		String[] excpected = { "headless", "window-size=1200x600", "--testMe" };
		
		assertThat(RuntimeParametersSelenium.BROWSER_OPTIONS.getValues(),
				Matchers.arrayContaining(excpected));
		
		assertThat(RuntimeParametersSelenium.BROWSER_OPTIONS.getValues(),
				Matchers.arrayWithSize(3));
		
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
		RuntimeParametersSelenium.BROWSER_OPTIONS.refreshParameterValue();
		
		assertThat("System parameters for empty property 'browser' should be 'chrome'", RuntimeParametersSelenium.BROWSER.getValue(), Matchers.equalTo("chrome"));
		assertThat("System parameters for empty property 'browserVersion' should be 'null'", RuntimeParametersSelenium.BROWSER_VERSION.getValue(), Matchers.isEmptyString());
		assertThat("System parameters for empty property 'seleniumGrid' should be 'false'", RuntimeParametersSelenium.SELENIUM_GRID.getValue(), Matchers.isEmptyString());
		assertThat("System parameters for empty property 'os' should be 'null'", RuntimeParametersSelenium.OS.getValue(), Matchers.isEmptyString());
		assertThat("System parameters for empty property 'browserOptions' should be 'null'", RuntimeParametersSelenium.BROWSER_OPTIONS.getValue(), Matchers.isEmptyString());
		
	}
	
	@Test
	public void testParamsToString() throws Exception {
		
		RuntimeParametersSelenium.valueOf("BROWSER")
				.toString()
				.equals("browser=magicBrowser");
		
	}
	
}
