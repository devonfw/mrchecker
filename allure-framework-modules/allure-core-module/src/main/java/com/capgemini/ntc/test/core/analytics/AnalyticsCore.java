package com.capgemini.ntc.test.core.analytics;

import com.brsanthu.googleanalytics.GoogleAnalytics;

/**
 * This class stores various system properties
 * 
 * @author LUSTEFAN
 */
public enum AnalyticsCore implements IAnalytics {
  
  GOOGLE();
  
  private GoogleAnalytics instance = null;
  
  private String testTrackingID = "UA-110726320-1";
  
  private AnalyticsCore() {
    instance = GoogleAnalytics.builder()
        .withTrackingId(testTrackingID)
        .withAppName("Allure-Test-Framework")
        .withAppVersion("1.0.0")
        .build();
  }
  
  @Override
  public GoogleAnalytics getInstance() {
    return this.instance;
  }
}
