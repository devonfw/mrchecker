package com.capgemini.ntc.core.tests.core;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;

import com.capgemini.ntc.core.tests.testRunners.ParallelTestClassRunner;

@RunWith(ParallelTestClassRunner.class)
public abstract class BaseTest implements IBaseTest {
	

	@BeforeClass
	public static final void setUpClass() throws MalformedURLException {
	}

	@AfterClass
	public static final void tearDownClass() {
	}

	@After
	public void tearDownTestLast() {
	}

	@Override
	abstract public void setUp();

	@Override
	abstract public void tearDown();

	@Rule
	public TestWatcher testWatcher = new BaseTestWatcher(this);



}
