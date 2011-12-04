package com.acmetelecom;

import java.util.Calendar;
import java.util.Date;

//TODO LOOKs Like a utility class --> should be converted into static ?
//Todo hardcoded data to config
public class DaytimePeakPeriod {
    public boolean offPeak(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour < 7 || hour >= 19;
    }
}
