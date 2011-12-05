package com.acmetelecom.billingsystems;

import com.acmetelecom.utils.CustomDate;

import java.util.Calendar;
import java.util.Date;

//TODO LOOKs Like a utility class --> should be converted into static ?
//Todo hardcoded data to config
public class DaytimePeakPeriod {
    
	private final static int peakStart = 7;
	private final static int peakEnd = 19;
	
	public boolean offPeak(Date time) {
        Calendar calendar = getCalendarInstance(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour < DaytimePeakPeriod.peakStart || hour >= DaytimePeakPeriod.peakEnd;
    }
	
	public Date getPeakStart(Date callStart){
        Calendar calendar = getCalendarInstance(callStart);
        return new CustomDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), DaytimePeakPeriod.peakStart, 00, 00).getDate();
	}

	public Date getPeakEnd(Date callStart){
        Calendar calendar = getCalendarInstance(callStart);
        return new CustomDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), DaytimePeakPeriod.peakEnd, 00, 00).getDate();
	}
	
	private Calendar getCalendarInstance(Date callStart) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(callStart);
		return calendar;
	}
}
