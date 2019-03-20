package com.capgemini.mrchecker.core.groupTestCases.testSuites;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsTag1;
import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.IncludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;
import org.junit.runner.RunWith;

@RunWith(WildcardPatternSuite.class)
@IncludeCategories({ TestsTag1.class })
@ExcludeCategories({})
@SuiteClasses("../**/*Test.class")

public class TS_Tag1 {

}
