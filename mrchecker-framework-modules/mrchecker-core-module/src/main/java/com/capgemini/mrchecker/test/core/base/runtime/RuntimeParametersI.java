package com.capgemini.mrchecker.test.core.base.runtime;

/**
 * @author LUSTEFAN
 */
public interface RuntimeParametersI {
	
	/**
	 * @return value of parameter
	 */
	public String getValue();
	
	/**
	 * Read one more time Runtime parameters
	 */
	public void refreshParameterValue();
	
}
