package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TBL_BIDDINGDETAILS")
public class BiddingDetails {

	@Id
	@GeneratedValue
	@Column(name = "bidding_id")
	private int biddingId;

	@Column(name = "bid_amount")
	private double bidAmount;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "bidder_id")
	private BidderDetails bidderDetails;
	
	@ManyToOne
	@JoinColumn(name="sell_request_id")
	private FarmerSellRequest farmerSellRequest;

	public int getBiddingId() {
		return biddingId;
	}

	public void setBiddingId(int biddingId) {
		this.biddingId = biddingId;
	}

	public double getBidAmount() {
		return bidAmount;
	}

	public void setBidAmount(double bidAmount) {
		this.bidAmount = bidAmount;
	}

	public BidderDetails getBidderDetails() {
		return bidderDetails;
	}

	public void setBidderDetails(BidderDetails bidderDetails) {
		this.bidderDetails = bidderDetails;
	}

	public FarmerSellRequest getFarmerSellRequest() {
		return farmerSellRequest;
	}

	public void setFarmerSellRequest(FarmerSellRequest farmerSellRequest) {
		this.farmerSellRequest = farmerSellRequest;
	}

}
