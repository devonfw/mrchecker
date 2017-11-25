package com.capgemini.ntc.selenium.core.base.runtime;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.ntc.test.core.base.runtime.RuntimeParametersI;
import com.google.common.collect.Lists;

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
		
		RuntimeParameters.BROWSER.refreshParameterValue();
		RuntimeParameters.BROWSER_VERSION.refreshParameterValue();
		RuntimeParameters.OS.refreshParameterValue();
		RuntimeParameters.SELENIUM_GRID.refreshParameterValue();
		
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetProperty() {
		
		assertEquals("System parameters for empty property 'browser' should be 'magicbrowser'", "magicbrowser", RuntimeParameters.BROWSER.getValue());
		assertEquals("System parameters for empty property 'browserVersion' should be '11.0'", "11.0", RuntimeParameters.BROWSER_VERSION.getValue());
		assertEquals("System parameters for empty property 'seleniumGrid' should be 'smth'", "smth", RuntimeParameters.SELENIUM_GRID.getValue());
		assertEquals("System parameters for empty property 'os' should be 'linux'", "linux", RuntimeParameters.OS.getValue());
		
	}
	
	@Test
	public void testUnknonwRuntimeVariable() throws Exception {
		
	}
	
	
	@Test
	public void testBrowserIE() throws Exception {
		System.setProperty("browser", "ie");
		RuntimeParameters.BROWSER.refreshParameterValue();
		
		assertEquals("System parameters for empty property 'browser' should be 'internet explorer'", "internet explorer", RuntimeParameters.BROWSER.getValue());
		
		
	}
	
	@Test
	public void testGetEmptyProperty() {
		values.forEach((String key, String value) -> System.clearProperty(key));
		
		RuntimeParameters.BROWSER.refreshParameterValue();
		RuntimeParameters.BROWSER_VERSION.refreshParameterValue();
		RuntimeParameters.OS.refreshParameterValue();
		RuntimeParameters.SELENIUM_GRID.refreshParameterValue();
		
		assertEquals("System parameters for empty property 'browser' should be 'chrome'", "chrome", RuntimeParameters.BROWSER.getValue());
		assertEquals("System parameters for empty property 'browserVersion' should be '8.0'", "8.0", RuntimeParameters.BROWSER_VERSION.getValue());
		assertEquals("System parameters for empty property 'seleniumGrid' should be 'false'", "false", RuntimeParameters.SELENIUM_GRID.getValue());
		assertEquals("System parameters for empty property 'os' should be 'windows'", "windows", RuntimeParameters.OS.getValue());
		
		
		
	}
	
	@Test
	public void testParamsToString() throws Exception {
		
		RuntimeParameters.valueOf("BROWSER").toString().equals("browser=magicBrowser");
		

	}
	
}
