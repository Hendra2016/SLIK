package common.spring.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class DetailSource extends WebAuthenticationDetailsSource {

	@Override
	public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
		return new Detail(context);
	}
	
	class Detail extends WebAuthenticationDetails {

		public Detail(HttpServletRequest request) {
			super(request);
		}
	}

}
