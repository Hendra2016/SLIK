package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.model.BankRole;

public interface BankRoleDao extends JpaRepository<BankRole, Integer> {

}
