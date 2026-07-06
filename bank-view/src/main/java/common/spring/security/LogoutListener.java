package common.spring.security;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;

import common.util.JsonUtil;

public class LogoutListener implements ApplicationListener<SessionDestroyedEvent> {

	public void onApplicationEvent(SessionDestroyedEvent event) {
		List<SecurityContext> lstSecurityContext = event.getSecurityContexts();
		UserDetails ud;
		for (SecurityContext securityContext : lstSecurityContext) {
			ud = (UserDetails) securityContext.getAuthentication().getPrincipal();
			System.out.println("Data Session destroy = "+JsonUtil.getJson(ud));
		}
	}

}
