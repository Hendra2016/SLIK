package common.util;

import java.math.RoundingMode;

public class CommonConstants {
	public static final int OK_REST_STATUS = 0;
	public static final int ERROR_REST_STATUS = 1;
	
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
	
	public static final String PREFIX_URI_PAGING = "/pq";
	public static final String PREFIX_URI_WS = "/q";
	public static final String BPM_URI_WS = "/bpm";
	
	public static final int SCALE = 2;
	public static final RoundingMode ROUNDING_MODE_PERSON = RoundingMode.CEILING;
}