package com.acmetelecom.acceptance;

import com.acmetelecom.billingsystem.Report;
import com.acmetelecom.billingsystem.customers.CustomerInterface;
import com.acmetelecom.billingsystem.utils.CustomDate;
import com.acmetelecom.billingsystem.utils.MoneyFormatter;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.Customer;
import fit.RowFixture;

import java.util.ArrayList;
import java.util.List;

public class GenerateBillForOffPeakTime extends RowFixture{

    public static class Row {
    	public String Caller;
        public String StartDate;
        public String EndDate;
        public String Cost;
    	
	    public Row(String Caller, String Cost) {
	        this.Caller = Caller;
	        this.Cost = Cost;
	    }
    }

	@Override
    public Class<?> getTargetClass() {
        return Row.class;
    }

	@Override
    public Object[] query() throws Exception {		
        //Set the start and end times
		CustomDate startDate = new CustomDate(2011, 11, 11, 21, 00, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 21, 20, 00);
        SystemUnderTest.setTimes(startDate.getDate(), endDate.getDate());
        List<CustomerInterface> customers = SystemUnderTest.customerDatabase.getCustomers();
        for (CustomerInterface customer : customers) {
        	SystemUnderTest.billingSystem.callInitiated(customer.getPhoneNumber(), "callee");
        	SystemUnderTest.billingSystem.callCompleted(customer.getPhoneNumber(), "callee");
        }
  
        SystemUnderTest.billingSystem.createCustomerBills();      
        List<Row> rows = new ArrayList<Row>();
        Report report = SystemUnderTest.billingSystem.getBillReport();
        int lineCount = 0;
        for (CustomerInterface customer : customers) {
        	String totalBill = MoneyFormatter.penceToPounds(report.getTotalBillOf(customer));
        	Row row = new Row(customer.getPhoneNumber(), totalBill);
        	rows.add(lineCount, row);
        	lineCount++;
        }
        
        return rows.toArray();
    }
	
}
