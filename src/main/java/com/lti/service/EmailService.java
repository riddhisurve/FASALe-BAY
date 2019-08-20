package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.Dao;
import com.lti.entity.BidderDetails;
import com.lti.entity.FarmerDetails;

@Service
public class EmailService {
	
	@Autowired
	private Dao dao;

	
	public FarmerDetails fetchByFId(int id) {
		return dao.fetchById(FarmerDetails.class, id);
	}

	public BidderDetails fetchByBId(int id) {
		return dao.fetchById(BidderDetails.class, id);
	}

}
