package com.aurora.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

	@RequestMapping(value = "/401", method = RequestMethod.GET)
	public ModelAndView accesssDenied401(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you missing login to access this page!");
		} else {
			model.addObject("msg", "You must login to access this page!");
		}

		model.setViewName("errorpage/401");
		return model;

	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied403(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}

		model.setViewName("errorpage/403");
		return model;

	}

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public ModelAndView accesssDenied404(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", the page you looking dont not exist!");
		} else {
			model.addObject("msg", "the page you looking dont not exist!");
		}

		model.setViewName("errorpage/404");
		return model;

	}

	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public ModelAndView accesssDenied500(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ",  Uncaught exception error!");
		} else {
			model.addObject("msg", "Uncaught exception error!");
		}

		model.setViewName("errorpage/500");
		return model;

	}

	@RequestMapping(value = "/503", method = RequestMethod.GET)
	public ModelAndView accesssDenied503(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ",  Unsupported servlet method!");
		} else {
			model.addObject("msg", "Unsupported servlet method!");
		}

		model.setViewName("errorpage/503");
		return model;

	}
}
