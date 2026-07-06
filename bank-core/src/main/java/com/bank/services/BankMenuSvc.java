package com.bank.services;

import java.util.List;

import com.bank.dto.MenuDto;


/**
 *
 * @author Hendra
 */
public interface BankMenuSvc {
	public List<MenuDto> getMenuByUser(String username, int roleId);
}