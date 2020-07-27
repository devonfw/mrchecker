package com.capgemini.mrchecker.selenium.projectY;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@IncludeTags({ "TestsLocal", "TestsNONParallel" })
@SelectPackages("com.capgemini.mrchecker.selenium.projectY")

public class _TestSuiteLocal {
	
}
