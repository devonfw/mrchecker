package com.example.selenium.tests.testSuites.testSuitsFeatures;

import com.example.core.tests.utils.junitoolboxpi.WildcardPatternSuiteBF;
import com.example.selenium.tests.testSuites.testType.TestsSmoke;

import org.junit.runner.RunWith;

import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.IncludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;

@RunWith(WildcardPatternSuiteBF.class)
@IncludeCategories({ TestsSmoke.class })
@ExcludeCategories({})
@SuiteClasses({ "**/*.class" })
public class TS_SmokeTests {

}
