package com.capgemini.ntc.selenium.core.base.properties;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PropertiesSelenium {
	
	private String seleniumChromePath;

	@Inject
	private void setChromeDriverPath(@Named("selenium.chrome") String path) {
		BFLogger.logDebug("Selenium.chrome path=" + path);
		this.seleniumChromePath = path;
		
	}
	
	public String getChromeDriverPath() {
		return this.seleniumChromePath;
	}
	
}
