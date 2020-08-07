package com.capgemini.mrchecker.webapi;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@IncludeTags({ "TestsWebApi" })
@SelectPackages("com.capgemini.mrchecker.webapi")
public class _TestSuiteRemote {
}
