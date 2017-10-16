package com.capgemini.ntc.test.core.download;

import java.io.File;
import java.util.*;
import java.util.Map.Entry;

import com.example.selenium.core.exceptions.BFElementNotFoundException;
import com.example.selenium.core.exceptions.BFInitializationException;
import com.example.selenium.core.newDrivers.INewWebDriver;
import com.example.selenium.core.newDrivers.NewChromeDriver;

import org.openqa.selenium.*;

/**
 * This class contains utils related to file download using WebDriver
 * 
 * @author
 */
public class DownloadManager {

	private final String CHROME_DOWNLOADS_URL = "chrome://downloads/";
	private File downloadedFile;
	private Map<Integer, File> downloadedFiles;
	private INewWebDriver driver;
	private final By selectorChromeClearAllDownloads = By.cssSelector("#clear-all");
	private final By selectorChromeDownloadCompleted = By.cssSelector("div.safe a.show");
	private final By selectorChromeDownloadElement = By.cssSelector("div.safe div.title-area > a.name");
	public final static String DOWNLOAD_DIR = System.getProperty("java.io.tmpdir");

	public DownloadManager(INewWebDriver driver) {
		super();
		this.driver = driver;
		downloadedFiles = new HashMap<Integer, File>();
	}

	/**
	 * @param downloadNumber
	 *            - download number to analyze ordered from 1
	 * @return CsvAnalyzer instance for specified file
	 * @throws BFInitializationException
	 *             if requested download does not exist
	 * @author
	 */
	public CsvAnalyzer analyzeDownload(Integer downloadNumber) {
		if (downloadNumber > downloadedFiles.size() || downloadNumber < 1) {
			throw new BFInitializationException("Download '" + downloadNumber + "' does not exist");
		}
		return new CsvAnalyzer(downloadedFiles.get(downloadNumber));
	}

	/**
	 * @return CsvAnalyzer instance for anylyzing recently downloaded file
	 * @author
	 */
	public CsvAnalyzer analyzeRecentlyDownloadedFile() {
		return new CsvAnalyzer(downloadedFile);
	}

	/**
	 * @return Map of downloaded files and number indicating order of download starting from 1
	 * @author
	 */
	public Map<Integer, File> getDownloadedFiles() {
		return downloadedFiles;
	}

	/**
	 * @return Number of downloads
	 * @author
	 */
	public int getDownloadsCount() {
		return downloadedFiles.size();
	}

	/**
	 * @return File - downloaded file
	 * @author
	 */
	public File handleDownload() {
		String fileName = null;
		String rootHandle = driver.getWindowHandle();

		if (driver instanceof NewChromeDriver) {

			((JavascriptExecutor) driver).executeScript("window.open();");

			for (String a : driver.getWindowHandles()) {
				driver.switchTo().window(a);
			}

			driver.get(CHROME_DOWNLOADS_URL);
			try {
				driver.waitForElementVisible(selectorChromeDownloadCompleted);
			} catch (BFElementNotFoundException e) {
				throw new BFElementNotFoundException("Nothing has been downloaded yet");
			}
			WebElement element = driver.findDynamicElement(selectorChromeDownloadElement);
			fileName = element.getText();
			driver.findElement(selectorChromeClearAllDownloads).click();
			((JavascriptExecutor) driver).executeScript("window.close();");
			driver.switchTo().window(rootHandle);
		} else {
			throw new BFInitializationException(
					"Download in " + driver.getClass().getSimpleName() + " is not implemented yet");
		}

		downloadedFile = new File(System.getProperty("java.io.tmpdir") + fileName);
		downloadedFile.deleteOnExit();
		downloadedFiles.put(downloadedFiles.size() + 1, downloadedFile);
		return downloadedFile;
	}

	/**
	 * @return True if recently downloaded file exists, false otherwise
	 * @author
	 */
	public boolean isLastDownloadedFileExist() {
		return downloadedFile.exists();
	}

	/**
	 * Delete specified file if it exists. Use this to claen up after tests with download.
	 * 
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (file != null && file.exists()) {
			file.delete();
		}
	}

	/**
	 * Delete specified files if it exists. Use this to claen up after tests with download.
	 * 
	 * @param file
	 */
	public static void deleteFile(List<File> files) {
		for (File file : files) {
			deleteFile(file);
		}
	}

	/**
	 * Deletes all downloaded files it they exist.
	 * 
	 * @author
	 */
	public void deleteDownloads() {
		for (Entry<Integer, File> fileEntry : downloadedFiles.entrySet()) {
			deleteFile(fileEntry.getValue());
		}
	}
}
