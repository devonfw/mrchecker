package com.capgemini.ntc.test.core.base.environment;

import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.exceptions.BFInputDataException;

public enum GetEnvironmentParam {
	WWW_FONT_URL,
	SPS_WI_URL,
	TOOLS_QA,
	WEB_SERVICE,
	HEROKUAPP;
	
	public String getAddress() {
		
		if (null == BaseTest.getEnvironmentService()) {
			throw new BFInputDataException("Environment Parameters class wasn't initialized properly");
		}
		
		return BaseTest.getEnvironmentService()
						.getServiceAddress(this.name());
		
	}
	
}
