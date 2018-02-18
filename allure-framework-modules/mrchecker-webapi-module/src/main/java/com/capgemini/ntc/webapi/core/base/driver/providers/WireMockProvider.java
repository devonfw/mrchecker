package com.capgemini.ntc.webapi.core.base.driver.providers;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.webapi.core.base.driver.IMockDriver;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

public class WireMockProvider implements IMockDriver {
	
	private final WireMockServer instance;
	
	public WireMockProvider(MockBuilder builder) {
		instance = builder.instance;
	}
	
	@Override
	public int port() {
		return this.instance.port();
	}
	
	@Override
	public int httpsPort() {
		return this.instance.httpsPort();
	}
	
	@Override
	public void start() {
		this.instance.start();
	}
	
	@Override
	public void stop() {
		this.instance.stop();
	}
	
	public static class MockBuilder {
		private int				portHttp;
		private int				portHttps;
		private WireMockServer	instance;
		
		public MockBuilder setHttpPort(int portHttp) {
			this.portHttp = portHttp;
			return this;
		}
		
		public MockBuilder setHttpsPort(int portHttps) {
			this.portHttps = portHttps;
			return this;
		}
		
		public WireMockProvider build() {
			
			WireMockConfiguration wireMockConfig = wireMockConfig();
			wireMockConfig = setHttpPort(wireMockConfig);
			wireMockConfig = setHttpsPort(wireMockConfig);
			
			instance = new WireMockServer(wireMockConfig);
			BFLogger.logDebug("instance = " + this.instance.toString());
			BFLogger.logDebug("wiremockConfig = " + wireMockConfig.toString());
			return new WireMockProvider(this);
			
		}
		
		private WireMockConfiguration setHttpPort(WireMockConfiguration wireMockConfig) {
			if (0 != this.portHttp) {
				wireMockConfig.port(this.portHttp);
			} else {
				wireMockConfig.dynamicPort();
			}
			return wireMockConfig;
		}
		
		private WireMockConfiguration setHttpsPort(WireMockConfiguration wireMockConfig) {
			if (0 != this.portHttp) {
				wireMockConfig.httpsPort(portHttps);
			} else {
				wireMockConfig.dynamicHttpsPort();
			}
			return wireMockConfig;
		}
	}
	
}