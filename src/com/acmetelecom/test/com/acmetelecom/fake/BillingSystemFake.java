package com.acmetelecom.test.com.acmetelecom.fake;

import com.acmetelecom.billingsystem.*;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.utils.MoneyFormatter;

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
    private BillGeneratorInterface billGenerator;

    public BillingSystemFake(BillGeneratorInterface billGenerator, Logger logger, Report report) {
        super(logger, report);
        this.billGenerator = billGenerator;
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
    	this.billGenerator.send(customer, items, MoneyFormatter.penceToPounds(totalBill));
    }

}
