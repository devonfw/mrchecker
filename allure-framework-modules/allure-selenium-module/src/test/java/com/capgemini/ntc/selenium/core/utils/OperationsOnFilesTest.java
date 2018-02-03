package com.capgemini.ntc.selenium.core.utils;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OperationsOnFilesTest {
	
	String sourcePath;
	
	@Before
	public void setUp() {
		String sourcePath = "C:" + File.separator + "hello" + File.separator + "hi.txt";
		// Use relative path for Unix systems
		File f = new File(sourcePath);
		
		f.getParentFile()
						.mkdirs();
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TASK Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void test() {
		// Given
		String notExisitingTargetPath = "c:/NotExisitingDirectory2";
		
		// When
		try { 
		OperationsOnFiles.moveWithPruneEmptydirectories(sourcePath, notExisitingTargetPath);
		}
		// Then
		
	}
	
	@Test
	public void test2() {
		OperationsOnFiles.moveWithPruneEmptydirectories("d", "asd");
	}
	
}
