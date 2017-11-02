package com.capgemini.ntc.test.core.tests.testRunners.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.manipulation.Filter;
import org.junit.runners.model.FrameworkMethod;

import com.capgemini.ntc.test.core.tests.testRunners.ParallelParameterized;

public class ParametrizedTestMethodsFilter {
	private final ParallelParameterized jUnitParamsRunner;

	private final Filter filter;

	public ParametrizedTestMethodsFilter(ParallelParameterized jUnitParamsRunner, Filter filter) {
		this.jUnitParamsRunner = jUnitParamsRunner;
		this.filter = filter;
	}

	public ParametrizedTestMethodsFilter(ParallelParameterized jUnitParamsRunner) {
		this.jUnitParamsRunner = jUnitParamsRunner;
		this.filter = Filter.ALL;
	}

	public List<FrameworkMethod> filteredMethods(List<FrameworkMethod> frameworkMethods) {
		List<FrameworkMethod> filteredMethods = new ArrayList<FrameworkMethod>();

		for (FrameworkMethod frameworkMethod : frameworkMethods) {
			if (filter.shouldRun(jUnitParamsRunner.describeMethod(frameworkMethod))) {
				filteredMethods.add(frameworkMethod);
			}
		}

		return filteredMethods;
	}
}