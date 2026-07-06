package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.model.BankLogin;

public interface BankLoginDao extends JpaRepository<BankLogin, Integer> {

	@Query("select a.loginId from BankLogin a where a.token=:token ")
	int validateToken(@Param("token") String token);
}
