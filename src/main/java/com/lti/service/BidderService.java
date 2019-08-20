package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.Dao;
import com.lti.entity.BidderDetails;
import com.lti.entity.BiddingDetails;
import com.lti.entity.FarmerSellRequest;

@Service
public class BidderService {
	
	@Autowired
	private Dao dao;
	
	@Transactional
	public int addBidder(BidderDetails bidder) {
		BidderDetails bidderdetails = (BidderDetails) dao.save(bidder);
		return bidderdetails.getBidderId();
	}
	
	public List<BidderDetails> displayAllBidders() {
		return dao.fetchAll(BidderDetails.class);
	}
	@Transactional
	public List<FarmerSellRequest> currentBidDetails() {
		return dao.currentBidDetails();
	}
	
	@Transactional
	public int updateCurrentBid(BiddingDetails biddingDetails) {

		BiddingDetails bidDetails = (BiddingDetails) dao.save(biddingDetails);
		return bidDetails.getBiddingId();
	}

	@Transactional
	public List<FarmerSellRequest> fetchBidOverDetails() {
		return dao.fetchBidOverDetails();
	}
}
