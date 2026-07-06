package com.bank.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.dao.BankUserDao;
import com.bank.dto.BankUserDto;
import com.bank.model.BankUser;
import com.bank.services.BankUserSvc;

import common.util.Paging;
import ma.glasnost.orika.MapperFacade;

/**
 *
 * @author Hendra
 */
@Service("bankUserSvcImpl")
@Transactional
public class BankUserSvcImpl implements BankUserSvc {

	Paging paging = new Paging();
	@Autowired
	private BankUserDao bankUserDao;

	@Autowired
	private MapperFacade mapperFacade;

	@Override
	public Map<String, Object> getBankUserPaging(String search, int pageSequence, int size, String direction,
			String orderBy) {
		search = (search.equals("") ? "%%" : search);
		if (orderBy == null || orderBy.isEmpty() || orderBy.equals("true"))
			orderBy = "userId";

		Page<BankUser> page = bankUserDao.getAllBankUserPaging("%" + search + "%",
				paging.getPageable(pageSequence, size, direction, orderBy));
		Map<String, Object> map = new HashMap<>();
		map.put("totalRecords", page.getTotalElements());
		if (page.getContent().size() > 0) {
			map.put("contentData", mapperFacade.mapAsList(page.getContent(), BankUserDto.class));
			return map;
		}

		map.put("contentData", null);
		return map;
	}

	@Override
	public BankUserDto findOneBankUser(Integer cntcod) {
		return bankUserDao.findById(cntcod)
				.map(user -> mapperFacade.map(user, BankUserDto.class))
				.orElse(null);
	}

	@Override
	public void save(BankUserDto cntrefDto) {
		bankUserDao.save(mapperFacade.map(cntrefDto, BankUser.class));
	}

	@Override
	public void delete(Integer id) {
		bankUserDao.deleteById(id);
	}

	@Override
	public List<BankUserDto> getUsers() {
		return mapperFacade.mapAsList(bankUserDao.findAll(), BankUserDto.class);
	}
}