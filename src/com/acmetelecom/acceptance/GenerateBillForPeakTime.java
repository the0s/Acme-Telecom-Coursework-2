package com.acmetelecom.acceptance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.acmetelecom.AbstractBillingSystem;
import com.acmetelecom.Call;
import com.acmetelecom.CallEnd;
import com.acmetelecom.CallEvent;
import com.acmetelecom.CallStart;
import com.acmetelecom.utils.BillingSystemFake;

import fit.ActionFixture;

public class GenerateBillForPeakTime extends ActionFixture{
	private Call call;
	private Date startDate;
	private Date endDate;
	private CallEvent start;
	private CallEvent end;
	private String caller;
	private String callee;
	
	public void theCallerIs(String caller){
		this.caller = caller;
	}
	
	public void theCalleeIs(String callee){
		this.callee = callee;
	}
	
	public void callerStartedAt(Date start){
		this.startDate = start;
	}
	
	public void callerEndedAt(Date end){
		this.endDate = end;
	}
	
	public void amountPaid(){
		this.start = new CallStart(caller, callee, startDate);
		this.end = new CallEnd(caller, callee, endDate);
		this.call = new Call(start, end);
		
		List<Long> times = new ArrayList<Long>();
        times.add(startDate.getTime());
        times.add(endDate.getTime());
		AbstractBillingSystem billingSystem = new BillingSystemFake(times);
		billingSystem.callInitiated(caller, callee);
        billingSystem.callCompleted(caller, callee);
        billingSystem.createCustomerBills();		
	}
	
}
