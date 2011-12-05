package com.acmetelecom.acceptance;

import java.util.ArrayList;
import java.util.List;

import com.acmetelecom.AbstractBillingSystem;
import com.acmetelecom.BillingSystem;
import com.acmetelecom.BillingSystemFIT;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.utils.BillingSystemFake;
import com.acmetelecom.utils.FilePrinter;

import fit.RowFixture;

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
        AbstractBillingSystem billingSystem = new BillingSystemFIT();
        billingSystem.callInitiated("447711232343", "callee");
        billingSystem.callCompleted("447711232343", "callee");
        billingSystem.createCustomerBills();
        List<Row> rows = new ArrayList<Row>();
        
        List<Customer> customers = CentralCustomerDatabase.getInstance().getCustomers();
        int lineCount = 0;
        for (Customer customer : customers) {
        	String totalBill = FilePrinter.getInstance().readFile(customer.getPhoneNumber());
        	Row row = new Row(customer.getPhoneNumber(), "11/11/2011 14:00:00", "11/11/2011 14:20:00", totalBill);
        	rows.add(lineCount, row);
        	lineCount++;
        }
        
        return rows.toArray();
    }
	
}
