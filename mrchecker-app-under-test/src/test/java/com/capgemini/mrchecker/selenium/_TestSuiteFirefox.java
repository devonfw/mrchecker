package com.capgemini.mrchecker.selenium;

import org.junit.runner.RunWith;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsFirefox;
import com.googlecode.junittoolbox.IncludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

@RunWith(WildcardPatternSuite.class)
@IncludeCategories({ TestsFirefox.class })
@SuiteClasses({ "../**/*Test.class" })

public class _TestSuiteFirefox {
	
}
