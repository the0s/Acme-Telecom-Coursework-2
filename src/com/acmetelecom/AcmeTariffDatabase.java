package com.acmetelecom;

import java.math.BigDecimal;

import com.acmetelecom.billingsystem.customers.CustomerInterface;
import com.acmetelecom.billingsystem.customers.TariffDatabaseInterface;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.TariffLibrary;

public class AcmeTariffDatabase implements TariffDatabaseInterface {	
	public static TariffLibrary tariffLibrary = null;

	public AcmeTariffDatabase(){
		tariffLibrary = CentralTariffDatabase.getInstance();
	}
	
	public BigDecimal getOffPeakRateFor(CustomerInterface customer){
		String fullName = customer.getFullName();
		String phoneNumber = customer.getPhoneNumber();
		String pricePlan = customer.getPricePlan();
		return tariffLibrary.tarriffFor(new Customer(fullName, phoneNumber, pricePlan)).offPeakRate();
	}
	
	public BigDecimal getPeakRateFor(CustomerInterface customer){
		String fullName = customer.getFullName();
		String phoneNumber = customer.getPhoneNumber();
		String pricePlan = customer.getPricePlan();
		return tariffLibrary.tarriffFor(new Customer(fullName, phoneNumber, pricePlan)).peakRate();
	}
	
}
