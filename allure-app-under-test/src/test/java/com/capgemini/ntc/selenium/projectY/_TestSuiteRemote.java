package com.capgemini.ntc.selenium.projectY;

import org.junit.runner.RunWith;

import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsLocal;
import com.capgemini.ntc.core.groupTestCases.testSuites.tags.TestsNONParallel;
import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

@RunWith(WildcardPatternSuite.class)
@ExcludeCategories({ TestsLocal.class, TestsNONParallel.class })
@SuiteClasses({ "*Test.class" })

public class _TestSuiteRemote {
	
}
