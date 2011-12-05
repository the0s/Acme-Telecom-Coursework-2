package com.acmetelecom.billingsystems;

import com.acmetelecom.billingsystems.loggers.CallLogger;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 03/12/11
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractBillingSystem {
    //protected List<CallEvent> callLog = new ArrayList<CallEvent>();
    protected Logger callLog = new CallLogger();

    public abstract void callInitiated(String caller, String callee);

    public abstract void callCompleted(String caller, String callee);

    public void createCustomerBills() {
        List<Customer> customers = CentralCustomerDatabase.getInstance().getCustomers();
        for (Customer customer : customers) {
            createBillFor(customer);
        }
        callLog.clear();
    }

    private void createBillFor(Customer customer) {

        List<CallEvent> customerEvents = getCustomerEvents(customer);

        List<Call> calls = getCallsDetails(customerEvents);

        BigDecimal totalBill = new BigDecimal(0);
        List<LineItem> items = new ArrayList<LineItem>();

        for (Call call : calls) {
            BigDecimal cost = calculateCallCost(customer, call);

            cost = cost.setScale(0, RoundingMode.HALF_UP);
            BigDecimal callCost = cost;
            totalBill = totalBill.add(callCost);
            items.add(new LineItem(call, callCost));
        }

        GenerateBill(customer, totalBill, items);
    }

    private BigDecimal calculateCallCost(Customer customer, Call call) {
        Tariff tariff = CentralTariffDatabase.getInstance().tarriffFor(customer);
        BigDecimal cost = new BigDecimal(0);

        DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();
        int halfDayInSeconds = 12 * 60 * 60;

        if (call.durationSeconds() < halfDayInSeconds) {
            if (peakPeriod.offPeak(call.startTime()) && peakPeriod.offPeak(call.endTime())) {
                cost = getCost(tariff, call.durationSeconds(), 0);
            } else if (!peakPeriod.offPeak(call.startTime()) && !peakPeriod.offPeak(call.endTime())) {
                cost = getCost(tariff, 0, call.durationSeconds());
            } else if (peakPeriod.offPeak(call.startTime()) && !peakPeriod.offPeak(call.endTime())) {
                Date peakStart = peakPeriod.getPeakStart(call.startTime());
                int offPeakDuration = getDurationInSeconds(call.startTime(), peakStart);
                int peakDuration = call.durationSeconds() - offPeakDuration;
                cost = getCost(tariff, offPeakDuration, peakDuration);
            } else if (!peakPeriod.offPeak(call.startTime()) && peakPeriod.offPeak(call.endTime())) {
                Date peakEnd = peakPeriod.getPeakEnd(call.startTime());
                int peakDuration = getDurationInSeconds(call.startTime(), peakEnd);
                int offPeakDuration = call.durationSeconds() - peakDuration;
                cost = getCost(tariff, offPeakDuration, peakDuration);
            }
        } else if (call.durationSeconds() > halfDayInSeconds) {
            if (peakPeriod.offPeak(call.startTime()) && peakPeriod.offPeak(call.endTime())) {
                int peakDuration = halfDayInSeconds;
                int offPeakDuration = call.durationSeconds() - peakDuration;
                cost = getCost(tariff, offPeakDuration, peakDuration);
            } else if (!peakPeriod.offPeak(call.startTime()) && !peakPeriod.offPeak(call.endTime())) {
                int offPeakDuration = halfDayInSeconds;
                int peakDuration = call.durationSeconds() - offPeakDuration;
                cost = getCost(tariff, offPeakDuration, peakDuration);
            }
        }
        return cost;
    }

    private BigDecimal getCost(Tariff tariff, int offPeakDuration, int peakDuration) {
        BigDecimal costOffPeak, costPeak;
        costOffPeak = new BigDecimal(offPeakDuration).multiply(tariff.offPeakRate());
        costPeak = new BigDecimal(peakDuration).multiply(tariff.peakRate());
        return costOffPeak.add(costPeak);
    }

    private int getDurationInSeconds(Date start, Date end) {
        return (int) ((end.getTime() - start.getTime()) / 1000);
    }

    private List<Call> getCallsDetails(List<CallEvent> customerEvents) {
        List<Call> calls = new ArrayList<Call>();
        CallEvent start = null;
        for (CallEvent event : customerEvents) {
            if (event instanceof CallStart) {
                start = event;
            }
            if (event instanceof CallEnd && start != null) {
                calls.add(new Call(start, event));
                start = null;
            }
        }
        return calls;
    }

    private List<CallEvent> getCustomerEvents(Customer customer) {
        List<CallEvent> customerEvents = new ArrayList<CallEvent>();
        for (CallEvent callEvent : callLog.getEvents()) {
            if (callEvent.getCaller().equals(customer.getPhoneNumber())) {
                customerEvents.add(callEvent);
            }
        }
        return customerEvents;
    }

    protected abstract void GenerateBill(Customer customer, BigDecimal totalBill, List<LineItem> items);

}
