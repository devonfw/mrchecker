package com.capgemini.mrchecker.selenium.tests.myThaiStar;

import org.junit.runner.RunWith;

import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.IncludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

@RunWith(WildcardPatternSuite.class)
@IncludeCategories({ Tests1.class })
@ExcludeCategories({ Tests0.class })
@SuiteClasses({ "../**/*Test.class" })
public class _TestSuite1 {
	
}
