package com.capgemini.ntc.selenium.pages.projectY;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.Button;
import com.capgemini.ntc.selenium.core.newDrivers.elementType.MenuElement;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.ntc.test.core.logger.BFLogger;

public class IFramePage extends BasePage {
	
	private final static String	URL				= GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue()
			+ PageSubURLsProjectYEnum.IFRAME.getValue();;
	private final static By		headerLocator	= By.xpath("//div[@class='example']/h3");
	
	private final static By		editorDivLocator				= By.id("mceu_13-body");
	private final static By		editorMenuLocator				= By.id("mceu_14-body");
	private final static By		editorMenuFileLocator			= By.id("mceu_15-open");
	private final static By		editorMenuEditLocator			= By.id("mceu_16-open");
	private final static By		editorMenuViewLocator			= By.id("mceu_17-open");
	private final static By		editorMenuFormatLocator			= By.id("mceu_18-open");
	private final static By		editorMenuNewDocumentLocator	= By.id("mceu_32");
	private final static By		editorToolbarLocator			= By.id("mceu_20-body");
	private final static By		boldButtonAreaLocator			= By.id("mceu_3");
	private final static By		boldButtonLocator				= By.xpath("//div[@id='mceu_3']/button/i");
	private final static String	EDITOR_ID						= "mce_0_ifr";
	private final static By		editorLocator					= By.id(EDITOR_ID);
	private final static By		editorContentLocator			= By.id("tinymce");
	private final static By		statusbarLocator				= By.id("mceu_28");
	
	private static WebElement	header;
	private static WebElement	editorDiv;
	private static MenuElement	editorMenu;
	private static WebElement	editorMenuFile;
	private static MenuElement	editorMenuEdit;
	private static MenuElement	editorMenuView;
	private static MenuElement	editorMenuFormat;
	private static WebElement	editorToolbar;
	private static WebElement	editor;
	private static WebElement	statusbar;
	private static WebElement	editorMenuNewDocument;
	private static WebElement	boldButtonArea;
	private static Button		boldButton;
	
	@Override
	public void load() {
		getDriver().get(URL);
		BFLogger.logInfo("" + isLoaded());
		
		findElements();
	}
	
	public void findElements() {
		header = getDriver().findElementQuietly(headerLocator);
		editorDiv = getDriver().findElementQuietly(editorDivLocator);
		editorMenu = getDriver().elementMenu(editorMenuLocator);
		editorMenuFile = getDriver().findElementQuietly(editorMenuFileLocator);
		editorMenuEdit = getDriver().elementMenu(editorMenuEditLocator);
		editorMenuView = getDriver().elementMenu(editorMenuViewLocator);
		editorMenuFormat = getDriver().elementMenu(editorMenuFormatLocator);
		editorToolbar = getDriver().findElementQuietly(editorToolbarLocator);
		editorMenuEdit = getDriver().elementMenu(editorMenuEditLocator);
		editor = getDriver().findElementQuietly(editorLocator);
		statusbar = getDriver().findElementQuietly(statusbarLocator);
		boldButtonArea = getDriver().findElementQuietly(boldButtonAreaLocator);
		boldButton = getDriver().elementButton(boldButtonLocator);
	}
	
	public void createNewDocument() {
		editorMenuFile.click();
		editorMenuNewDocument = getDriver().findElementQuietly(editorMenuNewDocumentLocator);
		editorMenuNewDocument.click();
	}
	
	public void clickBoldButton() {
		boldButton.click();
	}
	
	public boolean isBoldButtonPressed() {
		if (boldButtonArea.getAttribute("aria-pressed") != null) {
			return boldButtonArea.getAttribute("aria-pressed")
					.equals("true");
		} else
			return false;
	}
	
	public boolean getHeaderVisibility() {
		return header.isDisplayed();
	}
	
	public boolean getEditorVisibility() {
		return editorDiv.isDisplayed();
	}
	
	public boolean getMenuVisibility() {
		return editorMenu.isDisplayed()
				&& editorMenuFile.isDisplayed()
				&& editorMenuEdit.isDisplayed()
				&& editorMenuView.isDisplayed()
				&& editorMenuFormat.isDisplayed();
	}
	
	public boolean getToolbarVisibility() {
		return editorToolbar.isDisplayed();
	}
	
	public String getStatusbarText() {
		return statusbar.getText();
	}
	
	public void addTextToEditor(String text) {
		getDriver().switchTo()
				.frame(EDITOR_ID);
		
		WebElement editorPane = getDriver().findElementQuietly(editorContentLocator);
		
		editorPane.sendKeys(text);
		editorPane.sendKeys(Keys.END);
		
		getDriver().switchTo()
				.defaultContent();
	}
	
	public void putTextToEditor(String text) {
		clearEditor();
		addTextToEditor(text);
	}
	
	public void clearEditor() {
		editor.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		editor.sendKeys(Keys.DELETE);
	}
	
	public String getTextFromEditor() {
		getDriver().switchTo()
				.frame(EDITOR_ID);
		
		String editorContent = getDriver().findElementQuietly(editorContentLocator)
				.getText();
		
		getDriver().switchTo()
				.defaultContent();
		
		return editorContent;
	}
	
	public void changeWholeTextBold() {
		changeTextAttribute("b");
	}
	
	public void changeWholeTextItalic() {
		changeTextAttribute("i");
	}
	
	public void changeWholeTextBoldItalicUnderline() {
		changeTextAttribute("b");
		changeTextAttribute("i");
		changeTextAttribute("u");
	}
	
	private final void changeTextAttribute(String key) {
		editor.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		editor.sendKeys(Keys.chord(Keys.CONTROL, key));
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	@Override
	public boolean isLoaded() {
		boolean loadCompleted = ((JavascriptExecutor) getDriver()).executeScript("return document.readyState")
				.equals("complete");
		
		return loadCompleted && isUrlAndPageTitleAsCurrentPage(URL);
	}
	
}
