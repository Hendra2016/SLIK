package common.util.exel.converter;

import java.math.BigDecimal;

public class IntegerTypeConverter implements TypeConverter<Integer> {

	public Integer convert(Object value, String... pattern) {
		if (value == null) {
			return null;
		}

		if (value instanceof Integer) {
			return (Integer) value;
		}

		if (value instanceof String) {
			try {
				return Integer.valueOf(((String) value).trim());
			} catch (Exception ex) {
				return null;
			}
		}

		if (value instanceof BigDecimal) {
			return ((BigDecimal) value).intValue();
		}

		return null;
	}

}
