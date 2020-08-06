package com.capgemini.mrchecker.selenium;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@IncludeTags({ "TestsIE" })
@ExcludeTags({ "TestsLocal", "TestsNONParallel" })
@SelectPackages("com.capgemini.mrchecker.selenium")
public class _TestSuiteIE {
	
}
