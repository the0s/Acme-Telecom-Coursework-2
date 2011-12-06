package com.acmetelecom.acceptance;

import com.acmetelecom.AcmeCustomerDatabase;
import com.acmetelecom.AcmeTariffDatabase;
import com.acmetelecom.billingsystem.*;
import com.acmetelecom.billingsystem.billinggenerators.BillGenerator;
import com.acmetelecom.billingsystem.customers.CustomerDatabaseInterface;
import com.acmetelecom.billingsystem.customers.TariffDatabaseInterface;
import com.acmetelecom.billingsystem.loggers.CallLogger;
import com.acmetelecom.billingsystem.printers.HtmlPrinter;
import com.acmetelecom.billingsystem.reports.BillReport;
import com.acmetelecom.test.com.acmetelecom.fake.BillingSystemFake;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SystemUnderTest {
    public static final BillGeneratorInterface billGenerator = new BillGenerator();
    public static final Logger logger = new CallLogger();
    public static final Report report = new BillReport();
    public final static CustomerDatabaseInterface customerDatabase = new AcmeCustomerDatabase();
    public final static TariffDatabaseInterface tariffDatabase = new AcmeTariffDatabase();
    public final static Printer printer = HtmlPrinter.getInstance();
    public static final AbstractBillingSystem billingSystem = new BillingSystemFake(billGenerator,
            logger,
            report,
            customerDatabase,
            tariffDatabase,
            printer);

    //A convenience method to set the times for testing
    public static void setTimes(Date startDate, Date endDate) {
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getTime());
        times.add(endDate.getTime());
        ((BillingSystemFake) billingSystem).setTimes(times);
    }
}
