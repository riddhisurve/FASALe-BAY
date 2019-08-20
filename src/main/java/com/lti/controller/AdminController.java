package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Status;
import com.lti.entity.FarmerSellRequest;
import com.lti.service.AdminService;
import com.lti.service.BidderService;

@RestController
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private BidderService bidderService;

	@PostMapping("/requestApproved.lti")
	public Status requestApproved(@RequestBody int requestId) {
		adminService.requestApproved(requestId);
		Status status = new Status();
		status.setMessage("Request added!");
		status.setGeneratedId(requestId);
		return status;
	}

	
	@PostMapping("/updateBidApproval.lti")
	public String updateBidApproval(@RequestBody int sellReqId) {

		return adminService.updateBidApproval(sellReqId);
	}

	@PostMapping("/requestUnapproved.lti")
	public Status requestUnapproved(@RequestBody int requestId) {
		adminService.requestUnapproved(requestId);
		Status status = new Status();
		status.setMessage("Request rejected!");
		status.setGeneratedId(requestId);
		return status;
	}

	@GetMapping("/bidApprovePage.lti")
	public List<FarmerSellRequest> fetchBidOverDetails() {
		return bidderService.fetchBidOverDetails();

	}
}
