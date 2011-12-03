package com.acmetelecom.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * A utility to use it as a convinient method 
 * to input human readable dates. i.e 03/12/11 22:42
 */

public class CustomDate {	
	private Date date;
	
	public CustomDate(int year, int month, int day, int hours, int mins, int secs){
		//We have to use a calendar instance since Date(year, month, day, hours, mins) is deprecated
		Calendar cal = Calendar.getInstance();
		cal.set(year-1900, month, day, hours, mins, secs);
		this.date = cal.getTime();
	}
	
	public Date getDate(){
		return this.date;
	}
	
}
