package com.capgemini.ntc.selenium.core.utils;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import com.capgemini.ntc.test.core.logger.BFLogger;

public class OperationsOnFiles {
	
	private OperationsOnFiles() {
	}
	
	public static void moveWithPruneEmptydirectories(String source, String target) {
		Path sourcePath = FileSystems.getDefault()
						.getPath(source);
		Path targetPath = FileSystems.getDefault()
						.getPath(target);
		try {
			createDirectoryIfNotExists(targetPath);
			Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
			removeFileAndParentsIfEmpty(sourcePath);
		} catch (IOException e) {
			BFLogger.logError("Unable to move file from: [" + sourcePath + "] to: [" + targetPath + "]. Original message - " + e.getMessage());
		}
	}
	
	private static void createDirectoryIfNotExists(Path directoryPath) {
		Path targetDirPath = directoryPath.getParent();
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
				Files.delete(path);
			} catch (DirectoryNotEmptyException e) {
				return;
			}
		}
		removeFileAndParentsIfEmpty(path.getParent());
	}
	
}