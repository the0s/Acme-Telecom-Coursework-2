package com.acmetelecom;

import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 03/12/11
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractBillingSystem {
    protected List<CallEvent> callLog = new ArrayList<CallEvent>();

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
        BigDecimal cost;

        DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();
        if (peakPeriod.offPeak(call.startTime()) && peakPeriod.offPeak(call.endTime()) && call.durationSeconds() < 12 * 60 * 60) {
            cost = new BigDecimal(call.durationSeconds()).multiply(tariff.offPeakRate());
        } else {
            cost = new BigDecimal(call.durationSeconds()).multiply(tariff.peakRate());
        }
        return cost;
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
        for (CallEvent callEvent : callLog) {
            if (callEvent.getCaller().equals(customer.getPhoneNumber())) {
                customerEvents.add(callEvent);
            }
        }
        return customerEvents;
    }

    protected abstract void GenerateBill(Customer customer, BigDecimal totalBill, List<LineItem> items);

}
