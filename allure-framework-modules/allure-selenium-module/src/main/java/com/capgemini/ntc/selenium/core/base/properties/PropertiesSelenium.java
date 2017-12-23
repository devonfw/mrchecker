package com.capgemini.ntc.selenium.core.base.properties;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PropertiesSelenium {
	
	private String seleniumChrome = "./lib/webdrivers/chrome/chromedriver.exe";// default value
	private String seleniumPhantomjs = "./lib/webdrivers/phantomjs/bin/phantomjs.exe";// default value
	private String seleniumFirefox = "./lib/webdrivers/firefox/geckodriver.exe";// default value
	private String seleniumIE = "./lib/webdrivers/internetexplorer/IEDriverServer.exe";// default value
	
	@Inject(optional = true)
	private void setSeleniumChrome(@Named("selenium.chrome") String path) {
		this.seleniumChrome = path;
		
	}
	
	public String getSeleniumChrome() {
		return this.seleniumChrome;
	}
	
	@Inject(optional = true)
	private void setSeleniumPhantomjs(@Named("selenium.phantomjs") String path) {
		this.seleniumPhantomjs = path;
		
	}
	
	public String getSeleniumPhantomjs() {
		return this.seleniumPhantomjs;
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
	
}
