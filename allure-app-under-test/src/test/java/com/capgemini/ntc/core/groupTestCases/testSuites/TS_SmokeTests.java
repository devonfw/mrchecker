package com.capgemini.ntc.core.groupTestCases.testSuites;

import org.junit.runner.RunWith;

import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsSmoke;
import com.capgemini.ntc.test.core.utils.junitoolboxpi.WildcardPatternSuiteBF;
import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.IncludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;

@RunWith(WildcardPatternSuiteBF.class)
@IncludeCategories({ TestsSmoke.class })
@ExcludeCategories({})
@SuiteClasses({ "**/*Test.class" })
public class TS_SmokeTests {
	
}
