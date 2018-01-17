package com.capgemini.ntc.core.groupTestCases.testSuites;

import org.junit.runner.RunWith;

import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsSelenium;
import com.capgemini.ntc.test.core.utils.junitoolboxpi.WildcardPatternSuiteBF;
import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.IncludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;

@RunWith(WildcardPatternSuiteBF.class)
@IncludeCategories({ TestsSelenium.class })
@ExcludeCategories({})
@SuiteClasses({ "**/*Test.class" })
public class TS_TestsSelenium {
	
}
