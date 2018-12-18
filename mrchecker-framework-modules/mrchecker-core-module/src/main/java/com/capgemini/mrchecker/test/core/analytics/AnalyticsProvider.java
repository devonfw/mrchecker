package com.capgemini.mrchecker.test.core.analytics;

/**
 * This class stores various system properties
 * 
 * @author LUSTEFAN
 */
public enum AnalyticsProvider implements IAnalytics {
	
	DISABLED() {
		@Override
		public void sendClassName(String url, String title, String description) {
			// Enable for future enhancements around Analitycs
			// BFLogger.logAnalytics("Analytics disabled. Dry run sendClassName()");
		}
		
		@Override
		public void sendMethodEvent(String analitycsCategoryName, String eventName) {
			// Enable for future enhancements around Analitycs
			// BFLogger.logAnalytics("Analytics disabled. Dry run sendMethodEvent()");
		}
		
		@Override
		public void setInstance() {
		}
	};
	
	private AnalyticsProvider() {
		setInstance();
	}
	
}
