package com.acmetelecom;

import java.util.ArrayList;
import java.util.List;

import com.acmetelecom.billingsystem.customers.CustomerDatabaseInterface;
import com.acmetelecom.billingsystem.customers.CustomerInterface;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.Customer;

public class AcmeCustomerDatabase implements CustomerDatabaseInterface {	
	private List<CustomerInterface> customers = new ArrayList<CustomerInterface>();
	
	public AcmeCustomerDatabase(){
		List<Customer> customers= CentralCustomerDatabase.getInstance().getCustomers();
		for (Customer customer: customers){
			this.customers.add(new AcmeCustomer(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan()));
		}
	}
	
	@Override
	public List<CustomerInterface> getCustomers() {
		return this.customers; 
	}
}
