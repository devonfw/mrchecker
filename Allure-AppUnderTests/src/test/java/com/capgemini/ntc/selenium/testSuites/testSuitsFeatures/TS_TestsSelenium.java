package com.capgemini.ntc.selenium.tests.testSuites.testSuitsFeatures;

import org.junit.runner.RunWith;

import com.example.core.tests.utils.junitoolboxpi.WildcardPatternSuiteBF;
import com.capgemini.ntc.selenium.tests.testSuites.testType.TestsSelenium;
import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.IncludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;

@RunWith(WildcardPatternSuiteBF.class)
@IncludeCategories({ TestsSelenium.class })
@ExcludeCategories({})
@SuiteClasses({ "**/*.class" })
public class TS_TestsSelenium {

}
