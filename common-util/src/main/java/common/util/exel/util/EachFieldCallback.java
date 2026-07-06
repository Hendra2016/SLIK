package common.util.exel.util;

import java.lang.reflect.Field;

public interface EachFieldCallback {

	void each(Field field, String name) throws Throwable;
}
