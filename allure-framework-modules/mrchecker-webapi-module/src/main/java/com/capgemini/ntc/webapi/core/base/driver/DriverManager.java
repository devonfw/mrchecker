package com.capgemini.ntc.webapi.core.base.driver;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.base.properties.PropertiesFileSettings;
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
		getDriver();
	}
	
	public void stop() {
		try {
			closeDriver();
			BFLogger.logDebug("Closing Driver in stop()");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		try {
			closeDriver();
			BFLogger.logDebug("Closed Driver in finalize()");
		} catch (Exception e) {
			e.printStackTrace();
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
				BFLogger.logDebug("Closing WebDriver for this thread.");
				// driver.quit();
			} catch (Exception e) {
				BFLogger.logDebug("An exception occurred during closing the driver: " + e.getMessage());
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
		BFLogger.logDebug("Creating new driver.");
		
		String driver = "created driver";
		return driver;
	}
	
}
