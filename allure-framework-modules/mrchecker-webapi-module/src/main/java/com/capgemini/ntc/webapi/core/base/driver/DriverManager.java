package com.capgemini.ntc.webapi.core.base.driver;

import static io.restassured.RestAssured.given;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.base.properties.PropertiesFileSettings;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import io.restassured.specification.RequestSpecification;

public class DriverManager {
	
	private static ThreadLocal<RequestSpecification> drivers = new ThreadLocal<RequestSpecification>();
	
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
	
	public static RequestSpecification getDriver() {
		RequestSpecification driver = drivers.get();
		if (driver == null) {
			driver = createDriver();
			drivers.set(driver);
			BFLogger.logDebug("driver: " + driver.toString());
		}
		return driver;
	}
	
	public static void closeDriver() {
		RequestSpecification driver = drivers.get();
		if (driver == null) {
			BFLogger.logDebug("closeDriver() was called but there was no driver for this thread.");
		} else {
			driver = null;
			drivers.remove();
		}
	}
	
	/**
	 * Method sets desired 'driver' depends on chosen parameters
	 */
	private static RequestSpecification createDriver() {
		BFLogger.logDebug("Creating new driver.");
		return given();
	}
	
}
