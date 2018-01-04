package com.capgemini.ntc.test.core.base.properties;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PropertiesCoreTest {
	
	private Boolean coreIsAnalyticsEnabled = true; // default value
	
	@Inject(optional = true)
	private void setIsAnalyticsEnabled(@Named("core.isAnalyticsEnabled") String status) {
		this.coreIsAnalyticsEnabled = status.toLowerCase()
						.equals("false") ? false : true;
		
	}
	
	public Boolean isAnalyticsEnabled() {
		return this.coreIsAnalyticsEnabled;
	}
	
}
