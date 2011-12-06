package com.acmetelecom.billingsystem.customers;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 04/12/11
 * Time: 02:28
 * To change this template use File | Settings | File Templates.
 */
public class CustomerFind {

    public static CustomerInterface getCustomerFromNumber(CustomerDatabaseInterface db, String number) {
        final List<CustomerInterface> customers = db.getCustomers();
        for (CustomerInterface customer : customers) {
            if (customer.getPhoneNumber().equals(number)) {
                return customer;
            }
        }
        return null;
    }

}
