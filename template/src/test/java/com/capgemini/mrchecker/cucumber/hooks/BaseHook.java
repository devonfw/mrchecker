package com.capgemini.mrchecker.cucumber.hooks;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstances;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.TestExecutionObserver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class BaseHook {
	
	/*
	 * PLEASE DO NOT MODIFY THIS FILE. For any Hook file, please create separate one
	 */
	private final CucumberExtensionContext context;
	
	public BaseHook() {
		// Initializes environment
		// Include any global actions before Scenario
		// If no global action needed. Please create separate Hook class with Cucumber Before
		// Include any global actions after Scenario.
		// If no global action needed. Please create separate Hook class with Cucumber After
		BaseTest baseTestHooks = new BaseTest() {
			
			@Override
			public void setUp() {
				// Include any global actions before Scenario
				// If no global action needed. Please create separate Hook class with Cucumber Before
			}
			
			@Override
			public void tearDown() {
				// Include any global actions after Scenario.
				// If no global action needed. Please create separate Hook class with Cucumber After
			}
		};
		
		context = new CucumberExtensionContext(baseTestHooks);
	}
	
	@Before(order = 0)
	public void setup(Scenario scenario) {
		context.setDisplayName(scenario.getName());
		Allure.suite(getFeatureFileNameFromId(scenario.getId()));
		TestExecutionObserver.getInstance()
				.beforeTestExecution(context);
	}
	
	private static String getFeatureFileNameFromId(String id) {
		id = id.substring(id.lastIndexOf("/") + 1);
		return id.substring(0, id.indexOf("."));
	}
	
	@After(order = Integer.MAX_VALUE)
	public void tearDown(Scenario scenario) {
		TestExecutionObserver.getInstance()
				.afterTestExecution(context);
		if (scenario.isFailed()) {
			TestExecutionObserver.getInstance()
					.testFailed(context, null);
		} else {
			TestExecutionObserver.getInstance()
					.testSuccessful(context);
		}
	}
	
	private static class CucumberExtensionContext implements ExtensionContext {
		private String testName;
		private final BaseTest testInstance;
		
		private CucumberExtensionContext(BaseTest testInstance) {
			this.testInstance = testInstance;
		}
		
		@Override
		public Optional<ExtensionContext> getParent() {
			return Optional.empty();
		}
		
		@Override
		public ExtensionContext getRoot() {
			return null;
		}
		
		@Override
		public String getUniqueId() {
			return null;
		}
		
		@Override
		public String getDisplayName() {
			return testName;
		}
		
		public void setDisplayName(String testName) {
			this.testName = testName;
		}
		
		@Override
		public Set<String> getTags() {
			return null;
		}
		
		@Override
		public Optional<AnnotatedElement> getElement() {
			return Optional.empty();
		}
		
		@Override
		public Optional<Class<?>> getTestClass() {
			return Optional.of(testInstance.getClass());
		}
		
		@Override
		public Optional<TestInstance.Lifecycle> getTestInstanceLifecycle() {
			return Optional.empty();
		}
		
		@Override
		public Optional<Object> getTestInstance() {
			return Optional.of(testInstance);
		}
		
		@Override
		public Optional<TestInstances> getTestInstances() {
			return Optional.empty();
		}
		
		@Override
		public Optional<Method> getTestMethod() {
			return Optional.empty();
		}
		
		@Override
		public Optional<Throwable> getExecutionException() {
			return Optional.empty();
		}
		
		@Override
		public Optional<String> getConfigurationParameter(String s) {
			return Optional.empty();
		}
		
		@Override
		public void publishReportEntry(Map<String, String> map) {
		}
		
		@Override
		public Store getStore(Namespace namespace) {
			return null;
		}
		
		@Override
		public <T> Optional<T> getConfigurationParameter(String key, Function<String, T> transformer) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}