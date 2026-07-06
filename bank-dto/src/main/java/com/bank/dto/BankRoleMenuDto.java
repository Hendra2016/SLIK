package com.bank.dto;

import java.io.Serializable;

public class BankRoleMenuDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private int roleId;
	private int menuId;
	private String description;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}