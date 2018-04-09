package com.capgemini.ntc.selenium.pages.projectY;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.ntc.selenium.pages.environment.PageSubURLsProjectYEnum;

public class FileDownloadPage extends BasePage {
	
	private static final By	selectorSomeFileTxt	= By.cssSelector("a[href*=some-file]");
	private final String	DOWNLOAD_DIR		= System.getProperty("java.io.tmpdir");
	
	@Override
	public boolean isLoaded() {
		return getDriver().getCurrentUrl()
				.equals("http://the-internet.herokuapp.com/download");
	}
	
	@Override
	public void load() {
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.CHALLENGING_DOM.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getDriver().getTitle();
	}
	
	public File downloadTheSomeFileTxt() {
		String nameOfDownloadFile = getNameOfDownloadFile();
		File fileToDownload = new File(DOWNLOAD_DIR + nameOfDownloadFile);
		
		if (fileToDownload.exists()) {
			throw new RuntimeException("File that you want to download already exists. "
					+ "Please remove it manually. Path to this file: " + fileToDownload.getPath());
		}
		
		getDriver().elementButton(selectorSomeFileTxt)
				.click();
		
		waitForFileDownload(2000, fileToDownload);
		return fileToDownload;
	}
	
	private void waitForFileDownload(int totalTimeoutInMillis, File expectedFile) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(getDriver())
				.withTimeout(totalTimeoutInMillis, TimeUnit.MILLISECONDS)
				.pollingEvery(200, TimeUnit.MILLISECONDS);
		
		wait.until((WebDriver wd) -> expectedFile.exists());
	}
	
	private String getNameOfDownloadFile() {
		String urlToDownload = getDriver().findElementDynamic(selectorSomeFileTxt)
				.getAttribute("href");
		String[] urlHierachy = urlToDownload.split("/");
		return urlHierachy[urlHierachy.length - 1];
	}
}