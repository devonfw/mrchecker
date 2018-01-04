package com.capgemini.ntc.test.core.analytics;

import com.brsanthu.googleanalytics.GoogleAnalytics;
import com.capgemini.ntc.test.core.logger.BFLogger;

/**
 * This class stores various system properties
 * 
 * @author LUSTEFAN
 */
public enum AnalyticsProvider implements IAnalytics {
	
	GOOGLE() {
		private GoogleAnalytics instance;
		
		@Override
		public void setInstance() {
			String testTrackingID = "UA-110726320-1";
			instance = GoogleAnalytics.builder()
							.withTrackingId(testTrackingID)
							.withAppName("Allure-Test-Framework")
							.withAppVersion("1.0.0") // TASK: Get app version from pom.xml
							.build();
			
		}
		
		public GoogleAnalytics getInstance() {
			return instance;
		}
		
		@Override
		public void sendClassName(String packageName, String className, String description) {
			
			this.getInstance()
							.pageView(packageName, className, description)
							.postAsync();
			
		}
		
		@Override
		public void sendMethodEvent(String analitycsCategoryName, String eventName) {
			this.getInstance()
							.event()
							.eventCategory(analitycsCategoryName)
							.eventAction(eventName)
							.postAsync();
		}
		
	},
	
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
