package com.aurora.controller;
//
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		logger.debug("index()");
		return "redirect:/login";
	}


	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView showAllUsers(Authentication authentication) {
		if(!authentication.isAuthenticated()){
			logger.info("not Authenticated");
		}
		ModelAndView mv = new ModelAndView("fragments/content");
		mv.addObject("user", authentication.getPrincipal());
		return mv;
	}

}
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import common.spring.data.Paging;
//
//@Controller
//public class HomeController {
//
//	private final Logger logger = LoggerFactory.getLogger(HomeController.class);
//
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String index() {
//		logger.debug("index()");
//		return "home";
//	}
//
//	@RequestMapping(value = "/home", method = RequestMethod.GET)
//	public ModelAndView showAllUsers(Authentication authentication) {
//		ModelAndView mv = new ModelAndView("home");
//		return mv;
//	}
//
//	@RequestMapping(value = "hpm/render", method = RequestMethod.GET)
//	public @ResponseBody Paging renderAkadref(@RequestParam(name = "pageNumber", required = false) Integer activePage,
//			@RequestParam(name = "search[value]", required = false) String search) {
//		final int currentPage = activePage == null ? 0 : activePage;
//		logger.debug("renderAkadref pageNumber={}, search={}", currentPage, search);
//		Paging page = new Paging();
//		List<Test> as = new ArrayList<>();
//		for (int i = 0; i < 10; i++) {
//			Test a = new Test();
//			a.setCoba("Coba " + i);
//			a.setData("Data " + i);
//			as.add(a);
//		}
//		page.setData(as);
//		page.setRecordsTotal(as.size());
//		page.setRecordsFiltered(as.size());
//		return page;
//	}
//
//
//}
