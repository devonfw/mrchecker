package com.capgemini.mrchecker.selenium.projectY;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsLocal;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsNONParallel;
import com.capgemini.mrchecker.selenium.pages.projectY.FileDownloadPage;
import com.capgemini.mrchecker.selenium.pages.projectY.FileUploadPage;

import com.capgemini.mrchecker.test.core.logger.BFLogger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static org.junit.Assert.*;


@Category({ TestsLocal.class, TestsNONParallel.class })
public class FileUploadTest extends TheInternetBaseTest {

	private static FileUploadPage fileUploadPage;
	private static File           downloadedFile;

	@BeforeClass
	public static void setUpBeforeClass() {
		FileDownloadPage fileDownloadPage = shouldTheInternetPageBeOpened().clickFileDownloadLink();
		logStep("Verify if File Download page is opened");
		if(!fileDownloadPage.isLoaded()) {
			logInitializationErrorAneThrowException("Unable to open File Download page");
		}

		downloadedFile = fileDownloadPage.downloadTextFile();
		logStep("Verify if downloaded file exists");
		if(!downloadedFile.exists()) {
			logInitializationErrorAneThrowException("Downloaded file does not exist");
		}

		fileUploadPage = shouldTheInternetPageBeOpened().clickFileUploadLink();
		logStep("Verify if File Upload page is opened");
		assertTrue("Unable to open File Upload page", fileUploadPage.isLoaded());
	}

	private static void logInitializationErrorAneThrowException(String message) {
		BFLogger.logError(message);
		throw new IllegalStateException(message);
	}

	@AfterClass
	public static void tearDownAfterClass() {
		if (downloadedFile.exists()) {
			logStep("Deleting the temporary file");
			downloadedFile.delete();
		}

		logStep("Verify if the downloaded file has been removed");
		if (downloadedFile.exists()) {
			BFLogger.logError("Could not delete the temporary file");
		}
	}

	@Test
	public void shouldFileBeUploadedBySelection() {
		logStep("Click Select File Button to add chosen file to the upload");
		fileUploadPage.selectFileToUploadByClickingSelectFileButton(downloadedFile.getAbsolutePath());

		logStep("Click Upload File Button");
		fileUploadPage.clickUploadButton();

		logStep("Verify if selected file has been uploaded");
		assertEquals("Selected file has not been uploaded", downloadedFile.getName(), fileUploadPage.getUploadedFileNameText());
	}
}