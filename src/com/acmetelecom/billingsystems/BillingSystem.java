package com.acmetelecom.billingsystems;

import com.acmetelecom.customer.Customer;
import com.acmetelecom.utils.MoneyFormatter;

import java.math.BigDecimal;
import java.util.List;

public class BillingSystem extends AbstractBillingSystem {
    private BillGeneratorInterface billingGenerator = null;

    public BillingSystem(BillGeneratorInterface billingGenerator,Logger logger, Report report) {
        super(logger, report);
        this.billingGenerator = billingGenerator;
    }


    public void callInitiated(String caller, String callee) {
        callLog.add(new CallStart(caller, callee));
    }

    public void callCompleted(String caller, String callee) {
        callLog.add(new CallEnd(caller, callee));
    }

    @Override
    protected void GenerateBill(Customer customer, BigDecimal totalBill, List<LineItem> items) {

    	this.billingGenerator.send(customer, items, MoneyFormatter.penceToPounds(totalBill));
    }

}
