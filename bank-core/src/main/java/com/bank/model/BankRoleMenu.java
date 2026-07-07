/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.model;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Hendra Dotjang
 */
@Entity
@Table(name = "bank_role_menu")
public class BankRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BankRoleMenuPK bankRoleMenuPK;
    @Column(name = "description", length = 50)
    private String description;

    public BankRoleMenu() {
    }

    public BankRoleMenu(BankRoleMenuPK bankRoleMenuPK) {
        this.bankRoleMenuPK = bankRoleMenuPK;
    }

    public BankRoleMenu(int roleId, int menuId) {
        this.bankRoleMenuPK = new BankRoleMenuPK(roleId, menuId);
    }

    public BankRoleMenuPK getBankRoleMenuPK() {
        return bankRoleMenuPK;
    }

    public void setBankRoleMenuPK(BankRoleMenuPK bankRoleMenuPK) {
        this.bankRoleMenuPK = bankRoleMenuPK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bankRoleMenuPK != null ? bankRoleMenuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BankRoleMenu)) {
            return false;
        }
        BankRoleMenu other = (BankRoleMenu) object;
        if ((this.bankRoleMenuPK == null && other.bankRoleMenuPK != null) || (this.bankRoleMenuPK != null && !this.bankRoleMenuPK.equals(other.bankRoleMenuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bank.model.BankRoleMenu[ bankRoleMenuPK=" + bankRoleMenuPK + " ]";
    }
    
}
