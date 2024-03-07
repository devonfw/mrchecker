package com.capgemini.testTemplateGeneratorFromXrayCsv;

import com.capgemini.framework.playwright.BaseTest;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tomasz Fulek
 * @author Jerzy Tomes
 * @author Malgorzata Dzienia
 * 		This is a simple tool to convert csv files with test steps exported from Xray to java test template with allure steps and annotations.
 * 		usage: -open your test case in jira -click on ... → export to csv over the test steps -use default settings and save the file -Put the file to src/resources/xrayManualTests -Edit
 * 		INPUT_CSV_FILE_PATH here to pick your manual test case for generation -Run this test case. Template will be generated into target/testTemplate -Move it to test package, execute, see allure and
 * 		fill with implementation
 */
public class TestTemplateGeneratorFromXrayCsv extends BaseTest {
	private static final String INPUT_CSV_FILE_PATH          = "src/test/resources/xrayManualTests/JIRAPREFIX-1135.csv";
	
	private static final String COMMENT_IMPORTS              = "//imports";
	private static final String COMMENT_TODO_ADD_TMS_LINK    = " // TODO add TMS link";
	private static final String COMMENT_TODO_ADD_EPIC        = " // TODO add epic";
	private static final String COMMENT_TODO_ADD_STORY       = " // TODO add story";
	private static final String COMMENT_TODO_ADD_DESCRIPTION = " // TODO add description";
	
	private static final String TEST_TEMPLATE_CLASS_NAME                    = "TestTemplateGenerated";
	private static final String TEST_TEMPLATE_PATH                          = "target/testTemplate";
	private static final String LINE_END                                    = "\r\n";
	private static final String SEPARATOR                                   = LINE_END + LINE_END;
	private static final String COMMENT_MULTIPLE_METHODS_WITH_THE_SAME_NAME = " /* TODO remove or change the method's name */ ";
	private static final String COMMENT_CALLS_TO_METHODS                    = " //calls to methods:" + LINE_END + LINE_END;
	private static final String COMMENT_DEFINITIONS_OF_METHODS              = " //definitions of methods:";
	private static final String STEP_EXPECTED_RESULT                        = "Expected Result";
	private static final String STEP_ACTION                                 = "Action";
	
	private static final String tmsLink = "TmsLink-TODO";
	
	@Test
	public void generateTemplateFromXrayCSV_test() {
		
		List<Map<String, String>> csvToListMap = readXrayCsvToListMap();
		
		List<String> methodNames = createMethodNamesFromXraySteps(csvToListMap);
		
		List<String> methodDefinitions = createMethodDefinitionsFromXraySteps(csvToListMap, methodNames);
		
		StringBuilder outputString = createContentOfTemplateFile(methodNames, methodDefinitions);
		
		System.out.println(outputString);
		
		saveResultToFile(outputString.toString());
		
	}
	
	private static StringBuilder createContentOfTemplateFile(List<String> methodNames, List<String> methodDefinitions) {
		StringBuilder outputString = new StringBuilder();
		
		/* Imports */
		outputString.append(createImportString());
		
		outputString.append(createTestClassMethod(methodNames));
		
		outputString.append(LINE_END)
				.append(SEPARATOR);
		
		/* Metod definitions with steps */
		outputString.append(COMMENT_DEFINITIONS_OF_METHODS)
				.append(LINE_END)
				.append(LINE_END);
		
		for (String methodDefinition : methodDefinitions) {
			outputString.append(methodDefinition)
					.append(LINE_END)
					.append(LINE_END);
		}
		outputString.append("}");
		return outputString;
	}
	
	private static StringBuilder createTestClassMethod(List<String> methodNames) {
		StringBuilder outputString = new StringBuilder();
		/* Test clas name */
		outputString.append("public class " + StringUtils.capitalize(TEST_TEMPLATE_CLASS_NAME) + " extends BaseTest {")
				.append(LINE_END);
		/* Test method annotations */
		outputString.append(SEPARATOR);
		if (tmsLink != null && !tmsLink.isBlank()) {
			outputString.append("@TmsLink(\"")
					.append(tmsLink)
					.append("\")")
					.append(LINE_END);
		} else {
			outputString.append("@TmsLink(\"\")")
					.append(COMMENT_TODO_ADD_TMS_LINK)
					.append(LINE_END);
		}
		
		outputString.append("@Epic(\"\")" + COMMENT_TODO_ADD_EPIC)
				
				.append(LINE_END)
				.append("@Story(\"\")" + COMMENT_TODO_ADD_STORY)
				.append(LINE_END)
				.append("@Description(\"\")" + COMMENT_TODO_ADD_DESCRIPTION)
				.append(LINE_END)
				.append("@Test")
				.append(LINE_END)
				.append("@Tag(Status.IN_PROGRESS)")
				.append(LINE_END)
				.append("public void ")
				.append(TEST_TEMPLATE_CLASS_NAME)
				.append("_test(){")
				.append(LINE_END);
		
		/* Step methods calls */
		outputString.append(SEPARATOR)
				.append(COMMENT_CALLS_TO_METHODS);
		
		for (String methodName : methodNames) {
			outputString.append("\t\t")
					.append(methodName)
					.append(";")
					.append(LINE_END);
		}
		/* end of test method */
		outputString.append("\t\tfail( \"This is test case template generated from Xray exported CSV. Please fill it with test implementation.\");")
				.append(LINE_END);
		outputString.append("}");
		return outputString;
	}
	
	private static StringBuilder createImportString() {
		return new StringBuilder(COMMENT_IMPORTS).append(LINE_END)
				.append("import com.capgemini.framework.logger.AllureStepLogger;")
				.append(LINE_END)
				.append("import com.capgemini.framework.playwright.BaseTest;")
				.append(LINE_END)
				.append("import com.capgemini.framework.playwright.PlaywrightFactory;")
				.append(LINE_END)
				.append("import io.qameta.allure.*;")
				.append(LINE_END)
				.append("import org.junit.jupiter.api.Test;")
				.append(LINE_END)
				.append("import org.junit.jupiter.api.Tag;")
				.append(LINE_END)
				.append("import static org.junit.Assert.fail;")
				.append(LINE_END)
				.append("import com.capgemini.framework.tags.Status;")
				.append(LINE_END);
	}
	
	private static String getTmsLink(String name) {
		return name.substring(0, name.indexOf(" "));
	}
	
	private static String createClassName(String name) {
		return changeStringToValidJavaClassName(name);
	}
	
	private static String changeStringToValidJavaClassName(String name) {
		String charsToDelete = "()/\\\":.;,*\n\r[]&";
		name = name.replace("-", "")
				.replace("\\u00a0", "");
		for (int i = 0; i < charsToDelete.length(); i++) {
			name = name.replace("" + charsToDelete.charAt(i), " ");
		}
		
		name = name.replace(" ", "_");
		name = name.replace(" ", "");
		name = name.replaceAll("_+", "_");
		
		name = name
				.replace(">", "MoreThan")
				.replace("<", "LessThan")
				.replace("=", "Equal")
				.replace("?", "QuestionMark")
				.replace("!", "ExclamationMark")
				.replace("ü", "ue")
				.replace("Ü", "Ue")
				.replace("ß", "ss")
				.replace("ö", "oe")
				.replace("Ö", "Oe")
				.replace("ä", "ae")
				.replace("Ä", "Ae");
		
		return name;
		
	}
	
	private static void saveResultToFile(String outputString) {
		try {
			Files.createDirectories(Paths.get(TEST_TEMPLATE_PATH));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		File file = new File(TEST_TEMPLATE_PATH + "/" + StringUtils.capitalize(TEST_TEMPLATE_CLASS_NAME) + ".java");
		try (FileWriter myWriter = new FileWriter(file)) {
			myWriter.write(outputString);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static List<String> createMethodDefinitionsFromXraySteps(List<Map<String, String>> csvToListMap, List<String> methodNames) {
		
		List<String> definitions = new ArrayList<>();
		int i = 0;
		for (Map<String, String> entry : csvToListMap) {
			String definition = "\t@Step(\"" +
					String.valueOf(entry.get(STEP_ACTION))
							.replace("\"", "\\\"")
							.replace("\n", " ") +
					"\")" + LINE_END +
					"	private void " +
					methodNames.get(i) +
					" {" + LINE_END +
					"		AllureStepLogger.info(\"" +
					String.valueOf(entry.get(STEP_EXPECTED_RESULT.replace("\n", "")))
							.replace("\"", "\\\"")
							.replace("\r\n", "\\n")
							.replace("\n\n", "\\n")
							.replace("\\n\\n", "\\n")
						//	.replace("\r\n", "\\n\"+" + LINE_END + "\t\t\t\"")
							.replace("\\n", "\\n\"+" + LINE_END + "\t\t\t\"")
					        .replace("\n ", "\\n\"+" + LINE_END + "\t\t\t\"") +
					
					"\");" + LINE_END +
					"		AllureStepLogger.makeScreenshot();" + LINE_END +
					"	}" + LINE_END;
			
			definitions.add(definition);
			i++;
		}
		
		return definitions;
	}
	
	private static List<String> createMethodNamesFromXraySteps(List<Map<String, String>> csvToListMap) {
		
		List<String> methods = new ArrayList<>();
		
		for (Map<String, String> entry : csvToListMap) {
			
			String methodRaw = entry.get(STEP_ACTION);
			String methodName = changeStringToValidJavaName(methodRaw);
			
			if (methods.contains(methodName + "()")) {
				
				try {
					int suffix = 1;
					while (methods.contains(methodName + suffix + "()" + COMMENT_MULTIPLE_METHODS_WITH_THE_SAME_NAME)) {
						suffix++;
						
					}
					
					methodName = methodName + suffix + "()" + COMMENT_MULTIPLE_METHODS_WITH_THE_SAME_NAME;
					
				} catch (NumberFormatException nfe) {
					
					methodName = methodName + 1;
				}
				
			} else {
				methodName += "()";
			}
			
			methods.add(methodName);
		}
		
		return methods;
	}
	
	private static String changeStringToValidJavaName(String name) {
		
		String alphabet = "aäbcdefghijklmnoöpqrsßtuüvwxyz1234567890";
		
		String charsToDelete = "'()/\\\"-:.;,*\n\r[]&";
		
		name = name.toLowerCase();
		
		for (int i = 0; i < charsToDelete.length(); i++) {
			name = name.replace("" + charsToDelete.charAt(i), " ");
		}
		for (int i = 0; i < alphabet.length(); i++) {
			name = name.replace(" " + alphabet.charAt(i), String.valueOf(alphabet.charAt(i))
					.toUpperCase());
		}
		
		name = name.replace(" ", "");
		
		name = name.replace(">", "MoreThan")
				.replace("<", "LessThan")
				.replace("=", "Equal")
				.replace("?", "QuestionMark")
				.replace("!", "ExclamationMark")
				.replace("ü", "ue")
				.replace("Ü", "Ue")
				.replace("ß", "ss")
				.replace("ö", "oe")
				.replace("Ö", "Oe")
				.replace("ä", "ae")
				.replace("Ä", "Ae")
				.replace(" ", "");
		
		name = String.valueOf(name.charAt(0))
				.toLowerCase() + name.substring(1);
		
		return name;
	}
	
	private static List<Map<String, String>> readXrayCsvToListMap() {
		List<String> labelList = new ArrayList<>();
		int rowNumber = 0;
		
		List<Map<String, String>> entries = new ArrayList<>();
		
		try (Reader reader = Files.newBufferedReader(Paths.get(INPUT_CSV_FILE_PATH)); CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {
			for (CSVRecord csvRecord : csvParser) {
				// Accessing Values by Column Index
				
				if (rowNumber == 0) {
					
					for (int td = 0; td < csvRecord.size(); td++) {
						labelList.add(csvRecord.get(td));
					}
					
				} else {
					
					Map<String, String> entry = new HashMap<>();
					
					for (int td = 0; td < csvRecord.size(); td++) {
						
						entry.put(labelList.get(td), csvRecord.get(td));
						
					}
					entries.add(entry);
					
				}
				
				rowNumber++;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return entries;
	}
	
}
