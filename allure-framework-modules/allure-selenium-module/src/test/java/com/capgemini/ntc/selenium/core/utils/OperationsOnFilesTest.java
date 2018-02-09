package com.capgemini.ntc.selenium.core.utils;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OperationsOnFilesTest {
	
	private String	testSourceFile		= "sourceTest.txt";
	private String	testTargetFile		= "targetTest.txt";
	private String	goodSourceFilePath	= "./operationsOnFilesTestDir/sourceDir/" + testSourceFile;
	private String	goodTargetFilePath	= "./operationsOnFilesTestDir/targetDir/" + testTargetFile;
	
	private String	badSourceFilePath	= "./notExistingDir/" + testSourceFile;
	private String	badTargetFilePath	= "./notExistingDir/" + testTargetFile;
	
	File	goodSourceFile;
	File	badSourceFile;
	File	goodTargetFile;
	File	badTargetfile;
	
	@Before
	public void setUp() throws IOException {
		goodSourceFile = new File(goodSourceFilePath);
		badSourceFile = new File(badSourceFilePath);
		goodTargetFile = new File(goodTargetFilePath);
		badTargetfile = new File(badTargetFilePath);
		
		Assert.assertTrue("Failed to create source directories.", goodSourceFile.getParentFile()
						.mkdirs());
		Assert.assertTrue("Failed to create source file.", goodSourceFile.createNewFile());
		Assert.assertTrue("Failed to create target directories.", goodTargetFile.getParentFile()
						.mkdirs());
	}
	
	@After
	public void tearDown() throws IOException {
		OperationsOnFiles.removeFileAndParentsIfEmpty(goodSourceFile.toPath());
		OperationsOnFiles.removeFileAndParentsIfEmpty(goodTargetFile.toPath());
		OperationsOnFiles.removeFileAndParentsIfEmpty(badTargetfile.toPath());
		
		Assert.assertFalse("Failed to remove source file.", goodSourceFile.exists());
		Assert.assertFalse("Failed to remove target file from existing directory.", goodTargetFile.exists());
		Assert.assertFalse("Failed to remove target file from created directory.", badTargetfile.exists());
	}
	
	@Test
	public void movingFileFromExistingToExistingPath() {
		OperationsOnFiles.moveWithPruneEmptydirectories(goodSourceFilePath, goodTargetFilePath);
		Assert.assertTrue("File was not moved properly into the target directory.", goodTargetFile.exists());
		Assert.assertFalse("File still exist in source path after moving.", goodSourceFile.exists());
	}
	
	@Test
	public void movingFileFromExisitingToNotExitsingPath() {
		OperationsOnFiles.moveWithPruneEmptydirectories(goodSourceFilePath, badTargetFilePath);
		Assert.assertTrue("File was not moved properly into the target directory.", badTargetfile.exists());
		Assert.assertFalse("File still exist in source path after moving.", goodSourceFile.exists());
	}
	
	@Test
	public void movingFileFromNotExisitingToExitsingPath() {
		OperationsOnFiles.moveWithPruneEmptydirectories(badSourceFilePath, goodTargetFilePath);
		Assert.assertFalse("File was moved properly into the target directory.", badTargetfile.exists());
		Assert.assertFalse("File still exist in source path after moving.", badSourceFile.exists());
	}
	
	@Test
	public void movingFileFromNotExisitingToNotExitsingPath() {
		OperationsOnFiles.moveWithPruneEmptydirectories(badSourceFilePath, badTargetFilePath);
		Assert.assertFalse("File was moved properly into the target directory.", badTargetfile.exists());
		Assert.assertFalse("File still exist in source path after moving.", badSourceFile.exists());
	}
	
	@Test
	public void movingFileFromNullToNotExitsingPath() {
		OperationsOnFiles.moveWithPruneEmptydirectories(null, badTargetFilePath);
		Assert.assertFalse("File was moved properly into the target directory.", badTargetfile.exists());
		Assert.assertFalse("File still exist in source path after moving.", badSourceFile.exists());
	}
	
}
