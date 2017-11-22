package com.capgemini.ntc.selenium.core.base.runtime;

import com.capgemini.ntc.test.core.logger.BFLogger;

/**
 * This class stores various system properties
 * 
 * @author LUSTEFAN
 */
public enum RuntimeParameters implements RuntimeParametersImp {
	
	BROWSER("browser", "chrome"),
	BROWSER_VERSION("browserVersion", "8.0"),
	SELENIUM_GRID("seleniumGrid", "false"),
	OS("os", "windows");
	
	private String paramName;
	private String defaultValue;
	
	private RuntimeParameters(String paramName, String defaultValue) {
		this.paramName = paramName;
		this.defaultValue = defaultValue;
		
	}
	
	@Override
	public String getValue() {
		String paramValue = System.getProperty(this.paramName)
				.toLowerCase();
		paramValue = setDefaultValue(paramValue);
		
		switch (this.name()) {
		case "BROWSER":
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
		default:
			BFLogger.logError("Unknown RuntimeParameter = " + this.name());
			break;
		}
		
		return paramValue;
	}
	
	@Override
	public String toString() {
		return paramName + "=" + this.getValue();
	}
	
	private String setDefaultValue(String paramValue) {
		return (null == paramValue || paramValue.equals("")) ? this.defaultValue : paramValue;
	}
	
}
