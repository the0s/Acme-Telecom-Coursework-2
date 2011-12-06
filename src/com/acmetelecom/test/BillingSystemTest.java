package com.acmetelecom.test;

import com.acmetelecom.AbstractFactory;
import com.acmetelecom.AcmeCustomerDatabase;
import com.acmetelecom.AcmeTariffDatabase;
import com.acmetelecom.FactoryMaker;
import com.acmetelecom.billingsystem.AbstractBillingSystem;
import com.acmetelecom.billingsystem.Logger;
import com.acmetelecom.billingsystem.customers.CustomerDatabaseInterface;
import com.acmetelecom.billingsystem.customers.CustomerFind;
import com.acmetelecom.billingsystem.customers.CustomerInterface;
import com.acmetelecom.billingsystem.customers.TariffDatabaseInterface;
import com.acmetelecom.billingsystem.utils.CustomDate;
import com.acmetelecom.billingsystem.utils.MoneyFormatter;
import com.acmetelecom.test.com.acmetelecom.fake.BillingSystemFake;
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
    CustomerInterface customer;
    AbstractBillingSystem billingSystem;
    Logger callLogger;
    CustomerDatabaseInterface customerDatabase = new AcmeCustomerDatabase();
    TariffDatabaseInterface tariffDatabase = new AcmeTariffDatabase();

    @Before
    public void init() {
    	AbstractFactory factory = FactoryMaker.getTestFactory();
        this.billingSystem = factory.createBillingSystem();
        this.caller = "447711232343";
        this.callee = "447766814143";
        this.customer = CustomerFind.getCustomerFromNumber(customerDatabase, this.caller);
        //this.tariff = CentralTariffDatabase.getInstance().tarriffFor(this.customer);
    }

    @Test
    public void customerCallsDuringPeakOnlyForLessThan12Hours() {
        CustomDate startDate = new CustomDate(2011, 11, 11, 14, 00, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 14, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        ((BillingSystemFake) billingSystem).setTimes(times);
        makeTestCall(billingSystem);
        BigDecimal totalBill = getTotalBillOfCaller();
        BigDecimal calculatedBill = getCalculatedCost(0, 20*60);
        assertThat(MoneyFormatter.penceToPounds(totalBill), is(MoneyFormatter.penceToPounds(calculatedBill)));
    }

    @Test
    public void customerCallsDuringOffPeakOnlyForLessThan12Hours() {
        CustomDate startDate = new CustomDate(2011, 11, 11, 19, 00, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 19, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        ((BillingSystemFake) billingSystem).setTimes(times);
        makeTestCall(billingSystem);
        BigDecimal totalBill = getTotalBillOfCaller();
        BigDecimal calculatedBill = getCalculatedCost(20*60, 0);
        assertThat(MoneyFormatter.penceToPounds(totalBill), is(MoneyFormatter.penceToPounds(calculatedBill)));
    }

    @Test
    public void customerCallsDuringOffPeakAndEntertsPeakForLessThan12Hours() {
        CustomDate startDate = new CustomDate(2011, 11, 11, 06, 40, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 07, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        ((BillingSystemFake) billingSystem).setTimes(times);
        makeTestCall(billingSystem);
        BigDecimal totalBill = getTotalBillOfCaller();
        BigDecimal calculatedBill = getCalculatedCost(20*60, 20*60);
        assertThat(MoneyFormatter.penceToPounds(totalBill), is(MoneyFormatter.penceToPounds(calculatedBill)));
    }


    @Test
    public void customerCallsDuringPeakAndEntertsOffPeakForLessThan12Hours() {
        CustomDate startDate = new CustomDate(2011, 11, 11, 18, 40, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 19, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        ((BillingSystemFake) billingSystem).setTimes(times);
        makeTestCall(billingSystem);
        BigDecimal totalBill = getTotalBillOfCaller();
        BigDecimal calculatedBill = getCalculatedCost(20 * 60, 20 * 60);
        assertThat(MoneyFormatter.penceToPounds(totalBill), is(MoneyFormatter.penceToPounds(calculatedBill)));
    }

    @Test
    public void customerCallsDuringPeakPassesOffPeakAndReachesPeakAgainMoreThan12Hours() {
        CustomDate startDate = new CustomDate(2011, 11, 11, 18, 40, 00);
        CustomDate endDate = new CustomDate(2011, 11, 12, 07, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        ((BillingSystemFake) billingSystem).setTimes(times);
        makeTestCall(billingSystem);
        BigDecimal totalBill = getTotalBillOfCaller();
        BigDecimal calculatedBill = getCalculatedCost(12 * 60 * 60, 40 * 60);
        assertThat(MoneyFormatter.penceToPounds(totalBill), is(MoneyFormatter.penceToPounds(calculatedBill)));
    }

    @Test
    public void customerCallsDuringOffPeakPassesPeakAndReachesOffPeakAgainMoreThan12Hours() {
        CustomDate startDate = new CustomDate(2011, 11, 11, 06, 40, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 19, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        ((BillingSystemFake) billingSystem).setTimes(times);
        makeTestCall(billingSystem);
        BigDecimal totalBill = getTotalBillOfCaller();
        BigDecimal calculatedBill = getCalculatedCost(40 * 60, 12 * 60 * 60);
        assertThat(MoneyFormatter.penceToPounds(totalBill), is(MoneyFormatter.penceToPounds(calculatedBill)));
    }

    @Test
    public void customerCallsDuringOffPeakAndDateChangesLessThan12Hours(){
        CustomDate startDate = new CustomDate(2011, 11, 11, 19, 20, 00);
        CustomDate endDate = new CustomDate(2011, 11, 12, 06, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        ((BillingSystemFake) billingSystem).setTimes(times);
        makeTestCall(billingSystem);
        BigDecimal totalBill = getTotalBillOfCaller();
        BigDecimal calculatedBill = getCalculatedCost(11* 60 * 60, 0);
        assertThat(MoneyFormatter.penceToPounds(totalBill), is(MoneyFormatter.penceToPounds(calculatedBill)));


    }

    @Test
    public void customerCallsDuringOffPeakAndDateChangesAndEntersPeakMoreThan12Hours(){
        CustomDate startDate = new CustomDate(2011, 11, 11, 19, 20, 00);
        CustomDate endDate = new CustomDate(2011, 11, 12, 9, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        ((BillingSystemFake) billingSystem).setTimes(times);
        makeTestCall(billingSystem);
        BigDecimal totalBill = getTotalBillOfCaller();
        BigDecimal calculatedBill = getCalculatedCost(700 * 60, 140*60);
        assertThat(MoneyFormatter.penceToPounds(totalBill), is(MoneyFormatter.penceToPounds(calculatedBill)));
    }

    @Test
    public void customerCallsDuringPeakAndDateChangesAndEntersOffPeakMoreThan12Hours(){
        CustomDate startDate = new CustomDate(2011, 11, 11, 9, 00, 00);
        CustomDate endDate = new CustomDate(2011, 11, 12, 5, 00, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        ((BillingSystemFake) billingSystem).setTimes(times);
        makeTestCall(billingSystem);
        BigDecimal totalBill = getTotalBillOfCaller();
        BigDecimal calculatedBill = getCalculatedCost(10 * 60 * 60, 10*60*60);
        assertThat(MoneyFormatter.penceToPounds(totalBill), is(MoneyFormatter.penceToPounds(calculatedBill)));
    }

    @Test
    public void customerCallsOnBoundariesPeakEqualTo12Hours(){
        CustomDate startDate = new CustomDate(2011, 11, 11, 7, 00, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 19, 00, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        ((BillingSystemFake) billingSystem).setTimes(times);
        makeTestCall(billingSystem);
        BigDecimal totalBill = getTotalBillOfCaller();
        BigDecimal calculatedBill = getCalculatedCost(0, 12*60*60);
        assertThat(MoneyFormatter.penceToPounds(totalBill), is(MoneyFormatter.penceToPounds(calculatedBill)));
    }

     @Test
    public void customerCallsOnBoundariesOffPeakEqualTo12Hours(){
        CustomDate startDate = new CustomDate(2011, 11, 11, 19, 00, 00);
        CustomDate endDate = new CustomDate(2011, 11, 12, 07, 00, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        ((BillingSystemFake) billingSystem).setTimes(times);
        makeTestCall(billingSystem);
        BigDecimal totalBill = getTotalBillOfCaller();
        BigDecimal calculatedBill = getCalculatedCost(12*60*60,0);
        assertThat(MoneyFormatter.penceToPounds(totalBill), is(MoneyFormatter.penceToPounds(calculatedBill)));
    }

    private BigDecimal getTotalBillOfCaller() {
        BigDecimal total =  billingSystem.getBillReport().getTotalBillOf(customer);
        //billingSystem.clear();
        return total;
    }

    private void makeTestCall(AbstractBillingSystem billingSystem) {
        billingSystem.callInitiated(caller, callee);
        billingSystem.callCompleted(caller, callee);
        billingSystem.createCustomerBills();
    }

    private BigDecimal getCalculatedCost(int offPeakDuration, int peakDuration) {
        BigDecimal costOffPeak, costPeak;
        costOffPeak = new BigDecimal(offPeakDuration).multiply(tariffDatabase.getOffPeakRateFor(customer));
        costPeak = new BigDecimal(peakDuration).multiply(tariffDatabase.getPeakRateFor(customer));
        return costOffPeak.add(costPeak);
    }

}
