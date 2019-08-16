package com.lti.dto;

public class CurrentBid {

	private int cropId;
	private double currentBid;
	private int bidderId;
	public int getCropId() {
		return cropId;
	}
	public void setCropId(int cropId) {
		this.cropId = cropId;
	}
	public double getCurrentBid() {
		return currentBid;
	}
	public void setCurrentBid(double currentBid) {
		this.currentBid = currentBid;
	}
	public int getBidderId() {
		return bidderId;
	}
	public void setBidderId(int bidderId) {
		this.bidderId = bidderId;
	}
}


