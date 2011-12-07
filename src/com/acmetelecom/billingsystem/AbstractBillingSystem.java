package com.acmetelecom.billingsystem;

import com.acmetelecom.billingsystem.customers.CustomerDatabaseInterface;
import com.acmetelecom.billingsystem.customers.CustomerInterface;
import com.acmetelecom.billingsystem.customers.TariffDatabaseInterface;
import com.acmetelecom.billingsystem.utils.MoneyFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import static com.acmetelecom.configs.CommonConfig.*;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 03/12/11
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractBillingSystem {
    protected Logger callLog;
    protected Report billReport;
    private BillGeneratorInterface billingGenerator;
    private CustomerDatabaseInterface customerDatabase;
    private TariffDatabaseInterface tariffDatabase;
    private Printer printer;

    public AbstractBillingSystem(BillGeneratorInterface billingGenerator,
                                 Logger logger,
                                 Report report,
                                 CustomerDatabaseInterface customerDatabase,
                                 TariffDatabaseInterface tariffDatabase,
                                 Printer printer) {
        this.callLog = logger;
        this.billReport = report;
        this.billingGenerator = billingGenerator;
        this.customerDatabase = customerDatabase;
        this.tariffDatabase = tariffDatabase;
        this.printer = printer;
    }

    public abstract void callInitiated(String caller, String callee);

    public abstract void callCompleted(String caller, String callee);

    public void createCustomerBills() {
        List<CustomerInterface> customers = customerDatabase.getCustomers();
        for (CustomerInterface customer : customers) {
            createBillFor(customer);
        }
        callLog.clear();
    }

    private void createBillFor(CustomerInterface customer) {
        List<Call> calls = callLog.getCallsDetailsOf(customer);
        BigDecimal totalBill = new BigDecimal(ZERO);

        for (Call call : calls) {
            BigDecimal cost = calculateCallCost(customer, call);
            cost = cost.setScale(ZERO, RoundingMode.HALF_UP);
            BigDecimal callCost = cost;
            totalBill = totalBill.add(callCost);
            billReport.add(new LineItem(call, callCost));
        }
        billReport.addCustomerBill(customer, totalBill);
        GenerateBill(customer, totalBill, billReport.getCalls());
        billReport.clearCalls();
    }

    private BigDecimal calculateCallCost(CustomerInterface customer, Call call) {
        BigDecimal cost = new BigDecimal(ZERO);
        DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();
        int halfDayInSeconds = HALF_DAY_IN_SECONDS;
        int peakDuration = ZERO;
        int offPeakDuration = ZERO;

        if (peakPeriod.offPeak(call.startTime()) && peakPeriod.offPeak(call.endTime())) {
            peakDuration = call.durationSeconds() <= halfDayInSeconds
                    ? ZERO
                    : halfDayInSeconds;
            offPeakDuration = call.durationSeconds() - peakDuration;
            cost = getCost(customer, offPeakDuration, peakDuration);
        } else if (!peakPeriod.offPeak(call.startTime()) && !peakPeriod.offPeak(call.endTime())) {
            offPeakDuration = call.durationSeconds() <= halfDayInSeconds
                    ? ZERO
                    : halfDayInSeconds;
            peakDuration = call.durationSeconds() - offPeakDuration;
            cost = getCost(customer, offPeakDuration, peakDuration);
        } else if (peakPeriod.offPeak(call.startTime()) && !peakPeriod.offPeak(call.endTime())) {
            Date peakStart = peakPeriod.getPeakStart(call);
            offPeakDuration = getDurationInSeconds(call.startTime(), peakStart);
            peakDuration = call.durationSeconds() - offPeakDuration;
            cost = getCost(customer, offPeakDuration, peakDuration);
        } else if (!peakPeriod.offPeak(call.startTime()) && peakPeriod.offPeak(call.endTime())) {
            Date peakEnd = peakPeriod.getPeakEnd(call);
            peakDuration = getDurationInSeconds(call.startTime(), peakEnd);
            offPeakDuration = call.durationSeconds() - peakDuration;
            cost = getCost(customer, offPeakDuration, peakDuration);
        }
        return cost;
    }

    private BigDecimal getCost(CustomerInterface customer, int offPeakDuration, int peakDuration) {
        BigDecimal costOffPeak, costPeak;
        costOffPeak = new BigDecimal(offPeakDuration).multiply(tariffDatabase.getOffPeakRateFor(customer));
        costPeak = new BigDecimal(peakDuration).multiply(tariffDatabase.getPeakRateFor(customer));
        return costOffPeak.add(costPeak);
    }

    private int getDurationInSeconds(Date start, Date end) {
        return (int) ((end.getTime() - start.getTime()) / THOUSAND);
    }

    public Report getBillReport() {
        return this.billReport;
    }

    protected void GenerateBill(CustomerInterface customer, BigDecimal totalBill, List<LineItem> items) {
        this.billingGenerator.send(printer, customer, items, MoneyFormatter.penceToPounds(totalBill));
    }

    public void clear() {
        this.billReport.clear();
        this.callLog.clear();
    }

}
