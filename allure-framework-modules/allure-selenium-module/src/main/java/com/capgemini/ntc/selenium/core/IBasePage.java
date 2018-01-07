package com.capgemini.ntc.selenium.core;

public interface IBasePage {
	
	boolean isLoaded();
	
	void load();
	
	BasePage getParent();
	
	void setParent(BasePage parent);
}
