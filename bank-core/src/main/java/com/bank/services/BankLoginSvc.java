package com.bank.services;


import com.bank.dto.UserDto;

/**
 *
 * @author Hendra
 */
public interface BankLoginSvc {
	UserDto getLoginUser(String username, String password) throws Exception;
}