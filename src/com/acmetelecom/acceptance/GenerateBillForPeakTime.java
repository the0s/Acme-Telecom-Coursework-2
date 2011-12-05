package com.acmetelecom.acceptance;

import com.acmetelecom.billingsystems.AbstractBillingSystem;

import com.acmetelecom.billingsystems.loggers.CallLogger;
import com.acmetelecom.billingsystems.reports.BillReport;

import com.acmetelecom.billingsystems.BillGeneratorInterface;

import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.test.com.acmetelecom.fake.BillGeneratorFake;
import com.acmetelecom.test.com.acmetelecom.fake.BillingSystemFake;
import com.acmetelecom.utils.CustomDate;
import com.acmetelecom.utils.FilePrinter;
import fit.RowFixture;

import java.util.ArrayList;
import java.util.List;

public class GenerateBillForPeakTime extends RowFixture{

    public static class Row {
    	public String Caller;
        public String StartDate;
        public String EndDate;
        public String Cost;
    	
	    public Row(String Caller,String StartDate, String EndDate, String Cost) {
	        this.Caller = Caller;
	        this.StartDate = StartDate;
	        this.EndDate = EndDate;
	        this.Cost = Cost;
	    }
    }

	
	@Override
    public Class<?> getTargetClass() {
        return Row.class;
    }

	@Override
    public Object[] query() throws Exception {		
		CustomDate startDate = new CustomDate(2011, 11, 11, 14, 00, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 14, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());

        BillGeneratorInterface billGenerator = new BillGeneratorFake();
        AbstractBillingSystem billingSystem = new BillingSystemFake(billGenerator,new CallLogger(), new BillReport());

        ((BillingSystemFake) billingSystem).setTimes(times);
		
        billingSystem.callInitiated("447711232343", "callee");
        billingSystem.callCompleted("447711232343", "callee");
        billingSystem.createCustomerBills();
        List<Row> rows = new ArrayList<Row>();
        
        List<Customer> customers = CentralCustomerDatabase.getInstance().getCustomers();
        int lineCount = 0;
        for (Customer customer : customers) {
        	String totalBill = FilePrinter.getInstance().readFile(customer.getPhoneNumber());
        	totalBill = (totalBill != null) ? totalBill : "0";
        	Row row = new Row(customer.getPhoneNumber(), "11/11/2011 14:00:00", "11/11/2011 14:20:00", totalBill);
        	rows.add(lineCount, row);
        	lineCount++;
        }
        
        return rows.toArray();
    }
	
}
