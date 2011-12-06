package com.acmetelecom.billingsystem.customers;

import java.util.List;


public interface CustomerDatabaseInterface {
    public List<CustomerInterface> getCustomers();

    public CustomerInterface getCustomerFrom(String number);
}
