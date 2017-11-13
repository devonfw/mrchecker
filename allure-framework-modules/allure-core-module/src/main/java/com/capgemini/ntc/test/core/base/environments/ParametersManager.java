package com.capgemini.ntc.test.core.base.environments;

/**
 * This class maintains various parameters which can be different for each run
 * 
 * @author
 *
 */
public enum ParametersManager {
	
	INSTANCE;

	/**
	 * @return {@link SpreadsheetEnvironmentService} singleton
	 */
	public SpreadsheetEnvironmentService environment() {
		return SpreadsheetEnvironmentService.INSTANCE;
	}

	/**
	 * @return {@link RuntimeParameters} singleton
	 */
	public RuntimeParameters parameters() {
		return RuntimeParameters.INSTANCE;
	}
}
