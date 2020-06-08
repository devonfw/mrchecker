package com.capgemini.mrchecker.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

public class TheInternetPage extends BasePage {
	
	private static final By	abTestLinkSelector					= By.cssSelector("li > a[href*='abtest']");
	private static final By	basicAuthLinkSelector				= By.cssSelector("li > a[href*='basic_auth']");
	private static final By	brokenImageLinkSelector				= By.cssSelector("li > a[href*='broken_images']");
	private static final By	challengingDomLinkSelector			= By.cssSelector("li > a[href*='challenging_dom']");
	private static final By	checkboxesLinkSelector				= By.cssSelector("li > a[href*='checkboxes']");
	private static final By	contextMenuLinkSelector				= By.cssSelector("li > a[href*='context_menu']");
	private static final By	disappearingElementsLinkSelector	= By.cssSelector("li > a[href*='disappearing_elements']");
	private static final By	dragAndDropLinkSelector				= By.cssSelector("li > a[href*='drag_and_drop']");
	private static final By	dropdownLinkSelector				= By.cssSelector("li > a[href*='dropdown']");
	private static final By	dynamicContentLinkSelector			= By.cssSelector("li > a[href*='dynamic_content']");
	private static final By	dynamicControlsLinkSelector			= By.cssSelector("li > a[href*='dynamic_controls']");
	private static final By	dynamicLoadingLinkSelector			= By.cssSelector("li > a[href*='dynamic_loading']");
	private static final By	exitIntentLinkSelector				= By.cssSelector("li > a[href*='exit_intent']");
	private static final By	fileDownloadLinkSelector			= By.cssSelector("li > a[href$='download']");
	private static final By	fileUploadLinkSelector				= By.cssSelector("li > a[href*='upload']");
	private static final By	floatingMenuLinkSelector			= By.cssSelector("li > a[href*='floating_menu']");
	private static final By	forgotPasswordLinkSelector			= By.cssSelector("li > a[href*='forgot_password']");
	private static final By	formAuthenticationLinkSelector		= By.cssSelector("li > a[href*='login']");
	private static final By	framesLinkSelector					= By.cssSelector("li > a[href*='frames']");
	private static final By	geolocationLinkSelector				= By.cssSelector("li > a[href*='geolocation']");
	private static final By	horizontalSliderLinkSelector		= By.cssSelector("li > a[href*='horizontal_slider']");
	private static final By	hoversLinkSelector					= By.cssSelector("li > a[href*='hovers']");
	private static final By	infiniteScrollLinkSelector			= By.cssSelector("li > a[href*='infinite_scroll']");
	private static final By	javaScriptAlertLinkSelector			= By.cssSelector("li > a[href*='javascript_alerts']");
	private static final By	javaScriptErrorLinkSelector			= By.cssSelector("li > a[href*='javascript_error']");
	private static final By	jQueryUIMenuLinkSelector			= By.cssSelector("li > a[href*='jqueryui/menu']");
	private static final By	keyPressesLinkSelector				= By.cssSelector("li > a[href*='key_presses']");
	private static final By	largeAndDeepDOMLinkSelector			= By.cssSelector("li > a[href*='large']");
	private static final By	multipleWindowsLinkSelector			= By.cssSelector("li > a[href*='windows']");
	private static final By	nestedFramesLinkSelector			= By.cssSelector("li > a[href*='nested_frames']");
	private static final By	notificationMessagesLinkSelector	= By.cssSelector("li > a[href*='notification_message']");
	private static final By	redirectLinkSelector				= By.cssSelector("li > a[href*='redirector']");
	private static final By	secureFileDownloadLinkSelector		= By.cssSelector("li > a[href*='download_secure']");
	private static final By	shiftingContentLinkSelector			= By.cssSelector("li > a[href*='shifting_content']");
	private static final By	slowResourcesLinkSelector			= By.cssSelector("li > a[href*='slow']");
	private static final By	sortableDataTablesLinkSelector		= By.cssSelector("li > a[href*='tables']");
	private static final By	statusCodesLinkSelector				= By.cssSelector("li > a[href*='status_codes']");
	private static final By	typosLinkSelector					= By.cssSelector("li > a[href*='typos']");
	private static final By	wYSIWYGEditorLinkSelector			= By.cssSelector("li > a[href*='tinymce']");
	
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
	
	public ABtestPage clickABtestingLink() {
		new Button(abTestLinkSelector).click();
		return PageFactory.getPageInstance(ABtestPage.class);
	}
	
	public BasicAuthPage clickBasicAuthLink() {
		getDriver().waitForPageLoaded();
		WebElement link = getDriver().findElementDynamic(basicAuthLinkSelector);
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", link);
		return PageFactory.getPageInstance(BasicAuthPage.class);
	}
	
	public BrokenImagePage clickBrokenImageLink() {
		new Button(brokenImageLinkSelector).click();
		return PageFactory.getPageInstance(BrokenImagePage.class);
	}
	
	public ChallengingDomPage clickChallengingDomLink() {
		new Button(challengingDomLinkSelector).click();
		return PageFactory.getPageInstance(ChallengingDomPage.class);
	}
	
	public CheckboxesPage clickCheckboxesLink() {
		new Button(checkboxesLinkSelector).click();
		return PageFactory.getPageInstance(CheckboxesPage.class);
	}
	
	public ContextMenuPage clickContextMenuLink() {
		new Button(contextMenuLinkSelector).click();
		return PageFactory.getPageInstance(ContextMenuPage.class);
	}
	
	public DisappearingElementsPage clickDisappearingElementsLink() {
		new Button(disappearingElementsLinkSelector).click();
		return PageFactory.getPageInstance(DisappearingElementsPage.class);
	}
	
	public DragAndDropPage clickDragAndDropLink() {
		new Button(dragAndDropLinkSelector).click();
		return PageFactory.getPageInstance(DragAndDropPage.class);
	}
	
	public DropdownPage clickDropdownLink() {
		new Button(dropdownLinkSelector).click();
		return PageFactory.getPageInstance(DropdownPage.class);
	}
	
	public DynamicContentPage clickDynamicContentLink() {
		new Button(dynamicContentLinkSelector).click();
		return PageFactory.getPageInstance(DynamicContentPage.class);
	}
	
	public DynamicControlsPage clickDynamicControlsLink() {
		new Button(dynamicControlsLinkSelector).click();
		return PageFactory.getPageInstance(DynamicControlsPage.class);
	}
	
	public DynamicLoadingPage clickDynamicLoadingLink() {
		new Button(dynamicLoadingLinkSelector).click();
		return PageFactory.getPageInstance(DynamicLoadingPage.class);
	}
	
	public ExitIntentPage clickExitIntentLink() {
		new Button(exitIntentLinkSelector).click();
		return PageFactory.getPageInstance(ExitIntentPage.class);
	}
	
	public FileDownloadPage clickFileDownloadLink() {
		new Button(fileDownloadLinkSelector).click();
		return PageFactory.getPageInstance(FileDownloadPage.class);
	}
	
	public FileUploadPage clickFileUploadLink() {
		new Button(fileUploadLinkSelector).click();
		return PageFactory.getPageInstance(FileUploadPage.class);
	}
	
	public FloatingMenuPage clickFloatingMenuLink() {
		new Button(floatingMenuLinkSelector).click();
		return PageFactory.getPageInstance(FloatingMenuPage.class);
	}
	
	public ForgotPasswordPage clickForgotPasswordLink() {
		new Button(forgotPasswordLinkSelector).click();
		return PageFactory.getPageInstance(ForgotPasswordPage.class);
	}
	
	public FormAuthenticationPage clickFormAuthenticationLink() {
		new Button(formAuthenticationLinkSelector).click();
		return PageFactory.getPageInstance(FormAuthenticationPage.class);
	}
	
	public FramesPage clickFramesLink() {
		new Button(framesLinkSelector).click();
		return PageFactory.getPageInstance(FramesPage.class);
	}
	
	public GeolocationPage clickGeolocationLink() {
		new Button(geolocationLinkSelector).click();
		return PageFactory.getPageInstance(GeolocationPage.class);
	}
	
	public HorizontalSliderPage clickHorizontalSliderLink() {
		new Button(horizontalSliderLinkSelector).click();
		return PageFactory.getPageInstance(HorizontalSliderPage.class);
	}
	
	public HoversPage clickHoversLink() {
		new Button(hoversLinkSelector).click();
		return PageFactory.getPageInstance(HoversPage.class);
	}
	
	public InfiniteScrollPage clickInfiniteScrollLink() {
		new Button(infiniteScrollLinkSelector).click();
		return PageFactory.getPageInstance(InfiniteScrollPage.class);
	}
	
	public JavaScriptAlertsPage clickJavaScriptAlertLink() {
		new Button(javaScriptAlertLinkSelector).click();
		return PageFactory.getPageInstance(JavaScriptAlertsPage.class);
	}
	
	public JavaScriptErrorPage clickJavaScriptErrorLink() {
		new Button(javaScriptErrorLinkSelector).click();
		return PageFactory.getPageInstance(JavaScriptErrorPage.class);
	}
	
	public JQueryUIMenuPage clickJQueryUIMenuLink() {
		new Button(jQueryUIMenuLinkSelector).click();
		return PageFactory.getPageInstance(JQueryUIMenuPage.class);
	}
	
	public KeyPressesPage clickKeyPressesLink() {
		new Button(keyPressesLinkSelector).click();
		return PageFactory.getPageInstance(KeyPressesPage.class);
	}
	
	public LargeAndDeepDOMPage clickLargeAndDeepDOMLink() {
		new Button(largeAndDeepDOMLinkSelector).click();
		return PageFactory.getPageInstance(LargeAndDeepDOMPage.class);
	}
	
	public MultipleWindowsPage clickmultipleWindowsLink() {
		new Button(multipleWindowsLinkSelector).click();
		return PageFactory.getPageInstance(MultipleWindowsPage.class);
	}
	
	public NestedFramesPage clickNestedFramesLink() {
		new Button(nestedFramesLinkSelector).click();
		return PageFactory.getPageInstance(NestedFramesPage.class);
	}
	
	public NotificationMessagesPage clickNotificationMessagesLink() {
		new Button(notificationMessagesLinkSelector).click();
		return PageFactory.getPageInstance(NotificationMessagesPage.class);
	}
	
	public RedirectLinkPage clickRedirectLink() {
		new Button(redirectLinkSelector).click();
		return PageFactory.getPageInstance(RedirectLinkPage.class);
	}
	
	public SecureFileDownloadPage clickSecureFileDownloadLink() {
		new Button(secureFileDownloadLinkSelector).click();
		return PageFactory.getPageInstance(SecureFileDownloadPage.class);
	}
	
	public ShiftingContentPage clickShiftingContentLink() {
		new Button(shiftingContentLinkSelector).click();
		return PageFactory.getPageInstance(ShiftingContentPage.class);
	}
	
	public SlowResourcesPage clickSlowResourcesLink() {
		new Button(slowResourcesLinkSelector).click();
		return PageFactory.getPageInstance(SlowResourcesPage.class);
	}
	
	public SortableDataTablesPage clickSortableDataTablesLink() {
		new Button(sortableDataTablesLinkSelector).click();
		return PageFactory.getPageInstance(SortableDataTablesPage.class);
	}
	
	public StatusCodesHomePage clickStatusCodesLink() {
		new Button(statusCodesLinkSelector).click();
		return PageFactory.getPageInstance(StatusCodesHomePage.class);
	}
	
	public TyposPage clickTyposLink() {
		new Button(typosLinkSelector).click();
		return PageFactory.getPageInstance(TyposPage.class);
	}
	
	public WYSIWYGEditorPage clickWYSIWYGEditorLink() {
		new Button(wYSIWYGEditorLinkSelector).click();
		return PageFactory.getPageInstance(WYSIWYGEditorPage.class);
	}
}