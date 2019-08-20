package com.lti.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.Dao;
import com.lti.entity.BiddingDetails;
import com.lti.entity.FarmerDetails;
import com.lti.entity.FarmerSellRequest;

@Service
public class AdminService {
	
	@Autowired
	private Dao dao;

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private SimpleMailMessage message;

	
	@Transactional
	public int requestApproved(int requestId) {
		BiddingDetails bd = new BiddingDetails();
		FarmerSellRequest insertFarmerIdFSR = dao.fetchById(FarmerSellRequest.class, requestId);
		bd.setBidAmount(insertFarmerIdFSR.getBasePrice());
		bd.setFarmerSellRequest(insertFarmerIdFSR);

		insertFarmerIdFSR.setStatus(1);
		insertFarmerIdFSR.setDateTime(LocalDateTime.now());

		LocalDateTime startDateTime = insertFarmerIdFSR.getDateTime();
		int duration = insertFarmerIdFSR.getDuration();
		LocalDateTime endDateTime = startDateTime.plusDays(duration);
		insertFarmerIdFSR.setEndDateTime(endDateTime);

		dao.save(insertFarmerIdFSR);
		dao.save(bd);
		return requestId;
	}
	
	@Transactional
	public String updateBidApproval(int sellReqId) {
		double sprice = dao.getMaxBid(sellReqId);
		FarmerSellRequest fsr = dao.fetchById(FarmerSellRequest.class, sellReqId);
		fsr.setSoldPrice(sprice);
		fsr.setSold(1);
		FarmerDetails farmer=dao.fetchById(FarmerDetails.class, fsr.getFarmerDetails().getFarmerId());
		 message.setTo(farmer.getEmail()); //set a proper recipient of the mail
		 message.setSubject("Request Status");
		 message.setText("BID SUCCESS with price of "+sprice+" for crop Sell Request Id "+sellReqId+" CHECK YOUR ACCOUNT!!!"); 
		 mailSender.send(message);
		dao.save(fsr);
		return "Approved";
	}
	@Transactional
	public int requestUnapproved(int requestId) {

		FarmerSellRequest insertFarmerIdFSR = dao.fetchById(FarmerSellRequest.class, requestId);
		insertFarmerIdFSR.setStatus(-1);
		insertFarmerIdFSR.setDateTime(LocalDateTime.now());

		LocalDateTime startDateTime = insertFarmerIdFSR.getDateTime();
		int duration = insertFarmerIdFSR.getDuration();
		LocalDateTime endDateTime = startDateTime.plusDays(duration);
		insertFarmerIdFSR.setEndDateTime(endDateTime);

		dao.save(insertFarmerIdFSR);
		return requestId;
	}

}
