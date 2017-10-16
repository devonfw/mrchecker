package com.example.selenium.tests.tests.webElements;

import com.example.selenium.core.BasePage;
import com.example.selenium.pages.enums.PageSubURLsEnum;
import com.example.selenium.tests.testSuites.testType.TestsSelenium;
import com.example.core.tests.core.BaseTest;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by LKURZAJ on 03.03.2017.
 */
@Category(TestsSelenium.class)
public class LabelTest extends BaseTest {
    private static By text1Label = By.cssSelector("span.bcd");

    @Test
    public void test(){
        // check if label is displayed
        assertTrue(BasePage.getDriver().elementLabel(LabelTest.text1Label).isDisplayed());

        // check if label has properly text
        assertEquals("Text1",BasePage.getDriver().elementLabel(LabelTest.text1Label).getText());

        // check if label has properly class field
        assertEquals("bcd",BasePage.getDriver().elementLabel(LabelTest.text1Label).getClassName());
    }

    @Override
    public void setUp() {
        BasePage.getDriver().get(PageSubURLsEnum.TOOLS_QA.subURL() + PageSubURLsEnum.AUTOMATION_PRACTICE_FORM.subURL());
        return;
    }

    @Override
    public void tearDown() {
        // TODO Auto-generated method stub
    }
}
