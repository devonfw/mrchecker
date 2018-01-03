package com.capgemini.ntc.test.core.base.runtime;

/**
 * This class stores various system properties
 * 
 * @author LUSTEFAN
 */
public enum RuntimeParametersCore implements RuntimeParametersI {
	
	ENV("env", "DEV");
	
	private String paramName;
	private String paramValue;
	private String defaultValue;
	
	private RuntimeParametersCore(String paramName, String defaultValue) {
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
		String systemParameterValue = System.getProperty(this.paramName);
		this.paramValue = isSystemParameterEmpty(systemParameterValue) ? this.defaultValue : systemParameterValue;
	}
	
	private boolean isSystemParameterEmpty(String systemParameterValue) {
		return (null == systemParameterValue || "".equals(systemParameterValue) || "null".equals(systemParameterValue));
	}
}
