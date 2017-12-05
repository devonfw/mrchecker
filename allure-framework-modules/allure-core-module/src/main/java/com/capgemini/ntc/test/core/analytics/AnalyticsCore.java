package com.capgemini.ntc.test.core.analytics;

import static com.brsanthu.googleanalytics.internal.Constants.TEST_TRACKING_ID;

import com.brsanthu.googleanalytics.GoogleAnalytics;

/**
 * This class stores various system properties
 * 
 * @author LUSTEFAN
 */
public enum AnalyticsCore implements IAnalytics{
	
	GOOGLE();
	
	private GoogleAnalytics instance = null; 
	
	private AnalyticsCore() {
		instance = GoogleAnalytics.builder().withTrackingId(TEST_TRACKING_ID).withAppName("Allure-Test-Framework").withAppVersion("1.0.0").build();
	}


	@Override
	public GoogleAnalytics getBuilder() {
		return this.instance;
	}
}
