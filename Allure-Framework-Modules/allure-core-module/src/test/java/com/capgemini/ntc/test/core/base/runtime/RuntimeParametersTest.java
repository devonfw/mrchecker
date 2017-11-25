package com.capgemini.ntc.test.core.base.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class RuntimeParametersTest {
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("env", "UNKNOWN");
		RuntimeParameters.ENV.refreshParameterValue();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetProperty() {
		assertEquals("System parameters for 'env' are not equal", "UNKNOWN", RuntimeParameters.ENV.getValue());
	}
	
	@Test
	public void testGetEmptyProperty() {
		System.clearProperty("env");
		RuntimeParameters.ENV.refreshParameterValue();
		
		assertEquals("System parameters for empty property 'env' should be 'DEV'", "DEV", RuntimeParameters.ENV.getValue());
	}
	
	@Test
	public void testGetParams() throws Exception {
		ArrayList<RuntimeParameters> collection = Lists.newArrayList(RuntimeParameters.values());
		assertThat(collection, Matchers.hasItem(Matchers.hasToString("env=UNKNOWN")));
	}
	
}
