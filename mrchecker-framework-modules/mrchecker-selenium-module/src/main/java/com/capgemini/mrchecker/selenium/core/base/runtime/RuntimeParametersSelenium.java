package com.capgemini.mrchecker.selenium.core.base.runtime;

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
	OS("os", "");
	
	private String	paramName;
	private String	paramValue;
	private String	defaultValue;
	
	private RuntimeParametersSelenium(String paramName, String defaultValue) {
		this.paramName = paramName;
		this.defaultValue = defaultValue;
		setValue();
		
	}
	
	@Override
	public String getValue() {
		return this.paramValue;
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
		paramValue = isSystemParameterEmpty(paramValue) ? this.defaultValue : paramValue.toLowerCase();
		;
		
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
		
		this.paramValue = paramValue;
		
	}
	
	private boolean isSystemParameterEmpty(String systemParameterValue) {
		return (null == systemParameterValue || "".equals(systemParameterValue) || "null".equals(systemParameterValue));
	}
	
}
