package com.capgemini.mrchecker.selenium.mts.pages;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.capgemini.mrchecker.common.mts.data.Reservation;
import com.capgemini.mrchecker.test.core.utils.PageFactory;

public class ThaiTableBodyPage extends MyThaiStarBasePage {
	
	private static final By	reservationsTableSearch	= By.xpath("//tbody[@class='td-data-table-body']/tr");
	private static final By	reservationRowSearch	= By.xpath("./td//span");
	private static final By	nextPageSearch			= By.xpath("//button[@class=\"td-paging-bar-next-page mat-icon-button\"]");
	
	@Override
	protected By getDisplayableElementSelector() {
		return nextPageSearch;
	}
	
	@Override
	public String pageTitle() {
		return "";
	}
	
	public Map<String, List<Reservation>> getReservations(Map<String, List<Reservation>> idReservations) {
		List<WebElement> reservations = null;
		List<WebElement> reservationsR = null;
		List<String> reservationsRow = null;
		List<Reservation> reservationsByDate;
		String date, id, email;
		
		reservations = getDriver().findElementDynamics(reservationsTableSearch);
		
		for (WebElement reservationWe : reservations) {
			reservationsR = reservationWe.findElements(reservationRowSearch);
			reservationsRow = reservationsR.stream()
					.map((we) -> we.getText())
					.collect(Collectors.toList());
			
			// get email
			date = reservationsRow.get(0);
			email = reservationsRow.get(1);
			id = reservationsRow.get(2);
			
			reservationsByDate = idReservations.getOrDefault(date, new LinkedList<Reservation>());
			reservationsByDate.add(new Reservation(date, email, id));
			
			idReservations.put(date, reservationsByDate);
			
		}
		
		return idReservations;
		
	}
	
	public ThaiTableBodyPage nextPage() {
		WebElement nextPage = getDriver().findElementDynamic(nextPageSearch);
		getAction().moveToElement(nextPage)
				.click()
				.perform();
		
		return PageFactory.getPageInstance(ThaiTableBodyPage.class);
	}
	
	public boolean isThereANextPage() {
		WebElement nextButton = getDriver().findElementDynamic(nextPageSearch);
		getAction().moveToElement(nextButton);
		JavascriptExecutor js = ((JavascriptExecutor) getDriver());
		
		return !((Boolean) js.executeScript("return arguments[0].disabled", nextButton));
	}
}
