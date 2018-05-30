package com.capgemini.mrchecker.selenium.pages.projectY;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.base.environment.GetEnvironmentParam;
import com.capgemini.mrchecker.selenium.core.newDrivers.INewWebDriver;
import com.capgemini.mrchecker.selenium.pages.environment.PageSubURLsProjectYEnum;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class DragAndDropPage extends BasePage {
	
	private static final By	selectorDragAndDropText		= By.cssSelector("div#content h3");
	private static final By	selectorAElementContainer	= By.cssSelector("div#column-a");
	private static final By	selectorBElementContainer	= By.cssSelector("div#column-b");
	private static final By	selectorDescriptionElement	= By.cssSelector("header");
	
	private static final String dndHelperPath = "src/test/resources/js/drag_and_drop_helper.js";
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		return getDriver().getCurrentUrl()
						.contains(PageSubURLsProjectYEnum.DRAG_AND_DROP.getValue());
	}
	
	@Override
	public void load() {
		BFLogger.logDebug("Load 'Drag and Drop' page.");
		getDriver().get(GetEnvironmentParam.THE_INTERNET_MAIN_PAGE.getValue() + PageSubURLsProjectYEnum.DRAG_AND_DROP.getValue());
		getDriver().waitForPageLoaded();
	}
	
	@Override
	public String pageTitle() {
		return getActualPageTitle();
	}
	
	/**
	 * Returns information if drag and drop message is visible or not.
	 * 
	 * @return true if exit drag and drop message was found on web page.
	 */
	public boolean isDragAndDropMessageVisible() {
		return getDriver().findElementDynamic(selectorDragAndDropText)
						.isDisplayed();
	}
	
	/**
	 * Verifies if specified element is placed in designated container.
	 * 
	 * @param element
	 *            WebElement to be verified.
	 * @return true if element described as A exists in container A or element B exists in container B, false otherwise.
	 */
	public boolean isElementPlacedInCorrectContainer(String element) {
		return getDescriptionElement(findElementByDescription(element)).getText()
						.equals(element);
	}
	
	private WebElement findElementByDescription(String element) {
		WebElement result;
		switch (element) {
			case "A":
				result = getContainerElement(selectorAElementContainer);
				break;
			case "B":
				result = getContainerElement(selectorBElementContainer);
				break;
			default:
				result = null;
				BFLogger.logDebug("Chosen element doesn't exist on web page");
		}
		return result;
	}
	
	private WebElement getContainerElement(By container) {
		return getDriver().findElementDynamic(container);
	}
	
	private WebElement getDescriptionElement(WebElement container) {
		return container.findElement(selectorDescriptionElement);
	}
	
	/**
	 * Drags element to designated container and drops it.
	 * 
	 * @param element
	 *            String describing WebElement expected to be dragged.
	 * @param from
	 *            String describing WebElement representing container of element expected to be dragged.
	 * @param destinationDesc
	 *            String describing WebElement representing destination container where other element will be dragged.
	 */
	public void dragElementToPosition(String element, String from, String destinationDesc) {
		WebElement source = findElementByDescription(from);
		WebElement description = getDescriptionElement(source);
		WebElement destination = findElementByDescription(destinationDesc);
		if (description.getText()
						.equals(element))
			dragElement(source, destination);
	}
	
	/**
	 * Drags and drops given WebElement to it's destination location.
	 * <p>
	 * Since HTML5 all Selenium Actions performing drag and drop operations stopped working as expected, e.g.
	 * original implementation, which was:
	 * <code>
	 *  BasePage.getAction()
	 * 			.clickAndHold(draggable)
	 * 			.moveToElement(target)
	 * 			.release()
	 * 			.build()
	 * 			.perform();
	 * </code>
	 * had finished with no effect. For this reason, there is javaScript function used, to make sure that
	 * drag and drop operation will be successful.
	 * JavaScript function is stored under following path: 'src/test/resources/js/drag_and_drop_helper.js'.
	 * Original source of the script:
	 * <a href="https://gist.github.com/rcorreia/2362544">drag_and_drop_helper</a>
	 * </p>
	 * 
	 * @param draggable
	 *            A WebElement to be dragged and dropped.
	 * @param target
	 *            A destination, where element will be dropped.
	 * @see JavascriptExecutor
	 * @see Actions
	 */
	private void dragElement(WebElement draggable, WebElement target) {
		JavascriptExecutor js;
		INewWebDriver driver = getDriver();
		List<String> fileContent;
		String draggableId = draggable.getAttribute("id");
		String targetId = target.getAttribute("id");
		String script = null;
		if (draggable.getAttribute("draggable")
						.contains("true")) {
			if (driver instanceof JavascriptExecutor) {
				js = (JavascriptExecutor) driver;
				Path path = Paths.get(dndHelperPath);
				try {
					fileContent = Files.readAllLines(path);
					script = fileContent.stream()
									.collect(Collectors.joining());
				} catch (IOException e) {
					BFLogger.logDebug("Unable to read file content: " + e.getMessage());
				}
				if (script != null && !script.isEmpty()) {
					String arguments = "$('#%s').simulateDragDrop({ dropTarget: '#%s'});";
					js.executeScript(script + String.format(arguments, draggableId, targetId));
				}
			}
		}
	}
	
}
