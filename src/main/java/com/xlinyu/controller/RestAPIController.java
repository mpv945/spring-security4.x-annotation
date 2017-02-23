package com.xlinyu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xlinyu.model.User;
import com.xlinyu.service.UserService;

@RestController
public class RestAPIController {

	private static final Logger logger = LoggerFactory.getLogger(RestAPIController.class);
	
    @Autowired
    UserService userService;
	
	@GetMapping("/hello")
	public String say(){
		logger.info("restful api.");
		return "rest api";
	}
	
	@GetMapping("/u01")
	public String u01(){
		return "u01";
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
}
