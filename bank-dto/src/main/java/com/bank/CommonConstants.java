package com.bank;

import java.math.RoundingMode;

public class CommonConstants {

	// LOCALHOST
	@Deprecated
	public static final String WS_URL = "http://localhost:8082/bank-core/";

	public static final int OK_REST_STATUS = 1;
	public static final int ERROR_REST_STATUS = 0;

	public static final String USER_BUTTON = "0";
	public static final String MENU_BUTTON = "1";

	public static final String ASC = "asc";
	public static final String DESC = "desc";
	public static final String PARAM = "param";

	public static final String PAGE = "page";
	public static final String SIZE = "size";
	public static final String TOKEN = "token";
	public static final String SEARCH = "search";
	public static final String DIRECTION = "direction";
	public static final String ORDER_BY = "orderBy";

	public static final int SCALE = 2;
	public static final RoundingMode ROUNDING_MODE_PERSON = RoundingMode.CEILING;
}