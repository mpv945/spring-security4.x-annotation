package com.xlinyu.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@GetMapping(value = "/signin")
	public String signin(){
		logger.info("login page.");
		return "account/signin";
	}
	
	@GetMapping(value = "/403")
	public String accesssDenied(Principal user, Model model){
		if (user != null) {
			model.addAttribute("msg", "Hi 【 " + user.getName() + "】, you do not have permission to access this page!");
		} else {
			model.addAttribute("msg", "You do not have permission to access this page!");
		}
		return "error/403";
	}
}
