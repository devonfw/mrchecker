package com.capgemini.ntc.selenium.tests.tests.webElements;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.enums.PageSubURLsEnum;
import com.capgemini.ntc.selenium.tests.testSuites.testType.TestsSelenium;
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
public class ButtonTest extends BaseTest {

    private static By buttonSubmit = By.cssSelector("button#submit");

    @Test
    public void test(){
        // check if element is displayed
        assertTrue(BaseTest.getDriver().elementButton(ButtonTest.buttonSubmit).isDisplayed());

        // check if element type equals Button
        assertEquals( "Button" , BaseTest.getDriver().elementButton(ButtonTest.buttonSubmit).getElementTypeName());

        // click on button and verify if url was changed
        BaseTest.getDriver().elementButton(ButtonTest.buttonSubmit).click();
        assertTrue(BasePage.getDriver().getCurrentUrl().contains("&submit="));
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
