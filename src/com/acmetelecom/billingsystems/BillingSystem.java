package com.acmetelecom.billingsystems;

import com.acmetelecom.billingsystems.billinggenerators.BillGeneratorFactory;
import com.acmetelecom.billingsystems.billinggenerators.BillGeneratorInterface;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.utils.MoneyFormatter;

import java.math.BigDecimal;
import java.util.List;

public class BillingSystem extends AbstractBillingSystem {

    public void callInitiated(String caller, String callee) {
        callLog.add(new CallStart(caller, callee));
    }

    public void callCompleted(String caller, String callee) {
        callLog.add(new CallEnd(caller, callee));
    }

    @Override
    protected void GenerateBill(Customer customer, BigDecimal totalBill, List<LineItem> items) {
        BillGeneratorInterface billGenerator = BillGeneratorFactory.createBillGenerator();
    	billGenerator.send(customer, items, MoneyFormatter.penceToPounds(totalBill));
    }
}
