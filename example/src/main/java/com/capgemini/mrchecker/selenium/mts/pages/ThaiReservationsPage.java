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

import com.capgemini.mrchecker.selenium.core.BasePage;
import com.capgemini.mrchecker.selenium.core.newDrivers.elementType.Button;
import com.capgemini.mrchecker.selenium.mts.common.data.Reservation;
import com.capgemini.mrchecker.selenium.mts.common.utils.Utils;
import com.capgemini.mrchecker.test.core.logger.BFLogger;

public class ThaiReservationsPage extends BasePage {
	
	private static final By	reservationsTableSearch	= By.xpath("//tbody/tr");
	private static final By	searchBarFilter			= By.cssSelector("#mat-expansion-panel-header-1");
	private static final By	emailInputSearch		= By.xpath("//input[@name=\"email\"]");
	private static final By	submitButtonSearch		= By.xpath("//button[@type='submit']");
	
	private static final By		COLUMN_BOOKING_DATE_SEARCH	= By.className("cdk-column-bookingDate");
	private static final By		COLUMN_EMAIL_SEARCH			= By.className("cdk-column-email");
	private static final By		COLUMN_BOOKING_TOKEN_SEARCH	= By.className("cdk-column-bookingToken");
	private static final String	DATE_FORMAT_UI				= "dd MMM yyyy hh:mm";
	private static final String	DATE_FORMAT_INTERNAL		= "MM/dd/yyyy hh:mm a";
	
	private Map<String, List<String>> tableData;
	
	@Override
	public boolean isLoaded() {
		getDriver().waitForPageLoaded();
		
		return getDriver().getCurrentUrl()
				.contains("reservations");
	}
	
	@Override
	public void load() {
		BFLogger.logError("MyThaiStar reservation page was not loaded.");
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	public List<String> findReservationsIdByEmail(String email) {
		return tableData.getOrDefault(email, new LinkedList<String>());
	}
	
	public HashMap<String, List<Reservation>> searchDatesByEmail(String email) {
		WebElement searchBar = getDriver().findElementDynamic(searchBarFilter);
		
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click()", searchBar);
		
		try {
			getDriver().findElementDynamic(By.xpath("adasd"), 2);
		} catch (Exception e) {
			
		}
		
		int index = 1;
		
		Utils.sendKeysWithCheck(email, emailInputSearch, getDriver(), getWebDriverWait(), index);
		getDriver().findElementDynamic(submitButtonSearch)
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
		
		reservations = getDriver().findElementDynamics(reservationsTableSearch);
		
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
		Button searchBar = getDriver().elementButton(searchBarFilter);
		getWebDriverWait().until(ExpectedConditions.elementToBeClickable(searchBar.getElement()));
		searchBar.click();
		Utils.sendKeysWithCheck(email, emailInputSearch, getDriver(), getWebDriverWait(), 1);
		getDriver().findElementDynamic(submitButtonSearch)
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
		
		List<WebElement> reservationsLines = getDriver().findElementDynamics(reservationsTableSearch);
		
		for (WebElement reservationLine : reservationsLines) {
			String date = reformatReservationDate(reservationLine.findElement(COLUMN_BOOKING_DATE_SEARCH)
					.getText());
			String email = reservationLine.findElement(COLUMN_EMAIL_SEARCH)
					.getText();
			String id = reservationLine.findElement(COLUMN_BOOKING_TOKEN_SEARCH)
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
