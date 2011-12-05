package com.acmetelecom.configs;

import com.acmetelecom.billingsystem.BillGeneratorInterface;
import com.acmetelecom.billingsystem.Logger;
import com.acmetelecom.billingsystem.Report;
import com.acmetelecom.billingsystem.loggers.CallLogger;
import com.acmetelecom.billingsystem.reports.BillReport;
import com.acmetelecom.test.com.acmetelecom.fake.BillGeneratorFake;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 05/12/11
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */
public class TestConfig {
   // public final static int peakStart = 7;
   // public final static int peakEnd = 19;
    public final static BillGeneratorInterface billGenerator = new BillGeneratorFake();
	public final static Logger logger = new CallLogger();
	public final static Report report = new BillReport();
}
