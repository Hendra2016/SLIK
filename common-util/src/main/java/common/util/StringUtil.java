package common.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for String data type.
 * 
 * @author Leo Sendra
 */
public class StringUtil {

	/**
	 * Add n character on left side of word.
	 * 
	 * @param word
	 *            word string to be added
	 * @param addedChar
	 *            character to be added to word
	 * @param totalFinalChars
	 *            total character to be added
	 * @return full word with added character
	 */
	public static String addCharToLeft(String word, char addedChar, int totalFinalChars) {
		for (int n = word.length() + 1; n <= totalFinalChars; n++) {
			word = addedChar + word;
		}

		return word;
	}

	/**
	 * Add n character on right side of word.
	 * 
	 * @param word
	 *            word string to be added
	 * @param addedChar
	 *            character to be added to word
	 * @param totalFinalChars
	 *            total character to be added
	 * @return full word with added character
	 */
	public static String addCharToRight(String word, char addedChar, int totalFinalChars) {
		for (int n = word.length() + 1; n <= totalFinalChars; n++) {
			word = word + addedChar;
		}

		return word;
	}

	/**
	 * Convert a value into default / other value. Conversion happen if checked
	 * value is null or empty.
	 * 
	 * @param val
	 *            checked / to be converted value
	 * @param defVal
	 *            replacement value if checked value is null / empty
	 * @return result
	 */
	public static String nevl(String val, String defVal) {
		if (val == null)
			return defVal;

		if (val.isEmpty())
			return defVal;

		return val;
	}

	/**
	 * Add another string in front and behind a string
	 * 
	 * @param word
	 *            String to be added
	 * @param added
	 *            added string
	 * @return
	 */
	public static String surroundString(String word, String added) {
		return added + word + added;
	}

	/**
	 * Replace multiple spaces with single space
	 * 
	 * @param word
	 * @return
	 */
	public static String eliminateSpace(String word) {
		return word.trim().replaceAll(" +", " ");
	}

	public static Integer getIntegerValue(String data) {
		try {
			return Integer.valueOf(data);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date getDateValue(String data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		try {
			return sdf.parse(data);
		} catch (Exception e) {
			return null;
		}
	}

	public static BigDecimal getBigDecimalValue(String data) {
		try {
			return new BigDecimal(data);
		} catch (Exception e) {
			return null;
		}
	}

}