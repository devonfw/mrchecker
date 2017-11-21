package com.capgemini.ntc.test.core.base.runtime;

import java.util.Collection;
import java.util.Map;

/**
 * @author LUSTEFAN
 *
 */
public interface RuntimeParameters {

	/**
	 * 
	 * @return environment, e.g. DEV, DEV
	 */
	public String getEnv(); 
	
	
	/**
	 * 
	 * @return collection containing all parameters
	 */
	public Map<String, String> getParameters(); 
	
}
