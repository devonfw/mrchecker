package com.capgemini.ntc.selenium.core.newDrivers;

import com.capgemini.ntc.selenium.core.BasePage;

public interface IDriverManager {
    final static String AnalitycsCategoryName = "Selenium-NewDrivers";
    
    static void sendAnalyticsEvent() {
        String eventName = Thread.currentThread()
                .getStackTrace()[2].getMethodName();
        BasePage.getAnalytics()
                .event()
                .eventCategory(AnalitycsCategoryName)
                .eventAction(eventName)
                .postAsync();
    }
}
