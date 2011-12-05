package com.acmetelecom.billingsystems.loggers;

import com.acmetelecom.billingsystems.*;
import com.acmetelecom.customer.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 05/12/11
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
public class CallLogger implements Logger{
    private List<CallEvent> callLog = new ArrayList<CallEvent>();

    private List<CallEvent> getEvents() {
        return this.callLog;
            }

    public void clear() {
        this.callLog.clear();
    }

    public void add(CallEvent callEvent) {
        this.callLog.add(callEvent);
    }

    private List<CallEvent> getCustomerEvents(Customer customer) {
        List<CallEvent> customerEvents = new ArrayList<CallEvent>();
        for (CallEvent callEvent : this.getEvents()) {
            if (callEvent.getCaller().equals(customer.getPhoneNumber())) {
                customerEvents.add(callEvent);
            }
        }
        return customerEvents;
    }

    public List<Call> getCallsDetailsOf(Customer customer) {
        List<CallEvent> customerEvents = this.getCustomerEvents(customer);
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



}
