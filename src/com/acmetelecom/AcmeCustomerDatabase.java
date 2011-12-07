package com.acmetelecom;

import com.acmetelecom.billingsystem.customers.CustomerDatabaseInterface;
import com.acmetelecom.billingsystem.customers.CustomerInterface;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class AcmeCustomerDatabase implements CustomerDatabaseInterface {

    @Override
    public List<CustomerInterface> getCustomers() {
        List<Customer> customersCentral = CentralCustomerDatabase.getInstance().getCustomers();
        List<CustomerInterface> customers = new ArrayList<CustomerInterface>();
        for (Customer customer : customersCentral) {
            customers.add(new AcmeCustomer(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan()));
        }
        return customers;
    }

    @Override
    public CustomerInterface getCustomerFrom(String number) {
        List<Customer> customersCentral = CentralCustomerDatabase.getInstance().getCustomers();
        for (Customer customer : customersCentral) {
            if (customer.getPhoneNumber().equals(number)) {
                CustomerInterface acmeCustomer = new AcmeCustomer(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan());
                return acmeCustomer;
            }
        }
        return null;
    }
}



