package com.capgemini.mrchecker.test.core.base.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.mrchecker.test.core.base.runtime.RuntimeParametersCore;
import com.google.common.collect.Lists;

public class RuntimeParametersTest {
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("env", "UNKNOWN");
		RuntimeParametersCore.ENV.refreshParameterValue();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetProperty() {
		assertEquals("System parameters for 'env' are not equal", "UNKNOWN", RuntimeParametersCore.ENV.getValue());
	}
	
	@Test
	public void testGetEmptyProperty() {
		System.clearProperty("env");
		RuntimeParametersCore.ENV.refreshParameterValue();
		
		assertEquals("System parameters for empty property 'env' should be 'DEV'", "DEV", RuntimeParametersCore.ENV.getValue());
	}
	
	@Test
	public void testGetParams() throws Exception {
		ArrayList<RuntimeParametersCore> collection = Lists.newArrayList(RuntimeParametersCore.values());
		assertThat(collection, Matchers.hasItem(Matchers.hasToString("env=UNKNOWN")));
	}
	
}
