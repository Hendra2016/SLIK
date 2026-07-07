package common.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.bank.dto.AuthRequestDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;

import com.bank.CommonConstants;
import com.bank.dto.RestResponse;

import common.spring.global.BaseController;
import common.util.JsonUtil;

@Controller
public class AuthController extends BaseController implements AuthenticationProvider {
	protected final Log logger = LogFactory.getLog(this.getClass());
	protected final String SEC_SECURE = "secure-admin";

	public Authentication authenticate(Authentication authentication) {
		logger.info("invoke : additionalAuthenticationChecks");
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();

		if (password == null || password.isEmpty() || password.equals(""))
			throw new BadCredentialsException("password tidak boleh kosong");
		if (username.equals(SEC_SECURE) && password.equals(getMessage("sec.secure.password"))) {
			LoginData login = new LoginData();
			login.setEmail("secure");
			login.setUsername(SEC_SECURE);
			login.setUserId(99);
			List<StpRoleDto> l = new ArrayList<StpRoleDto>();
			StpRoleDto o = new StpRoleDto();
			o.setRoleId(99);
			o.setRoleName(SEC_SECURE);
			l.add(o);
			login.setAuthorities(l);
			Collection<? extends GrantedAuthority> authorities = login.getAuthorities();
			return new UsernamePasswordAuthenticationToken(login, password, authorities);
		} else {
			LoginData login;
			try {
				login = loadUserByUsername(username, password);
			}catch(HttpClientErrorException e){
				System.out.println(e.getMessage());
				throw new BadCredentialsException("Something Error");
			}
			if (login != null) {
				Collection<? extends GrantedAuthority> authorities = login.getAuthorities();
				return new UsernamePasswordAuthenticationToken(login, password, authorities);
			}
		}
		throw new BadCredentialsException("User not found");
	}

	public LoginData loadUserByUsername(String username, String password) {
		AuthRequestDto authRequestDto = new AuthRequestDto();
		authRequestDto.setUsername(username);
		authRequestDto.setPassword(password);
		RestResponse rest = callWsBank("login", authRequestDto, HttpMethod.POST);
		if (rest.getStatus() == CommonConstants.OK_REST_STATUS) {
			try {
				LoginData login = JsonUtil.mapJsonToSingleObject(rest.getContents(), LoginData.class);
				return login;
			} catch (Exception e) {
				e.printStackTrace();
				throw new UsernameNotFoundException("User Not Found!!");
			}
		}
		throw new UsernameNotFoundException("User Not Found");
	}

	public boolean supports(Class<?> arg0) {
		logger.info("invoke : supports");
		return arg0.equals(UsernamePasswordAuthenticationToken.class);
	}

}