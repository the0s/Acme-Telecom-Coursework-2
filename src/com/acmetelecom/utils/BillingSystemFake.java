package com.acmetelecom.utils;

import com.acmetelecom.*;
import com.acmetelecom.customer.Customer;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 03/12/11
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
 */
public class BillingSystemFake extends AbstractBillingSystem {
    private List<Long> times;
    
    public BillingSystemFake() {
        super();
        this.times = null;
    }

    public void setTimes(List<Long> times){
    	//This method is used only in test environment to input custom times
    	this.times = times;
    }
    
    public void callInitiated(String caller, String callee) {

        callLog.add(new CallStart(caller, callee, times.get(0)));
    }

    public void callCompleted(String caller, String callee) {
        callLog.add(new CallEnd(caller, callee, times.get(1)));
    }

    @Override
    protected void GenerateBill(Customer customer, BigDecimal totalBill, List<LineItem> items) {
        BillGeneratorInterface billGenerator = BillGeneratorFactory.createBillGeneratorFake();
    	billGenerator.send(customer, items, MoneyFormatter.penceToPounds(totalBill));
    }

}
