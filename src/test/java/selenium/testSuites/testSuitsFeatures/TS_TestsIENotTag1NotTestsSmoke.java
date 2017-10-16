package com.example.selenium.tests.testSuites.testSuitsFeatures;

import com.example.core.tests.utils.junitoolboxpi.WildcardPatternSuiteBF;
import com.example.selenium.tests.testSuites.testType.TestsIE;
import com.example.selenium.tests.testSuites.testType.TestsSmoke;
import com.example.selenium.tests.testSuites.testType.TestsTag1;

import org.junit.runner.RunWith;

import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.IncludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;

@RunWith(WildcardPatternSuiteBF.class)
@ExcludeCategories({ TestsTag1.class, TestsSmoke.class })
@SuiteClasses({ "**/*.class" })
public class TS_TestsIENotTag1NotTestsSmoke {}
