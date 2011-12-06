package com.acmetelecom.acceptance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.acmetelecom.billingsystem.AbstractBillingSystem;
import com.acmetelecom.billingsystem.BillGeneratorInterface;
import com.acmetelecom.billingsystem.Logger;
import com.acmetelecom.billingsystem.Report;
import com.acmetelecom.billingsystem.loggers.CallLogger;
import com.acmetelecom.billingsystem.reports.BillReport;
import com.acmetelecom.test.com.acmetelecom.fake.BillGeneratorFake;
import com.acmetelecom.test.com.acmetelecom.fake.BillingSystemFake;

public class SystemUnderTest {
    public static final BillGeneratorInterface billGenerator = new BillGeneratorFake();
	public static final Logger logger = new CallLogger();
	public static final Report report = new BillReport();
	public static final AbstractBillingSystem billingSystem= new BillingSystemFake(billGenerator, logger, report);
	
	//A convenience method to set the times for testing
	public static void setTimes(Date startDate, Date endDate) {
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getTime());
        times.add(endDate.getTime());
        ((BillingSystemFake) billingSystem).setTimes(times);
	}
}
