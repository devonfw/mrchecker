package com.capgemini.ntc.test.core.base.environments;

import com.google.inject.Singleton;

/**
 * This class maintains various parameters which can be different for each run
 * 
 * @author
 *
 */
@Singleton
public enum ParametersManager {
	
	INSTANCE;
	/**
	 * @return {@link RuntimeParameters} singleton
	 */
	public SystemRuntimeParameters parameters() {
		return SystemRuntimeParameters.INSTANCE;
	}

	/**
	 * @return {@link SpreadsheetEnvironmentService} singleton
	 */
	public SpreadsheetEnvironmentService environment() {
		return SpreadsheetEnvironmentService.INSTANCE;
	}

}
