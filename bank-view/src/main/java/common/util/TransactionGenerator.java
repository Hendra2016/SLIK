/**
 * 
 */
package common.util;

import java.util.Date;
import java.util.UUID;


/**
 * @author arydewo
 *
 */
public class TransactionGenerator {
	private static long lastDate;
	private static long counterID;

	public String getUUID() {
		return UUID.randomUUID().toString();
	}

	public static synchronized String getTrxIDInternal() {
		String tmpDateTime = ""+new Date().getTime();
		if (lastDate == Long.parseLong(tmpDateTime)) {
			counterID++;
		} else {
			lastDate = Long.parseLong(tmpDateTime);
			counterID = 1;
		}
		return tmpDateTime + getStringLeftPad(String.valueOf(counterID), "0", 8);
	}

	public static String getStringLeftPad(String inputStr, String padStr, int padLength) {
		String outputStr = "";
		String padTmpStr = "";
		int inputLength = inputStr.length();
		for (int i = 0; i < padLength - inputLength; i++) {
			padTmpStr = padTmpStr + padStr;
		}
		outputStr = padTmpStr + inputStr;
		return outputStr;
	}

	public static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}

	public static String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s);
	}

}
