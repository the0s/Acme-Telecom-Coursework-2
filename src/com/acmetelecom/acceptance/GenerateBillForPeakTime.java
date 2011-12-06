package com.acmetelecom.acceptance;

import com.acmetelecom.billingsystem.Report;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.utils.CustomDate;
import com.acmetelecom.utils.MoneyFormatter;
import fit.RowFixture;

import java.util.ArrayList;
import java.util.List;

public class GenerateBillForPeakTime extends RowFixture{

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
		CustomDate startDate = new CustomDate(2011, 11, 11, 14, 00, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 14, 20, 00);
        SystemUnderTest.setTimes(startDate.getDate(), endDate.getDate());
        
        List<Customer> customers = CentralCustomerDatabase.getInstance().getCustomers();
        for (Customer customer : customers) {
        	SystemUnderTest.billingSystem.callInitiated(customer.getPhoneNumber(), "callee");
        	SystemUnderTest.billingSystem.callCompleted(customer.getPhoneNumber(), "callee");
        }
  
        SystemUnderTest.billingSystem.createCustomerBills();      
        List<Row> rows = new ArrayList<Row>();
        Report report = SystemUnderTest.billingSystem.getBillReport();
        int lineCount = 0;
        for (Customer customer : customers) {
        	String totalBill = MoneyFormatter.penceToPounds(report.getTotalBillOf(customer));
        	Row row = new Row(customer.getPhoneNumber(), totalBill);
        	rows.add(lineCount, row);
        	lineCount++;
        }
        
        return rows.toArray();
    }
	
}
