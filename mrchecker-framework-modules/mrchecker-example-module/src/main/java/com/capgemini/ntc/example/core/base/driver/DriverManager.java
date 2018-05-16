package com.capgemini.ntc.example.core.base.driver;

import com.capgemini.ntc.example.core.base.properties.PropertiesFileSettings;
import com.capgemini.ntc.example.core.base.runtime.RuntimeParameters;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class DriverManager {
	
	private static ThreadLocal<String> drivers = new ThreadLocal<String>();
	
	private static PropertiesFileSettings propertiesFileSettings;
	
	@Inject
	public DriverManager(@Named("properties") PropertiesFileSettings propertiesFileSettings) {
		
		if (null == DriverManager.propertiesFileSettings) {
			DriverManager.propertiesFileSettings = propertiesFileSettings;
		}
		
		this.start();
	}
	
	public void start() {
		DriverManager.getDriver();
	}
	
	public void stop() {
		try {
			closeDriver();
			BFLogger.logDebug("Closing Driver in stop()");
		} catch (Exception e) {
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		try {
			closeDriver();
			BFLogger.logDebug("Closed Driver in finalize()");
		} catch (Exception e) {
		}
		
	}
	
	public static String getDriver() {
		String driver = drivers.get();
		if (driver == null) {
			driver = createDriver();
			drivers.set(driver);
			BFLogger.logDebug("driver:" + driver.toString());
		}
		return driver;
	}
	
	public static void closeDriver() {
		String driver = drivers.get();
		if (driver == null) {
			BFLogger.logDebug("closeDriver() was called but there was no driver for this thread.");
		} else {
			try {
				BFLogger.logDebug("Closing WebDriver for this thread. " + RuntimeParameters.PARAM_1.getValue());
				// driver.quit();
			} catch (Exception e) {
				BFLogger.logDebug("Ooops! Something went wrong while closing the driver: ");
				e.printStackTrace();
			} finally {
				driver = null;
				drivers.remove();
			}
		}
	}
	
	/**
	 * Method sets desired 'driver' depends on chosen parameters
	 */
	private static String createDriver() {
		BFLogger.logDebug("Creating new " + RuntimeParameters.PARAM_1.toString() + "  driver.");
		
		String driver = "created driver";
		return driver;
	}
	
}
