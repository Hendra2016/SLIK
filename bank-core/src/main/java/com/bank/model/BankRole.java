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
@Table(name = "bank_role")
public class BankRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "role_id", nullable = false)
    private Integer roleId;
    @Column(name = "role_name", length = 50)
    private String roleName;

    public BankRole() {
    }

    public BankRole(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BankRole)) {
            return false;
        }
        BankRole other = (BankRole) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bank.model.BankRole[ roleId=" + roleId + " ]";
    }
    
}
