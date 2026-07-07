package com.bank.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.CommonConstants;
import com.bank.dto.BankUserDto;
import com.bank.dto.RestResponse;
import com.bank.services.BankUserSvc;

@RestController
@RequestMapping("/bankuser")
public class BankUserCtl {

	@Autowired
	private BankUserSvc bankUserSvc;

	@RequestMapping(value = "all/{pageSequence}/{size}", method = RequestMethod.GET)
	public RestResponse getBankUserPaging(@PathVariable("pageSequence") int pageSequence,
			@PathVariable("size") int size, @RequestParam(CommonConstants.SEARCH) String search,
			@RequestParam(CommonConstants.DIRECTION) String direction,
			@RequestParam(CommonConstants.ORDER_BY) String orderBy) {
		if (size == 0) {
			return new RestResponse(CommonConstants.ERROR_REST_STATUS, "SIZE DATA MUST BE GREATER THAN 0", null);
		}
		try {
			Map<String, Object> map = bankUserSvc.getBankUserPaging(search, pageSequence, size, direction, orderBy);
			return new RestResponse(CommonConstants.OK_REST_STATUS, null, map.get("contentData"),
					(Long) map.get("totalRecords"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new RestResponse(CommonConstants.ERROR_REST_STATUS, "Error", null);
		}
	}

	@RequestMapping(value = "{akadref}", method = RequestMethod.GET)
	public RestResponse getOneBankUser(@PathVariable("akadref") Integer akadref) {
		RestResponse restRestponse = new RestResponse();
		try {
			restRestponse.setContents(bankUserSvc.findOneBankUser(akadref));
			restRestponse.setStatus(CommonConstants.OK_REST_STATUS);
			restRestponse.setMessage("Berhasil");
		} catch (Exception e) {
			System.out.println(e);
			restRestponse.setStatus(CommonConstants.ERROR_REST_STATUS);
			restRestponse.setMessage("Gagal " + e);
		}
		return restRestponse;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public RestResponse saveBankUser(@RequestBody BankUserDto BankUserDto) {
		RestResponse restRestponse = new RestResponse();
		try {
			bankUserSvc.save(BankUserDto);
			restRestponse.setStatus(CommonConstants.OK_REST_STATUS);
			restRestponse.setMessage("Berhasil");
		} catch (Exception e) {
			restRestponse.setStatus(CommonConstants.ERROR_REST_STATUS);
			restRestponse.setMessage("Gagal " + e);
		}
		return restRestponse;
	}

	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public RestResponse deleteBankUser(@RequestBody Integer BankUserDto) {
		RestResponse restRestponse = new RestResponse();
		try {
			bankUserSvc.delete(BankUserDto);
			restRestponse.setStatus(CommonConstants.OK_REST_STATUS);
			restRestponse.setMessage("Berhasil");
		} catch (Exception e) {
			restRestponse.setStatus(CommonConstants.ERROR_REST_STATUS);
			restRestponse.setMessage("Gagal " + e);
		}
		return restRestponse;
	}

}
