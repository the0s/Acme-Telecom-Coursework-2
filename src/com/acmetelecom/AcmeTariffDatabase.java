package com.acmetelecom;

import com.acmetelecom.billingsystem.customers.CustomerInterface;
import com.acmetelecom.billingsystem.customers.TariffDatabaseInterface;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.TariffLibrary;

import java.math.BigDecimal;

public class AcmeTariffDatabase implements TariffDatabaseInterface {

    public BigDecimal getOffPeakRateFor(CustomerInterface customer) {
        TariffLibrary tariffLibrary = CentralTariffDatabase.getInstance();
        String fullName = customer.getFullName();
        String phoneNumber = customer.getPhoneNumber();
        String pricePlan = customer.getPricePlan();
        return tariffLibrary.tarriffFor(new Customer(fullName, phoneNumber, pricePlan)).offPeakRate();
    }

    public BigDecimal getPeakRateFor(CustomerInterface customer) {
        TariffLibrary tariffLibrary = CentralTariffDatabase.getInstance();
        String fullName = customer.getFullName();
        String phoneNumber = customer.getPhoneNumber();
        String pricePlan = customer.getPricePlan();
        return tariffLibrary.tarriffFor(new Customer(fullName, phoneNumber, pricePlan)).peakRate();
    }

}
