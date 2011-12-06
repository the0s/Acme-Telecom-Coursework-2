package com.acmetelecom.acceptance;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	 
	public void getComDotAcmetelecomDotAcceptanceDotTestCustomerStory() { } 
	public void comDotAcmetelecomDotAcceptanceDotTestCustomerStory() { }
}