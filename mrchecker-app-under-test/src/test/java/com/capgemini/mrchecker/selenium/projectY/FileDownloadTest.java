package com.capgemini.mrchecker.selenium.projectY;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.pages.projectY.FileDownloadPage;
import com.capgemini.mrchecker.selenium.pages.projectY.TheInternetPage;
import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class FileDownloadTest extends BaseTest {
	
	private static TheInternetPage	theInternetPage;
	private static FileDownloadPage	fileDownloadPage;
	
	@Override
	public void setUp() {
		BFLogger.logDebug("Step 1 - Open the Url http://the-internet.herokuapp.com/ page");
		theInternetPage = new TheInternetPage();
		
		BFLogger.logDebug("Step 2 - Verify if Url http://the-internet.herokuapp.com/ is open");
		assertTrue("The Internet Page was not open", theInternetPage.isLoaded());
		
		BFLogger.logDebug("Step 3 - Open the File Download link");
		fileDownloadPage = theInternetPage.clickFileDownloadLink();
		
		BFLogger.logDebug("Step 4 - Verify if http://the-internet.herokuapp.com/download is open");
		assertTrue("The File Download page was not open", fileDownloadPage.isLoaded());
	}
	
	@Override
	public void tearDown() {
		BFLogger.logDebug("Step 9 - navigate back to The-Internet page");
		BasePage.navigateBack();
	}
	
	@Test
	public void fileShouldBeDownloaded() {
		BFLogger.logDebug("Step 5 - Download the some-file.txt");
		File downloadedFile = fileDownloadPage.downloadTheSomeFileTxt();
		
		BFLogger.logDebug("Step 6 - Check if downloaded file exist");
		assertTrue("Downloaded file does not exist", downloadedFile.exists());
		
		BFLogger.logDebug("Step 7 - Remove the downloaded file");
		downloadedFile.delete();
		
		BFLogger.logDebug("Step 8 - Check if downloaded file has been removed");
		assertFalse("Downloaded file still exist", downloadedFile.exists());
	}
	
}