package com.capgemini.ntc.selenium.core.base.properties;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PropertiesSelenium {
	
	private String	webDrivers				= "./lib/webdrivers";									// default value
	private String	seleniumChrome			= webDrivers + "/chrome/chromedriver.exe";				// default value
	private String	seleniumFirefox			= webDrivers + "/firefox/geckodriver.exe";				// default value
	private String	seleniumIE				= webDrivers + "/internetexplorer/IEDriverServer.exe";	// default value
	private String	proxy					= "";													// default value
	private boolean	driverAutoUpdateFlag	= true;													// default value
	
	@Inject(optional = true)
	private void setSeleniumChrome(@Named("selenium.chrome") String path) {
		this.seleniumChrome = path;
		
	}
	
	public String getSeleniumChrome() {
		return this.seleniumChrome;
	}
	
	@Inject(optional = true)
	private void setSeleniumFirefox(@Named("selenium.firefox") String path) {
		this.seleniumFirefox = path;
		
	}
	
	public String getSeleniumFirefox() {
		return this.seleniumFirefox;
	}
	
	@Inject(optional = true)
	private void setSeleniumIE(@Named("selenium.ie") String path) {
		this.seleniumIE = path;
		
	}
	
	public String getSeleniumIE() {
		return this.seleniumIE;
	}
	
	@Inject(optional = true)
	private void setWebDrivers(@Named("selenium.webdrivers") String path) {
		this.webDrivers = path;
		
	}
	
	public String getWebDrivers() {
		return this.webDrivers;
	}
	
	@Inject(optional = true)
	private void setProxy(@Named("selenium.proxy") String path) {
		this.proxy = path;
		
	}
	
	public String getProxy() {
		return this.proxy;
	}
	
	@Inject(optional = true)
	private void setDriverAutoUpdateFlag(@Named("selenium.driverAutoUpdate") boolean flag) {
		this.driverAutoUpdateFlag = flag;
	}
	
	public boolean getDriverAutoUpdateFlag() {
		return this.driverAutoUpdateFlag;
	}
	
}
