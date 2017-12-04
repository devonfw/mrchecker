package com.capgemini.ntc.selenium.core.base.properties;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PropertiesSelenium {
	
	private String seleniumChrome;
	private String seleniumPhantomjs;
	private String seleniumFirefox;
	private String seleniumIE;

	@Inject
	private void setSeleniumChrome(@Named("selenium.chrome") String path) {
		this.seleniumChrome = path;
		
	}
	
	public String getSeleniumChrome() {
		return this.seleniumChrome;
	}
	
	
	
	@Inject
	private void setSeleniumPhantomjs(@Named("selenium.phantomjs") String path) {
		this.seleniumPhantomjs = path;
		
	}
	
	public String getSeleniumPhantomjs() {
		return this.seleniumPhantomjs;
	}
	
	
	@Inject
	private void setSeleniumFirefox(@Named("selenium.firefox") String path) {
		this.seleniumFirefox = path;
		
	}
	
	public String getSeleniumFirefox() {
		return this.seleniumFirefox;
	}
	

	@Inject
	private void setSeleniumIE(@Named("selenium.ie") String path) {
		this.seleniumIE = path;
		
	}
	
	public String getSeleniumIE() {
		return this.seleniumIE;
	}

	
	
}
