package com.capgemini.mrchecker.selenium.core.base.runtime;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.capgemini.mrchecker.test.core.base.runtime.RuntimeParametersI;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

/**
 * This class stores various system properties
 * 
 * @author LUSTEFAN
 */
public enum RuntimeParametersSelenium implements RuntimeParametersI {
	
	BROWSER("browser", "chrome"),
	BROWSER_VERSION("browserVersion", ""),
	SELENIUM_GRID("seleniumGrid", ""),
	OS("os", ""),
	BROWSER_OPTIONS("browserOptions", "") {
		public Map<String, Object> getValues() {
			return Arrays.asList(this.paramValue.split(";"))
					.stream()
					.filter(i -> i != "") // remove empty inputs
					.map(i -> i.split("=", 2)) // split to key, value. Not more than one time
					.map(i -> new String[] { i[0], (i.length == 1) ? "" : i[1] }) // if value is empty, set empty text
					.collect(Collectors.toMap(i -> i[0], i -> (Object) i[1])); // create Map<String, Object>
			
		}
	};
	
	private String		paramName;
	protected String	paramValue;
	private String		defaultValue;
	
	private RuntimeParametersSelenium(String paramName, String defaultValue) {
		this.paramName = paramName;
		this.defaultValue = defaultValue;
		setValue();
		
	}
	
	@Override
	public String getValue() {
		return this.paramValue;
	}
	
	public Map<String, Object> getValues() {
		return null;
	}
	
	@Override
	public String toString() {
		return paramName + "=" + this.getValue();
	}
	
	@Override
	public void refreshParameterValue() {
		setValue();
	}
	
	private void setValue() {
		
		String paramValue = System.getProperty(this.paramName);
		paramValue = isSystemParameterEmpty(paramValue) ? this.defaultValue : paramValue;
		;
		
		switch (this.name()) {
			case "BROWSER":
				paramValue = paramValue.toLowerCase();
				if (paramValue.equals("ie")) {
					paramValue = "internet explorer";
				}
				break;
			case "BROWSER_VERSION":
				break;
			case "SELENIUM_GRID":
				break;
			case "OS":
				break;
			case "BROWSER_OPTIONS":
				break;
			default:
				BFLogger.logError("Unknown RuntimeParameter = " + this.name());
				break;
		}
		
		this.paramValue = paramValue;
		
	}
	
	private boolean isSystemParameterEmpty(String systemParameterValue) {
		return (null == systemParameterValue || "".equals(systemParameterValue) || "null".equals(systemParameterValue));
	}
	
}
