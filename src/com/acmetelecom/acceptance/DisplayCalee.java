package com.acmetelecom.acceptance;

import com.acmetelecom.Call;
import com.acmetelecom.CallEvent;
import com.acmetelecom.CallStart;
import com.acmetelecom.utils.CustomDate;

import fit.ColumnFixture;

public class DisplayCalee extends ColumnFixture {

	public CallEvent EventType;
	public String Caller;
	public String Callee;
	public CustomDate Date;
	
 	@Override
	public void reset() throws Exception {
        EventType = null;
        Caller = null;
        Callee = null;
        Date = null;
	}
	
	@Override
	public void execute() throws Exception {
		EventType = new CallStart("447711232343", "447766814143");
	}

}
