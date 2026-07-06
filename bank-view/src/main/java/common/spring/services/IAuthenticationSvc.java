package common.spring.services;

import org.springframework.security.core.Authentication;

public interface IAuthenticationSvc {
	Authentication getAuthentication();
}
