/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Hendra Dotjang
 */
@Embeddable
public class BankRoleMenuPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "role_id", nullable = false)
    private int roleId;
    @Basic(optional = false)
    @Column(name = "menu_id", nullable = false)
    private int menuId;

    public BankRoleMenuPK() {
    }

    public BankRoleMenuPK(int roleId, int menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) roleId;
        hash += (int) menuId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BankRoleMenuPK)) {
            return false;
        }
        BankRoleMenuPK other = (BankRoleMenuPK) object;
        if (this.roleId != other.roleId) {
            return false;
        }
        if (this.menuId != other.menuId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bank.model.BankRoleMenuPK[ roleId=" + roleId + ", menuId=" + menuId + " ]";
    }
    
}
