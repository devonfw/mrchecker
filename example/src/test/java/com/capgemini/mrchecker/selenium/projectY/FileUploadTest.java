package com.capgemini.mrchecker.selenium.projectY;

import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsLocal;
import com.capgemini.mrchecker.core.groupTestCases.testSuites.tags.TestsNONParallel;
import com.capgemini.mrchecker.selenium.pages.projectY.FileDownloadPage;
import com.capgemini.mrchecker.selenium.pages.projectY.FileUploadPage;

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
		assertTrue("Unable to open File Download page", fileDownloadPage.isLoaded());

		downloadedFile = fileDownloadPage.downloadTextFile();

		logStep("Verify if downloaded file exists");
		assertTrue("Downloaded file does not exist", downloadedFile.exists());

		fileUploadPage = shouldTheInternetPageBeOpened().clickFileUploadLink();

		logStep("Verify if File Upload page is opened");
		assertTrue("Unable to open File Upload page", fileUploadPage.isLoaded());
	}

	@AfterClass
	public static void tearDownAfterClass() {
		if (downloadedFile.exists()) {
			logStep("Deleting the temporary file");
			downloadedFile.delete();
		}

		logStep("Verify if the downloaded file has been removed");
		assertFalse("The downloaded file still exists", downloadedFile.exists());
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