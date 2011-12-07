package com.acmetelecom.acceptance;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.acmetelecom.AcmeCustomer;
import com.acmetelecom.billingsystem.customers.CustomerInterface;
import com.acmetelecom.customer.Customer;

import fitlibrary.DoFixture;

public class TestCustomerStory extends DoFixture {
	private String caller;
	private String callee;
	private SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public void callerIsAndCalleeIs(String caller, String callee){
		this.caller = caller;
		this.callee = callee;
	}
	
	public void callStartedAtAndEndedAt(String startDate, String endDate) throws ParseException{
		SystemUnderTest.setTimes(dfm.parse(startDate), dfm.parse(startDate));
		SystemUnderTest.billingSystem.callInitiated(caller, callee);
		SystemUnderTest.billingSystem.callCompleted(caller, callee);
	}
	
	public boolean theNumberOfCallsForCallerIs(String caller, int noOfCalls){
		SystemUnderTest.billingSystem.createCustomerBills();
		return (noOfCalls == SystemUnderTest.billingSystem.getBillReport().getCallsOf(caller).size());
	}
	
	public boolean theCostOfTheCallsIsIs(String caller, String totalCost){
		CustomerInterface customer = new AcmeCustomer("caller", caller, "standard");
		return (totalCost == SystemUnderTest.billingSystem.getBillReport().getTotalBillOf(customer).toString());
	}
	 
	public void getComDotAcmetelecomDotAcceptanceDotTestCustomerStory() { } 
	public void comDotAcmetelecomDotAcceptanceDotTestCustomerStory() { }
}