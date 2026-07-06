package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.RestResponse;
import com.bank.services.BankLoginSvc;

@RestController
@RequestMapping("/login")
public class BankLoginCtl {

	@Autowired
	private BankLoginSvc bankLoginSvc;

	@RequestMapping(value = "/{username}/{password}", method = RequestMethod.GET)
	public RestResponse loginUser(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		RestResponse restRestponse = new RestResponse();
		try {
			restRestponse.setContents(bankLoginSvc.getLoginUser(username, password));
			restRestponse.setStatus(1);
			restRestponse.setMessage("Berhasil");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			restRestponse.setStatus(0);
			restRestponse.setMessage("Gagal " + e);
		}
		return restRestponse;
	}

}
