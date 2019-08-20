package com.lti.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Credential;
import com.lti.dto.Status;
import com.lti.entity.BidderDetails;
import com.lti.entity.FarmerDetails;
import com.lti.service.EmailService;
import com.lti.service.LoginService;

@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private SimpleMailMessage message;


	@PostMapping("/sendMailFarmer.lti")
	public void sendMailFarmer(@RequestBody int id) {
	 FarmerDetails fd  = emailService.fetchByFId(id);
	 System.out.println("Sending...");
	 message.setTo(fd.getEmail()); 
	 message.setSubject("Password");
	 message.setText(fd.getPassword()); 
	 mailSender.send(message);
	}
	
	@PostMapping("/sendMailBidder.lti")
	public void sendMailBidder(@RequestBody int id) {
	 BidderDetails bd  = emailService.fetchByBId(id);
	 System.out.println("Sending...");
	 message.setTo(bd.getEmail());
	 message.setSubject("Password");
	 message.setText(bd.getPassword()); 
	 mailSender.send(message);
	}

	@PostMapping("/forgetPassword.lti")
	public Status forgetPassword(@RequestBody Credential credentials) {
	 System.out.println(credentials.getAadhar());
	 System.out.println(credentials.getEmail());
	 Status obj = loginService.forget(credentials.getEmail(), credentials.getAadhar(), credentials.getRole());
	 return obj;
	}

}
