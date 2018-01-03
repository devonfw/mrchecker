package com.capgemini.ntc.test.core.testRunners;

import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import com.capgemini.ntc.test.core.testRunners.core.ParametrizedTestMethodsFilter;

import junitparams.internal.ParameterisedTestClassRunner;
import junitparams.internal.TestMethod;

/**
 * Although usually a bad idea, since it makes tests less readable, sometimes inheritance is the best way to remove
 * repetitions from tests. JUnitParams is fine with inheritance - you can define a common test in the superclass, and
 * have separate parameters provider methods in the subclasses. Also the other way around is ok, you can define
 * parameter providers in superclass and have tests in subclasses uses them as their input.
 *
 * @author Lipinski (lipinski.pawel@gmail.com)
 */
public class ParallelParameterized extends BlockJUnit4ClassRunner {
	
	private ParametrizedTestMethodsFilter parametrizedTestMethodsFilter = new ParametrizedTestMethodsFilter(this);
	private ParameterisedTestClassRunner parameterisedRunner;
	private Description description;
	
	public ParallelParameterized(Class<?> klass) throws InitializationError {
		super(klass);
		parameterisedRunner = new ParameterisedTestClassRunner(getTestClass());
	}
	
	@Override
	public void filter(Filter filter) throws NoTestsRemainException {
		super.filter(filter);
		this.parametrizedTestMethodsFilter = new ParametrizedTestMethodsFilter(this, filter);
	}
	
	protected void collectInitializationErrors(List<Throwable> errors) {
		for (Throwable throwable : errors)
			throwable.printStackTrace();
	}
	
	@Override
	protected void runChild(FrameworkMethod method, RunNotifier notifier) {
		if (handleIgnored(method, notifier))
			return;
		
		TestMethod testMethod = parameterisedRunner.testMethodFor(method);
		if (parameterisedRunner.shouldRun(testMethod))
			parameterisedRunner.runParameterisedTest(testMethod, methodBlock(method), notifier);
		else
			super.runChild(method, notifier);
	}
	
	private boolean handleIgnored(FrameworkMethod method, RunNotifier notifier) {
		TestMethod testMethod = parameterisedRunner.testMethodFor(method);
		if (testMethod.isIgnored())
			notifier.fireTestIgnored(describeMethod(method));
		
		return testMethod.isIgnored();
	}
	
	@Override
	protected List<FrameworkMethod> computeTestMethods() {
		return parameterisedRunner.computeFrameworkMethods();
	}
	
	@Override
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		Statement methodInvoker = parameterisedRunner.parameterisedMethodInvoker(method, test);
		if (methodInvoker == null)
			methodInvoker = super.methodInvoker(method, test);
		
		return methodInvoker;
	}
	
	@Override
	public Description getDescription() {
		if (description == null) {
			description = Description.createSuiteDescription(getName(), getTestClass().getAnnotations());
			List<FrameworkMethod> resultMethods = getListOfMethods();
			
			for (FrameworkMethod method : resultMethods)
				description.addChild(describeMethod(method));
		}
		
		return description;
	}
	
	private List<FrameworkMethod> getListOfMethods() {
		List<FrameworkMethod> frameworkMethods = parameterisedRunner.returnListOfMethods();
		return parametrizedTestMethodsFilter.filteredMethods(frameworkMethods);
	}
	
	public Description describeMethod(FrameworkMethod method) {
		Description child = parameterisedRunner.describeParameterisedMethod(method);
		
		if (child == null)
			child = describeChild(method);
		
		return child;
	}
	
	/**
	 * Shortcut for returning an array of objects. All parameters passed to this method are returned in an
	 * <code>Object[]</code> array.
	 *
	 * @param params
	 *            Values to be returned in an <code>Object[]</code> array.
	 * @return Values passed to this method.
	 */
	public static Object[] $(Object... params) {
		return params;
	}
}
