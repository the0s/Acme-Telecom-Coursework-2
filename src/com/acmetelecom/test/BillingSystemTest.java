package com.acmetelecom.test;


import com.acmetelecom.AbstractBillingSystem;
import com.acmetelecom.MoneyFormatter;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.utils.BillingSystemFake;
import com.acmetelecom.utils.CustomDate;
import com.acmetelecom.utils.CustomerFind;
import com.acmetelecom.utils.FilePrinter;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 03/12/11
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */
public class BillingSystemTest {

    String caller, callee;
    Customer customer;
    Tariff tariff;

    @Before
    public void init() {
        this.caller = "447711232343";
        this.callee = "447766814143";
        this.customer = CustomerFind.getCustomerFromNumber(this.caller);
        this.tariff = CentralTariffDatabase.getInstance().tarriffFor(this.customer);

    }


    @Test
    public void createAndCheckBillsForCustomersPeak() {
        CustomDate startDate = new CustomDate(2011, 11, 11, 14, 00, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 14, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        AbstractBillingSystem billingSystem = new BillingSystemFake(times);
        billingSystem.callInitiated(caller, callee);
        billingSystem.callCompleted(caller, callee);
        billingSystem.createCustomerBills();
        String totalBill = FilePrinter.getInstance().readFile(caller);
        BigDecimal calculatedBill = new BigDecimal(20 * 60).multiply(tariff.peakRate());
        assertThat(totalBill, is(MoneyFormatter.penceToPounds(calculatedBill).toString()));
        FilePrinter.getInstance().deleteFile();
    }

    @Test
    public void createAndCheckBillsForCustomersOffPeak() {
        CustomDate startDate = new CustomDate(2011, 11, 11, 19, 00, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 19, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        AbstractBillingSystem billingSystem = new BillingSystemFake(times);
        billingSystem.callInitiated(caller, callee);
        billingSystem.callCompleted(caller, callee);
        billingSystem.createCustomerBills();
        String totalBill = FilePrinter.getInstance().readFile(caller);
        BigDecimal calculatedBill = new BigDecimal(20 * 60).multiply(tariff.offPeakRate());
        assertThat(totalBill, is(MoneyFormatter.penceToPounds(calculatedBill).toString()));
        ;
        FilePrinter.getInstance().deleteFile();
    }

    @Test
    public void createAndCheckBillsForCustomersOffPeakToPeak() {
        CustomDate startDate = new CustomDate(2011, 11, 11, 06, 40, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 07, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        AbstractBillingSystem billingSystem = new BillingSystemFake(times);
        billingSystem.callInitiated(caller, callee);
        billingSystem.callCompleted(caller, callee);
        billingSystem.createCustomerBills();
        String totalBill = FilePrinter.getInstance().readFile(caller);
        BigDecimal calculatedBill = new BigDecimal(20 * 60).multiply(tariff.offPeakRate());
        calculatedBill.add(new BigDecimal(20 * 60).multiply(tariff.peakRate()));
        assertThat(totalBill, is(MoneyFormatter.penceToPounds(calculatedBill).toString()));
        FilePrinter.getInstance().deleteFile();
    }


    @Test
    public void createAndCheckBillsForCustomersPeakToOffPeak() {
        CustomDate startDate = new CustomDate(2011, 11, 11, 18, 40, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 19, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        AbstractBillingSystem billingSystem = new BillingSystemFake(times);
        billingSystem.callInitiated(caller, callee);
        billingSystem.callCompleted(caller, callee);
        billingSystem.createCustomerBills();
        String totalBill = FilePrinter.getInstance().readFile(caller);
        BigDecimal calculatedBill = new BigDecimal(20 * 60).multiply(tariff.offPeakRate());
        calculatedBill.add(new BigDecimal(20 * 60).multiply(tariff.peakRate()));
        assertThat(totalBill, is(MoneyFormatter.penceToPounds(calculatedBill).toString()));
        FilePrinter.getInstance().deleteFile();
    }

    @Test
    public void createAndCheckBillsForCustomersPeakToOffPeakToPeak() {
        CustomDate startDate = new CustomDate(2011, 11, 11, 18, 40, 00);
        CustomDate endDate = new CustomDate(2011, 11, 12, 07, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        AbstractBillingSystem billingSystem = new BillingSystemFake(times);
        billingSystem.callInitiated(caller, callee);
        billingSystem.callCompleted(caller, callee);
        billingSystem.createCustomerBills();
        String totalBill = FilePrinter.getInstance().readFile(caller);
        BigDecimal calculatedBill = new BigDecimal(12 * 60 * 60).multiply(tariff.offPeakRate());
        calculatedBill.add(new BigDecimal(40 * 60).multiply(tariff.peakRate()));
        assertThat(totalBill, is(MoneyFormatter.penceToPounds(calculatedBill).toString()));
        FilePrinter.getInstance().deleteFile();
    }

    @Test
    public void createAndCheckBillsForCustomersOffPeakToPeakToOffPeak() {
        CustomDate startDate = new CustomDate(2011, 11, 11, 06, 40, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 19, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        AbstractBillingSystem billingSystem = new BillingSystemFake(times);
        billingSystem.callInitiated(caller, callee);
        billingSystem.callCompleted(caller, callee);
        billingSystem.createCustomerBills();
        String totalBill = FilePrinter.getInstance().readFile(caller);
        BigDecimal calculatedBill = new BigDecimal(40 * 60).multiply(tariff.offPeakRate());
        calculatedBill.add(new BigDecimal(12 * 60 * 60).multiply(tariff.peakRate()));
        assertThat(totalBill, is(MoneyFormatter.penceToPounds(calculatedBill).toString()));
        FilePrinter.getInstance().deleteFile();
    }


}
