package com.capgemini.mrchecker.webapi.core.base.driver;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.webapi.core.base.properties.PropertiesFileSettings;
import com.capgemini.mrchecker.webapi.core.base.runtime.RuntimeParameters;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.FatalStartupException;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.opentable.extension.BodyTransformer;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.specification.RequestSpecification;

public class DriverManager {
	
	private static ThreadLocal<WireMockServer> driversVirtualServer = new ThreadLocal<WireMockServer>();
	
	private static PropertiesFileSettings propertiesFileSettings;
	
	@Inject
	public DriverManager(@Named("properties") PropertiesFileSettings propertiesFileSettings) {
		
		if (null == DriverManager.propertiesFileSettings) {
			DriverManager.propertiesFileSettings = propertiesFileSettings;
		}
		
		this.start();
	}
	
	public void start() {
		
		if (DriverManager.propertiesFileSettings.isVirtualServerEnabled()) {
			DriverManager.getDriverVirtualService();
		}
		DriverManager.getDriverWebAPI();
	}
	
	public void stop() {
		try {
			closeDriverVirtualServer();
			BFLogger.logDebug("Closing Driver in stop()");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		this.stop();
	}
	
	public static void clearAllDrivers() {
		driversVirtualServer.remove();
	}
	
	public static WireMockServer getDriverVirtualService() {
		WireMockServer driver = driversVirtualServer.get();
		if (driver == null) {
			driver = createDriverVirtualServer();
			driversVirtualServer.set(driver);
			BFLogger.logDebug("driver:" + driver.toString());
		}
		return driver;
	}
	
	public static RequestSpecification getDriverWebAPI() {
		RequestSpecification driver = createDriverWebAPI();
		BFLogger.logDebug("driver:" + driver.toString());
		return driver;
	}
	
	public static void closeDriverVirtualServer() {
		WireMockServer driverVirtualServer = driversVirtualServer.get();
		if (driverVirtualServer == null) {
			BFLogger.logDebug("closeDriverVirtualServer() was called but there was no driver for this thread.");
		} else {
			try {
				BFLogger.logDebug("Closing Mock Server under http://localhost:" + driverVirtualServer.port() + " https://localhost:" + driverVirtualServer.httpsPort());
				driverVirtualServer.stop();
			} catch (Exception e) {
				BFLogger.logDebug("Ooops! Something went wrong while closing the driver");
				e.printStackTrace();
			} finally {
				driverVirtualServer = null;
				driversVirtualServer.remove();
			}
		}
	}
	
	/**
	 * Method sets desired 'driver' depends on chosen parameters
	 */
	private static RequestSpecification createDriverWebAPI() {
		BFLogger.logDebug("Creating new driver.");
		RestAssured.config = new RestAssuredConfig().encoderConfig(new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
		return given();
	}
	
	static WireMockServer createDriverVirtualServer() {
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
