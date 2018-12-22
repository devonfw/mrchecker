package com.capgemini.mrchecker.selenium.core.tests.webElements;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.enums.PageSubURLsEnum;
import com.capgemini.mrchecker.test.core.BaseTest;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by TTRZCINSKI on 19.10.2018.
 */
public class ImageTest extends BaseTest {
    private static By img1 = By.cssSelector("img");

    @AfterClass
    public static void tearDownAll() {
        BasePage.getDriver()
                .close();
    }

    @Test
    public void test() {
        // check if label is displayed
        assertTrue(BasePage.getDriver()
                .elementImage(ImageTest.img1)
                .isDisplayed());
    }

    @Override
    public void setUp() {
        BasePage.getDriver()
                .get(PageSubURLsEnum.TOOLS_QA.subURL() + PageSubURLsEnum.AUTOMATION_PRACTICE_FORM.subURL());
        return;
    }

    @Override
    public void tearDown() {
        // TODO Auto-generated method stub
    }
}
