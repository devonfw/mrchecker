package com.capgemini.ntc.webapi.core.base.runtime;

import com.capgemini.ntc.test.core.base.runtime.RuntimeParametersI;

/**
 * This class stores various system properties These parameters are accessible while test case executes Example :
 * PARAM_3("param_3", "1410") IS: mvn test -Dparam_3=1525 -Dtest=MyTestClass
 * 
 * @author LUSTEFAN
 */
public enum RuntimeParameters implements RuntimeParametersI {
	
	// NAME(<maven-variable-name>, <default-value>)
	;
	
	private String	paramName;
	private String	paramValue;
	private String	defaultValue;
	
	private RuntimeParameters(String paramName, String defaultValue) {
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
		
		this.paramValue = paramValue;
		
	}
	
	private boolean isSystemParameterEmpty(String systemParameterValue) {
		return (null == systemParameterValue || systemParameterValue.isEmpty() || "null".equals(systemParameterValue));
	}
	
}
