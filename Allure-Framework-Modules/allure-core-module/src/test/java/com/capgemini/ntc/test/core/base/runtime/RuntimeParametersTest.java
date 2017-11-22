package com.capgemini.ntc.test.core.base.runtime;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.ntc.test.core.base.runtime.provider.RuntimeParameters;
import com.google.common.collect.Lists;

public class RuntimeParametersTest {
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("env", "DEV");
	}
	
	@After
	public void tearDown() throws Exception {
		System.clearProperty("env");
	}
	
	@Test
	public void testGetProperty() {
		assertEquals("System parameters for 'env' are not equal", "DEV", RuntimeParameters.ENV.getValue());
	}
	
	@Test
	public void testGetEmptyProperty() {
		System.clearProperty("env");
		assertEquals("System parameters for empty property 'env' are not equal", null, RuntimeParameters.ENV.getValue());
	}
	
	@Test
	public void testGetParams() throws Exception {
		ArrayList<RuntimeParameters> collection = Lists.newArrayList(RuntimeParameters.values());
		assertThat(collection, Matchers.hasItem(Matchers.hasToString("env=DEV")));
	}
	
}
