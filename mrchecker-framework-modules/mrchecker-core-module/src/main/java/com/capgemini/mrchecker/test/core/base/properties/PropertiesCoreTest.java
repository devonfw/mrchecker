package com.capgemini.mrchecker.test.core.base.properties;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PropertiesCoreTest {
	
	private boolean	coreIsAnalyticsEnabled	= true;
	private boolean	coreIsEncryptionEnabled	= false;
	
	@Inject(optional = true)
	private void setIsAnalyticsEnabled(@Named("core.isAnalyticsEnabled") String status) {
		this.coreIsAnalyticsEnabled = status.toLowerCase()
		        .equals("false") ? false : true;
	}
	
	@Inject(optional = true)
	private void setIsEncryptionEnabled(@Named("core.isEncryptionEnabled") String status) {
		this.coreIsEncryptionEnabled = status.toLowerCase()
		        .equals("true") ? true : false;
	}
	
	public boolean isAnalyticsEnabled() {
		return this.coreIsAnalyticsEnabled;
	}
	
	public boolean isEncryptionEnabled() {
		return coreIsEncryptionEnabled;
	}
	
}
