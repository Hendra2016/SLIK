package common.util.exel.util;

import java.util.Collection;

public class CollectionUtils {

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Collection collection) {
		return collection == null || collection.isEmpty();
	}

	public static boolean isEmpty(Object[] object) {
		return object == null || object.length < 1;
	}
}
