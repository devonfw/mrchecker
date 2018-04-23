package com.capgemini.ntc.test.core.base.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

public class PropertiesManagerTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	private PropertiesCoreTest properties;
	
	@Before
	public void setUp() throws Exception {
		String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/settings.properties");
		Injector i = Guice.createInjector(PropertiesSettingsModule.init(path));
		this.properties = i.getInstance(PropertiesCoreTest.class);
	}
	
	@After
	public void tearDown() throws Exception {
		PropertiesSettingsModule.delInstance();
	}
	
	@Test
	public void testParamterIsAnalyticsEnabled() {
		assertEquals("", true, properties.isAnalyticsEnabled());
	}
	
	@Test
	public void testParamterIsEncryptionEnabled() {
		assertEquals("", true, properties.isEncryptionEnabled());
	}
	
	@Test
	public void testParamterIsAnalyticsEnabledWithUnkonwText() throws Exception {
		PropertiesSettingsModule.delInstance();
		String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/settings3.properties");
		Injector i = Guice.createInjector(PropertiesSettingsModule.init(path));
		properties = i.getInstance(PropertiesCoreTest.class);
		
		assertTrue("", properties.isAnalyticsEnabled());
	}
	
	@Test
	public void testParamterIsEncryptionEnabledWithUnkonwText() throws Exception {
		PropertiesSettingsModule.delInstance();
		String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/settings3.properties");
		Injector i = Guice.createInjector(PropertiesSettingsModule.init(path));
		properties = i.getInstance(PropertiesCoreTest.class);
		
		assertFalse("", properties.isEncryptionEnabled());
	}
	
	@Test
	public void testParamterIsAnalyticsEnabledWithFalse() throws Exception {
		PropertiesSettingsModule.delInstance();
		String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/settings2.properties");
		Injector i = Guice.createInjector(PropertiesSettingsModule.init(path));
		properties = i.getInstance(PropertiesCoreTest.class);
		
		assertFalse("", properties.isAnalyticsEnabled());
	}
	
	@Test
	public void testParamterIsEncryptionEnabledWithFalse() throws Exception {
		PropertiesSettingsModule.delInstance();
		String path = System.getProperty("user.dir") + Paths.get("/src/test/resources/settings2.properties");
		Injector i = Guice.createInjector(PropertiesSettingsModule.init(path));
		properties = i.getInstance(PropertiesCoreTest.class);
		
		assertFalse("", properties.isEncryptionEnabled());
	}
	
	@Test
	public void testDefaultParamters() {
		PropertiesSettingsModule.delInstance();
		
		Injector i = Guice.createInjector(PropertiesSettingsModule.init());
		PropertiesCoreTestFake properties = i.getInstance(PropertiesCoreTestFake.class);
		
		assertEquals("", true, properties.doesNotExist());
	}
	
	private static class PropertiesCoreTestFake {
		
		private Boolean coreDoesNotExist = true;
		
		@Inject(optional = true)
		private void setDoesNotExist(@Named("core.doesNotExist") Boolean status) {
			this.coreDoesNotExist = status;
			
		}
		
		public Boolean doesNotExist() {
			return this.coreDoesNotExist;
		}
		
	}
	
}
