package com.bank.services;

import java.util.List;
import java.util.Map;

import com.bank.dto.BankUserDto;

/**
 *
 * @author Hendra
 */
public interface BankUserSvc {
	public List<BankUserDto> getUsers();

	public Map<String, Object> getBankUserPaging(String search, int pageSequence, int size, String direction,
			String orderBy);

	public BankUserDto findOneBankUser(Integer userId);

	public void save(BankUserDto BankUserDto);

	public void delete(Integer BankUserDto);
}