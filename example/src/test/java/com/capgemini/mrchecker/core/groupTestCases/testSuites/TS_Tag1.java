package com.capgemini.mrchecker.core.groupTestCases.testSuites;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@IncludeTags("TestsTag1")
@ExcludeTags("TagToExclude")
@SelectPackages("com.capgemini.mrchecker.core.groupTestCases.testCases")
public class TS_Tag1 {
	
}
