package com.capgemini.framework.logger;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.qameta.allure.model.Link;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.capgemini.framework.playwright.PlaywrightFactory.getPage;
import static com.capgemini.framework.playwright.PlaywrightFactory.isBrowserContextPresent;
import static io.qameta.allure.util.ResultsUtils.TMS_LINK_TYPE;
import static java.nio.file.StandardOpenOption.APPEND;

public final class AllureStepLogger {
	private static final String TEMP_PATH = System.getProperty("java.io.tmpdir");
	private static final String pattern = "https://jiraLink.com/jira/browse/";
	public static final String KEYWORD_CHECK = "[Check] ";

	private AllureStepLogger() {
	}
	
	@Step("[INFO] {info}")
	public static synchronized void info(String info) {
		Logger.logInfo("[INFO] " + info);
	}
	
	@Step(KEYWORD_CHECK +"{check}")
	public static synchronized void check(String check) {
		Logger.logInfo("[INFO] " + check);
	}
	
	public static synchronized void error(String error) {
		var message = "[ERROR] " + error;
		var uuid = UUID.randomUUID()
				.toString();
		try {
			var allureLifeCycle = Allure.getLifecycle();
			allureLifeCycle.startStep(uuid, new StepResult().setStatus(Status.FAILED)
					.setName(message));
			Logger.logError(message);
			allureLifeCycle.stopStep(uuid);
		} catch (NoSuchElementException e) {
			info(message);
		}
	}
	
	public static synchronized void issue(String testId) {
		
		try {
			Allure.addLinks(new Link().setType("issue")
					.setName(testId)
					.setUrl(pattern + testId));
		} catch (NullPointerException e) {
			// Catch when no allure report
			info(pattern + testId);
		}
	}
	
	public static synchronized void tmsLink(String testId) {
		try {
			Allure.link(testId, TMS_LINK_TYPE, pattern + testId);
		} catch (NullPointerException e) {
			// Catch when no allure report
			info(pattern + testId);
		}
	}
	
	@Step("{step}")
	public static synchronized void step(String step) {
		Logger.logInfo(step);
	}
	
	public static synchronized void makeScreenshot() {
		Allure.addAttachment("--Screenshot--", new ByteArrayInputStream(getPage().screenshot()));
	}
	
	public static synchronized void makeScreenShotFull() {
		Allure.addAttachment("--Screenshot full page--", new ByteArrayInputStream(getPage().screenshot(new Page.ScreenshotOptions().setFullPage(true))));
	}
	
	public static synchronized void makeScreenshot(String name, boolean fullPage) {
		//do screenshots only for opened browser
		if (isBrowserContextPresent()) {
			Allure.addAttachment(name, new ByteArrayInputStream(getPage().screenshot(new Page.ScreenshotOptions().setFullPage(fullPage))));
		} else {
			Logger.logError("Screenshot cannot be done - no Browser Context exists");
		}
	}
	
	@Step("{nameWrapper}")
	public static synchronized void addListOfItemsToRaport(List<String> itemListByName, String nameWrapper) {
		for (var item : itemListByName) {
			step(item);
		}
	}
	
	@Step()
	public static synchronized void saveMailCalendarToLog(String attachName, String message) {
		saveTextAttachmentToLog("Mail calendar attachment", message);
	}
	
	/**
	 * Taking a text and attach it to allure-result
	 *
	 * @return String
	 * @author Mariusz K
	 * @author Jacek Z
	 */
	@Attachment(value = "{attachName}", type = "text/plain")
	public static synchronized String saveTextAttachmentToLog(String attachName, String message) {
		return message;
	}
	
	@Attachment(value = "{attachName}", type = "text/plain")
	public static synchronized String saveTextAttachmentToLogNoConsole(String attachName, String message) {
		return message;
	}
	
	@Attachment(value = "{name}", type = "text/csv")
	private static synchronized byte[] attachCSVFile(File file, String name) throws IOException {
		return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
	}
	
	@Attachment(value = "{name}", type = "text/plain")
	private static synchronized byte[] attachTXTFile(File file, String name) throws IOException {
		return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
	}
	
	@Attachment("Zipped [{name}]")
	private static synchronized byte[] attachZippedFile(File fileToAttach, String name) throws IOException {
		var zipFile = new File(TEMP_PATH, "attachment.zip");
		var buffer = new byte[1024];
		var zos = new ZipOutputStream(new FileOutputStream(zipFile));
		var fis = new FileInputStream(fileToAttach);
		zos.putNextEntry(new ZipEntry(fileToAttach.getName()));

		int length;
		while ((length = fis.read(buffer)) > 0) {
			zos.write(buffer, 0, length);
		}
		zos.closeEntry();
		fis.close();
		zos.close();
		return Files.readAllBytes(Paths.get(zipFile.getAbsolutePath()));
	}
	
	@Attachment(value = "{name}", type = "application/pdf")
	private static synchronized byte[] attachPDFFile(File file, String name) throws IOException {
		return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
	}
	
	@Attachment(value = "{name}", type = "image/png")
	private static synchronized byte[] attachPNGFile(File file, String name) throws IOException {
		return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
	}
	
	@Attachment(value = "{name}", type = "application/zip")
	private static synchronized byte[] attachZIPFile(File file, String name) throws IOException {
		return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
	}
	
	public static synchronized void attachFile(File file, String name) throws IOException {
		var fileName = file.getName();
		if (file.length() == 0) {
			Files.write(Paths.get(file.toURI()), " ".getBytes(), APPEND);
		}

		var extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		switch (extension.toLowerCase()) {
			case "pdf": {
				attachPDFFile(file, name);
				break;
			}
			case "xlsx": {
				attachZippedFile(file, name);
				break;
			}
			case "csv": {
				attachCSVFile(file, name);
				break;
			}
			case "png": {
				attachPNGFile(file, name);
				break;
			}
			case "zip": {
				attachZIPFile(file, name);
				break;
			}
			case "txt": {
				attachTXTFile(file, name);
				break;
			}
			default:
				error("Couldn't attach file with extension: " + extension);
		}
	}
}
