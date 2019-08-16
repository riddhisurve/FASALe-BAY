package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="TBL_FARMERDETAILS")
public class FarmerDetails {

	@Id
	@GeneratedValue
	@Column(name="farmer_id")
	private int farmerId;
	
	private String name;
	@Column(name="contact_number")
	private int contactNumber;
	
	private String email;
	private String password;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private int pincode;
	
	@Column(name="account_number")
	private int accountNumber;
	
	private String ifsc;
	private String aadharcard;
	private String pancard;
	
	private String certificate;
	
	@Column(name="land_area")
	private int landArea;
	
	@Column(name="land_address")
	private String landAddress;
	
	@Column(name="land_pin")
	private int landPin;
	
	@JsonIgnore
	@OneToMany(mappedBy="farmerDetails", cascade=CascadeType.ALL)
	private List<FarmerSellRequest> farmerSellRequest;

	public int getFarmerId() {
		return farmerId;
	}

	public void setFarmerId(int farmerId) {
		this.farmerId = farmerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public int getLandArea() {
		return landArea;
	}

	public void setLandArea(int landArea) {
		this.landArea = landArea;
	}

	public String getLandAddress() {
		return landAddress;
	}

	public void setLandAddress(String landAddress) {
		this.landAddress = landAddress;
	}

	public int getLandPin() {
		return landPin;
	}

	public void setLandPin(int landPin) {
		this.landPin = landPin;
	}

	public String getAadharcard() {
		return aadharcard;
	}

	public void setAadharcard(String aadharcard) {
		this.aadharcard = aadharcard;
	}

	public String getPancard() {
		return pancard;
	}

	public void setPancard(String pancard) {
		this.pancard = pancard;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public List<FarmerSellRequest> getFarmerSellRequest() {
		return farmerSellRequest;
	}

	public void setFarmerSellRequest(List<FarmerSellRequest> farmerSellRequest) {
		this.farmerSellRequest = farmerSellRequest;
	}

	


}
