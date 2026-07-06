package com.aurora.controller.master;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bank.CommonConstants;
import com.bank.dto.BankUserDto;
import com.bank.dto.RestResponse;
import common.spring.data.Paging;
import common.spring.global.BaseController;
import common.util.JsonUtil;

@Controller
public class BankUserController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(BankUserController.class);
	private static String BANKUSER = "bankUser/";
	private BankUserDto bankUserDto;
	private List<BankUserDto> bankUserDtos = new ArrayList<>();

	@RequestMapping(value = "bankUser", method = RequestMethod.GET)
	public ModelAndView indexBankUser(Authentication authentication) {
		if (!authentication.isAuthenticated()) {
			logger.info("not Authenticated");
		}
		ModelAndView mv = new ModelAndView("master/bankuser/index");
		mv.addObject("pagingUrl", "bankUser/render");
		mv.addObject("user", authentication.getPrincipal());
		return mv;
	}

	@RequestMapping(value = "bankUser/add", method = RequestMethod.GET)
	public ModelAndView onAdd(Authentication authentication) {
		ModelAndView mv = new ModelAndView("master/bankuser/detail");
		mv.addObject("bankUser", new BankUserDto());
		mv.addObject("title", "Tambah User");
		mv.addObject("url", "bankUser/add");
		return mv;
	}

	@RequestMapping(value = "bankUser/update/{id}", method = RequestMethod.GET)
	public ModelAndView onUpdate(Authentication authentication, @PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("master/bankuser/detail");
		RestResponse rest = callWsBank(BANKUSER + id, null, HttpMethod.GET);
		if (rest.getStatus() == CommonConstants.OK_REST_STATUS) {
			try {
				setBankUserDto(JsonUtil.mapJsonToSingleObject(rest.getContents(), BankUserDto.class));
				mv.addObject("bankUser", getBankUserDto());
				mv.addObject("url", "bankUser/update");
				mv.addObject("title", "Ubah User");
				return mv;
			} catch (Exception e) {
				e.printStackTrace();
				return new ModelAndView("bankUser");
			}
		}
		return new ModelAndView("bankUser");
	}

	@RequestMapping(value = "bankUser/add", method = RequestMethod.POST)
	public ModelAndView add(@ModelAttribute("bankUser") BankUserDto bankUserDto) {
		ModelAndView mv = new ModelAndView("redirect:/bankUser");
		RestResponse rest = callWsBank(BANKUSER + "save", bankUserDto, HttpMethod.POST);
		if (rest.getStatus() == CommonConstants.OK_REST_STATUS) {
			return mv;
		}
		return mv;
	}

	@RequestMapping(value = "bankUser/delete/{id}", method = RequestMethod.POST)
	public @ResponseBody boolean delete(@PathVariable("id") String id) {
		RestResponse rest = callWsBank(BANKUSER + "delete", id, HttpMethod.POST);
		if (rest.getStatus() == CommonConstants.OK_REST_STATUS) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "bankUser/update", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute("bankUser") BankUserDto bankUserDto) {
		ModelAndView mv = new ModelAndView("redirect:");
		bankUserDto.setUserId(getBankUserDto().getUserId());
		RestResponse rest = callWsBank(BANKUSER + "save", bankUserDto, HttpMethod.POST);
		if (rest.getStatus() == CommonConstants.OK_REST_STATUS) {
			return mv;
		}

		return mv;
	}

	@RequestMapping(value = "bankUser/render", method = RequestMethod.GET)
	public @ResponseBody Paging renderBankUser(@RequestParam(name = "pageNumber", required = false) Integer activePage,
			@RequestParam(name = "search[value]", required = false) String search) {
		setSearch(search);
		setActivePage(activePage);
		Paging page = new Paging();
		RestResponse rest = callWsWithPaging(BANKUSER + "all", null, HttpMethod.GET);
		if (rest.getStatus() == CommonConstants.OK_REST_STATUS) {
			try {
				List<BankUserDto> o = JsonUtil.mapJsonToListObject(rest.getContents(), BankUserDto.class);
				if (o != null) {
					page.setData(o);
				} else {
					page.setData(new ArrayList<BankUserDto>());
				}
				page.setRecordsTotal(rest.getTotalRecords());
				page.setRecordsFiltered(rest.getTotalRecords());
				return page;
			} catch (Exception e) {
				e.printStackTrace();
				return new Paging();
			}
		}
		return new Paging();
	}

	public BankUserDto getBankUserDto() {
		return bankUserDto;
	}

	public void setBankUserDto(BankUserDto bankUserDto) {
		this.bankUserDto = bankUserDto;
	}

	public List<BankUserDto> getBankUserDtos() {
		return bankUserDtos;
	}

	public void setBankUserDtos(List<BankUserDto> bankUserDtos) {
		this.bankUserDtos = bankUserDtos;
	}

}
