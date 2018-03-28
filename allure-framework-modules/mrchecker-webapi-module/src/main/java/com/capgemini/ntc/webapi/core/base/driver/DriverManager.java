package com.capgemini.ntc.webapi.core.base.driver;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;
import io.restassured.specification.RequestSpecification;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.base.properties.PropertiesFileSettings;
import com.capgemini.ntc.webapi.core.base.runtime.RuntimeParameters;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.FatalStartupException;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.opentable.extension.BodyTransformer;

public class DriverManager {

	private static ThreadLocal<WireMockServer> drivers = new ThreadLocal<WireMockServer>();
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
		DriverManager.getDriverVirtualService();
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
	
public static void clearAllDrivers() {
		drivers.remove();
	}
	
	public static WireMockServer getDriverVirtualService() {
		WireMockServer driver = drivers.get();
		if (driver == null) {
			driver = createDriver();
			drivers.set(driver);
			BFLogger.logDebug("driver:" + driver.toString());
		}
		return driver;
	}
	

	
	public static RequestSpecification getDriver() {
		RequestSpecification driver = drivers.get();	
	
	public static void closeDriver() {
		WireMockServer driver = drivers.get();
		RequestSpecification driver = drivers.get();
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
private static RequestSpecification createDriver() {
		BFLogger.logDebug("Creating new driver.");
		return given();

private static WireMockServer createDriver() {
		BFLogger.logDebug("Creating new Mock Server");
		
		WireMockServer driver = Driver.WIREMOCK.getDriver();
		
		BFLogger.logDebug("Mock server running under http://localhost:" + driver.port() + " https://localhost:" + driver.httpsPort());
		return driver;
	}
	
	private enum Driver {
		
		WIREMOCK {
			
			private WireMockConfiguration wireMockConfig = wireMockConfig().extensions(new BodyTransformer());
			
			public WireMockServer getDriver() throws FatalStartupException {
				
				int portHttp = RuntimeParameters.MOCK_HTTP_PORT.getValue()
						.isEmpty() ? 0 : getInteger(RuntimeParameters.MOCK_HTTP_PORT.getValue());
				
				int portHttps = RuntimeParameters.MOCK_HTTPS_PORT.getValue()
						.isEmpty() ? 0 : getInteger(RuntimeParameters.MOCK_HTTPS_PORT.getValue());
				
				setHttpPort(portHttp);
				setHttpsPort(portHttps);
				
				WireMockServer driver = new WireMockServer(wireMockConfig);
				
				try {
					driver.start();
				} catch (FatalStartupException e) {
					BFLogger.logError(e.getMessage() + " http_port=" + RuntimeParameters.MOCK_HTTP_PORT.getValue() + " https_port=" + RuntimeParameters.MOCK_HTTPS_PORT.getValue());
					throw new FatalStartupException(e);
				}
				return driver;
				
			}
			
			private void setHttpPort(int portHttp) {
				if (0 != portHttp) {
					this.wireMockConfig.port(portHttp);
				} else {
					this.wireMockConfig.dynamicPort();
				}
			}
			
			private void setHttpsPort(int portHttps) {
				if (0 != portHttps) {
					this.wireMockConfig.httpsPort(portHttps);
				} else {
					this.wireMockConfig.dynamicHttpsPort();
				}
			}
			
			private int getInteger(String value) {
				int number = 0;
				try {
					number = Integer.parseInt(value);
				} catch (NumberFormatException e) {
					BFLogger.logError("Unable convert to integer value=" + value + " Setting default value=0");
				}
				return number;
			}
			
		};
		
		public WireMockServer getDriver() {
			return null;
		}
		
	}
	}
	
}
