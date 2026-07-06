package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.RestResponse;
import com.bank.services.BankMenuSvc;

@RestController
@RequestMapping("/menu")
public class BankMenuCtl {

	@Autowired
	private BankMenuSvc bankMenuSvc;

	@RequestMapping(value = "/{username}/{roleId}", method = RequestMethod.GET)
	public RestResponse getMenu(@PathVariable("username") String username,
			@PathVariable("roleId") Integer  roleId) {
		RestResponse restRestponse = new RestResponse();
		try {
			restRestponse
					.setContents(bankMenuSvc.getMenuByUser(username, roleId));
			restRestponse.setStatus(1);
			restRestponse.setMessage("Berhasil");
		} catch (Exception e) {
			restRestponse.setStatus(0);
			restRestponse.setMessage("Gagal " + e);
		}
		return restRestponse;
	}

}
