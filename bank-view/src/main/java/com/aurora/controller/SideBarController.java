package com.aurora.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bank.CommonConstants;
import com.bank.dto.MenuDto;
import com.bank.dto.RestResponse;
import com.bank.tree.RODTreeNode;

import common.spring.global.BaseController;
import common.spring.security.LoginData;
import common.util.JsonUtil;

@Controller
public class SideBarController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(SideBarController.class);

	@RequestMapping(value = "/sidebar", method = RequestMethod.GET)
	public ModelAndView returnMenu() {
		ModelAndView mv = new ModelAndView("fragments/sidebar");
		LoginData login = getLoginData();
		RestResponse rest = new RestResponse();
		if (login.getAuthorities().size() > 0) {
			settingMenu(rest, mv, login.getUsername(), login.getAuthorities().get(0).getRoleId());
		}
		return mv;
	}

	protected final String SEC_SECURE = "secure-admin";

	private void settingMenu(RestResponse rest, ModelAndView mv, String username, int role) {
		if (username.equals(SEC_SECURE)) {
			// Waiting to be implemented
		} else {
			rest = callWsBank("menu/" + username + "/" + role, null, HttpMethod.GET);
			if (rest.getStatus() == CommonConstants.OK_REST_STATUS) {
				if (rest.getStatus() == CommonConstants.OK_REST_STATUS) {
					try {
						List<MenuDto> dto = JsonUtil.mapJsonToListObject(rest.getContents(), MenuDto.class);
						RODTreeNode<MenuDto> root = new RODTreeNode<MenuDto>(null);
						for (MenuDto m : dto) {
							if (m.getParentId() == 0) {
								root = new RODTreeNode(m, new ArrayList<MenuDto>());
							} else {
								MenuDto b = recurseSearch(root, m);
								if (b != null) {
									root.getData().appendChild(b);
								}
							}
						}
						for (MenuDto os : root.getData().getChildren()) {
							for (MenuDto is : dto) {
								if (is.getParentId() == os.getId()) {
									os.appendChild(is);
								}
							}
						}
						mv.addObject("menus", root);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					mv.addObject("menus", new ArrayList<MenuDto>());
				}
			}
		}

	}

	private MenuDto recurseSearch(RODTreeNode<MenuDto> root, MenuDto menuParent) {
		if (root.getData().getId().equals(menuParent.getParentId())) {
			return menuParent;
		}
		MenuDto res = null;
		if (root.getChildren() != null) {
			for (RODTreeNode<MenuDto> fn : root.getChildren()) {
				res = recurseSearch(fn, menuParent);
			}
		}
		return res;
	}
}
