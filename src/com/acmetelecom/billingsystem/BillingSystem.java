package com.acmetelecom.billingsystem;

public class BillingSystem extends AbstractBillingSystem {


    public BillingSystem(BillGeneratorInterface billingGenerator,Logger logger, Report report) {
        super(billingGenerator, logger, report);
    }

    public void callInitiated(String caller, String callee) {
        callLog.add(new CallStart(caller, callee));
    }

    public void callCompleted(String caller, String callee) {
        callLog.add(new CallEnd(caller, callee));
    }



}
