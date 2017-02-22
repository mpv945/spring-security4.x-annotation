package com.xlinyu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAPIController {

	private static final Logger logger = LoggerFactory.getLogger(RestAPIController.class);
	
	@GetMapping("/hello")
	public String say(){
		logger.info("restful api.");
		return "rest api";
	}
	
}
