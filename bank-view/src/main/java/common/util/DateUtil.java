//package common.util;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.Period;
//import java.util.Date;
//
///**
// *
// * @author Hendra Dotjang
// */
//public class DateUtil {
//	public static final String DATE_FORMAT = "yyyy-MM-dd";
//	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd  HH:mm:ss";
//
//	public static SimpleDateFormat getFormatter(String format) {
//		return new SimpleDateFormat(format);
//	}
//
//	public static Date getMinDate() {
//		return stringToDate("1900-1-1");
//	}
//
//	public static Date getMaxDate() {
//		return stringToDate("4712-12-31");
//	}
//
//	public static Date stringToDate(String strDate) {
//		try {
//			return getFormatter(DATE_FORMAT).parse(strDate);
//		} catch (ParseException ex) {
//			return null;
//		}
//	}
//
//	public static Date stringToDateTime(String strDate) {
//		try {
//			return getFormatter(DATE_TIME_FORMAT).parse(strDate);
//		} catch (ParseException ex) {
//			return null;
//		}
//	}
//
//	public static String dateTimeToString(Date date) {
//		return getFormatter(DATE_TIME_FORMAT).format(date);
//	}
//
//	public static String dateToString(Date date) {
//		return getFormatter(DATE_FORMAT).format(date);
//	}
//
//	public static int getDaysFromPeriod(Period p) {
//		return p.getDays();
//	}
//
//	public static int getMonthsFromPeriod(Period p) {
//		return p.getMonths();
//	}
//
//	public static int getYearsFromPeriod(Period p) {
//		return p.getYears();
//	}
//
//	// public static void main(String[] args){
//	// DateTime start = new DateTime(2004, 12, 25, 20, 30, 0, 0);
//	// DateTime end = new DateTime(2006, 1, 2, 0, 0, 0, 0);
//	// String tanggal = "30.12.2012 20:55";
//	// System.out.println(stringToDateTime(tanggal));
//	// System.out.println(dateTimeToString(convertToJdkFormat(start)));
//	// System.out.println(dateTimeToString(getMaxDate()));
//	// // period of 1 year and 7 days
//	// Period period = new Period(start, end);
//	// System.out.println(period.getYears() + "--" + period.getMonths() + "--" +
//	// period.getDays());
//	//
//	// System.out.println(getDaysBetween(getSpecificDate(2014, 3, 1, 10, 0, 0),
//	// getSpecificDate(2014, 4, 10, 10, 0, 0)));
//	// }
//}
