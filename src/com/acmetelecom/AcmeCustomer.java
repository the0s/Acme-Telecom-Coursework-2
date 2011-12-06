package com.acmetelecom;

import com.acmetelecom.billingsystem.customers.CustomerInterface;

public class AcmeCustomer implements CustomerInterface {
	
	private String fullName;
	private String phoneNumber;
	private String pricePlan;
	
	public AcmeCustomer(String fullName, String phoneNumber, String pricePlan ){
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.pricePlan = pricePlan;
	}
	
	@Override
	public String getFullName() {
		return fullName;
	}

	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public String getPricePlan() {	
		return pricePlan;
	}

}
