package com.devonfw.mrchecker.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.devonfw.mrchecker.common.utils.Utils;
import com.devonfw.mrchecker.common.utils.ConfigFileReader;

public class MainPage extends BasePage {

  /** Object used to read properties */
  private static final ConfigFileReader configFileReader = new ConfigFileReader();

  /**
   * Method to check if the current page is loaded
   *
   * @return boolean
   * */
  @Override
  public boolean isLoaded() {
    /* TODO: Code to check if the page is loaded, for example:
     * - An element is found (logo, button, ...).
     * - Url matching (check redirections).
     */
    getDriver();
    return false;
  }

  /**
   * Operations to load the page represented by this class
   * */
  @Override
  public void load() {
    /* TODO: Code to load the main page, for example:
     *  - Go to a url.
     *  - Click a button.
     */

    getDriver();
  }

  /**
   * Page title
   *
   * @return Title used for the page
   * */
  @Override
  public String pageTitle() {
    return "Main page title";
  }

}
