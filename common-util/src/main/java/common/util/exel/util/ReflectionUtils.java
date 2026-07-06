package common.util.exel.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import common.util.exel.annotation.Column;
import common.util.exel.converter.TypeConverter;
import common.util.exel.converter.TypeConverters;

public class ReflectionUtils {

	private static String toUpperCaseFirstCharacter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setValueOnField(Object instance, Field field, Object value) throws Exception {
		Class clazz = instance.getClass();
		String setMethodName = "set" + toUpperCaseFirstCharacter(field.getName());

		for (Map.Entry<Class, TypeConverter> entry : TypeConverters.getConverter().entrySet()) {
			if (field.getType().equals(entry.getKey())) {
				Method method = clazz.getDeclaredMethod(setMethodName, entry.getKey());
				Column column = field.getAnnotation(Column.class);

				method.invoke(instance, entry.getValue().convert(value, column == null ? null : column.pattern()));
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static void eachFields(Class clazz, EachFieldCallback callback) throws Throwable {
		Field[] fields = clazz.getDeclaredFields();
		if (!CollectionUtils.isEmpty(fields)) {
			for (Field field : fields) {
				callback.each(field, field.isAnnotationPresent(Column.class) ? field.getAnnotation(Column.class).name()
						: field.getName());
			}
		}
	}
}
