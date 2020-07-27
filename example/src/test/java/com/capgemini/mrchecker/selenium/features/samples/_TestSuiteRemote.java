package com.capgemini.mrchecker.selenium.features.samples;

import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.SelectPackages;

@ExcludeTags({ "TestsLocal", "TestsNONParallel" })
@SelectPackages("com.capgemini.mrchecker.selenium.samples")
public class _TestSuiteRemote {
	
}
