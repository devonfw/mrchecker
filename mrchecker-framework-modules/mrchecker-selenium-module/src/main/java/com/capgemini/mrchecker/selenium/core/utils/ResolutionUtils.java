package com.capgemini.mrchecker.selenium.core.utils;

import org.openqa.selenium.Dimension;

import com.capgemini.mrchecker.selenium.core.enums.ResolutionEnum;
import com.capgemini.mrchecker.selenium.core.newDrivers.INewWebDriver;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ResolutionUtils {
	
	/**
	 * Changes resolution of browser to width specified by parameter and height of 900
	 * 
	 * @param resolution
	 * @author
	 */
	public static void setResolution(INewWebDriver driver, int width) {
		Dimension resolution = new Dimension(width, 900);
		setResolution(driver, resolution);
	}
	
	/**
	 * Changes resolution of browser to width and height specified by parameter
	 * 
	 * @param width
	 *            window width
	 * @param height
	 *            window height
	 * @author
	 */
	public static void setResolution(INewWebDriver driver, int width, int height) {
		Dimension resolution = new Dimension(width, height);
		setResolution(driver, resolution);
	}
	
	/**
	 * Changes resolution of browser to width and height taken from {@link ResolutionEnum}
	 */
	public static void setResolution(INewWebDriver driver, ResolutionEnum resolutionEnum) {
		Dimension resolution = new Dimension(resolutionEnum.getWidth(), resolutionEnum.getHeight());
		setResolution(driver, resolution);
	}
	
	/**
	 * Changes resolution of browser to resolution specified by parameter
	 * 
	 * @param resolution
	 * @author
	 */
	public static void setResolution(INewWebDriver driver, Dimension resolution) {
		
		BFLogger.logInfo("Trying to set requested resolution: " + resolution.width + "x" + resolution.height + ".");
		driver.manage()
				.window()
				.setSize(resolution);
		
		BFLogger.logInfo(
				"Resolution readed from device: " + getResolution(driver).width + "x" + getResolution(driver).height);
		
		Dimension dimensionWithOffset = new Dimension(getOffsetWidth(driver), resolution.height);
		BFLogger.logInfo("Trying to set increased resolution for 'View port': " + dimensionWithOffset.width + "x"
				+ dimensionWithOffset.height + ".");
		driver.manage()
				.window()
				.setSize(dimensionWithOffset);
		
		BFLogger.logInfo(
				"Resolution readed from device: " + getResolution(driver).width + "x" + getResolution(driver).height);
	}
	
	private static Dimension getResolution(INewWebDriver driver) {
		int width = driver.manage()
				.window()
				.getSize()
				.getWidth();
		int height = driver.manage()
				.window()
				.getSize()
				.getHeight();
		return new Dimension(width, height);
	}
	
	private static int getOffsetWidth(INewWebDriver driver) {
		int widthScreen = WindowUtils.getScreenWidth(driver);
		int offset = driver.manage()
				.window()
				.getSize()
				.getWidth() - widthScreen;
		return driver.manage()
				.window()
				.getSize()
				.getWidth() + offset;
		
	}
	
}
