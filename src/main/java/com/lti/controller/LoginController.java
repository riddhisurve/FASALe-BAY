package com.lti.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Credential;

import com.lti.dto.Status;

import com.lti.service.LoginService;

@RestController
public class LoginController {
	

	@Autowired
	private LoginService loginService;
	

	@PostMapping("/login.lti")
	public Status loginValidation(@RequestBody Credential credentials) {
		Status obj = loginService.login(credentials.getEmail(), credentials.getPassword(), credentials.getRole());
		return obj;
	}



}
