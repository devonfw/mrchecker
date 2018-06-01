package com.capgemini.mrchecker.webapi.core.base.properties;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PropertiesFileSettings {
	
	private boolean isVirtualServerEnabled = true; // default value
	
	@Inject(optional = true)
	private void setProperty_isVirtualServerEnabled(@Named("webapi.isVirtualServerEnabled") String value) {
		this.isVirtualServerEnabled = value.toLowerCase()
				.equals("false") ? false : true;
	}
	
	public boolean isVirtualServerEnabled() {
		return this.isVirtualServerEnabled;
	}
	
}
