package com.capgemini.mrchecker.core.groupTestCases.testSuites;

import org.junit.runner.RunWith;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.IncludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

@RunWith(WildcardPatternSuite.class)
@IncludeCategories({ TestsSelenium.class })
@ExcludeCategories({})
@SuiteClasses({ "../**/*Test.class" })
public class TS_TestsSelenium {
	
}
