package com.capgemini.ntc.test.core.base.environment;

import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.exceptions.BFInputDataException;

enum GetEnvironmentParam {
	WWW_FONT_URL,
	SPS_WI_URL,
	TOOLS_QA,
	WEB_SERVICE,
	HEROKUAPP;
	
	IEnvironmentService environmentService = BaseTest.getEnvironmentService();
	
	public String getValue() {
		
		if (null == environmentService) {
			throw new BFInputDataException("Environment Parameters class wasn't initialized properly");
		}
		return environmentService
				.getValue(this.name());
		
	}
	
	public static void refreshAll() {
		for (GetEnvironmentParam e : values()) {
			e.environmentService = BaseTest.getEnvironmentService();
		}
	}
	
}
