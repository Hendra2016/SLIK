package com.bank.controller;

import com.bank.dto.AuthRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bank.dto.RestResponse;
import com.bank.services.BankLoginSvc;

@RestController
@RequestMapping("/login")
public class BankLoginCtl {

	@Autowired
	private BankLoginSvc bankLoginSvc;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public RestResponse loginUser(@RequestBody AuthRequestDto request) {
		RestResponse restRestponse = new RestResponse();
		try {
			restRestponse.setContents(bankLoginSvc.getLoginUser(request.getUsername(), request.getPassword()));
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
