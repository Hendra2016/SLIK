package com.bank.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.model.BankUser;

public interface BankUserDao extends JpaRepository<BankUser, Integer> {

	@Query(value = "select a  " + "from BankUser a " + "where (UPPER(a.email) LIKE UPPER(:search) "
			+ "or UPPER(a.username) LIKE UPPER(:search)  ) ")
	Page<BankUser> getAllBankUserPaging(@Param("search") String search, Pageable page);

	@Query("select u from BankUser u where u.username=:username and u.password=:password")
	List<BankUser> getUserLogin(@Param("username") String username, @Param("password") String password);

}
