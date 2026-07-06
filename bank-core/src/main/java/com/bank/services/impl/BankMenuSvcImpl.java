package com.bank.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.dao.BankMenuDao;
import com.bank.dto.MenuDto;
import com.bank.services.BankMenuSvc;

/**
 *
 * @author Hendra
 */
@Service("bankMenuSvcImpl")
@Transactional
public class BankMenuSvcImpl implements BankMenuSvc {

	@Autowired
	private BankMenuDao bankMenuDao;

	// @Autowired
	// private MapperFacade mapperFacade;

	@Override
	public List<MenuDto> getMenuByUser(String username, int roleId) {
		List<MenuDto> result = new ArrayList<MenuDto>();
		List<Object[]> menu = (bankMenuDao.getMenuParent(roleId));
		for (Object[] m : menu) {
			MenuDto dto = new MenuDto();
			dto.setId((int) m[0]);
			dto.setMenuName((String) m[1]);
			dto.setMenuUrl((String) m[2]);
			dto.setParentId((int) m[4]);
			dto.setMenuType((String) m[3]);
			result.add(dto);
		}
		return result;

	}

}