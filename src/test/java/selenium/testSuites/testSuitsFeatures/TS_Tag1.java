package com.example.selenium.tests.testSuites.testSuitsFeatures;

import org.junit.runner.RunWith;

import com.example.core.tests.utils.junitoolboxpi.WildcardPatternSuiteBF;
import com.example.selenium.tests.testSuites.testType.TestsTag1;
import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.IncludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;

@RunWith(WildcardPatternSuiteBF.class)
@IncludeCategories({ TestsTag1.class })
@ExcludeCategories({})
@SuiteClasses({ "**/*.class" })
public class TS_Tag1 {

}
