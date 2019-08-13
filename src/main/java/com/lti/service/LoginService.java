package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.GenericDao;


@Service
public class LoginService {

	
	@Autowired
	private GenericDao dao; 
	
	public String farmerLogin(String email,String password,String role) {
		
		if(role.equals("farmer")) { 
			return dao.loginValidationFarmer(email,password);
		}
		else if(role.equals("admin")) {
			return dao.loginValidationAdmin(email,password);
		}
		else
			return dao.loginValidationBidder(email,password);
	}
	}


