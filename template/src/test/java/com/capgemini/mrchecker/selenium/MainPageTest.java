package com.capgemini.mrchecker.selenium;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.utils.PageFactory;
import org.junit.Test;

public class MainPageTest extends BaseTest {

	@Test
	public void test() {
		PageFactory.getPageInstance(MainPage.class);
	}
}
