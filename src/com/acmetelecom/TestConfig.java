package com.acmetelecom;

import com.acmetelecom.billingsystem.*;
import com.acmetelecom.billingsystem.customers.CustomerDatabaseInterface;
import com.acmetelecom.billingsystem.customers.TariffDatabaseInterface;
import com.acmetelecom.loggers.CallLogger;
import com.acmetelecom.printers.ConsolePrinter;
import com.acmetelecom.reports.BillReport;
import com.acmetelecom.test.com.acmetelecom.fake.BillGeneratorFake;
import com.acmetelecom.test.com.acmetelecom.fake.BillingSystemFake;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 05/12/11
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */

public class TestConfig {//TODO ADD PRINTER
    private final static BillGeneratorInterface billGenerator = new BillGeneratorFake();
    private final static Logger logger = new CallLogger();
    private final static Report report = new BillReport();
    private final static CustomerDatabaseInterface customerDatabase = new AcmeCustomerDatabase();
    private final static TariffDatabaseInterface tariffDatabase = new AcmeTariffDatabase();
    private final static Printer printer = ConsolePrinter.getInstance();
    public final static AbstractBillingSystem billingSystem = new BillingSystemFake(billGenerator,
            logger,
            report,
            customerDatabase,
            tariffDatabase,
            printer);
}
