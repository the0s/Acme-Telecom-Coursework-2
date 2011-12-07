package com.acmetelecom.test;

import com.acmetelecom.AcmeCustomerDatabase;
import com.acmetelecom.AcmeTariffDatabase;
import com.acmetelecom.billingsystem.customers.CustomerDatabaseInterface;
import com.acmetelecom.billingsystem.customers.CustomerInterface;
import com.acmetelecom.billingsystem.customers.TariffDatabaseInterface;
import org.junit.Test;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 07/12/11
 * Time: 03:53
 * To change this template use File | Settings | File Templates.
 */
public class SystemData {

    private CustomerDatabaseInterface customerDatabaseInterface = new AcmeCustomerDatabase();
    private TariffDatabaseInterface tarrifDatabaseInterface = new AcmeTariffDatabase();
    private List<CustomerInterface> customers = customerDatabaseInterface.getCustomers();


    @Test
    public void findAboutCustomers() {

        System.out.println("Customers: ");
        for (CustomerInterface customer : customers) {
            System.out.println("********");
            System.out.println("Name: " + customer.getFullName());
            System.out.println("Number: " + customer.getPhoneNumber());
            System.out.println("Plan: " + customer.getPricePlan());
            System.out.println("OFF-Peak Tariff: " + tarrifDatabaseInterface.getOffPeakRateFor(customer));
            System.out.println("Peakrate Tariff: " + tarrifDatabaseInterface.getPeakRateFor(customer));
            System.out.println("********");
        }

    }
}
