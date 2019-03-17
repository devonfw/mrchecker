package com.capgemini.mrchecker.selenium.projectY;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsLocal;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsNONParallel;
import com.capgemini.mrchecker.selenium.pages.projectY.FileDownloadPage;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@Category({ TestsLocal.class, TestsNONParallel.class })
public class FileDownloadTest extends TheInternetBaseTest {

	private static FileDownloadPage fileDownloadPage;

	@BeforeClass
	public static void setUpBeforeClass() {
		fileDownloadPage = shouldTheInternetPageBeOpened().clickFileDownloadLink();

		logStep("Verify if File Download page is opened");
		assertTrue("Unable to open File Download page", fileDownloadPage.isLoaded());
	}

	@Test
	public void shouldfileBeDownloaded() {

		logStep("Download the some-file.txt");
		File downloadedFile = fileDownloadPage.downloadTextFile();

		logStep("Verify if downloaded file exists");
		assertTrue("Downloaded file does not exist", downloadedFile.exists());

		logStep("Remove downloaded file");
		downloadedFile.delete();

		logStep("Verify if downloaded file has been removed");
		assertFalse("Downloaded file still exists", downloadedFile.exists());
	}

}