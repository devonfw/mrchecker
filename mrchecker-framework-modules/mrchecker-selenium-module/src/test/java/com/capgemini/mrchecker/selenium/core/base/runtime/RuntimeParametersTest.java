package com.capgemini.mrchecker.selenium.core.base.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RuntimeParametersTest {
	
	private Map<String, String> values = new HashMap<String, String>();
	
	@Rule
	public ExpectedException exceptionGrabber = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		
		values.put("browser", "magicBrowser");
		values.put("browserVersion", "11.0");
		values.put("seleniumGrid", "smth");
		values.put("os", "linux");
		values.put("browserOptions", "headless;window-size=1200x600;testEquals=FirstEquals=SecondEquals;--testMe;acceptInsecureCerts=true;maxInstances=3");
		
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
		assertThat(
				"System parameters for empty property 'browserOptions' should be 'headless;window-size=1200x600;testEquals=FirstEquals=SecondEquals;--testMe;acceptInsecureCerts=true;maxInstances=3'",
				RuntimeParametersSelenium.BROWSER_OPTIONS.getValue(),
				Matchers.equalTo("headless;window-size=1200x600;testEquals=FirstEquals=SecondEquals;--testMe;acceptInsecureCerts=true;maxInstances=3"));
		
	}
	
	@Test
	public void testConvertToCorrectTypeBoolean() throws Exception {
		
		// validate type as Boolean
		String value = "true";
		Object convertToCorrectType = RuntimeParametersSelenium.convertToCorrectType(value);
		assertThat(BooleanUtils.toBooleanObject((boolean) convertToCorrectType), IsInstanceOf.instanceOf(Boolean.class));
		
		value = "false";
		convertToCorrectType = RuntimeParametersSelenium.convertToCorrectType(value);
		assertThat(BooleanUtils.toBooleanObject((boolean) convertToCorrectType), IsInstanceOf.instanceOf(Boolean.class));
		
		value = "blue";
		convertToCorrectType = RuntimeParametersSelenium.convertToCorrectType(value);
		exceptionGrabber.expect(ClassCastException.class);
		exceptionGrabber.expectMessage("java.lang.String cannot be cast to java.lang.Boolean");
		assertThat(BooleanUtils.toBooleanObject((boolean) convertToCorrectType), Matchers.not(IsInstanceOf.instanceOf(Boolean.class)));
		
	}
	
	@Test
	public void testConvertToCorrectTypeInteger() throws Exception {
		
		String value = "1";
		Object convertToCorrectType = RuntimeParametersSelenium.convertToCorrectType(value);
		assertThat((Integer) convertToCorrectType, IsInstanceOf.instanceOf(Integer.class));
		
		value = "0.23";
		convertToCorrectType = RuntimeParametersSelenium.convertToCorrectType(value);
		assertThat((Float) convertToCorrectType, IsInstanceOf.instanceOf(Float.class));
		
		value = "blue";
		convertToCorrectType = RuntimeParametersSelenium.convertToCorrectType(value);
		exceptionGrabber.expect(ClassCastException.class);
		exceptionGrabber.expectMessage("java.lang.String cannot be cast to java.lang.Integer");
		assertThat((Integer) convertToCorrectType, IsInstanceOf.instanceOf(Integer.class));
	}
	
	@Test
	public void testConvertToCorrectTypeString() throws Exception {
		
		String value = "hello";
		Object convertToCorrectType = RuntimeParametersSelenium.convertToCorrectType(value);
		assertThat((String) convertToCorrectType, IsInstanceOf.instanceOf(String.class));
		
		value = "";
		convertToCorrectType = RuntimeParametersSelenium.convertToCorrectType(value);
		assertThat((String) convertToCorrectType, IsInstanceOf.instanceOf(String.class));
		
		value = null;
		convertToCorrectType = RuntimeParametersSelenium.convertToCorrectType(value);
		assertThat((String) convertToCorrectType, Matchers.isEmptyOrNullString());
		
	}
	
	@Test
	public void testBrowserOptionsVariable() throws Exception {
		
		Map<String, Object> expected = new HashMap();
		expected.put("testEquals", "FirstEquals=SecondEquals");
		expected.put("headless", "");
		expected.put("--testMe", "");
		expected.put("window-size", "1200x600");
		expected.put("acceptInsecureCerts", true);
		expected.put("maxInstances", 3);
		
		assertThat(RuntimeParametersSelenium.BROWSER_OPTIONS.getValues()
				.size(),
				Matchers.is(6));
		
		assertThat(RuntimeParametersSelenium.BROWSER_OPTIONS.getValues()
				.toString(),
				Matchers.is(expected.toString()));
		
	}
	
	@Test
	public void testBrowserOptionsSet() throws Exception {
		
		RuntimeParametersSelenium.BROWSER_OPTIONS.getValues()
				.forEach((key, value) -> {
					String item = (value.toString()
							.isEmpty()) ? key : key + "=" + value;
					System.out.println(item);
				});
		
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
