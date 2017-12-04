package com.capgemini.ntc.selenium.testSuites.testSuitsFeatures;

import org.junit.runner.RunWith;

import com.capgemini.ntc.selenium.testSuites.testType.TestsSmoke;
import com.capgemini.ntc.selenium.testSuites.testType.TestsTag1;
import com.capgemini.ntc.test.core.utils.junitoolboxpi.WildcardPatternSuiteBF;
import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;

@RunWith(WildcardPatternSuiteBF.class)
@ExcludeCategories({ TestsTag1.class, TestsSmoke.class })
@SuiteClasses({ "**/*.class" })
public class TS_TestsIENotTag1NotTestsSmoke {}
