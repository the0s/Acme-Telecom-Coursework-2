package com.acmetelecom.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * A utility to use it as a convinient method
 * to input human readable dates. i.e 03/12/11 22:42
 */
 //Todo Why not static??? just return date
public class CustomDate {
    private Date date;
    public CustomDate(int year, int month, int day, int hours, int mins, int secs) {
        //We have to use a calendar instance since Date(year, month, day, hours, mins) is deprecated
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getDefault());
        //cal.set(year - 1900, month, day, hours, mins, secs);
        cal.set(year, month, day, hours, mins, secs);
        this.date = cal.getTime();
    }

    public Date getDate() {
        return this.date;
    }

}
