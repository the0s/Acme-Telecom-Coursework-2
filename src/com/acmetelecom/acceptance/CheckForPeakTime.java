package com.acmetelecom.acceptance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.acmetelecom.billingsystems.DaytimePeakPeriod;
import fit.ColumnFixture;

public class CheckForPeakTime extends ColumnFixture{
	
	public String Time;
	private DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();
	private SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");;
	
	public Boolean isOffPeak() throws ParseException{
		Date date = dfm.parse(Time);
		if (peakPeriod.offPeak(date))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
