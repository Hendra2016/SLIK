package common.spring.global;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import com.bank.CommonConstants;
import com.bank.dto.RestResponse;
import common.spring.security.LoginData;
import common.spring.services.IAuthenticationSvc;
import common.util.JsonUtil;

public class BaseController {

	private final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	private HttpServletRequest request;

	protected final String LOGIN = "login/";

	@Autowired
	private MessageSource messagesSource;

	@Autowired
	private IAuthenticationSvc iAuthenticationSvc;

	@Value("${service.bank-core.url:http://localhost:8082/bank-core/}")
	private String bankCoreBaseUrl;

	// FOR PAGING
	private Integer pageSize = 10;

	private Integer activePage = 0; // current active page index
	private long totalSize = 0; // total all records
	protected String orderBy = ""; // order by entity model column
	protected String currentOrderDirection = CommonConstants.ASC; // desc / asc
																	// order
	private String search;

	private boolean showAdvSearch = false;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getActivePage() {
		return activePage;
	}

	public void setActivePage(Integer activePage) {
		this.activePage = activePage;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getCurrentOrderDirection() {
		return currentOrderDirection;
	}

	public void setCurrentOrderDirection(String currentOrderDirection) {
		this.currentOrderDirection = currentOrderDirection;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public boolean isShowAdvSearch() {
		return showAdvSearch;
	}

	public void setShowAdvSearch(boolean showAdvSearch) {
		this.showAdvSearch = showAdvSearch;
	}

	protected RestResponse callWsWithPaging(String uri, Object sentObject, HttpMethod httpMethod) {
		String searchParm = "";
		if (getSearch() == null) {
			search = "";
		}
		String orderByParm = "";
		int current = activePage;
		searchParm = "&" + CommonConstants.SEARCH + "=" + search;
		orderByParm = "&" + CommonConstants.ORDER_BY + "=" + orderBy;
		String url = getBankCoreBaseUrl() + uri + "/" + current + "/" + getPageSize() + "?"
				+ CommonConstants.DIRECTION + "=" + currentOrderDirection + searchParm + orderByParm;
		return executeWebService(url, sentObject, httpMethod);
	}

	protected RestResponse callWs(String host, String uri, Object sentObject, HttpMethod httpMethod) {
		String url = host + uri;
		return executeWebService(url, sentObject, httpMethod);
	}

	protected RestResponse callWsBank(String uri, Object sentObject, HttpMethod httpMethod) {
		String url = getBankCoreBaseUrl() + uri;
		return executeWebService(url, sentObject, httpMethod);
	}

	private String getBankCoreBaseUrl() {
		if (bankCoreBaseUrl == null || bankCoreBaseUrl.trim().isEmpty()) {
			return "http://localhost:8082/bank-core/";
		}
		return bankCoreBaseUrl.endsWith("/") ? bankCoreBaseUrl : bankCoreBaseUrl + "/";
	}

	private RestResponse executeWebService(String url, Object sentObject, HttpMethod httpMethod) {
		url = url.replace("\\", "");
		logger.info("JSON Object : {} ", JsonUtil.getJson(sentObject));
		logger.info("Invoke web service with URL : {}", url);
		RestTemplate restTemplate = new RestTemplate();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			headers.set("Authorization", "Bearer "+getLoginData().getToken());
		}catch(Exception e){
			System.out.println("catch "+e.getMessage());
		}
		final HttpEntity<Object> requestEntity = new HttpEntity<Object>(sentObject, headers);
		final ResponseEntity<RestResponse> reponseEntity = restTemplate.exchange(url, httpMethod, requestEntity,
				RestResponse.class);

		return reponseEntity.getBody();
	}

	protected LoginData getLoginData() {
		try {
			return (LoginData) iAuthenticationSvc.getAuthentication().getPrincipal();
		} catch (Exception e) {
			throw new BadCredentialsException("Login Terlebih Dahulu");
		}
	}

	protected String getUsername() {
		return getLoginData().getUsername();
	}

	protected Integer getUserId() {
		return getLoginData().getUserId();
	}

	protected String getPassword() {
		return getLoginData().getPassword();
	}

	protected String getMessage(String code) {
		return messagesSource.getMessage(code, null, null);
	}

	protected String getMessage(String code, Object[] obj) {
		return messagesSource.getMessage(code, obj, null);
	}

	protected String getMessage(String code, Object[] obj, Locale local) {
		return messagesSource.getMessage(code, obj, local);
	}

	protected String getIpAddress() {
		return request.getRemoteAddr();
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public String handleException(Exception e) {
		return e.getMessage();
	}
}
