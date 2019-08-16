package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Credential;
import com.lti.dto.CurrentBid;
import com.lti.dto.Status;
import com.lti.entity.BidderDetails;
import com.lti.entity.BiddingDetails;
import com.lti.entity.FarmerDetails;
import com.lti.entity.FarmerSellRequest;
import com.lti.service.GenericService;
import com.lti.service.LoginService;

@RestController
public class GenericController {

	@Autowired
	private GenericService genericService;

	@Autowired
	private LoginService loginService;

	@PostMapping("/addFarmer.lti")
	public Status addFarmer(@RequestBody FarmerDetails farmer) {
		int farmerId = genericService.addFarmer(farmer);
		Status status = new Status();
		status.setMessage("Farmer added!");
		status.setGeneratedId(farmerId);
		return status;
	}

	@PostMapping("/addBidder.lti")
	public Status add(@RequestBody BidderDetails bidder) {
		int bidderId = genericService.addBidder(bidder);
		Status status = new Status();
		status.setMessage("Bidder added!");
		status.setGeneratedId(bidderId);
		return status;
	}

	@GetMapping("/dispRequest.lti")
	public List<FarmerSellRequest> dispRequest() {
		return genericService.displayRequest();
	}

	@PostMapping("/login.lti")
	public Status loginValidation(@RequestBody Credential credentials) {
		Status obj =  loginService.login(credentials.getEmail(), credentials.getPassword(), credentials.getRole());
		return obj;
	}
 
	int requestId;

	@PostMapping("/addFarmerSellRequest.lti")
	public Status addFarmerSellRequest(@RequestBody FarmerSellRequest fsr) {

		requestId = genericService.addFarmerSellRequest(fsr);

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
		System.out.println("req" + req);
		System.out.println("farmerID" + farmerId);
		genericService.assignFarmerIdToFSR(req, farmerId);
		return req;
	}

	@PostMapping("/listAll.lti")
	public List<FarmerSellRequest> listAll() {
		return genericService.listAll();
	}



@GetMapping("/bidPage.lti")
public List<FarmerSellRequest> fetchCurrentBidDetails() {
return genericService.currentBidDetails();

}
@PostMapping("/updateCurrentBid.lti")
public Status add(@RequestBody CurrentBid currentBid) {
	BiddingDetails bd= new BiddingDetails();
	bd.setBidAmount(currentBid.getCurrentBid());
	FarmerSellRequest fsr=new FarmerSellRequest();
	fsr.setSellRequestId(currentBid.getCropId());
	bd.setFarmerSellRequest(fsr);
	BidderDetails bdr=new BidderDetails();
	bdr.setBidderId(currentBid.getBidderId());
	bd.setBidderDetails(bdr);
	int biddingId = genericService.updateCurrentBid(bd);
	Status status = new Status();
	status.setGeneratedId(biddingId);
	status.setMessage("Bidding Successfull!");
   return status;
}

@PostMapping("/displayAllFarmerDetails.lti")
public List<FarmerDetails> displayAllFarmers() {
	return genericService.displayAllFarmers();
	}

@PostMapping("/displayAllBidderDetails.lti")
public List<BidderDetails> displayAllBidders() {
	return genericService.displayAllBidders();
	}

@PostMapping("/fetchAllFarmerSellRequest.lti")
public List<FarmerSellRequest> fetchAll() {
	List<FarmerSellRequest> list=genericService.listAll1();
    for(FarmerSellRequest x:list)System.out.println(x.getCropType());
	return list;
}

/*For view Marketplace*/
@PostMapping("/approvedCropDetails.lti")
public List<FarmerSellRequest> approvedCropDetails() {
	return genericService.approvedCropDetails();
	
}

/*After admin click approve*/
@PostMapping("/requestApproved.lti")
public Status requestApproved(@RequestBody int requestId) {
	genericService.requestApproved(requestId);
	Status status = new Status();
	status.setMessage("Request added!");
	status.setGeneratedId(requestId);
	return status;
}

}
