package com.capgemini.ntc.selenium.core.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.enums.EdgesEnum;
import com.capgemini.ntc.selenium.core.enums.RelatedPositionEnum;
import com.capgemini.ntc.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.ntc.selenium.core.newDrivers.DriverManager;

/**
 * This class contains utility functions related to the position of elements on the page.
 * 
 * @author
 */
public class ElementPositionUtils {
	
	private ElementPositionUtils() {
	}
	
	public static boolean isElementNextToEdge(WebElement element, EdgesEnum edge) {
		return element.getCssValue(edge.toString())
						.equals("0px");
	}
	
	/**
	 * Method checks if second element is in given relation to first element considering X coordinate
	 * 
	 * @param selectorFirst
	 *            By object with an selector
	 * @param selectorSecond
	 *            By object with an selector
	 * @param position
	 *            relation of a second element to first one
	 * @return true if two elements are placed in given relation
	 */
	@SuppressWarnings("deprecation")
	public static boolean isElementsPositionRelatedX(By selectorFirst,
					By selectorSecond,
					RelatedPositionEnum position) {
		WebElement elementFirst = BasePage.getDriver()
						.findElement(selectorFirst);
		WebElement elementSecond = BasePage.getDriver()
						.findElement(selectorSecond);
		return ElementPositionUtils.isElementsPositionRelatedX(elementFirst, elementSecond, position);
	}
	
	/**
	 * Method checks if second element is in given relation to first element considering Y coordinate
	 * 
	 * @param selectorFirst
	 *            By object with an selector
	 * @param selectorSecond
	 *            By object with an selector
	 * @param position
	 *            relation of a second element to first one
	 * @return true if two elements are placed in given relation
	 */
	public static boolean isElementsPositionRelatedY(By selectorFirst,
					By selectorSecond,
					RelatedPositionEnum position) {
		@SuppressWarnings("deprecation")
		WebElement elementFirst = BasePage.getDriver()
						.findElement(selectorFirst);
		@SuppressWarnings("deprecation")
		WebElement elementSecond = BasePage.getDriver()
						.findElement(selectorSecond);
		return ElementPositionUtils.isElementsPositionRelatedY(elementFirst, elementSecond, position);
	}
	
	/**
	 * Method checks if second element is in given relation to first element considering X coordinate
	 * 
	 * @param selectorFirst
	 *            By object with an selector
	 * @param selectorSecond
	 *            By object with an selector
	 * @param scope
	 *            WebElement which contains comparing objects
	 * @param position
	 *            relation of a second element to first one
	 * @return true if two elements are placed in given relation
	 * @throws BFElementNotFoundException
	 *             if elements do not exist
	 */
	public static boolean isElementsPositionRelatedX(By selectorFirst,
					By selectorSecond,
					WebElement scope,
					RelatedPositionEnum position) {
		@SuppressWarnings("deprecation")
		WebElement elementFirst = BasePage.getDriver()
						.findElementQuietly(scope, selectorFirst);
		@SuppressWarnings("deprecation")
		WebElement elementSecond = BasePage.getDriver()
						.findElementQuietly(scope, selectorSecond);
		if (elementFirst == null) {
			throw new BFElementNotFoundException(selectorFirst);
		}
		if (elementSecond == null) {
			throw new BFElementNotFoundException(selectorSecond);
		}
		return ElementPositionUtils.isElementsPositionRelatedX(elementFirst, elementSecond, position);
	}
	
	/**
	 * Method checks if second element is in given relation to first element considering Y coordinate
	 * 
	 * @param elementFirst
	 *            first WebElement
	 * @param elementSecond
	 *            second WebElement
	 * @param position
	 *            relation of a second element to first one
	 * @return true if two elements are placed in given relation
	 */
	public static boolean isElementsPositionRelatedY(WebElement elementFirst,
					WebElement elementSecond,
					RelatedPositionEnum position) {
		int firstElementY = elementFirst.getLocation()
						.getY();
		int secondElementY = elementSecond.getLocation()
						.getY();
		if (position == RelatedPositionEnum.INLINE) {
			return firstElementY == secondElementY;
		} else if (position == RelatedPositionEnum.ABOVE) {
			return firstElementY > secondElementY;
		} else if (position == RelatedPositionEnum.BELOW) {
			return firstElementY < secondElementY;
		}
		return false;
	}
	
	/**
	 * Method checks if second element is in given relation to first element considering X coordinate
	 * 
	 * @param elementFirst
	 *            first WebElement
	 * @param elementSecond
	 *            second WebElement
	 * @param position
	 *            relation of a second element to first one
	 * @return true if two elements are placed in given relation
	 */
	public static boolean isElementsPositionRelatedX(WebElement elementFirst,
					WebElement elementSecond,
					RelatedPositionEnum position) {
		int firstElementX = elementFirst.getLocation()
						.getX();
		int secondElementX = elementSecond.getLocation()
						.getX();
		if (position == RelatedPositionEnum.INLINE) {
			return firstElementX == secondElementX;
		} else if (position == RelatedPositionEnum.BEFORE) {
			return firstElementX > secondElementX;
		} else if (position == RelatedPositionEnum.AFTER) {
			return firstElementX < secondElementX;
		}
		return false;
	}
	
	/**
	 * Method checks if WebElement is in Right-Top corner of the screen
	 * 
	 * @param element
	 *            WebElement
	 * @return true if element is in Right-Top quarter of the screen
	 */
	public static boolean isOnRightTopCorner(WebElement element) {
		ScrollUtils.scrollToTop();
		int elementPositonX = element.getLocation()
						.getX();
		int screenWidthMiddle = WindowUtils.getScreenWidth(DriverManager.getDriver()) / 2;
		int elementPositonY = element.getLocation()
						.getY()
						+ element.getSize()
										.getHeight();
		int screenHeightMiddle = WindowUtils.getScreenHeight(DriverManager.getDriver()) / 2;
		return elementPositonX >= screenWidthMiddle && elementPositonY <= screenHeightMiddle;
	}
	
}
