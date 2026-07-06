package com.bank.services.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.bank.dto.UserDto;
import com.bank.model.BankRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.dao.BankLoginDao;
import com.bank.dao.BankRoleDao;
import com.bank.dao.BankUserDao;
import com.bank.dto.RoleDto;
import com.bank.model.BankLogin;
import com.bank.model.BankUser;
import com.bank.services.BankLoginSvc;

import common.util.Crypto;
import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Hendra
 */
@Service("bankLoginSvcImpl")
@Transactional
public class BankLoginSvcImpl implements BankLoginSvc {

	@Autowired
	private BankLoginDao loginDao;

	@Autowired
	private BankRoleDao bankRoleDao;

	@Autowired
	private BankUserDao userDao;

	@Autowired
	private MapperFacade mapperFacade;

	public UserDto getLoginUser(String username, String password) throws Exception {
		List<BankUser> user = userDao.getUserLogin(username, password);
		UserDto dto = new UserDto();
		if (!user.isEmpty()) {
			if (user.get(0) != null && user.get(0).getRoleId() >= 0) {
				String token = Crypto.performEncrypt(new Timestamp(new Date().getTime()).toString());
				BankLogin login = new BankLogin();
				login.setLoginTime(new Timestamp(new Date().getTime()));
				login.setToken(token);
				login.setUserId(dto.getUserId());
				loginDao.save(login);
				dto = mapperFacade.map(user.get(0), UserDto.class);
				Optional<BankRole> bankRoleOptional = bankRoleDao.findById(user.get(0).getRoleId());
				if(bankRoleOptional.isPresent()) {
                    dto.getAuthorities().add(mapperFacade.map(bankRoleOptional.get(), RoleDto.class));
					dto.setToken(token);
				}else {
					throw new Exception("Role not found for user with roleId: " + user.get(0).getRoleId());
				}
			}
		}else {
			throw new Exception("User not found with username: " + username);
		}

		return dto;
	}

}