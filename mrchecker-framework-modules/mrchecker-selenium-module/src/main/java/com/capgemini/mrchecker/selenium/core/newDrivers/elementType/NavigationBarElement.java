package com.capgemini.mrchecker.selenium.core.newDrivers.elementType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.selenium.core.exceptions.BFElementNotFoundException;
import com.capgemini.mrchecker.test.core.exceptions.BFInputDataException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LKURZAJ on 07.03.2017.
 */
public class NavigationBarElement extends BasicElement {
	
	private By inputChildsSelector;
	
	public NavigationBarElement(By cssSelector) {
		/**
		 * @param cssSelector
		 *            - selector of Navigation Bar element's set
		 **/
		this(cssSelector, By.cssSelector("li"));
	}
	
	public NavigationBarElement(By cssSelector, By inputChildsSelector) {
		/**
		 * @param cssSelector
		 *            - selector of Navigation Bar element's set
		 **/
		super(ElementType.NAVIGATION_BAR, cssSelector);
		this.inputChildsSelector = inputChildsSelector;
	}
	
	public List<String> getItemsTextList() {
		List<WebElement> listElems = this.getItems();
		List<String> out = new ArrayList<>();
		
		for (int i = 0; i < listElems.size(); i++) {
			out.add(listElems.get(i)
					.getText());
		}
		return out;
	}
	
	public String getFirstItemText() {
		return this.getItemsTextList()
				.get(0);
	}
	
	public String getActiveItemText() {
		List<WebElement> listItems = this.getItems();
		for (int i = 0; i < listItems.size(); i++) {
			if (listItems.get(i)
					.getAttribute("class")
					.contains("active")) {
				return listItems.get(i)
						.getText();
			}
		}
		throw new BFElementNotFoundException("Any active item was found in " + this.getElement()
				.toString());
	}
	
	public void clickFirstItem() {
		this.getItems()
				.get(0)
				.click();
	}
	
	public void clickActiveItem() {
		this.getItems()
				.get(this.getDepth() - 1)
				.click();
	}
	
	public void clickItemByIndex(int index) {
		if (index > 0 && index >= this.getItems()
				.size()) {
			throw new BFInputDataException("Index " + String.valueOf(index) + " larger than list's size: "
					+ String.valueOf(this.getItems()
							.size()));
		}
		this.getItems()
				.get(index)
				.click();
	}
	
	public void clickItemByText(String text) {
		for (int i = 0; i < this.getItems()
				.size(); i++) {
			if (this.getItems()
					.get(i)
					.getText()
					.equals(text)) {
				this.getItems()
						.get(i)
						.click();
				return;
			}
		}
		throw new BFElementNotFoundException("Item with text: " + text + " wasn't found in " + this.getText());
	}
	
	public int getDepth() {
		return this.getItems()
				.size();
	}
	
	private List<WebElement> getItems() {
		return this.getElement()
				.findElements(this.inputChildsSelector);
	}
}
