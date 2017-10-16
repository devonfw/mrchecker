package com.example.core.base.environments;

/**
 * This class maintains various parameters which can be different for each run
 * 
 * @author
 *
 */
public class ParametersManager {

	static EnvironmentServices environmentServices;
	static RuntimeParameters runtimeParameters;

	/**
	 * @return {@link EnvironmentServices} singleton
	 */
	public static EnvironmentServices environment() {
		if (environmentServices == null) {
			environmentServices = new EnvironmentServices();
		}
		return environmentServices;
	}

	/**
	 * @return {@link RuntimeParameters} singleton
	 */
	public static RuntimeParameters parameters() {
		if (runtimeParameters == null) {
			runtimeParameters = new RuntimeParameters();
		}
		return runtimeParameters;
	}

}