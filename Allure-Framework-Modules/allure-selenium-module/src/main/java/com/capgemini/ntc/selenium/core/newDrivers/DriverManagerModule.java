package com.capgemini.ntc.selenium.core.newDrivers;

import com.capgemini.ntc.selenium.core.base.properties.PropertiesModule;
import com.capgemini.ntc.selenium.core.base.properties.PropertiesSelenium;
import com.capgemini.ntc.selenium.core.base.runtime.RuntimeParameters;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.name.Names;

public class DriverManagerModule extends AbstractModule {
	
	@Override
	protected void configure() {
		
		PropertiesSelenium propertiesSelenium = Guice.createInjector(PropertiesModule.init())
				.getInstance(PropertiesSelenium.class);
		
		bind(PropertiesSelenium.class).annotatedWith(Names.named("properties"))
				.toInstance(propertiesSelenium);
		
		bind(RuntimeParameters.class).annotatedWith(Names.named("runtime"))
				.to(RuntimeParameters.class);
		
	}
	
}
