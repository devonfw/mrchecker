package com.capgemini.ntc.selenium.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.capgemini.ntc.selenium.core.BasePage;
import com.capgemini.ntc.selenium.core.exceptions.BFComponentStateException;
import com.capgemini.ntc.selenium.core.newDrivers.INewWebDriver;
import com.capgemini.ntc.test.core.exceptions.BFInputDataException;
import com.capgemini.ntc.test.core.logger.BFLogger;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Contains methods that validate timestamp, pause application for moment, check trading hours in NY etc.
 * 
 * @author
 */
public class TimeUtills {
	
	private TimeUtills() {
	}
	
	private static final String	MARKET_CLOSE			= "MARKET_CLOSE";
	private static final String	IS_OPEN					= "IS_OPEN";
	private static String		urlService				= "https://www.Bank.com/service/quote/json?productid=embeddedquotes&subproductid=default&market_close=1&symbols=.DJI%2C.IXIC%2C.SPX&dojo.preventCache=1436276037737&callback=dojo.io.script.jsonp_dojoIoScript1._jsonpCallback";
	public static final int		TEN_MINUTES_IN_SECONDS	= 600;
	
	/**
	 * Check is timestamp has valid format
	 * 
	 * @param format
	 *            - String you like to use as format
	 * @param valueToValidate
	 *            - String that you like to validate
	 * @return boolean
	 * @author
	 */
	public static boolean isValidFormat(String format, String valueToValidate) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			sdf.parse(valueToValidate);
		} catch (ParseException e) {
			BFLogger.logDebug("timestamp not in correct format :" + valueToValidate);
			return false;
		}
		return true;
	}
	
	/**
	 * @author
	 */
	public static void pauseOneMinute() {
		long startTime = System.currentTimeMillis();
		int pauseTimeInSec = 60;
		int periodForMessageInSec = 5;
		int loopMax = pauseTimeInSec / periodForMessageInSec;
		BFLogger.logInfo("Wait One minute");
		for (int i = 0; i < loopMax; i++) {
			try {
				Thread.sleep(periodForMessageInSec * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			BFLogger.logInfo("Wait One minute. Remains : " + (pauseTimeInSec - i * periodForMessageInSec) + "s");
		}
		BFLogger.logTime(startTime, "pauseOneMinute");
	}
	
	/**
	 * Us this method to wait. Method will print message in every 5 sec.
	 * 
	 * @param sec
	 * @author
	 */
	public static void waitSeconds(int sec) {
		long startTime = System.currentTimeMillis();
		int periodForMessageInSec = 5;
		if (sec >= periodForMessageInSec) {
			int loopMax = sec / periodForMessageInSec;
			BFLogger.logInfo("Wait " + sec + " sec");
			for (int i = 0; i < loopMax; i++) {
				BFLogger.logInfo("Remains : " + (sec - i * periodForMessageInSec) + " sec");
				wait(periodForMessageInSec * 1000);
			}
		} else {
			wait(sec * 1000);
		}
		BFLogger.logTime(startTime, "waitSeconds");
	}
	
	/**
	 * Us this method to wait. Method will print message in every 5 sec.
	 * 
	 * @param milisec
	 * @author
	 */
	public static void waitMiliseconds(int milisec) {
		long startTime = System.currentTimeMillis();
		int periodForMessageInMiliSec = 5000;
		if (milisec >= periodForMessageInMiliSec) {
			int loopMax = milisec / periodForMessageInMiliSec;
			BFLogger.logInfo("Wait " + milisec + " milisec");
			for (int i = 0; i < loopMax; i++) {
				BFLogger.logInfo("Remains : " + (milisec - i * periodForMessageInMiliSec) + " milisec");
				wait(periodForMessageInMiliSec);
			}
		} else {
			wait(milisec);
		}
		BFLogger.logTime(startTime, "waitMiliseconds");
	}
	
	/**
	 * It can be used to check is now are trading hours in New York. Method invoke service available on main Bank page.
	 * Bank.com Invoke this method before you start test. For example in setUp method. As side effect method show json
	 * in browser NYSEtrading hours are 9:30 to 16:00
	 * 
	 * @return boolean
	 * @author
	 */
	public static boolean isNowTradingHoursinNY(INewWebDriver iNewWebDriver) {
		String orginalUrl = iNewWebDriver.getCurrentUrl();
		iNewWebDriver.get(urlService);
		String preElement = iNewWebDriver.findElementDynamic(By.cssSelector("pre"))
						.getText();
		JsonParser parser = new JsonParser();
		String jsonString = preElement.substring(preElement.indexOf("{"), preElement.length() - 1);
		JsonObject jobject = (JsonObject) parser.parse(jsonString);
		JsonObject marketClosedObject = jobject.getAsJsonObject(MARKET_CLOSE);
		String result = marketClosedObject.get(IS_OPEN)
						.toString()
						.replace("\"", "");
		BFLogger.logInfo("isThisIsTradingHoursinNY - result: " + result);
		iNewWebDriver.get(orginalUrl);
		if (result.equals("N")) {
			return false;
		}
		return true;
	}
	
	/**
	 * Function waits specified time
	 * 
	 * @author
	 * @param miliseconds
	 *            Desired waiting time in milliseconds
	 */
	private static void wait(int milliseconds) {
		try {
			while (milliseconds > 0) {
				int waitTime = milliseconds > 1000 ? 1000 : milliseconds;
				Thread.sleep(waitTime);
				milliseconds -= waitTime;
			}
		} catch (InterruptedException e) {
			BFLogger.logDebug("Timed out after waiting timer to finish countdown.");
		}
	}
	
	/**
	 * To transform date in String to Date object
	 * 
	 * @param value
	 * @param format
	 * @return Date
	 * @author
	 */
	public static Date stringToDate(String value, String format) {
		Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			date = dateFormat.parse(value);
		} catch (ParseException e) {
			String exceptionMessage = "stringToDate failed  : value is " + value + ", format is " + format;
			BFLogger.logDebug(exceptionMessage);
			throw new BFInputDataException(exceptionMessage);
		}
		return date;
	}
	
	/**
	 * To create date that represents some days before current day
	 * 
	 * @param days
	 * @return
	 * @author
	 */
	public static Date createDateXDaysBeforeCurrentDay(int days) {
		long DAY_IN_MS = 1000 * 60 * 60 * 24;
		Date date = new Date(System.currentTimeMillis() - (days * DAY_IN_MS));
		return date;
	}
	
	private static boolean isItWeekend(Calendar today) {
		int dayOfWeek = today.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek > Calendar.SUNDAY && dayOfWeek <= Calendar.FRIDAY;
	}
	
	private static Calendar getCurrentESTDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
		Date date = stringToDate(dateFormat.format(new Date()), format);
		Calendar currentDate = Calendar.getInstance(TimeZone.getTimeZone("EST"));
		currentDate.setTime(date);
		return currentDate;
	}
	
	/**
	 * @author
	 * @param dateTimeMiliseconds
	 *            preferably value of someDate.getTime() in milliseconds
	 * @param periodLength
	 *            length of period in milliseconds
	 * @return true if given date is older than the given period, false otherwise
	 *         <p>
	 *         Example of use -> is date Dec/12/2013 older than 3 years? <br>
	 *         In such case parameters will be date.getTime() - 1386802800000L and 3 years in milliseconds -
	 *         94670000000L
	 */
	public static boolean isDateOlderThanGivenPeriod(long dateTimeMiliseconds, long periodLength) {
		return (System.currentTimeMillis() - dateTimeMiliseconds > periodLength);
	}
	
	/**
	 * @author
	 * @param date1
	 *            preferably value of someDate1
	 * @param date2
	 *            preferably value of someDate2
	 * @return true if period between given dates is shorter or equal 3 years, false otherwise
	 */
	public static boolean isMax3YearsBetweenDates(Date date1, Date date2) {
		return Math.abs(date2.getTime() - date1.getTime()) <= 94670000000L;
	}
	
	/**
	 * @author
	 * @param dateEpoch
	 *            value of someDate
	 * @return true if period between given date and today is equal exactly 3 years, false otherwise
	 */
	public static boolean isPass3YearsSinceGivenDate(long dateEpoch) {
		return isPassXYearsSinceGivenDate(dateEpoch, 3);
	}
	
	/**
	 * @author
	 * @param dateEpoch
	 *            value of someDate
	 * @return true if period between given date and today is equal exactly 0, false otherwise
	 */
	public static boolean isTodayDate(long dateEpoch) {
		return isPassXYearsSinceGivenDate(dateEpoch, 0);
	}
	
	private static boolean isPassXYearsSinceGivenDate(long dateEpoch, int passYears) {
		DateTime currentDate = new DateTime();
		DateTime today = new DateTime(currentDate.getYear(), currentDate.getMonthOfYear(), currentDate.getDayOfMonth(),
						0, 0, 0, 0);
		DateTime date = new DateTime(dateEpoch * 1000L);
		Period period = new Period(date, today);
		int years = period.getYears();
		int months = period.getMonths();
		int weeks = period.getWeeks();
		int days = period.getDays();
		return (years == passYears && months == 0 && days == 0 && weeks == 0);
	}
	
	/**
	 * @return get current year as String
	 */
	public static String getCurrentYear() {
		Calendar today = Calendar.getInstance();
		BFLogger.logInfo(String.valueOf(today.get(Calendar.YEAR)));
		return String.valueOf(today.get(Calendar.YEAR));
	}
	
	/**
	 * @return get previous year as String
	 */
	public static String getPreviousYear() {
		Calendar today = Calendar.getInstance();
		return String.valueOf(today.get(Calendar.YEAR) - 1);
	}
	
	/**
	 * @return true if date is between January 1st and April 15th, false otherwise
	 */
	public static boolean isPreTaxDay() {
		Calendar today = Calendar.getInstance();
		if (today.get(Calendar.MONTH) < Calendar.APRIL)
			return true;
		if (today.get(Calendar.MONTH) == Calendar.APRIL && today.get(Calendar.DAY_OF_MONTH) < 15)
			return true;
		return false;
	}
	
	/**
	 * @author
	 * @return true if timestamp value is delayed (older than current time), false otherwise
	 */
	public static boolean isTimestampValueDelayed(By selectorTimestamp) {
		@SuppressWarnings("deprecation")
		WebElement timestampElement = BasePage.getDriver()
						.findElement(selectorTimestamp);
		String timestampText = timestampElement.getText();
		int timestampBeginIndex = timestampText.indexOf("AS OF") + 6;
		int timestampEndIndex = timestampText.indexOf("ET") - 1;
		String timestampValue = timestampText.substring(timestampBeginIndex, timestampEndIndex);
		String currentDate = getCurrentESTTimestamp("MM/dd/yyyy h:mm a");
		return !timestampValue.equals(currentDate);
	}
	
	/**
	 * Checks if specified time interval between two timestamps has passed
	 * 
	 * @param firstHour
	 *            HH:mm format
	 * @param secondHour
	 *            HH:mm format
	 * @param timePassedInMiliseconds
	 *            time in miliseconds
	 * @return true if difference between two timestamps is bigger than timePassedInMiliseconds
	 */
	public static boolean isTimePassedBetweenHours(String firstHour, String secondHour, long timePassedInMiliseconds) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date1, date2;
		try {
			date1 = format.parse(firstHour);
			date2 = format.parse(secondHour);
		} catch (ParseException e) {
			throw new BFComponentStateException("time difference", "calculate", "in wrong format");
		}
		long difference = date2.getTime() - date1.getTime();
		return difference >= timePassedInMiliseconds;
	}
	
	private static String getCurrentESTTimestamp(String format) {
		return getDateTimestamp(format, new Date());
	}
	
	private static String getDateTimestamp(String format, Date date) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		if (Calendar.getInstance()
						.getTimeZone() != TimeZone.getTimeZone("EST")) {
			dateFormat.setTimeZone(TimeZone.getTimeZone("EST"));
		}
		return dateFormat.format(date);
	}
	
	private static String getOnlyDataString(String format, Calendar calendar) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(calendar.getTime());
	}
	
	/**
	 * Check if now is after date (with time)
	 * 
	 * @param date
	 * @return true if now is after date from arg otherwise false
	 */
	public static boolean isAfter(Date date) {
		Date now = new Date();
		return now.after(date);
	}
}
