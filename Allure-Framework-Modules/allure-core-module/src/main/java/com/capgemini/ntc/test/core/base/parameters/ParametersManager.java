package com.capgemini.ntc.test.core.base.parameters;

import com.capgemini.ntc.test.core.base.environment.providers.SpreadsheetEnvironmentService;
import com.capgemini.ntc.test.core.base.runtime.RuntimeParameters;
import com.capgemini.ntc.test.core.base.runtime.RuntimeParametersI;
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
	 * @return {@link RuntimeParametersI} singleton
	 */
	public RuntimeParameters parameters() {
		return RuntimeParameters.INSTANCE;
	}

	/**
	 * @return {@link SpreadsheetEnvironmentService} singleton
	 */
	public SpreadsheetEnvironmentService environment() {
		return SpreadsheetEnvironmentService.INSTANCE;
	}

}
