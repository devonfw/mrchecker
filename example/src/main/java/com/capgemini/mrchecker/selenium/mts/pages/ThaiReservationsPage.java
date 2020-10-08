package com.capgemini.mrchecker.selenium.mts.pages;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.capgemini.mrchecker.common.mts.data.Reservation;
import com.capgemini.mrchecker.common.mts.utils.Utils;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;

public class ThaiReservationsPage extends MyThaiStarBasePage {
	
	private static final By	selectorReservationsTableSearch	= By.xpath("//tbody/tr");
	private static final By	selectorSearchBarFilter			= By.cssSelector("#mat-expansion-panel-header-1");
	
	private static final By	selectorEmailInputSearch	= By.xpath("//input[@name=\"email\"]");
	private static final By	selectorSubmitButtonSearch	= By.xpath("//button[@type='submit']");
	
	private static final By		selectorColumnBookingDateSearch		= By.className("cdk-column-bookingDate");
	private static final By		selectorColumnEmailSearch			= By.className("cdk-column-email");
	private static final By		selectorColumnBookingTokenSearch	= By.className("cdk-column-bookingToken");
	private static final String	DATE_FORMAT_UI						= "dd MMM yyyy hh:mm";
	private static final String	DATE_FORMAT_INTERNAL				= "MM/dd/yyyy hh:mm a";
	
	private Map<String, List<String>> tableData;
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		
		return getDriver().getCurrentUrl()
				.contains("reservations");
	}
	
	@Override
	protected By getDisplayableElementSelector() {
		return null;
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	public List<String> findReservationsIdByEmail(String email) {
		return tableData.getOrDefault(email, new LinkedList<String>());
	}
	
	public HashMap<String, List<Reservation>> searchDatesByEmail(String email) {
		WebElement searchBar = getDriver().findElementDynamic(selectorSearchBarFilter);
		
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click()", searchBar);
		
		try {
			getDriver().findElementDynamic(By.xpath("adasd"), 2);
		} catch (Exception e) {
			
		}
		
		int index = 1;
		WebElement input = getDriver().findElementDynamic(selectorEmailInputSearch);
		if (!input.isEnabled()) {
			js = (JavascriptExecutor) getDriver();
			js.executeScript("arguments[0].click()", searchBar);
			
		}
		Utils.sendKeysWithCheck(email, selectorEmailInputSearch, getDriver(), getWebDriverWait(), index);
		getDriver().findElementDynamic(selectorSubmitButtonSearch)
				.click();
		
		try {
			getDriver().findElementDynamics(By.cssSelector("this-selector-doesnt-exist"), 2);
		} catch (Exception e) {
			
		}
		
		HashMap<String, List<Reservation>> idReservations = new HashMap<>();
		return getReservationsShownByDate(idReservations);
	}
	
	private HashMap<String, List<Reservation>> getReservationsShownByDate(
			HashMap<String, List<Reservation>> idReservations) {
		
		List<WebElement> reservations;
		List<Reservation> reservationsByDate;
		String date, id, email;
		
		reservations = getDriver().findElementDynamics(selectorReservationsTableSearch);
		
		for (int i = 1; i <= reservations.size(); i++) {
			
			date = getDriver().findElementDynamic(findDataCell(i, 1))
					.getText();
			email = getDriver().findElementDynamic(findDataCell(i, 2))
					.getText();
			id = getDriver().findElementDynamic(findDataCell(i, 3))
					.getText();
			try {
				date = Utils.changeDateFormat(date, "MMM dd, yyyy hh:mm a", "MM/dd/yyyy hh:mm a");
			} catch (ParseException e) {
				System.err.println("Date not formated properly at getReservationsShownByDate in ThaiReservationsPage");
				e.printStackTrace();
			}
			
			reservationsByDate = idReservations.getOrDefault(date, new LinkedList<Reservation>());
			reservationsByDate.add(new Reservation(date, email, id));
			
			idReservations.put(date, reservationsByDate);
		}
		
		return idReservations;
	}
	
	public By findDataCell(int indexRow, int indexCol) {
		return By.xpath("//tbody[@class='td-data-table-body']/tr[" + indexRow + "]/td[" + indexCol + "]//span");
	}
	
	public List<Reservation> getReservationsForEmail(String email) {
		Button searchBar = getDriver().elementButton(selectorSearchBarFilter);
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchBar.getElement()));
		searchBar.click();
		Utils.sendKeysWithCheck(email, selectorEmailInputSearch, getDriver(), getWebDriverWait(), 1);
		getDriver().findElementDynamic(selectorSubmitButtonSearch)
				.click();
		
		return getReservations().stream()
				.filter(r -> r.getEmail()
						.equals(email))
				.collect(Collectors.toList());
	}
	
	private List<Reservation> getReservations() {
		try {
			return getReservationsInternal();
		} catch (StaleElementReferenceException exc) {
			return getReservationsInternal();
		}
	}
	
	private List<Reservation> getReservationsInternal() {
		List<Reservation> reservations = new ArrayList<>();
		
		List<WebElement> reservationsLines = getDriver().findElementDynamics(selectorReservationsTableSearch);
		
		for (WebElement reservationLine : reservationsLines) {
			String date = reformatReservationDate(reservationLine.findElement(selectorColumnBookingDateSearch)
					.getText());
			String email = reservationLine.findElement(selectorColumnEmailSearch)
					.getText();
			String id = reservationLine.findElement(selectorColumnBookingTokenSearch)
					.getText();
			reservations.add(new Reservation(date, email, id));
			
		}
		return reservations;
	}
	
	private String reformatReservationDate(String dateFromTable) {
		try {
			return Utils.changeDateFormat(dateFromTable, DATE_FORMAT_UI, DATE_FORMAT_INTERNAL);
		} catch (ParseException exc) {
			throw new IllegalArgumentException(
					"Date" + dateFromTable + " not formatted properly at getReservationsShownByDate in ThaiReservationsPage", exc);
		}
	}
}
