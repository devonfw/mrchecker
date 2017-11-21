package com.capgemini.ntc.test.core.base.runtime.provider;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.capgemini.ntc.test.core.base.environment.providers.SpreadsheetEnvironmentService;
import com.capgemini.ntc.test.core.base.runtime.RuntimeParameters;
import com.google.inject.Singleton;

/**
 * This class stores various system properties
 * 
 * @author LUSTEFAN
 */
@Singleton
public class SystemRuntimeParameters implements RuntimeParameters {
	
	Map<String, String> parameters = new HashMap<String, String>();
	
	public SystemRuntimeParameters() {
		getSystemParameters();
	}
	
	/**
	 * @return environment, e.g. DEV, DEV
	 */
	public String getEnv() {
		return this.parameters.get("env");
	}
	
	/**
	 * @return collection containing all parameters
	 */
	public Map<String, String> getParameters() {
		return this.parameters;
	}
	
	private void getSystemParameters() {
		setParameter("env");
		
	}
	
	private void setParameter(String paramName) {
		String paramValue = System.getProperty(paramName)
				.toUpperCase();
		this.parameters.put(paramName, paramValue);
	}
}
