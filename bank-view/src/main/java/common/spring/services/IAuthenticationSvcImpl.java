package common.spring.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class IAuthenticationSvcImpl implements IAuthenticationSvc {

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
