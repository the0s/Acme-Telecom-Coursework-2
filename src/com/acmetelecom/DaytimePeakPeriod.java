package com.acmetelecom;

import java.util.Calendar;
import java.util.Date;

import com.acmetelecom.utils.CustomDate;

//TODO LOOKs Like a utility class --> should be converted into static ?
//Todo hardcoded data to config
public class DaytimePeakPeriod {
    
	private static int peakStart = 7;
	private static int peakEnd = 19;
	
	public boolean offPeak(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour < DaytimePeakPeriod.peakStart || hour >= DaytimePeakPeriod.peakEnd;
    }
	
	public Date getPeakStart(Date callStart){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(callStart);
        return new CustomDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), DaytimePeakPeriod.peakStart, 00, 00).getDate();
	}

	public Date getPeakEnd(Date callStart){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(callStart);
        return new CustomDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), DaytimePeakPeriod.peakEnd, 00, 00).getDate();
	}
}
