package com.capgemini.mrchecker.selenium.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.capgemini.mrchecker.test.core.logger.BFLogger;

/**
 * This class contains utility functions related to date (matching, conversion etc.).
 * 
 * @author
 */
public class DateUtils {
	
	private DateUtils() {
	}
	
	// 12 hours format with AM/PM
	public static final String TIME_12H_REGEX = "(1[012]|[1-9]):[0-5][0-9](\\s)?(?i) (AM|PM)";
	// MM/DD/YYYY regexp
	public static final String DATE_REGEX = "(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)";
	// timestamp with 'AS OF' prefix regexp: AS OF MM/DD/YYYY HH:MM A/PM ET
	public static final String AS_OF_TIMESTAMPT_REGEX = "(AS OF )" + DateUtils.DATE_REGEX + " "
			+ DateUtils.TIME_12H_REGEX + "( ET)";
	public static final String COMMON_DATE_REGEX = "\\d{2}/\\d{2}/\\d{4}";
	public static final String US_DATE_FORMAT = "MM/dd/yyyy";
	public static final String[] MOTHS = new String[] { "January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" };
	
	private static final String HTTP_RESPONSE_DATE_REGEX = "(\\w+,\\s\\d{1,2}\\s\\w+\\s\\d{4}\\s\\d{2}:\\d{2}:\\d{2}\\s\\w{2,6})";
	private static final String HTTP_RESPONSE_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss zzz";
	
	/**
	 * Matches date with MM/DD/YYYY pattern
	 * 
	 * @param date
	 */
	public static boolean matchDate(String date) {
		return DateUtils.matchDate(date, DATE_REGEX);
	}
	
	/**
	 * Matches date with MM/DD/YYYY pattern
	 * 
	 * @param date
	 */
	public static boolean matchDate(String date, String regex) {
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(date)
				.matches();
	}
	
	public static boolean areDatesOrdered(List<Date> dates, Utility.SortOrder sortOrder) {
		if (dates.isEmpty()) {
			throw new IllegalArgumentException("Dates list can't be empty");
		}
		Date prevElDate = dates.get(0);
		for (Date elDate : dates) {
			if (prevElDate.compareTo(elDate) == sortOrder.param) {
				// dates are not ordered correctly
				BFLogger.logDebug("Dates are not sorted in " + StringUtils.lowerCase(sortOrder.toString())
						+ " order. First date: " + prevElDate + " second date: " + elDate);
				return false;
			}
			prevElDate = elDate;
		}
		return true;
	}
	
	/**
	 * Converts String representing month (first three letters of month name i.e Jan, Feb and so on) to number from 1 to
	 * 12
	 * 
	 * @author
	 * @param String
	 *            representing month (first three letters of month name i.e Jan, Feb and so on)
	 * @return number from 0 to 11
	 * @throws ParseException
	 */
	public static int monthToNumber(String month) throws ParseException {
		Date date = null;
		Calendar cal;
		date = new SimpleDateFormat("MMM").parse(month);
		cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}
	
	/**
	 * Converts String representing date in following format: MM/DD/YYYY to Calendar object
	 * 
	 * @author
	 * @param String
	 *            representing date in following format: MM/DD/YYYY
	 * @return Calendar object representing date given as a param
	 * @throws ParseException
	 */
	public static Calendar convertStringToCalendar(String date) throws ParseException {
		SimpleDateFormat formater = new SimpleDateFormat(US_DATE_FORMAT);
		Date formattedDate = formater.parse(date);
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(formattedDate);
		return calendar;
	}
	
	public static Calendar convertDateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	public static String getCurrentDateInUsFormat() {
		return getCurrentDate(US_DATE_FORMAT);
	}
	
	public static String getCurrentDate(String dateFormatStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		return dateFormat.format(new Date());
	}
	
	/**
	 * Check if given string contains date in format dd/mm/yyyy
	 * 
	 * @param toCheck
	 * @return true if string contains date, false otherwise
	 */
	public static boolean containsDate(String toCheck) {
		String datePattern = COMMON_DATE_REGEX;
		Pattern p = Pattern.compile(datePattern);
		Matcher matcher = p.matcher(toCheck);
		return matcher.find();
	}
	
	public static int compareDatesWithoutTime(Date date1, Date date2) {
		Calendar c1 = convertDateToCalendar(date1);
		Calendar c2 = convertDateToCalendar(date2);
		if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR))
			return c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
		if (c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH))
			return c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
		return c1.get(Calendar.DAY_OF_MONTH) - c2.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * @param text
	 *            to check for date
	 * @return first dd/mm/yyyy date found in the text
	 * @throws ParseException
	 */
	public static Calendar getFirstDateFromString(String text) throws ParseException {
		Matcher matcher = Pattern.compile(COMMON_DATE_REGEX)
				.matcher(text);
		matcher.find();
		return convertStringToCalendar(matcher.group());
	}
	
}
