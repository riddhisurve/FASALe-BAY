package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.GenericDao;
import com.lti.dto.Status;
import com.lti.entity.Admin;
import com.lti.entity.BidderDetails;
import com.lti.entity.FarmerDetails;

@Service
public class LoginService {

	@Autowired
	private GenericDao dao;

	public Status login(String email, String password, String role) {

		if (role.equals("farmer")) {

			FarmerDetails obj = dao.loginValidationFarmer(email, password);
			if (obj == null) {
				Status status = new Status();
				status.setMessage("Invalid User");
				return status;
			} else {
				Status status = new Status();
				status.setMessage("Valid User");
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
				status.setMessage("Valid User");
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
				status.setMessage("Valid User");
				return status;
			}
		}
	}
}
