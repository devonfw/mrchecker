package com.capgemini.mrchecker.webapi;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@ExcludeTags({ "TestsLocal", "TestsNONParallel" })
@SelectPackages("com.capgemini.mrchecker.webapi")
public class _TestSuiteRemote {
	
}
