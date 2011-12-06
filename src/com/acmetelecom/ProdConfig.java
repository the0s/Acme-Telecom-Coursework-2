package com.acmetelecom;

import com.acmetelecom.billingsystem.*;
import com.acmetelecom.billingsystem.billinggenerators.BillGenerator;
import com.acmetelecom.billingsystem.customers.CustomerDatabaseInterface;
import com.acmetelecom.billingsystem.customers.TariffDatabaseInterface;
import com.acmetelecom.billingsystem.loggers.CallLogger;
import com.acmetelecom.billingsystem.printers.HtmlPrinter;
import com.acmetelecom.billingsystem.reports.BillReport;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 05/12/11
 * Time: 21:40
 * To change this template use File | Settings | File Templates.
 */
public class ProdConfig { //TODO ADD PRINTER
    private final static BillGeneratorInterface billGenerator = new BillGenerator();
    private final static Logger logger = new CallLogger();
    private final static Report report = new BillReport();
    private final static CustomerDatabaseInterface customerDatabase = new AcmeCustomerDatabase();
    private final static TariffDatabaseInterface tariffDatabase = new AcmeTariffDatabase();
    private final static Printer printer = HtmlPrinter.getInstance();
    public final static AbstractBillingSystem billingSystem = new BillingSystem(billGenerator,
            logger,
            report,
            customerDatabase,
            tariffDatabase,
            printer);
}
