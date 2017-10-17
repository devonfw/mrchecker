package com.capgemini.ntc.selenium.core;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.capgemini.ntc.test.core.logger.BFLogger;
import com.capgemini.ntc.selenium.core.enums.SubUrl;
import com.capgemini.ntc.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.ntc.selenium.core.newDrivers.DriverManager;
import com.capgemini.ntc.selenium.core.newDrivers.INewWebDriver;
import com.capgemini.ntc.selenium.core.utils.WindowUtils;
import com.example.selenium.pages.enums.PageSubURLsEnum;

abstract public class BasePage implements IBasePage {

	private static final By selectorH1TopHeading = By.cssSelector("h1.content-body--top-heading");
	private static final By selectorH1PageTitle = By.id("h1PageTitle");
	private static final By selectorH2TableTitle = By.cssSelector("table#table-content-text h2");

	// in seconds; this value should be used for very shot delay purpose e.g. to
	// wait for JavaScript take effort on element
	public static final int EXPLICITY_SHORT_WAIT_TIME = 1;

	public static final int PROGRESSBARWAITTIMER = 60; 
	// in seconds. timer used in findDynamicElement
	public static final int EXPLICITYWAITTIMER = 20;

	public static final int MAX_COMPONENT_RELOAD_COUNT = 3;

	private static String defaultPassword = "Password345!";

	private static InheritableThreadLocal<String> defaultUsernames = new InheritableThreadLocal<String>() {

		@Override
		protected String initialValue() {
			return "loginName";
		};
	};

	private static WebDriverWait webDriverWait;
	private BasePage parent;

	
	public BasePage() {
		this(getDriver(), null);
	}
	
	public BasePage(BasePage parent) {
		this(getDriver(), parent);
	}
	
	public BasePage(INewWebDriver driver, BasePage parent) {
		webDriverWait = new WebDriverWait(getDriver(), BasePage.EXPLICITYWAITTIMER);

		this.setParent(parent);

		// If the page is not yet loaded, then load it
		if (!(this.isLoaded())) { // In this scenario check if
			this.load();
		}

	}

	public String getActualPageTitle() {
		return getDriver().getTitle();
	}

	public void refreshPage() {
		getDriver().navigate().refresh();
	}

	public static void navigateBack() {
		navigateBack(true);
	}

	/**
	 * Navigates to previous site (works like pressing browsers 'Back' button)
	 * 
	 * @param andWait
	 *            - wait for progress bars to load if true
	 */
	public static void navigateBack(boolean andWait) {
		getDriver().navigate().back();
		getDriver().waitForPageLoaded();
	}

	public static INewWebDriver getDriver() {
		return DriverManager.getDriver();
	}

	public static String getDefaultPassword() {
		return defaultPassword;
	}

	public static String getDefaultUsername() {
		return defaultUsernames.get();
	}

	public static void setDefaultUsername(String username) {
		defaultUsernames.set(username);
	}

	public static Actions getAction() {
		return new Actions(getDriver());
	}

	public void setParent(BasePage parent) {
		this.parent = parent;
	}

	public BasePage getParent() {
		return this.parent;
	}

	/**
	 * @param selectorProgressBar
	 * @param attribute
	 * @param value
	 * @throws WaitForProgressBarTimeoutException
	 * @return true, if progress bar occurred and was handled
	 */
	/*public static boolean waitForFinishProgressBar(IProgressBar progressBar) throws BFWaitingTimeoutException {
		long startTime = System.currentTimeMillis();

		int tmp_timeout = BasePage.PROGRESSBARWAITTIMER;
		boolean progressBarHandled = true;
		String progressBarName = progressBar.getName();
		BFLogger.logDebug("Waiting for " + progressBarName + " to load.");
		try {
			while (progressBar.isLoading()) {
				TimeUtills.waitSeconds(1);
				if (tmp_timeout-- <= 0) {
					BFLogger.logDebug("Timed out after " + PROGRESSBARWAITTIMER + " seconds waiting for "
							+ progressBarName + ".");
					progressBarHandled = false;
					throw new BFWaitingTimeoutException(progressBarName);
				}
			}
		} catch (BFElementNotFoundException e) {
			BFLogger.logDebug("There was no " + progressBarName + " to handle.");
			progressBarHandled = false;
		}

		BFLogger.logTime(startTime, progressBar.getName());

		return progressBarHandled;
	}
*/
	public abstract String pageTitle();

	public void loadPage(SubUrl subUrl) {
		this.loadPage(PageSubURLsEnum.WWW_FONT_URL, subUrl);

	}

	public void loadPage(SubUrl baseURL, SubUrl subUrl) {
		BFLogger.logDebug(getClass().getName() + ": Opening  page: " + baseURL.subURL() + subUrl.subURL());
		getDriver().get(PageSubURLsEnum.WWW_FONT_URL.subURL() + subUrl);
		getDriver().waitForPageLoaded();

	}

	public boolean isUrlAndPageTitleAsCurrentPage(SubUrl subUrl) {
		return isUrlAndPageTitleAsCurrentPage(PageSubURLsEnum.WWW_FONT_URL, subUrl);
	}

	public boolean isUrlAndPageTitleAsCurrentPage(SubUrl baseURL, SubUrl subUrl) {
		getDriver().waitForPageLoaded();
		String pageTitle = this.pageTitle();
		String url = BasePage.getDriver().getCurrentUrl();
		String currentPageTitle = BasePage.getDriver().getTitle();
		if (!url.contains(baseURL.subURL() + subUrl.subURL()) || !pageTitle.equals(currentPageTitle)) {
			BFLogger.logDebug(getClass().getName() + ": Current loaded page (" + url + ") with pageTitle ("
					+ currentPageTitle + "). Page to load: (" + baseURL.subURL() + subUrl.subURL()
					+ ") ,for page title: (" + pageTitle + ")");
			return false;
		}
		return true;
	}

	/**
	 * Recomended to use. It is method to check is element visible. If element not exists in DOM , method throw
	 * PiAtElementNotFoundException
	 * 
	 * @throws BFElementNotFoundException
	 * @param cssSelector
	 * @return false if element have an attribute displayed = none, otherwise return true;
	 */
	public static boolean isElementDisplayed(By cssSelector) {
		List<WebElement> elements = getDriver().findElements(cssSelector);
		if (elements.isEmpty()) {
			throw new BFElementNotFoundException(cssSelector);
		}
		return elements.get(0).isDisplayed();
	}

	/**
	 * It is method to check is element visible. It search element inside parent. If element not exists in DOM , method
	 * throw PiAtElementNotFoundException
	 * 
	 * @throws BFElementNotFoundException
	 * @param cssSelector
	 * @param parent
	 * @return false if element have an attribute displayed = none, otherwise return true;
	 */
	public static boolean isElementDisplayed(By cssSelector, WebElement parent) {
		List<WebElement> elements = parent.findElements(cssSelector);
		if (elements.isEmpty()) {
			throw new BFElementNotFoundException(cssSelector);
		}
		return elements.get(0).isDisplayed();
	}

	/**
	 * Check if given selector is displayed on the page and it contain a specific text
	 * 
	 * @param cssSelector
	 * @param text
	 * @return true if a given element is displayed with a specific text
	 * @throws BFElementNotFoundException
	 *             if element is not found in DOM
	 */
	public static boolean isElementDisplayed(By cssSelector, String text) {
		List<WebElement> elements = getDriver().findElements(cssSelector);
		if (elements.isEmpty()) {
			throw new BFElementNotFoundException(cssSelector);
		}
		boolean retValue = elements.get(0).isDisplayed();
		if (retValue && text != null) {
			retValue = elements.get(0).getText().equals(text);
		}
		return retValue;
	}

	/**
	 * Check if given selector is displayed on the page
	 * 
	 * @param selector
	 * @param parent
	 * @return true if a given element is displayed
	 */
	public static boolean isElementDisplayedNoException(By selector, WebElement parent) {
		try {
			return BasePage.isElementDisplayed(selector, parent);
		} catch (BFElementNotFoundException exc) {
			return false;
		}
	}

	/**
	 * Check if given selector is displayed on the page
	 * 
	 * @param selector
	 * @return true if a given element is displayed
	 */
	public static boolean isElementDisplayedNoException(By selector) {
		try {
			return BasePage.isElementDisplayed(selector);
		} catch (BFElementNotFoundException exc) {
			return false;
		}
	}

	public static boolean isElementPresent(By cssSelector) {
		boolean a = (Assert.assertTrue(true)) ? true:false;
		
		return !getDriver().findElements(cssSelector).isEmpty();
	}

	public static boolean isLinkClickable(By selector) {
		WebElement linkElement = getDriver().findElement(selector);
		return isLinkClickable(linkElement);
	}

	public static boolean isLinkClickable(WebElement element) {
		return !element.getAttribute("href").equals("");
	}

	public static WebDriverWait getWebDriverWait() {
		if (null == webDriverWait) {
			BasePage.webDriverWait = new WebDriverWait(getDriver(), BasePage.EXPLICITYWAITTIMER);
		}
		return BasePage.webDriverWait;
	}

	/**
	 * @param title
	 *            Title of the page displayed in 'h1#PageTitle' or 'h1.content-body--top-heading' elements
	 * @return true if title is displayed
	 * @author
	 */
	public static boolean isH1PageTitleDisplayed(String title) {
		return isTitleElementDisplayed(selectorH1PageTitle, title)
				|| isTitleElementDisplayed(selectorH1TopHeading, title);
	}

	public static boolean isH2TableTitleDisplayed(String title) {
		return isTitleElementDisplayed(selectorH2TableTitle, title);
	}

	private static boolean isTitleElementDisplayed(By selector, String title) {
		List<WebElement> pageTitle = getDriver().findElements(selector);
		boolean resValue = !pageTitle.isEmpty();
		if (resValue) {
			resValue = pageTitle.get(0).isDisplayed() && pageTitle.get(0).getText().equals(title);
		}
		return resValue;
	}

	/**
	 * Open link in new tab
	 * 
	 * @param url
	 */
	public static void openInNewTab(String url) {
		JavascriptExecutor js = (JavascriptExecutor) BasePage.getDriver();
		js.executeScript("window.open(arguments[0], '_blank');", url);
		WindowUtils.switchWindow(url, true);
	}

}
