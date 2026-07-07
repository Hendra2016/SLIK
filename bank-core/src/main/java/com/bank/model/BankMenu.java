/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 *
 * @author Hendra Dotjang
 */
@Entity
@Table(name = "bank_menu")
public class BankMenu implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "menu_id", nullable = false)
	private Integer menuId;
	@Column(name = "menu_name", length = 50)
	private String menuName;
	@Column(name = "menu_url", length = 50)
	private String menuUrl;
	@Column(name = "menu_type", length = 50)
	private String menuType;
	@Column(name = "parent_id")
	private Integer parentId;

	public BankMenu() {
	}

	public BankMenu(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (menuId != null ? menuId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// not set
		if (!(object instanceof BankMenu)) {
			return false;
		}
		BankMenu other = (BankMenu) object;
		if ((this.menuId == null && other.menuId != null)
				|| (this.menuId != null && !this.menuId.equals(other.menuId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.bank.model.BankMenu[ menuId=" + menuId + " ]";
	}

}
