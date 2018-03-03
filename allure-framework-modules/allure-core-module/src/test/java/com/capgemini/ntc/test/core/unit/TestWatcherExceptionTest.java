package com.capgemini.ntc.test.core.unit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.Computer;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import com.capgemini.ntc.test.core.BaseTest;
import com.capgemini.ntc.test.core.exceptions.BFInputDataException;
import com.capgemini.ntc.test.core.logger.BFLogger;

/**
 * Class tests if exceptions thrown in each of method invoked in BaseTest lead to proper behavior (e.g. stop running).
 * Methods being tested:
 * <li>@BeforeClass method</li>
 * <li>setUp() method</li>
 * <li>sample test method</li>
 * <li>tearDown() method</li>
 * <li>@AfterClass() method</li> Assertion check if exception thrown in test is initialized or object is null/not null
 * what indicate program run stopped/run further
 */

@Ignore
public class TestWatcherExceptionTest {
	private static Computer		computer;
	private static JUnitCore	jUnitCore;
	private static Throwable	thrownException;
	private static Object		object;
	
	@BeforeClass
	public static void initJUnit() {
		BFLogger.logInfo("Starting base test");
		computer = new Computer();
		jUnitCore = new JUnitCore();
		jUnitCore.addListener(new RunListener() {
			@Override
			public void testFailure(Failure failure) throws Exception {
				thrownException = failure.getException();
				BFLogger.logInfo("An exception has been thrown: " + thrownException.getMessage());
			}
		});
	}
	
	private void resetValues() {
		thrownException = null;
		object = null;
	}
	
	@Test
	public void setupClassExceptionTest() {
		resetValues();
		try {
			jUnitCore.run(computer, SetUpClassExceptionTest.class);
		} catch (BFInputDataException e) {
		}
		assertNotNull("There was no exception thrown in test", thrownException);
		assertNull("Object is not null - program run further after exception, what is wrong behaviour", object);
	}
	
	@Test
	public void setupExceptionTest() {
		resetValues();
		try {
			jUnitCore.run(computer, SetUpExceptionTest.class);
		} catch (BFInputDataException e) {
		}
		assertNotNull("There was no exception thrown in test", thrownException);
		assertNull("Object is not null - program run further after exception what is wrong behaviour", object);
	}
	
	@Test
	public void testMethodExceptionTest() {
		resetValues();
		try {
			jUnitCore.run(computer, TestMethodExceptionTest.class);
		} catch (BFInputDataException e) {
		}
		assertNotNull("There was no exception thrown in test", thrownException);
		assertNotNull("Object is null - program didn't run further after exception what implicate test watcher logic was rebuild", object);
	}
	
	@Test
	public void tearDownExceptionTest() {
		resetValues();
		try {
			jUnitCore.run(computer, TearDownExceptionTest.class);
		} catch (BFInputDataException e) {
		}
		assertNotNull("There was no exception thrown in test", thrownException);
		assertNotNull("Object is null - program didn't run further after exception what implicate test watcher logic was rebuild", object);
	}
	
	@Test
	public void tearDownClassExceptionTest() {
		resetValues();
		try {
			jUnitCore.run(computer, TearDownClassExceptionTest.class);
		} catch (BFInputDataException e) {
		}
		assertNotNull("Object is null - program didn't run further after exception what implicate test watcher logic was rebuild", object);
	}
	
	@Ignore
	public static class SetUpClassExceptionTest extends BaseTest {
		
		@BeforeClass
		public static void setUpClazz() {
			BFLogger.logInfo(SetUpClassExceptionTest.class.getCanonicalName() + " -> setUpClazz()");
			throw new BFInputDataException("Text exception");
		}
		
		@Override
		public void setUp() {
			object = null;
		}
		
		@Test
		public void testMethod() {
		}
		
		@Override
		public void tearDown() {
		}
		
	}
	
	@Ignore
	public static class SetUpExceptionTest extends BaseTest {
		
		@Override
		public void setUp() {
			BFLogger.logInfo(this.getClass()
					.getCanonicalName() + " -> setUp()");
			throw new BFInputDataException("Text exception");
		}
		
		@Test
		public void testMethod() {
		}
		
		@Override
		public void tearDown() {
			object = new Object();
		}
		
	}
	
	@Ignore
	public static class TestMethodExceptionTest extends BaseTest {
		
		@Override
		public void setUp() {
		}
		
		@Test
		public void testMethod() {
			BFLogger.logInfo(this.getClass()
					.getCanonicalName() + " -> testMethod()");
			throw new BFInputDataException("Text exception");
		}
		
		@Override
		public void tearDown() {
			object = new Object();
		}
		
	}
	
	@Ignore
	public static class TearDownExceptionTest extends BaseTest {
		
		@Override
		public void setUp() {
		}
		
		@Test
		public void testMethod() {
		}
		
		@Override
		public void tearDown() {
			BFLogger.logInfo(this.getClass()
					.getCanonicalName() + " -> tearDown()");
			throw new BFInputDataException("Text exception");
		}
		
		@AfterClass
		public static void tearDownClazz() {
			object = new Object();
		}
	}
	
	@Ignore
	public static class TearDownClassExceptionTest extends BaseTest {
		
		@Override
		public void setUp() {
		}
		
		@Test
		public void testMethod() {
		}
		
		@Override
		public void tearDown() {
			object = new Object();
		}
		
		@AfterClass
		public static void tearDownClazz() {
			BFLogger.logInfo(TearDownClassExceptionTest.class.getCanonicalName() + " -> tearDownClazz()");
			throw new BFInputDataException("Text exception");
		}
	}
	
}
