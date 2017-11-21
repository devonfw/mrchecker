package com.capgemini.ntc.test.core.base.runtime;

import java.util.Arrays;
import java.util.Collection;

import com.capgemini.ntc.test.core.base.environment.providers.SpreadsheetEnvironmentService;

/**
 * This class stores various system properties
 * 
 * @author
 *
 */
public enum SystemRuntimeParameters implements RuntimeParameters{
	INSTANCE(SpreadsheetEnvironmentService.INSTANCE);

	public static boolean maintenanceMode = false;
	private int IMPLICITYWAITTIMER = 2; // in seconds

	private String env;
	
	private Collection<Object[]> params;
	
	private SpreadsheetEnvironmentService environmentServices;
	private String maintenanceParam;

	private SystemRuntimeParameters(SpreadsheetEnvironmentService environmentServices) {
		this.environmentServices = environmentServices;
		init();
	}

	/**
	 * 
	 * @return environment, e.g. DEV, DEV
	 */
	public String getEnv() {
		return env;
	}


	/**
	 * 
	 * @return maximal wait time of implicit wait
	 */
	public int getImplicityWaitTimer() {
		return IMPLICITYWAITTIMER;
	}

	/**
	 * 
	 * @return collection containing all parameters
	 */
	public Collection<Object[]> getParams() {
		return params;
	}
	
	

	private void init() {
		getParameters();
		setDefaults();
		createParamsCollection();
	}

	@Override
	public void getParameters() {
		env = System.getProperty("env");
		maintenanceParam = "";// System.getProperty("maintenance");
		
	}

	private void setDefaults() {

	
		if (null != env) {
			env = env.toUpperCase();
		} else {
			env = "DEV"; // default test environment DEV
		}

	
		environmentServices.setEnvironment(env);
	}

	private void createParamsCollection() {
		params = Arrays.asList(new Object[][] { { env, maintenanceMode } });
	}
}
