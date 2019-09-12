package com.capgemini.mrchecker.selenium.myThaiStar;

import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.IncludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;

// @RunWith(WildcardPatternSuite.class)
@IncludeCategories({ Tests1.class })
@ExcludeCategories({ Tests0.class })
@SuiteClasses({ "../**/*Test.class" })
public class _TestSuite1 {
	
}
