package com.capgemini.ntc.webapi.core.base.driver;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.base.driver.providers.WireMockProvider;
import com.capgemini.ntc.webapi.core.base.properties.PropertiesFileSettings;
import com.capgemini.ntc.webapi.core.base.runtime.RuntimeParameters;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class DriverManager {
	
	private static ThreadLocal<IMockDriver> drivers = new ThreadLocal<IMockDriver>();
	
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
	
	public static IMockDriver getDriver() {
		IMockDriver driver = drivers.get();
		if (driver == null) {
			driver = createDriver();
			drivers.set(driver);
			BFLogger.logDebug("driver:" + driver.toString());
		}
		return driver;
	}
	
	public static void closeDriver() {
		IMockDriver driver = drivers.get();
		if (driver == null) {
			BFLogger.logDebug("closeDriver() was called but there was no driver for this thread.");
		} else {
			try {
				BFLogger.logDebug("Closing Mock Server under http://localhost:" + driver.port() + " https://localhost:" + driver.httpsPort());
				driver.stop();
			} catch (Exception e) {
				BFLogger.logDebug("Ooops! Something went wrong while closing the driver");
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
	private static IMockDriver createDriver() {
		BFLogger.logDebug("Creating new Mock Server");
		
		IMockDriver driver = Driver.WIREMOCK.getDriver();
		driver.start();
		
		BFLogger.logDebug("Mock server running under http://localhost:" + driver.port() + " https://localhost:" + driver.httpsPort());
		return driver;
	}
	
	private enum Driver {
		
		WIREMOCK {
			
			@Override
			public IMockDriver getDriver() {
				
				int portHttp = RuntimeParameters.MOCK_HTTP_PORT.getValue()
						.isEmpty() ? 0 : Integer.parseInt(RuntimeParameters.MOCK_HTTP_PORT.getValue());
				
				int portHttps = RuntimeParameters.MOCK_HTTPS_PORT.getValue()
						.isEmpty() ? 0 : Integer.parseInt(RuntimeParameters.MOCK_HTTPS_PORT.getValue());
				
				WireMockProvider build = new WireMockProvider.MockBuilder()
						.setHttpPort(portHttp)
						.setHttpsPort(portHttps)
						.build();
				
				return build;
				
			}
			
		};
		
		public IMockDriver getDriver() {
			return null;
		}
	}
	
}
