package com.capgemini.mrchecker.test.core.analytics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.mrchecker.test.core.analytics.AnalyticsProvider;

public class AnalyticsProviderTest {
	
	@Before
	public void setup() {
		
	}
	
	@After
	public void teardown() {
		
	}
	
	@Test
	public void testDisabledProvider_sendClassName() {
		AnalyticsProvider analyticsProvider = AnalyticsProvider.DISABLED;
		analyticsProvider.sendClassName("/com/capgemini/ntc/test/core/analytics", "AnalyticsProviderTest", "testDisabledProvider");
	}
	
	@Test
	public void testDisabledProvider_sendMethodEventShort() {
		AnalyticsProvider analyticsProvider = AnalyticsProvider.DISABLED;
		analyticsProvider.sendMethodEvent("Selenium-Test-AnalyticsDisabledProvider");
	}
	
	@Test
	public void testDisabledProvider_sendMethodEventLong() {
		AnalyticsProvider analyticsProvider = AnalyticsProvider.DISABLED;
		analyticsProvider.sendMethodEvent("Selenium-Test-AnalyticsDisabledProvider", "testDisabledProvider - sendMethodEventLong");
	}
	
}
