package com.example.selenium.tests.tests.webElements;

import com.example.selenium.core.BasePage;
import com.example.selenium.core.newDrivers.elementType.NavigationBarElement;
import com.example.selenium.pages.enums.PageSubURLsEnum;
import com.example.selenium.tests.testSuites.testType.TestsSelenium;
import com.example.core.tests.core.BaseTest;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by LKURZAJ on 07.03.2017.
 */
@Category(TestsSelenium.class)
public class NavigationBarTest extends BaseTest {

    private final static By navigationBarSelector = By.cssSelector("ol#breadcrumbs");
    private final static By childsSelector = By.cssSelector("li");
    private NavigationBarElement navigationBarElement;

    @Test
    public void testGets(){
        assertEquals("Home",this.navigationBarElement.getFirstItemText());
        assertEquals("Tabs",this.navigationBarElement.getActiveItemText());
    }

    @Test
    public void testClickByIndex(){
        this.navigationBarElement.clickItemByIndex(1);
        assertEquals("Tabs",this.navigationBarElement.getActiveItemText());
    }

    @Test
    public void testClickByText(){
        this.navigationBarElement.clickItemByText("Home");
        assertEquals("Home", this.navigationBarElement.getActiveItemText());
    }

    @Test
    public void testClickActiveItem(){
        String url = BasePage.getDriver().getCurrentUrl();
        this.navigationBarElement.clickActiveItem();
        assertEquals(url,BasePage.getDriver().getCurrentUrl());
    }

    @Test
    public void testClickFirstItem(){
        this.navigationBarElement.clickFirstItem();
        assertEquals(1,this.navigationBarElement.getDepth());
    }

    @Test
    public void testConstructor() {
        NavigationBarElement navBarElem = BasePage.getDriver().elementNavigationBar(NavigationBarTest.navigationBarSelector, NavigationBarTest.childsSelector);
        assertEquals(Arrays.asList("Home", "Tabs"),navBarElem.getItemsTextList());
    }

    @Override
    public void setUp() {
        BasePage.getDriver().get(PageSubURLsEnum.WWW_FONT_URL.subURL() + PageSubURLsEnum.TABS.subURL());
        this.navigationBarElement = BasePage.getDriver().elementNavigationBar(NavigationBarTest.navigationBarSelector);
    }

    @Override
    public void tearDown() {

    }
}
