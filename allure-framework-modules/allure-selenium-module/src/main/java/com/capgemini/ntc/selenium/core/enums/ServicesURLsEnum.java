package com.capgemini.ntc.selenium.core.enums;

import static com.capgemini.ntc.test.core.base.environments.ParametersManager.environment;


public enum ServicesURLsEnum {
	WWW_FONT_URL,
	SPS_WI_URL,
	TOOLS_QA,
	WEB_SERVICE;

	public String getAddress() {
		return environment().getServiceAddress(this.name());
	}
}
