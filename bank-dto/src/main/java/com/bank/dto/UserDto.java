package com.bank.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
	private int userId;
	private String email;
	private String password;
	private String username;
	private List<RoleDto> authorities = new ArrayList<RoleDto>();
	private String token;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<RoleDto> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<RoleDto> authorities) {
		this.authorities = authorities;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
