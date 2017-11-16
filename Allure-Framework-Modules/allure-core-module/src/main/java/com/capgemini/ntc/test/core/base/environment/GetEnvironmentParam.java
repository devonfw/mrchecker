package com.capgemini.ntc.test.core.base.environment;



public enum GetEnvironmentParam {
	WWW_FONT_URL,
	SPS_WI_URL,
	TOOLS_QA,
	WEB_SERVICE;

	public String getAddress() {
		return EnvironmentMain.INSTANCE.getEnvironmentService().getServiceAddress(this.name());
	}
	
	
}
