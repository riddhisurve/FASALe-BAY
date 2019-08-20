package com.lti.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.Dao;
import com.lti.entity.FarmerDetails;
import com.lti.entity.FarmerSellRequest;

@Service
public class FarmerService {
	@Autowired
	private Dao dao;

	@Transactional
	public int addFarmer(FarmerDetails farmer) {
		FarmerDetails updatedfarmer = (FarmerDetails) dao.save(farmer);
		return updatedfarmer.getFarmerId();
	}

		@Transactional
	public List<FarmerSellRequest> displayRequest() {
		return dao.fetchAll(FarmerSellRequest.class);
	}

	@Transactional
	public int addFarmerSellRequest(FarmerSellRequest fsr) {

		FarmerSellRequest updatedfsr = (FarmerSellRequest) dao.save(fsr);
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

	public List<FarmerSellRequest> listAll(int requestId) {
		return dao.fetchDataFSR(FarmerSellRequest.class, requestId);
	}

	public List<FarmerSellRequest> listHistory(int fid) {
		return dao.fetchDataFSH(FarmerSellRequest.class, fid);
	}

	public List<FarmerSellRequest> listAll1() {
		return dao.fetchAllUnapproved(FarmerSellRequest.class);
	}


	/* View Marketplace */
	public List<FarmerSellRequest> approvedCropDetails(int farmerId) {
		return dao.fetchAllSellingCrops(farmerId);
	}

	
	@Transactional
	public int stopBidding(int requestId) {

		double  sp = dao.getMaxBid(requestId);
		
		FarmerSellRequest insertFarmerIdFSR = dao.fetchById(FarmerSellRequest.class, requestId);
		insertFarmerIdFSR.setSold(1);
		insertFarmerIdFSR.setDateTime(LocalDateTime.now());
		insertFarmerIdFSR.setSoldPrice(sp);

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
	
	public List<FarmerDetails> displayAllFarmers() {
		return dao.fetchAll(FarmerDetails.class);
	}

}
