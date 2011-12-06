package com.acmetelecom.billingsystem;

import com.acmetelecom.billingsystem.customers.CustomerDatabaseInterface;
import com.acmetelecom.billingsystem.customers.TariffDatabaseInterface;

public class BillingSystem extends AbstractBillingSystem {


    public BillingSystem(BillGeneratorInterface billingGenerator,
    		Logger logger, 
    		Report report, 
    		CustomerDatabaseInterface customerDatabase, 
    		TariffDatabaseInterface tariffDatabase, 
    		Printer printer) {
        super(billingGenerator, logger, report, customerDatabase, tariffDatabase, printer);
    }

    public void callInitiated(String caller, String callee) {
        callLog.add(new CallStart(caller, callee));
    }

    public void callCompleted(String caller, String callee) {
        callLog.add(new CallEnd(caller, callee));
    }



}
