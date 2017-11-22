package com.capgemini.ntc.test.core.base.runtime;

/**
 * This class stores various system properties
 * 
 * @author LUSTEFAN
 */
public enum RuntimeParameters implements RuntimeParametersImp {
	
	ENV("env");
	
	private String paramName;
	
	private RuntimeParameters(String paramName) {
		this.paramName = paramName;
	}
	
	@Override
	public String getValue() {
		return System.getProperty(this.paramName);
	}
	
	@Override
	public String toString() {
		return paramName + "=" + this.getValue();
	}
	
}
