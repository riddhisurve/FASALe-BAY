package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.CurrentBid;
import com.lti.dto.Status;
import com.lti.entity.BidderDetails;
import com.lti.entity.BiddingDetails;
import com.lti.entity.FarmerSellRequest;
import com.lti.service.BidderService;

@RestController
public class BidderController {
	@Autowired
	private BidderService bidderService;

	@PostMapping("/addBidder.lti")
	public Status add(@RequestBody BidderDetails bidder) {
		int bidderId = bidderService.addBidder(bidder);
		Status status = new Status();
		status.setMessage("Bidder added!");
		status.setGeneratedId(bidderId);
		return status;
	}

	@PostMapping("/displayAllBidderDetails.lti")
	public List<BidderDetails> displayAllBidders() {
		return bidderService.displayAllBidders();
	}

	@GetMapping("/bidPage.lti")
	public List<FarmerSellRequest> fetchCurrentBidDetails() {
		return bidderService.currentBidDetails();

	}

	@PostMapping("/updateCurrentBid.lti")
	public Status add(@RequestBody CurrentBid currentBid) {
		BiddingDetails bd = new BiddingDetails();
		bd.setBidAmount(currentBid.getCurrentBid());
		
		FarmerSellRequest fsr = new FarmerSellRequest();
		fsr.setSellRequestId(currentBid.getCropId());
		bd.setFarmerSellRequest(fsr);
		BidderDetails bdr = new BidderDetails();
		bdr.setBidderId(currentBid.getBidderId());
		bd.setBidderDetails(bdr);
		
		int biddingId = bidderService.updateCurrentBid(bd);
		Status status = new Status();
		status.setGeneratedId(biddingId);
		status.setMessage("Bidding Successfull!");
		return status;
	}

}