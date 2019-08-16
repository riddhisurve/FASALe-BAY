package com.lti.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="TBL_FARMERSELLREQUEST")
public class FarmerSellRequest {

	@Id
	@GeneratedValue
	@Column(name="sell_request_id")
	private int sellRequestId;
	
	@Column(name="crop_type")
	private String cropType;
	
	@Column(name="crop_name")
	private String cropName;
	
	@Column(name="fertilizer_type")
	private String fertilizerType;
	private int quantity;
	@Column(name="soil_ph_certificate")
	private String soilPhCertificate;
	private int status;
	@Column(name="date_time")
	private LocalDateTime dateTime=LocalDateTime.now();
	
	@Column(name="end_date_time")
	private LocalDateTime endDateTime;
	
	
	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	private int duration;
	
	@Column(name="base_price")
	private double basePrice;
	
	@Transient
	private double maxBidAmount;

	
	
	@ManyToOne
	@JoinColumn(name="farmer_Id")
	private FarmerDetails farmerDetails;
	
	@JsonIgnore
	@OneToMany
	private Set<BiddingDetails> biddingDetails;

	public String getSoilPhCertificate() {
		return soilPhCertificate;
	}

	public void setSoilPhCertificate(String soilPhCertificate) {
		this.soilPhCertificate = soilPhCertificate;
	}

	public int getSellRequestId() {
		return sellRequestId;
	}

	public void setSellRequestId(int sellRequestId) {
		this.sellRequestId = sellRequestId;
	}

	public String getCropType() {
		return cropType;
	}

	public void setCropType(String cropType) {
		this.cropType = cropType;
	}

	public String getCropName() {
		return cropName;
	}

	public void setCropName(String cropName) {
		this.cropName = cropName;
	}

	public String getFertilizerType() {
		return fertilizerType;
	}

	public void setFertilizerType(String fertilizerType) {
		this.fertilizerType = fertilizerType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}



	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public FarmerDetails getFarmerDetails() {
		return farmerDetails;
	}

	public void setFarmerDetails(FarmerDetails farmerDetails) {
		this.farmerDetails = farmerDetails;
	}

	public Set<BiddingDetails> getBiddingDetails() {
		return biddingDetails;
	}

	public void setBiddingDetails(Set<BiddingDetails> biddingDetails) {
		this.biddingDetails = biddingDetails;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getMaxBidAmount() {
		return maxBidAmount;
	}

	public void setMaxBidAmount(double maxBidAmount) {
		this.maxBidAmount = maxBidAmount;
	}

	
}
