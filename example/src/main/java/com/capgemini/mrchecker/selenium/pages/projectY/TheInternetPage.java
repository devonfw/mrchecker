package com.capgemini.mrchecker.selenium.pages.projectY;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class TheInternetPage extends BasePage {

    private static final By abTestLinkSelector = By.cssSelector("li > a[href*='abtest']");
    private static final By basicAuthLinkSelector = By.cssSelector("li > a[href*='basic_auth']");
    private static final By brokenImageLinkSelector = By.cssSelector("li > a[href*='broken_images']");
    private static final By challengingDomLinkSelector = By.cssSelector("li > a[href*='challenging_dom']");
    private static final By checkboxesLinkSelector = By.cssSelector("li > a[href*='checkboxes']");
    private static final By contextMenuLinkSelector = By.cssSelector("li > a[href*='context_menu']");
    private static final By disappearingElementsLinkSelector = By.cssSelector("li > a[href*='disappearing_elements']");
    private static final By dragAndDropLinkSelector = By.cssSelector("li > a[href*='drag_and_drop']");
    private static final By dropdownLinkSelector = By.cssSelector("li > a[href*='dropdown']");
    private static final By dynamicContentLinkSelector = By.cssSelector("li > a[href*='dynamic_content']");
    private static final By dynamicControlsLinkSelector = By.cssSelector("li > a[href*='dynamic_controls']");
    private static final By dynamicLoadingLinkSelector = By.cssSelector("li > a[href*='dynamic_loading']");
    private static final By exitIntentLinkSelector = By.cssSelector("li > a[href*='exit_intent']");
    private static final By fileDownloadLinkSelector = By.cssSelector("li > a[href$='download']");
    private static final By fileUploadLinkSelector = By.cssSelector("li > a[href*='upload']");
    private static final By floatingMenuLinkSelector = By.cssSelector("li > a[href*='floating_menu']");
    private static final By forgotPasswordLinkSelector = By.cssSelector("li > a[href*='forgot_password']");
    private static final By formAuthenticationLinkSelector = By.cssSelector("li > a[href*='login']");
    private static final By framesLinkSelector = By.cssSelector("li > a[href*='frames']");
    private static final By geolocationLinkSelector = By.cssSelector("li > a[href*='geolocation']");
    private static final By horizontalSliderLinkSelector = By.cssSelector("li > a[href*='horizontal_slider']");
    private static final By hoversLinkSelector = By.cssSelector("li > a[href*='hovers']");
    private static final By infiniteScrollLinkSelector = By.cssSelector("li > a[href*='infinite_scroll']");
    private static final By javaScriptAlertLinkSelector = By.cssSelector("li > a[href*='javascript_alerts']");
    private static final By javaScriptErrorLinkSelector = By.cssSelector("li > a[href*='javascript_error']");
    private static final By jQueryUIMenuLinkSelector = By.cssSelector("li > a[href*='jqueryui/menu']");
    private static final By keyPressesLinkSelector = By.cssSelector("li > a[href*='key_presses']");
    private static final By largeAndDeepDOMLinkSelector = By.cssSelector("li > a[href*='large']");
    private static final By multipleWindowsLinkSelector = By.cssSelector("li > a[href*='windows']");
    private static final By nestedFramesLinkSelector = By.cssSelector("li > a[href*='nested_frames']");
    private static final By notificationMessagesLinkSelector = By.cssSelector("li > a[href*='notification_message']");
    private static final By redirectLinkSelector = By.cssSelector("li > a[href*='redirector']");
    private static final By secureFileDownloadLinkSelector = By.cssSelector("li > a[href*='download_secure']");
    private static final By shiftingContentLinkSelector = By.cssSelector("li > a[href*='shifting_content']");
    private static final By slowResourcesLinkSelector = By.cssSelector("li > a[href*='slow']");
    private static final By sortableDataTablesLinkSelector = By.cssSelector("li > a[href*='tables']");
    private static final By statusCodesLinkSelector = By.cssSelector("li > a[href*='status_codes']");
    private static final By typosLinkSelector = By.cssSelector("li > a[href*='typos']");
    private static final By wYSIWYGEditorLinkSelector = By.cssSelector("li > a[href*='tinymce']");

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
        ABtestPage abTestPage = new ABtestPage();
        abTestPage.initialize();
        return abTestPage;
    }

    public BasicAuthPage clickBasicAuthLink() {
        getDriver().waitForPageLoaded();
        WebElement link = getDriver().findElementDynamic(basicAuthLinkSelector);
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", link);
        BasicAuthPage basicAuthPage = new BasicAuthPage();
        basicAuthPage.initialize();
        return basicAuthPage;
    }

    public BrokenImagePage clickBrokenImageLink() {
        new Button(brokenImageLinkSelector).click();
        BrokenImagePage brokenImagePage = new BrokenImagePage();
        brokenImagePage.initialize();
        return brokenImagePage;
    }

    public ChallengingDomPage clickChallengingDomLink() {
        new Button(challengingDomLinkSelector).click();
        ChallengingDomPage challengingDomPage = new ChallengingDomPage();
        challengingDomPage.initialize();
        return challengingDomPage;
    }

    public CheckboxesPage clickCheckboxesLink() {
        new Button(checkboxesLinkSelector).click();
        CheckboxesPage checkboxesPage = new CheckboxesPage();
        checkboxesPage.initialize();
        return checkboxesPage;
    }

    public ContextMenuPage clickContextMenuLink() {
        new Button(contextMenuLinkSelector).click();
        ContextMenuPage contextMenuPage = new ContextMenuPage();
        contextMenuPage.initialize();
        return contextMenuPage;
    }

    public DisappearingElementsPage clickDisappearingElementsLink() {
        new Button(disappearingElementsLinkSelector).click();
        DisappearingElementsPage disappearingElementsPage = new DisappearingElementsPage();
        disappearingElementsPage.initialize();
        return disappearingElementsPage;
    }

    public DragAndDropPage clickDragAndDropLink() {
        new Button(dragAndDropLinkSelector).click();
        DragAndDropPage dragAndDropPage = new DragAndDropPage();
        dragAndDropPage.initialize();
        return dragAndDropPage;
    }

    public DropdownPage clickDropdownLink() {
        new Button(dropdownLinkSelector).click();
        DropdownPage dropdownPage = new DropdownPage();
        dropdownPage.initialize();
        return dropdownPage;
    }

    public DynamicContentPage clickDynamicContentLink() {
        new Button(dynamicContentLinkSelector).click();
        DynamicContentPage dynamicContentPage = new DynamicContentPage();
        dynamicContentPage.initialize();
        return dynamicContentPage;
    }

    public DynamicControlsPage clickDynamicControlsLink() {
        new Button(dynamicControlsLinkSelector).click();
        DynamicControlsPage dynamicControlsPage = new DynamicControlsPage();
        dynamicControlsPage.initialize();
        return dynamicControlsPage;
    }

    public DynamicLoadingPage clickDynamicLoadingLink() {
        new Button(dynamicLoadingLinkSelector).click();
        DynamicLoadingPage dynamicLoadingPage = new DynamicLoadingPage();
		dynamicLoadingPage.initialize();
		return dynamicLoadingPage;
    }

    public ExitIntentPage clickExitIntentLink() {
        new Button(exitIntentLinkSelector).click();
        ExitIntentPage exitIntentPage = new ExitIntentPage();
		exitIntentPage.initialize();
		return exitIntentPage;
    }

    public FileDownloadPage clickFileDownloadLink() {
        new Button(fileDownloadLinkSelector).click();
        FileDownloadPage fileDownloadPage = new FileDownloadPage();
		fileDownloadPage.initialize();
		return fileDownloadPage;
    }

    public FileUploadPage clickFileUploadLink() {
        new Button(fileUploadLinkSelector).click();
        FileUploadPage fileUploadPage = new FileUploadPage();
		fileUploadPage.initialize();
		return fileUploadPage;
    }

    public FloatingMenuPage clickFloatingMenuLink() {
        new Button(floatingMenuLinkSelector).click();
        FloatingMenuPage floatingMenuPage = new FloatingMenuPage();
		floatingMenuPage.initialize();
		return floatingMenuPage;
    }

    public ForgotPasswordPage clickForgotPasswordLink() {
        new Button(forgotPasswordLinkSelector).click();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
		forgotPasswordPage.initialize();
		return forgotPasswordPage;
    }

    public FormAuthenticationPage clickFormAuthenticationLink() {
        new Button(formAuthenticationLinkSelector).click();
        FormAuthenticationPage formAuthenticationPage = new FormAuthenticationPage();
		formAuthenticationPage.initialize();
		return formAuthenticationPage;
    }

    public FramesPage clickFramesLink() {
        new Button(framesLinkSelector).click();
        FramesPage framesPage = new FramesPage();
		framesPage.initialize();
		return framesPage;
    }

    public GeolocationPage clickGeolocationLink() {
        new Button(geolocationLinkSelector).click();
        GeolocationPage geolocationPage = new GeolocationPage();
		geolocationPage.initialize();
		return geolocationPage;
    }

    public HorizontalSliderPage clickHorizontalSliderLink() {
        new Button(horizontalSliderLinkSelector).click();
        HorizontalSliderPage horizontalSliderPage = new HorizontalSliderPage();
		horizontalSliderPage.initialize();
		return horizontalSliderPage;
    }

    public HoversPage clickHoversLink() {
        new Button(hoversLinkSelector).click();
        HoversPage hoversPage = new HoversPage();
		hoversPage.initialize();
		return hoversPage;
    }

    public InfiniteScrollPage clickInfiniteScrollLink() {
        new Button(infiniteScrollLinkSelector).click();
        InfiniteScrollPage infiniteScrollPage = new InfiniteScrollPage();
		infiniteScrollPage.initialize();
		return infiniteScrollPage;
    }

    public JavaScriptAlertsPage clickJavaScriptAlertLink() {
        new Button(javaScriptAlertLinkSelector).click();
        JavaScriptAlertsPage javaScriptAlertsPage = new JavaScriptAlertsPage();
		javaScriptAlertsPage.initialize();
		return javaScriptAlertsPage;
    }

	public JavaScriptErrorPage clickJavaScriptErrorLink() {
		new Button(javaScriptErrorLinkSelector).click();
		JavaScriptErrorPage javaScriptErrorPage = new JavaScriptErrorPage();
		javaScriptErrorPage.initialize();
		return javaScriptErrorPage;
	}

	public JQueryUIMenuPage clickJQueryUIMenuLink() {
		new Button(jQueryUIMenuLinkSelector).click();
		JQueryUIMenuPage jQueryUIMenuPage = new JQueryUIMenuPage();
		jQueryUIMenuPage.initialize();
		return jQueryUIMenuPage;
	}

	public KeyPressesPage clickKeyPressesLink() {
		new Button(keyPressesLinkSelector).click();
		KeyPressesPage keyPressesPage = new KeyPressesPage();
		keyPressesPage.initialize();
		return keyPressesPage;
	}

	public LargeAndDeepDOMPage clickLargeAndDeepDOMLink() {
		new Button(largeAndDeepDOMLinkSelector).click();
		LargeAndDeepDOMPage largeAndDeepDOMPage = new LargeAndDeepDOMPage();
		largeAndDeepDOMPage.initialize();
		return largeAndDeepDOMPage;
	}

	public MultipleWindowsPage clickmultipleWindowsLink() {
		new Button(multipleWindowsLinkSelector).click();
		MultipleWindowsPage multipleWindowsPage = new MultipleWindowsPage();
		multipleWindowsPage.initialize();
		return multipleWindowsPage;
	}

	public NestedFramesPage clickNestedFramesLink() {
		new Button(nestedFramesLinkSelector).click();
		NestedFramesPage nestedFramesPage = new NestedFramesPage();
		nestedFramesPage.initialize();
		return nestedFramesPage;
	}

	public NotificationMessagesPage clickNotificationMessagesLink() {
		new Button(notificationMessagesLinkSelector).click();
		NotificationMessagesPage notificationMessagesPage = new NotificationMessagesPage();
		notificationMessagesPage.initialize();
		return notificationMessagesPage;
	}

	public RedirectLinkPage clickRedirectLink() {
		new Button(redirectLinkSelector).click();
		RedirectLinkPage redirectLinkPage = new RedirectLinkPage();
		redirectLinkPage.initialize();
		return redirectLinkPage;
	}

	public SecureFileDownloadPage clickSecureFileDownloadLink() {
		new Button(secureFileDownloadLinkSelector).click();
		SecureFileDownloadPage secureFileDownloadPage = new SecureFileDownloadPage();
		secureFileDownloadPage.initialize();
		return secureFileDownloadPage;
	}

	public ShiftingContentPage clickShiftingContentLink() {
		new Button(shiftingContentLinkSelector).click();
		ShiftingContentPage shiftingContentPage = new ShiftingContentPage();
		shiftingContentPage.initialize();
		return shiftingContentPage;
	}

	public SlowResourcesPage clickSlowResourcesLink() {
		new Button(slowResourcesLinkSelector).click();
		SlowResourcesPage slowResourcesPage = new SlowResourcesPage();
		slowResourcesPage.initialize();
		return slowResourcesPage;
	}

	public SortableDataTablesPage clickSortableDataTablesLink() {
		new Button(sortableDataTablesLinkSelector).click();
		SortableDataTablesPage sortableDataTablesPage = new SortableDataTablesPage();
		sortableDataTablesPage.initialize();
		return sortableDataTablesPage;
	}

	public StatusCodesHomePage clickStatusCodesLink() {
		new Button(statusCodesLinkSelector).click();
		StatusCodesHomePage statusCodesHomePage = new StatusCodesHomePage();
		statusCodesHomePage.initialize();
		return statusCodesHomePage;
	}

	public TyposPage clickTyposLink() {
		new Button(typosLinkSelector).click();
		TyposPage typosPage = new TyposPage();
		typosPage.initialize();
		return typosPage;
	}

	public WYSIWYGEditorPage clickWYSIWYGEditorLink() {
		new Button(wYSIWYGEditorLinkSelector).click();
		WYSIWYGEditorPage wYSIWYGEditorPage = new WYSIWYGEditorPage();
		wYSIWYGEditorPage.initialize();
		return wYSIWYGEditorPage;
	}
}