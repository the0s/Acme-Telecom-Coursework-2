package com.acmetelecom.test;

import com.acmetelecom.Call;
import com.acmetelecom.CallEnd;
import com.acmetelecom.CallStart;
import com.acmetelecom.utils.CustomDate;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;


public class CallTest {
	
	private CallStart start; 
	private CallEnd end;		
	private Call call;
	private static int duration = 1;

	@Before
	public void init() throws Exception {
		this.start = new CallStart("Caller", "Callee"); 
		Thread.sleep(this.duration * 1000);
		this.end = new CallEnd("Caller", "Callee");		
		this.call = new Call(start, end);
	}

	@Test
	public void testDateWithExplicitDateConstructor() {
		CustomDate startDate = new CustomDate(2011, 11, 11, 11, 11, 11);
		CustomDate endDate = new CustomDate(2011, 11, 11, 11, 12, 11);
		this.start = new CallStart("Caller", "Callee", startDate);
		this.end = new CallEnd("Caller", "Callee", endDate);
		this.call = new Call(this.start, this.end);
		assertEquals(this.call.date().toString(), "11/12/11 11:11");
	}
	
	@Test
	public void testCallee() {
		assertEquals(this.call.callee(), "Callee");
	}
	
	@Test
	public void testDuration(){
		assertEquals(this.call.durationSeconds(), this.duration);
	}
	
	@Test
	public void testDate(){
		assertEquals(this.call.date(), SimpleDateFormat.getInstance().format(new Date(start.time())));
	}	
}
