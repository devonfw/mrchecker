package com.capgemini.ntc.test.core.base.runtime;

import java.util.Collection;

public interface RuntimeParameters {

	public void getParameters();
	
	
	/**
	 * 
	 * @return environment, e.g. DEV, DEV
	 */
	public String getEnv(); 
	
	
	/**
	 * 
	 * @return collection containing all parameters
	 */
	public Collection<Object[]> getParameters(); 
	
}
