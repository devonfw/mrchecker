package com.capgemini.ntc.test.core.analytics;

import com.capgemini.ntc.test.core.logger.BFLogger;

/**
 * This class stores various system properties
 * 
 * @author LUSTEFAN
 */
public enum AnalyticsProvider implements IAnalytics {
	
	
	
	DISABLED() {
		@Override
		public void sendClassName(String url, String title, String description) {
			BFLogger.logAnalytics("Analytics disabled. Dry run sendClassName()");
		}
		
		@Override
		public void sendMethodEvent(String analitycsCategoryName, String eventName) {
			BFLogger.logAnalytics("Analytics disabled. Dry run sendMethodEvent()");
		}
		
		@Override
		public void setInstance() {
		}
	};
	
	private AnalyticsProvider() {
		setInstance();
	}
	
}
