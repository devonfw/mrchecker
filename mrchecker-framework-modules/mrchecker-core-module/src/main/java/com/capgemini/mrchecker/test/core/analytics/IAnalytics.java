package com.capgemini.mrchecker.test.core.analytics;

public interface IAnalytics {
	
	public default void sendClassName(String packageName, String className, String description) {
	};
	
	public default void sendClassName() {
		String packageName = Thread.currentThread()
				.getStackTrace()[2].getClassName();
		String className = Thread.currentThread()
				.getStackTrace()[2].getMethodName();
		sendClassName(packageName, className, "");
		
	};
	
	public default void sendMethodEvent(String analitycsCategoryName, String eventName) {
	};
	
	public default void sendMethodEvent(String analitycsCategoryName) {
		String eventName = Thread.currentThread()
				.getStackTrace()[2].getMethodName();
		sendMethodEvent(analitycsCategoryName, eventName);
	}
	
	void setInstance();
	
}
