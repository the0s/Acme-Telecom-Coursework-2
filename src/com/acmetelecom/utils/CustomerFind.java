package com.acmetelecom.utils;

import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.Customer;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 04/12/11
 * Time: 02:28
 * To change this template use File | Settings | File Templates.
 */
public class CustomerFind {

    public static Customer getCustomerFromNumber(String number) {
        final List<Customer> customers = CentralCustomerDatabase.getInstance().getCustomers();
        for (Customer customer : customers) {
            if (customer.getPhoneNumber().equals(number)) {
                return customer;
            }
        }
        return null;
    }

}
