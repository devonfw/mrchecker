package com.capgemini.mrchecker.selenium.features.samples;

import org.junit.runner.RunWith;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsLocal;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsNONParallel;
import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

@RunWith(WildcardPatternSuite.class)
@ExcludeCategories({ TestsLocal.class, TestsNONParallel.class })
@SuiteClasses({ "**/samples/**/*Test.class" })

public class _TestSuiteRemote {
	
}
