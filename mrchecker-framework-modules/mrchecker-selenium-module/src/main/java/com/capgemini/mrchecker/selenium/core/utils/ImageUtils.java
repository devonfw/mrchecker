package com.capgemini.mrchecker.selenium.core.utils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import com.capgemini.mrchecker.selenium.core.BasePage;

/**
 * This class contains methods related to getting image from web page.
 * 
 * @author
 */
public class ImageUtils {
	
	private ImageUtils() {
	}
	
	/**
	 * @return image as BufferedImage pointed by imageElement
	 * @author
	 */
	public static BufferedImage readImageFromScreenShotOfElement(WebElement imageElement) throws IOException {
		if (!(BasePage.getDriver() instanceof JavascriptExecutor))
			return null;
		
		scrollPageToImage(imageElement, (JavascriptExecutor) BasePage.getDriver());
		return getWebElementScreenshot(imageElement);
	}
	
	private static BufferedImage getWebElementScreenshot(WebElement we) throws IOException {
		File screenshotFile = ((TakesScreenshot) BasePage.getDriver()).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = ImageIO.read(screenshotFile);
		
		BufferedImage webElementScreenshot = fullImg.getSubimage(we.getLocation()
				.getX(),
				we.getLocation()
						.getY() + 1,
				we.getSize()
						.getWidth(),
				we.getSize()
						.getHeight());
		BufferedImage webElementScreenshotWithAlfa = new BufferedImage(we.getSize()
				.getWidth(),
				we.getSize()
						.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		
		for (int y = 0; y < we.getSize()
				.getHeight(); y++) {
			for (int x = 0; x < we.getSize()
					.getWidth(); x++) {
				int pixelColor = webElementScreenshot.getRGB(x, y);
				if (pixelColor == -1118482)
					pixelColor &= 0x00FFFFFF;
				webElementScreenshotWithAlfa.setRGB(x, y, pixelColor);
			}
		}
		
		ImageIO.write(webElementScreenshotWithAlfa, "png", screenshotFile);
		FileUtils.copyFile(screenshotFile, new File(System.getProperty("java.io.tmpdir")
				.concat("subimage")
				.concat("" + new Date().getTime())
				.concat(".png")));
		return webElementScreenshotWithAlfa;
	}
	
	private static void scrollPageToImage(WebElement webElement, JavascriptExecutor driver) {
		driver.executeScript(buildScrollScript(webElement.getLocation()));
	}
	
	private static String buildScrollScript(Point point) {
		return new StringBuilder("window.scrollTo(").append(point.x)
				.append(",")
				.append(point.y)
				.append(");")
				.toString();
	}
	
}
