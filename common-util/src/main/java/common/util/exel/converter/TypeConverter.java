package common.util.exel.converter;

public interface TypeConverter<T> {

	T convert(Object value, String... pattern);
}
