package com.capgemini.mrchecker.webapi.core.base.properties;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.mrchecker.test.core.base.properties.PropertiesSettingsModule;
import com.capgemini.mrchecker.webapi.core.base.properties.PropertiesFileSettings;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class PropertiesFileSettingsTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	private PropertiesFileSettings propertiesFileSettings;
	
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
		PropertiesSettingsModule.delInstance();
	}
	
	@Test
	public void testParameterEnableVirtualServer_True() {
		String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/settings.properties");
		Injector i = Guice.createInjector(PropertiesSettingsModule.init(path));
		this.propertiesFileSettings = i.getInstance(PropertiesFileSettings.class);
		
		assertEquals("", true, propertiesFileSettings.isVirtualServerEnabled());
	}
	
	@Test
	public void testParameterEnableVirtualServer_False() {
		String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/settings2.properties");
		Injector i = Guice.createInjector(PropertiesSettingsModule.init(path));
		this.propertiesFileSettings = i.getInstance(PropertiesFileSettings.class);
		
		assertEquals("", false, propertiesFileSettings.isVirtualServerEnabled());
	}
	
	@Test
	public void testParameterEnableVirtualServer_NoValue() {
		String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/settings3.properties");
		Injector i = Guice.createInjector(PropertiesSettingsModule.init(path));
		this.propertiesFileSettings = i.getInstance(PropertiesFileSettings.class);
		
		assertEquals("", true, propertiesFileSettings.isVirtualServerEnabled());
	}
	
	@Test
	public void testParameterEnableVirtualServer_Text() {
		String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/settings4.properties");
		Injector i = Guice.createInjector(PropertiesSettingsModule.init(path));
		this.propertiesFileSettings = i.getInstance(PropertiesFileSettings.class);
		
		assertEquals("", true, propertiesFileSettings.isVirtualServerEnabled());
	}
	
	@Test
	public void testParameterEnableVirtualServer_NoParameter() {
		String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/settings5.properties");
		Injector i = Guice.createInjector(PropertiesSettingsModule.init(path));
		this.propertiesFileSettings = i.getInstance(PropertiesFileSettings.class);
		
		assertEquals("", true, propertiesFileSettings.isVirtualServerEnabled());
	}
	
	@Test
	public void testDefaultParameters() {
		PropertiesSettingsModule.delInstance();
		
		Injector i = Guice.createInjector(PropertiesSettingsModule.init());
		PropertiesFileSettings propertiesFileSettings = i.getInstance(PropertiesFileSettings.class);
		
		assertEquals("", true, propertiesFileSettings.isVirtualServerEnabled());
	}
	
}
