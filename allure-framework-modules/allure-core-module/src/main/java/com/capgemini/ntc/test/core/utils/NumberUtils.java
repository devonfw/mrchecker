package com.capgemini.ntc.test.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class contains utility functions related to numeric operation (getting random numbers, conversion to numbers,
 * comparison etc.).
 * 
 * @author
 */
public class NumberUtils {
	
	private NumberUtils() {
	}
	
	public static final String DOLLAR_REGEX = "\\$(?:0|[1-9]\\d{0,2}(?:,?\\d{3})*)(\\.\\d{2})";
	public static final BigDecimal delta = new BigDecimal("0.02");
	
	/**
	 * Checks if input text is a amount and returns value of it (ie. -$23.15 --> -23.15) Warning if given textValue is
	 * null or empty return 0
	 * 
	 * @param textValue
	 * @return BigDecimal object with 2 decimal points precision
	 */
	public static BigDecimal getValueFromStringAmount(String text) {
		BigDecimal value = BigDecimal.ZERO;
		if (text.matches("^[-+]?\\+?\\$\\d+.*"))
			value = NumberUtils.convertTextValueToDecimal(text);
		return value;
	}
	
	/**
	 * Removes '$' and ',' marks from passed textValue and convert to BigDecimal object Warning if given textValue is
	 * null or empty return 0
	 * 
	 * @param textValue
	 * @return BigDecimal object with 2 decimal points precision
	 */
	public static BigDecimal convertTextValueToDecimal(String textValue) {
		if (textValue == null || textValue.isEmpty())
			return BigDecimal.ZERO;
		String textToConvert = textValue.replaceAll("\\$|\\,|\\%|\\(Margin\\)|\\#|\\(|\\)|\\‡|\\§|\\/Share|\\+*$|\\n|\\r|(--)|\\Short", "");
		if (textToConvert.isEmpty())
			return BigDecimal.ZERO;
		try {
			return new BigDecimal(textToConvert).setScale(2, BigDecimal.ROUND_FLOOR);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Cannot convert [" + textToConvert + "].");
		}
	}
	
	/**
	 * Removes '$' and ',' marks from passed textValue and convert to BigDecimal object Warning if given textValue is
	 * null or empty return 0
	 * 
	 * @param textValue
	 * @return BigDecimal object with 2 decimal points precision
	 */
	public static BigDecimal[] convertTextValueToDecimal(final String[] textValues) {
		return convertTextValueToDecimal(Arrays.asList(textValues));
	}
	
	/**
	 * Removes '$' and ',' marks from passed textValue and convert to BigDecimal object Warning if given textValue is
	 * null or empty return 0
	 * 
	 * @param textValue
	 * @return BigDecimal object with 2 decimal points precision
	 */
	public static BigDecimal[] convertTextValueToDecimal(final Iterable<String> textValues) {
		List<BigDecimal> currencies = new ArrayList<BigDecimal>();
		for (String textValue : textValues) {
			BigDecimal value = convertTextValueToDecimal(textValue);
			currencies.add(value);
		}
		return currencies.toArray(new BigDecimal[currencies.size()]);
	}
	
	/**
	 * Checks if given text is representing a numerical value (can be negative value, and decimals)
	 * 
	 * @param textValue
	 * @return true when is numerical; false otherwise
	 */
	public static boolean isNumeric(String textValue) {
		// match a number with optional '-' and decimal.
		return textValue.matches("-?(\\$)?(\\d+)+(,\\d+)*(\\.\\d+)?");
	}
	
	/**
	 * Checks if given text contain two decimal places
	 * 
	 * @param textValue
	 * @return boolean value
	 */
	public static boolean isTwoDecimalPlaces(String textValue) {
		// any_symbol-dot-number-number pattern
		return textValue.matches(".*\\.\\d{2}$?");
	}
	
	/**
	 * Tells if difference between two BigDecimal values is major, e.g. between balance of group of account received
	 * from page and second value received from summing it sub-accounts Warning: Check if accepted difference factor
	 * meets requirements
	 * 
	 * @param oneValue
	 *            - comparised value
	 * @param secondValue
	 *            - comparised value
	 * @return true if difference is major; false otherwise;
	 */
	public static boolean isDifferenceMajor(BigDecimal oneValue, BigDecimal secondValue) {
		final BigDecimal acceptedDifference = BigDecimal.ONE;
		BigDecimal absDifference = oneValue.subtract(secondValue)
				.abs();
		if (absDifference.compareTo(acceptedDifference) <= 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * This function returns a pseudorandom, uniformly distributed int value between 0 (inclusive) and the specified
	 * value (exclusive) - <0, seed)
	 * 
	 * @param seed
	 *            initial value for the random generator
	 */
	public static int getRandomInteger(int seed) {
		Random rnd = new Random();
		return rnd.nextInt(seed);
	}
	
	/**
	 * To check is single value as String is correctly formated. That is should contains 2 decimal places
	 * 
	 * @param percent
	 * @return true/false
	 * @author
	 */
	public static boolean isPercentRoundedTo2decimalPlaces(String percent) {
		String pr = percent.replaceAll("%", "")
				.replaceAll(",", "")
				.trim();
		BigDecimal precentAsBD = new BigDecimal(pr);
		BigDecimal decimalPart = precentAsBD.subtract(precentAsBD.setScale(0, RoundingMode.FLOOR))
				.movePointRight(precentAsBD.scale());
		return decimalPart.intValue() < 100;
	}
	
	/**
	 * Checks if input text is a value, converts it and returns value as String (ie. 1.1460001 --> 1.15 - with precision
	 * = 2). If given text is null or empty returns 0 with precision (ie 0.00)
	 * 
	 * @param number
	 *            as text
	 * @param precision
	 * @return String with desired decimal points precision
	 */
	public static String convertNumberToXDecimalPoints(String number, int precision) {
		try {
			return new BigDecimal(number == null || number.isEmpty() ? "0" : number).setScale(precision, BigDecimal.ROUND_HALF_UP)
					.toString();
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Cannot convert [" + number + "].");
		}
	}
	
	/**
	 * Checks if input text is a value, converts it and returns value as String (ie. 1.1460001 --> 1.15 - with precision
	 * = 2). If given text is null or empty returns 0 with precision (ie 0.00)
	 * 
	 * @param numbersList
	 *            as text list
	 * @param precision
	 * @return list of String with desired decimal points precision
	 */
	public static List<String> convertNumberToXDecimalPoints(List<String> numbersList, int precision) {
		for (int i = 0; i < numbersList.size(); ++i) {
			numbersList.set(i, convertNumberToXDecimalPoints(numbersList.get(i), precision));
		}
		return numbersList;
	}
	
	/**
	 * Check if two BigDecimal numbers differs
	 * 
	 * @return true if numbers differs more than precision
	 */
	public static boolean isBigDecimalDifferent(BigDecimal f1, BigDecimal f2) {
		return isBigDecimalDifferent(f1, f2, delta);
	}
	
	/**
	 * Check if two BigDecimal numbers differs with specified precision
	 * 
	 * @return true if numbers differs more than precision
	 */
	static public boolean isBigDecimalDifferent(BigDecimal f1, BigDecimal f2, BigDecimal delta) {
		if (f1.compareTo(f2) == 0) {
			return false;
		}
		if (f1.subtract(f2)
				.abs()
				.compareTo(delta) <= 0) {
			return false;
		}
		return true;
	}
}
