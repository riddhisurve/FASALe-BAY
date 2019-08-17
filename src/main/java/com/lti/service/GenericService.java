package com.lti.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.GenericDao;
import com.lti.entity.BidderDetails;
import com.lti.entity.BiddingDetails;
import com.lti.entity.FarmerDetails;
import com.lti.entity.FarmerSellRequest;

@Service
public class GenericService {
	
	@Autowired
	private GenericDao dao;
	
	
	@Transactional
	public int addFarmer(FarmerDetails farmer) {
		FarmerDetails updatedfarmer= (FarmerDetails) dao.save(farmer);
		return updatedfarmer.getFarmerId();
	}
	@Transactional
	public int addBidder(BidderDetails bidder) {
		BidderDetails bidderdetails = (BidderDetails) dao.save(bidder);
		return bidderdetails.getBidderId();
	}

	
	@Transactional
	public List<FarmerSellRequest> displayRequest() {
		return dao.fetchAll(FarmerSellRequest.class);
	}
	
	@Transactional
	public int addFarmerSellRequest(FarmerSellRequest fsr) {
		
		FarmerSellRequest updatedfsr= (FarmerSellRequest) dao.save(fsr);
		return updatedfsr.getSellRequestId();
	}
	
	@Transactional
	public void assignFarmerIdToFSR(int requestId, int farmerId) {

		FarmerSellRequest insertFarmerIdFSR = dao.fetchById(FarmerSellRequest.class, requestId);
		FarmerDetails farmerDetails = dao.fetchById(FarmerDetails.class, farmerId);
		System.out.println(insertFarmerIdFSR);
		System.out.println(farmerDetails);
		insertFarmerIdFSR.setFarmerDetails(farmerDetails);
		dao.save(insertFarmerIdFSR);
	}
	public List<FarmerSellRequest> listAll(int requestId){
		return dao.fetchDataFSR(FarmerSellRequest.class,requestId);
	}
	
	public List<FarmerSellRequest> listHistory(int fid){
		return dao.fetchDataFSR(FarmerSellRequest.class,fid);
	}
	
	public List<FarmerSellRequest> listAll1() {
		return dao.fetchAllUnapproved(FarmerSellRequest.class);
	}
	
	@Transactional
	public int requestApproved(int requestId) {
		
		FarmerSellRequest insertFarmerIdFSR = dao.fetchById(FarmerSellRequest.class, requestId);
		insertFarmerIdFSR.setStatus(1);
		insertFarmerIdFSR.setDateTime(LocalDateTime.now());
		
		LocalDateTime startDateTime = insertFarmerIdFSR.getDateTime();
		int duration = insertFarmerIdFSR.getDuration();
		LocalDateTime endDateTime = startDateTime.plusDays(duration);
		insertFarmerIdFSR.setEndDateTime(endDateTime);
		
		dao.save(insertFarmerIdFSR);
		return requestId;
	}
	
	/*View Marketplace*/
	public List<FarmerSellRequest> approvedCropDetails(int farmerId) {
		return dao.fetchAllSellingCrops(farmerId);
	}
	

	@Transactional
	public  List<FarmerSellRequest> currentBidDetails() {
		return dao.currentBidDetails();
	}
	@Transactional
	public int updateCurrentBid(BiddingDetails biddingDetails) {
		
		BiddingDetails bidDetails = (BiddingDetails) dao.save(biddingDetails);
		return bidDetails.getBiddingId();
	}
	
	public List<FarmerDetails> displayAllFarmers() {
		return dao.fetchAll(FarmerDetails.class);
		}
	
	public List<BidderDetails> displayAllBidders() {
		return dao.fetchAll(BidderDetails.class);
		}
	
	@Transactional
	public String updateBidApproval(int sellReqId ) {
		FarmerSellRequest fsr=dao.fetchById(FarmerSellRequest.class, sellReqId);
		fsr.setSold(1);
		dao.save(fsr);
		return "Approved";
	}
	
	@Transactional
	public int stopBidding(int requestId) {

		FarmerSellRequest insertFarmerIdFSR = dao.fetchById(FarmerSellRequest.class, requestId);
		insertFarmerIdFSR.setSold(1);
		insertFarmerIdFSR.setDateTime(LocalDateTime.now());

		LocalDateTime startDateTime = insertFarmerIdFSR.getDateTime();
		int duration = insertFarmerIdFSR.getDuration();
		LocalDateTime endDateTime = startDateTime.plusDays(duration);
		insertFarmerIdFSR.setEndDateTime(endDateTime);

		dao.save(insertFarmerIdFSR);
		return requestId;
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
	
	@Transactional
	public List<FarmerSellRequest> viewRequestStatus(int farmerId) {
		return dao.fetchAllRequestByFarmerId(farmerId);
	}
	
}
