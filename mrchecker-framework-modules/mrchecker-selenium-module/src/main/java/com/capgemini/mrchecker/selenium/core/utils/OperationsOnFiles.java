package com.capgemini.mrchecker.selenium.core.utils;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class OperationsOnFiles {
	
	private OperationsOnFiles() {
	}
	
	public static void moveWithPruneEmptydirectories(String source, String target) {
		Path sourcePath;
		Path targetPath;
		
		try {
			sourcePath = FileSystems.getDefault()
							.getPath(source);
			targetPath = FileSystems.getDefault()
							.getPath(target);
			
			createDirectoryIfNotExists(targetPath);
			Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
			removeFileAndParentsIfEmpty(sourcePath);
		} catch (IOException | NullPointerException e) {
			BFLogger.logError("Unable to move file from: [" + source + "] to: [" + target + "]");
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
	
	public static void removeFileAndParentsIfEmpty(Path path)
					throws IOException {
		if (path == null)
			return;
		if (Files.isRegularFile(path)) {
			Files.deleteIfExists(path);
			BFLogger.logInfo("Deleted file - " + path.toAbsolutePath());
		} else if (Files.isDirectory(path)) {
			try {
				Files.delete(path);
				BFLogger.logInfo("Deleted directory - " + path.toAbsolutePath());
			} catch (FileSystemException e) {
				return;
			}
		}
		removeFileAndParentsIfEmpty(path.getParent());
	}
	
}