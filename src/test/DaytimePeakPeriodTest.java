package test;


import com.acmetelecom.DaytimePeakPeriod;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 03/12/11
 * Time: 18:26
 * To change this template use File | Settings | File Templates.
 */
public class DaytimePeakPeriodTest {



    @Test
	public void checkForPeakTime() throws ParseException {
        DaytimePeakPeriod daytimePeakPeriod = new DaytimePeakPeriod();
        SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date PeakDate = dfm.parse("2012-12-03 18:15:00");
        assertFalse(daytimePeakPeriod.offPeak(PeakDate));
    }

    @Test
	public void checkForNonPeakTimeMorning() throws ParseException {
        DaytimePeakPeriod daytimePeakPeriod = new DaytimePeakPeriod();
        SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date nonPeakDate = dfm.parse("2012-12-03 06:15:00");
        assertTrue(daytimePeakPeriod.offPeak(nonPeakDate));
    }

     @Test
	public void checkForPeakTimeEvening() throws ParseException {
        DaytimePeakPeriod daytimePeakPeriod = new DaytimePeakPeriod();
        SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date nonPeakDate = dfm.parse("2012-12-03 19:15:00");
        assertTrue(daytimePeakPeriod.offPeak(nonPeakDate));
    }


}
