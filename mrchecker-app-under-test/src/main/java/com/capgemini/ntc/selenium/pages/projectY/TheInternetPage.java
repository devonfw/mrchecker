package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.Button;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class TheInternetPage<V> extends BasePage {
	
	private static final By	selectorCheckboxesLink				= By.cssSelector("li > a[href*='checkboxes']");
	private static final By	selectorElementClick				= By.cssSelector("li > a[href*='abtest']");
	private static final By	selectorBrokenImageClickLink		= By.cssSelector("li > a[href*='broken_images']");
	private static final By	selectorDropdownClickLink			= By.cssSelector("li > a[href*='dropdown']");
	private static final By	selectorMultipleWindowsLink			= By.cssSelector("li > a[href*='windows']");
	private static final By	selectorBasicAuthLink				= By.cssSelector("li > a[href*='basic_auth']");
	private static final By	selectorKeyPressesLink				= By.cssSelector("li > a[href*='key_presses']");
	private static final By	selectorRedirectLink				= By.cssSelector("li > a[href*='redirector']");
	private static final By	selectorJavaScriptAlertLink			= By.cssSelector("li > a[href*='javascript_alerts']");
	private static final By	selectorHoversLink					= By.cssSelector("li > a[href*='hovers']");
	private static final By	selectorSortableDataTablesLink		= By.cssSelector("li > a[href*='tables']");
	private static final By	selectorChallengingDomClick			= By.cssSelector("li > a[href*='challenging_dom']");
	private static final By	selectorStatusCodesLink				= By.cssSelector("li > a[href*='status_codes']");
	private static final By	selectorDynamicContent				= By.cssSelector("li > a[href*='dynamic_content']");
	private static final By	selectorHorizontalSliderLink		= By.cssSelector("li > a[href*='horizontal_slider']");
	private static final By	selectorFormAuthenticationLink		= By.cssSelector("li > a[href*='login']");
	private static final By	selectorFileDownloadLink			= By.cssSelector("li > a[href$='download']");
	private static final By	selectorForgotPasswordLink			= By.cssSelector("li > a[href*='forgot_password']");
	private static final By	selectorExitIntentLink				= By.cssSelector("li > a[href*='exit_intent']");
	private static final By	selectorDynamicLoadingLink			= By.cssSelector("li > a[href*='dynamic_loading']");
	private static final By	selectorDisappearingElementsLink	= By.cssSelector("li > a[href*='disappearing_elements']");
	private static final By	selectorDragAndDropLink				= By.cssSelector("li > a[href*='drag_and_drop']");
	private static final By	selectorContextMenuLink				= By.cssSelector("li > a[href*='context_menu']");
	private static final By	selectorDynamicControlsLink			= By.cssSelector("li > a[href*='dynamic_controls']");
	private static final By	selectorFileUploadLink				= By.cssSelector("li > a[href*='upload']");
	private static final By	selectorFloatingMenuLink			= By.cssSelector("li > a[href*='floating_menu']");
	private static final By	selectorFramesLink					= By.cssSelector("li > a[href*='frames']");
	private static final By	selectorGeolocationLink				= By.cssSelector("li > a[href*='geolocation']");
	private static final By	selectorInfiniteScrollLink			= By.cssSelector("li > a[href*='infinite_scroll']");
	private static final By	selectorJQueryUIMenuLink			= By.cssSelector("li > a[href*='jqueryui/menu']");
	private static final By	selectorJavaScriptErrorLink			= By.cssSelector("li > a[href*='javascript_error']");
	private static final By	selectorLargeAndDeepDOMLink			= By.cssSelector("li > a[href*='large']");
	private static final By	selectorNestedFramesLink			= By.cssSelector("li > a[href*='nested_frames']");
	private static final By	selectorNotificationMessagesLink	= By.cssSelector("li > a[href*='notification_message']");
	private static final By	selectorSecureFileDownloadLink		= By.cssSelector("li > a[href*='download_secure']");
	private static final By	selectorShiftingContentLink			= By.cssSelector("li > a[href*='shifting_content']");
	private static final By	selectorSlowResourcesLink			= By.cssSelector("li > a[href*='slow']");
	private static final By	selectorTyposLink					= By.cssSelector("li > a[href*='typos']");
	private static final By	selectorWYSIWYGEditorLink			= By.cssSelector("li > a[href*='tinymce']");
	
	@Override
	public boolean isLoaded() {
		BFLogger.logDebug("The internet page is loaded: " + getDriver().getCurrentUrl());
		return getDriver().getCurrentUrl()
						.equals(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'The internet' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	public CheckboxesPage clickCheckboxesLink() {
		Button elementLink = new Button(selectorCheckboxesLink);
		elementLink.click();
		return new CheckboxesPage();
	}
	
	public ABtestPage clickABtestingLink() {
		Button elementLink = new Button(selectorElementClick);
		elementLink.click();
		return new ABtestPage();
	}
	
	public ChallengingDomPage clickChallengingDomLink() {
		Button elementLink = new Button(selectorChallengingDomClick);
		elementLink.click();
		return new ChallengingDomPage();
	}
	
	public BrokenImagePage clickBrokenImageLink() {
		Button elementLink = new Button(selectorBrokenImageClickLink);
		elementLink.click();
		return new BrokenImagePage();
	}
	
	public DropdownPage clickDropdownLink() {
		Button elementLink = new Button(selectorDropdownClickLink);
		elementLink.click();
		return new DropdownPage();
	}
	
	public KeyPressesPage clickKeyPressesLink() {
		Button elementLink = new Button(selectorKeyPressesLink);
		elementLink.click();
		return new KeyPressesPage();
	}
	
	public SortableDataTablesPage clickSortableDataTablesLink() {
		Button elementLink = new Button(selectorSortableDataTablesLink);
		elementLink.click();
		return new SortableDataTablesPage();
	}
	
	public JavaScriptAlertsPage<V, Alert> clickJavaScriptAlertLink() {
		Button elementLink = new Button(selectorJavaScriptAlertLink);
		elementLink.click();
		return new JavaScriptAlertsPage<V, Alert>();
	}
	
	public DynamicContentPage clickDynamicContentPage() {
		Button elementLink = new Button(selectorDynamicContent);
		elementLink.click();
		return new DynamicContentPage();
	}
	
	public MultipleWindowsPage clickmultipleWindowsPageLink() {
		Button elementLink = new Button(selectorMultipleWindowsLink);
		elementLink.click();
		return new MultipleWindowsPage();
	}
	
	public RedirectLinkPage clickRedirectLinkPage() {
		Button elementLink = new Button(selectorRedirectLink);
		elementLink.click();
		return new RedirectLinkPage();
	}
	
	public TheBasicAuthPage clickBasicAuthLink() {
		getDriver().waitForPageLoaded();
		WebElement link = getDriver().findElementDynamic(selectorBasicAuthLink);
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", link);
		return new TheBasicAuthPage();
	}
	
	public HoversPage clickHoversLink() {
		Button elementLink = new Button(selectorHoversLink);
		elementLink.click();
		return new HoversPage();
	}
	
	public StatusCodesHomePage clickStatusCodesLink() {
		getDriver().findElementDynamic(selectorStatusCodesLink)
						.click();
		return new StatusCodesHomePage();
	}
	
	public HorizontalSliderPage clickHorizontalSliderLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorHorizontalSliderLink);
		elementLink.click();
		return new HorizontalSliderPage();
	}
	
	public FormAuthenticationPage clickFormAuthenticationLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorFormAuthenticationLink);
		elementLink.click();
		return new FormAuthenticationPage();
	}
	
	public FileDownloadPage clickFileDownloadLink() {
		Button elementLink = new Button(selectorFileDownloadLink);
		elementLink.click();
		return new FileDownloadPage();
	}
	
	public ForgotPasswordPage clickForgotPasswordLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorForgotPasswordLink);
		elementLink.click();
		return new ForgotPasswordPage();
	}
	
	public ExitIntentPage clickExitIntentLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorExitIntentLink);
		elementLink.click();
		return new ExitIntentPage();
	}
	
	public DynamicLoadingPage clickDynamicLoadingLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorDynamicLoadingLink);
		elementLink.click();
		return new DynamicLoadingPage();
	}
	
	public DisappearingElementsPage clickDisappearingElementsLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorDisappearingElementsLink);
		elementLink.click();
		return new DisappearingElementsPage();
	}
	
	public DragAndDropPage clickDragAndDropLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorDragAndDropLink);
		elementLink.click();
		return new DragAndDropPage();
	}
	
	public ContextMenuPage clickContextMenuLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorContextMenuLink);
		elementLink.click();
		return new ContextMenuPage();
	}
	
	public DynamicControlsPage clickDynamicControlsLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorDynamicControlsLink);
		elementLink.click();
		return new DynamicControlsPage();
	}
	
	public FileUploadPage clickFileUploadLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorFileUploadLink);
		elementLink.click();
		return new FileUploadPage();
	}
	
	public FloatingMenuPage clickFloatingMenuLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorFloatingMenuLink);
		elementLink.click();
		return new FloatingMenuPage();
	}
	
	public FramesPage clickFramesLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorFramesLink);
		elementLink.click();
		return new FramesPage();
	}
	
	public GeolocationPage clickGeolocationLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorGeolocationLink);
		elementLink.click();
		return new GeolocationPage();
	}
	
	public InfiniteScrollPage clickInfiniteScrollLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorInfiniteScrollLink);
		elementLink.click();
		return new InfiniteScrollPage();
	}
	
	public JQueryUIMenuPage clickJQueryUIMenuLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorJQueryUIMenuLink);
		elementLink.click();
		return new JQueryUIMenuPage();
	}
	
	public JavaScriptErrorPage clickJavaScriptErrorLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorJavaScriptErrorLink);
		elementLink.click();
		return new JavaScriptErrorPage();
	}
	
	public LargeAndDeepDOMPage clickLargeAndDeepDOMLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorLargeAndDeepDOMLink);
		elementLink.click();
		return new LargeAndDeepDOMPage();
	}
	
	public NestedFramesPage clickNestedFramesLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorNestedFramesLink);
		elementLink.click();
		return new NestedFramesPage();
	}
	
	public NotificationMessagesPage clickNotificationMessagesLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorNotificationMessagesLink);
		elementLink.click();
		return new NotificationMessagesPage();
	}
	
	public SecureFileDownloadPage clickSecureFileDownloadLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorSecureFileDownloadLink);
		elementLink.click();
		return new SecureFileDownloadPage();
	}
	
	public ShiftingContentPage clickShiftingContentLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorShiftingContentLink);
		elementLink.click();
		return new ShiftingContentPage();
	}
	
	public SlowResourcesPage clickSlowResourcesLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorSlowResourcesLink);
		elementLink.click();
		return new SlowResourcesPage();
	}
	
	public TyposPage clickTyposLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorTyposLink);
		elementLink.click();
		return new TyposPage();
	}
	
	public WYSIWYGEditorPage clickWYSIWYGEditorLink() {
		WebElement elementLink = getDriver().findElementDynamic(selectorWYSIWYGEditorLink);
		elementLink.click();
		return new WYSIWYGEditorPage();
	}
	
}