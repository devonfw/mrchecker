package com.capgemini.mrchecker.selenium.pages.projectY;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class FileDownloadPage extends BasePage {
	
	private static final By selectorSomeFileTxt = By.cssSelector("a[href*=some-file]");
	
	private final String DOWNLOAD_DIR = System.getProperty("java.io.tmpdir");
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.DOWNLOAD.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'File Downloader' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.DOWNLOAD.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	/**
	 * Verifies if chosen file is already downloaded and downloads it if not. Throws RuntimeException otherwise.
	 * 
	 * @return Downloaded file
	 */
	public File downloadTextFile() {
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