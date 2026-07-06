package common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.Years;

/**
 *
 * @author Hendra Dotjang
 */
public class DateUtil {
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";
	
    
    public static SimpleDateFormat getFormatter(String format){
        return new SimpleDateFormat(format);
    }
    
    public static DateTime convertToJodaFormat(Date jdkDate){
        return new DateTime(jdkDate);
    }
    
    public static Date convertToJdkFormat(DateTime jodaDate){
        return jodaDate.toDate();
    }
    
    public static Date getMinDate(){
        return stringToDate("1/1/1900");
    }
    
    public static Date getMaxDate(){
        return stringToDate("31/12/4712");
    }
    
    public static Date stringToDate(String strDate){
        try {
            return getFormatter(DATE_FORMAT).parse(strDate);
        } catch (ParseException ex) {
            return null;
        }
    }
    
    public static Date stringToDateTime(String strDate){
        try {
            return getFormatter(DATE_TIME_FORMAT).parse(strDate);
        } catch (ParseException ex) {
            return null;
        }
    }
    
    public static String dateTimeToString(Date date){
        return getFormatter(DATE_TIME_FORMAT).format(date);
    }
    
    public static String dateToString(Date date){
        return getFormatter(DATE_FORMAT).format(date);
    }
    
    public static Date addDate(Date date, int number){
        return convertToJdkFormat(convertToJodaFormat(date).plusDays(number));
    }
    
    public static Date addMonth(Date date, int number){
        return convertToJdkFormat(convertToJodaFormat(date).plusMonths(number));
    }
    
    public static Date addYear(Date date, int number){
        return convertToJdkFormat(convertToJodaFormat(date).plusYears(number));
    }
        
    public static Date getSpecificDate(int year, int month, int date, int hour, int min, int sec){
        return convertToJdkFormat(new DateTime(year, month, date, hour, min, sec));            
    }
    
    /**
     * Truncate date (remove time format from date)
     * @param date
     * @return
     */
    public static Date trunc(Date date){
    	return convertToJdkFormat(new DateTime(getYear(date), getMonth(date), getDate(date), 0, 0, 0));
    }
    
    public static int getYear(Date date){
        return convertToJodaFormat(date).getYear();
    }
    
    public static int getMonth(Date date){
        return convertToJodaFormat(date).getMonthOfYear();
    }
    
    public static int getDate(Date date){
        return convertToJodaFormat(date).getDayOfMonth();
    }
    
    public static int getDaysBetween(Date startDate, Date endDate){        
        Days days = Days.daysBetween(convertToJodaFormat(startDate), convertToJodaFormat(endDate));
        return days.getDays();
    }        
    
    public static int getMonthsBetween(Date startDate, Date endDate){
        Months months = Months.monthsBetween(convertToJodaFormat(startDate), convertToJodaFormat(endDate));
        return months.getMonths();
    }
    
    public static int getYearsBetween(Date startDate, Date endDate){
        Years years = Years.yearsBetween(convertToJodaFormat(startDate), convertToJodaFormat(endDate));
        return years.getYears();
    }
    
    public static int getHoursBetween(Date startDate, Date endDate){
        Hours hours = Hours.hoursBetween(convertToJodaFormat(startDate), convertToJodaFormat(endDate));
        return hours.getHours();
    }
    
    public static int getMinutesBetween(Date startDate, Date endDate){
        Minutes min = Minutes.minutesBetween(convertToJodaFormat(startDate), convertToJodaFormat(endDate));
        return min.getMinutes();
    }
    
    public static Period getPeriodBetween(Date startDate, Date endDate){
        return new Period(convertToJodaFormat(startDate), convertToJodaFormat(endDate));
    }
    
    public static int getDaysFromPeriod(Period p){
        return p.getDays();
    }
    
    public static int getMonthsFromPeriod(Period p){
        return p.getMonths();
    }
    
    public static int getYearsFromPeriod(Period p){
        return p.getYears();
    }
    
//    public static void main(String[] args){
//        DateTime start = new DateTime(2004, 12, 25, 20, 30, 0, 0);
//        DateTime end = new DateTime(2006, 1, 2, 0, 0, 0, 0);
//        String tanggal = "30.12.2012 20:55";
//        System.out.println(stringToDateTime(tanggal));
//        System.out.println(dateTimeToString(convertToJdkFormat(start)));
//        System.out.println(dateTimeToString(getMaxDate()));
//        // period of 1 year and 7 days
//        Period period = new Period(start, end);
//        System.out.println(period.getYears() + "--" + period.getMonths() + "--" + period.getDays());
//        
//        System.out.println(getDaysBetween(getSpecificDate(2014, 3, 1, 10, 0, 0), getSpecificDate(2014, 4, 10, 10, 0, 0)));
//    }
}
