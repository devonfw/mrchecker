package com.capgemini.ntc.selenium.core.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import com.capgemini.ntc.test.core.logger.BFLogger;

public class FilesUtils {
	
	private FilesUtils() {
	}
	
	public static void copyExecutableIntoTargetPath(String browserName, String binaryPath) {
		BFLogger.logInfo("Source binary path: " + binaryPath);
		Path sourceExePath = FileSystems.getDefault()
						.getPath(binaryPath);
		Path targetExePath = FileSystems.getDefault()
						.getPath(browserName);
		try {
			createTargetDirIfNotExists(targetExePath);
			// Files.copy(sourceExePath, targetExePath, StandardCopyOption.REPLACE_EXISTING);
			Files.move(sourceExePath, targetExePath, StandardCopyOption.REPLACE_EXISTING);
			removeFileAndParentsIfEmpty(sourceExePath);
		} catch (IOException e) {
			BFLogger.logError("Unable to copy webdriver file from: [" + sourceExePath + "] to: [" + targetExePath + "].");
		}
	}
	
	private static void createTargetDirIfNotExists(Path targetExePath) {
		Path targetDirPath = targetExePath.getParent();
		if (!Files.exists(targetDirPath)) {
			try {
				Files.createDirectories(targetDirPath);
			} catch (IOException e) {
				BFLogger.logError("Unable to create directories: [" + targetDirPath + "].");
			}
		}
	}
	
	private static void removeFileAndParentsIfEmpty(Path path)
					throws IOException {
		if (path == null)
			return;
		if (Files.isRegularFile(path)) {
			Files.deleteIfExists(path);
		} else if (Files.isDirectory(path)) {
			try {
				File directory = new File(path.toString());
				
				File[] directoryFiles = directory.listFiles();
				if (directoryFiles.length > 0)
					return;
				
				Files.delete(path);
			} catch (DirectoryNotEmptyException e) {
				return;
			}
		}
		removeFileAndParentsIfEmpty(path.getParent());
	}
	
}