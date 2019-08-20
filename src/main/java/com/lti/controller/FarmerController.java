package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Status;
import com.lti.entity.FarmerDetails;
import com.lti.entity.FarmerSellRequest;
import com.lti.service.FarmerService;


@RestController
public class FarmerController {
	
	@Autowired
	private FarmerService farmerService;


	@PostMapping("/addFarmer.lti")
	public Status addFarmer(@RequestBody FarmerDetails farmer) {
		int farmerId = farmerService.addFarmer(farmer);
		Status status = new Status();
		status.setMessage("Farmer added!");
		status.setGeneratedId(farmerId);
		return status;
	}

	@GetMapping("/dispRequest.lti")
	public List<FarmerSellRequest> dispRequest() {
		return farmerService.displayRequest();
	}

	int requestId;

	@PostMapping("/addFarmerSellRequest.lti")
	public Status addFarmerSellRequest(@RequestBody FarmerSellRequest fsr) {

		requestId = farmerService.addFarmerSellRequest(fsr);

		System.out.println("farmerSellID : " + requestId);

		Status status = new Status();
		status.setMessage("Request added!");
		status.setGeneratedId(requestId);
		return status;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	@PostMapping("/setFarmerIDToFSR.lti")
	public int setFarmerIDToFSR(@RequestBody int farmerId) {

		int req = getRequestId();

		farmerService.assignFarmerIdToFSR(req, farmerId);
		return req;
	}

	@GetMapping("/listAll.lti")
	public List<FarmerSellRequest> listAll(@RequestParam("id") int requestId) {

		return farmerService.listAll(requestId);
	}

	@GetMapping("/listHistory.lti")
	public List<FarmerSellRequest> listHistory(@RequestParam("fid") int fid) {
		System.out.println(fid);
		return farmerService.listHistory(fid);
	}

	@PostMapping("/displayAllFarmerDetails.lti")
	public List<FarmerDetails> displayAllFarmers() {
		return farmerService.displayAllFarmers();
	}


	@PostMapping("/fetchAllFarmerSellRequest.lti")
	public List<FarmerSellRequest> fetchAll() {
		List<FarmerSellRequest> list = farmerService.listAll1();
		for (FarmerSellRequest x : list)
			System.out.println(x.getCropType());
		return list;
	}
	
	/* For view Marketplace */
	@GetMapping("/approvedCropDetails.lti")
	public List<FarmerSellRequest> approvedCropDetails(@RequestParam("id") int farmerId) {
		return farmerService.approvedCropDetails(farmerId);

	}
	/* After farmer clicks stop bidding */
	@PostMapping("/stopBidding.lti")
	public Status stopBidding(@RequestBody int requestId) {
		farmerService.stopBidding(requestId);
		Status status = new Status();
		status.setMessage("Request added!");
		status.setGeneratedId(requestId);
		return status;
	}

	@GetMapping("viewRequestStatus.lti")
	public List<FarmerSellRequest> viewRequestStatus(@RequestParam("fid") int fid) {
		System.out.println(fid);
		return farmerService.viewRequestStatus(fid);
	}
	
}
