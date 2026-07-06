package common.util.exel.converter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTypeConverter implements TypeConverter<Date> {

	public Date convert(Object value, String... pattern) {
		if (value == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		if (value instanceof Timestamp) {
			calendar.setTimeInMillis(((Timestamp) value).getTime());
		} else if (value instanceof Date) {
			calendar.setTimeInMillis(((Date) value).getTime());
		} else if (value instanceof String) {
			try {
				return new SimpleDateFormat(pattern[0]).parse((String) value);
			} catch (Exception ex1) {
				return null;
			}
		} else {
			return null;
		}

		return calendar.getTime();
	}

}
