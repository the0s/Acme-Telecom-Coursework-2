package com.acmetelecom.acceptance;

import com.acmetelecom.Call;
import com.acmetelecom.CallEvent;
import com.acmetelecom.CallStart;
import com.acmetelecom.CallEnd;
import com.acmetelecom.utils.CustomDate;

import fit.ActionFixture;

public class CallFlowAndDuration extends ActionFixture {

	private Call call;
	private CallEvent start;
	private CallEvent end;
	
	public void startCall(){
		start = new CallStart("Caller", "Callee");
	}
	
	public void speakForSoManySeconds(int duration) throws InterruptedException{
		Thread.sleep(duration * 1000);
	}
	
	public void endCall(){
		end = new CallEnd("Caller", "Callee");
	}
	
	public int durationOf(){
		call = new Call(start, end);
		return call.durationSeconds();
	}


}