package common.util.exel.converter;

import java.math.BigDecimal;

public class DoubleTypeConverter implements TypeConverter<Double> {

	public Double convert(Object value, String... pattern) {
		if (value == null) {
			return null;
		}

		if (value instanceof Double) {
			return (Double) value;
		}

		if (value instanceof String) {
			try {
				return Double.valueOf(((String) value).trim());
			} catch (Exception ex) {
				return null;
			}
		}

		if (value instanceof BigDecimal) {
			return ((BigDecimal) value).doubleValue();
		}

		return null;
	}

}
