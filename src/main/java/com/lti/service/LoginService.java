package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.Dao;
import com.lti.dto.Status;
import com.lti.entity.Admin;
import com.lti.entity.BidderDetails;
import com.lti.entity.FarmerDetails;

@Service
public class LoginService {

	@Autowired
	private Dao dao;

	public Status login(String email, String password, String role) {

		if (role.equals("farmer")) {

			FarmerDetails obj = dao.loginValidationFarmer(email, password);
			if (obj == null) {
				Status status = new Status();
				status.setMessage("Invalid User");
				return status;
			} else {
				Status status = new Status();
				status.setMessage("Valid Farmer");
				   status.setGeneratedId(obj.getFarmerId());
				   status.setTemp(obj.getName());
				return status;
			}
		} else if (role.equals("admin")) {

			Admin obj = dao.loginValidationAdmin(email, password);
			if (obj == null) {
				Status status = new Status();
				status.setMessage("Invalid User");
				return status;
			} else {
				Status status = new Status();
				status.setMessage("Valid Admin");
				  status.setGeneratedId(obj.getAdminId());
				  status.setTemp(obj.getName());
				return status;
			}
		} else {
			BidderDetails obj = dao.loginValidationBidder(email, password);
			if (obj == null) {
				Status status = new Status();
				status.setMessage("Invalid User");
				
				return status;
			} else {
				Status status = new Status();
				status.setMessage("Valid Bidder");
				  status.setGeneratedId(obj.getBidderId());
				  status.setTemp(obj.getName());
				return status;
			}
		}
	}
	public Status forget(String email, String aadhar, String role) {
		System.out.println(role);
		 if (role.equals("farmer")) {

		  FarmerDetails obj = dao.forgetValidationFarmer(email, aadhar);
		  if (obj == null) {
		   Status status = new Status();
		   status.setMessage("Invalid User");
		   return status;
		  } else {
		   Status status = new Status();
		   status.setMessage("Valid Farmer");
		   status.setGeneratedId(obj.getFarmerId());
		   status.setTemp(obj.getName());
		   return status;
		  }
		 } else if(role.equals("bidder")){
		  BidderDetails obj = dao.forgetValidationBidder(email, aadhar);
		  if (obj == null) {
		   Status status = new Status();
		   status.setMessage("Invalid User");

		   return status;
		  } else {
		   Status status = new Status();
		   status.setMessage("Valid Bidder");
		   status.setGeneratedId(obj.getBidderId());
		   status.setTemp(obj.getName());
		   return status;
		  }
		 }
		 return null;
		}

}
