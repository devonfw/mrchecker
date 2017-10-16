package com.capgemini.ntc.selenium.tests.testSuites.testSuitsFeatures;

import com.example.core.tests.utils.junitoolboxpi.WildcardPatternSuiteBF;
import com.capgemini.ntc.selenium.tests.testSuites.testType.TestsTag2;

import org.junit.runner.RunWith;

import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.IncludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;

@RunWith(WildcardPatternSuiteBF.class)
@IncludeCategories({ TestsTag2.class })
@ExcludeCategories({})
@SuiteClasses({ "**/*.class" })
public class TS_Tag2 {

}
