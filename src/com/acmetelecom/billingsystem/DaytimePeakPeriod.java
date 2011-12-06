package com.acmetelecom.billingsystem;

import com.acmetelecom.configs.CommonConfig;
import com.acmetelecom.utils.CustomDate;

import java.util.Calendar;
import java.util.Date;

//TODO Converted TO Static

public class DaytimePeakPeriod {
    
	private final static int peakStart = CommonConfig.peakStart;
	private final static int peakEnd = CommonConfig.peakEnd;
	
	public boolean offPeak(Date time) {
        Calendar calendar = getCalendarInstance(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour < DaytimePeakPeriod.peakStart || hour >= DaytimePeakPeriod.peakEnd;
    }
	
	public Date getPeakStart(Call call){
        Calendar calendarStart = getCalendarInstance(call.startTime());
        Calendar calendarEnd = getCalendarInstance(call.endTime());
        if (calendarStart.get(Calendar.DAY_OF_MONTH) == calendarEnd.get(Calendar.DAY_OF_MONTH)){
            return new CustomDate(calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH), calendarStart.get(Calendar.DAY_OF_MONTH), DaytimePeakPeriod.peakStart, 00, 00).getDate();
        }else{
            return new CustomDate(calendarEnd.get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH), calendarEnd.get(Calendar.DAY_OF_MONTH), DaytimePeakPeriod.peakStart, 00, 00).getDate();
        }
	}

	public Date getPeakEnd(Call call){
        Calendar calendarStart = getCalendarInstance(call.startTime());
        return new CustomDate(calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH), calendarStart.get(Calendar.DAY_OF_MONTH), DaytimePeakPeriod.peakEnd, 00, 00).getDate();
	}
	
	private Calendar getCalendarInstance(Date callStart) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(callStart);
		return calendar;
	}
}
