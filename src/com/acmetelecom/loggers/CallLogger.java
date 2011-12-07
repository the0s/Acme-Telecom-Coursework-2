package com.acmetelecom.loggers;

import com.acmetelecom.billingsystem.*;
import com.acmetelecom.billingsystem.customers.CustomerInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 05/12/11
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
public class CallLogger implements Logger {
    private List<CallEventInterface> callLog = new ArrayList<CallEventInterface>();

    private List<CallEventInterface> getEvents() {
        return this.callLog;
    }

    public void clear() {
        this.callLog.clear();
    }

    public void add(CallEventInterface callEvent) {
        this.callLog.add(callEvent);
    }

    private List<CallEventInterface> getCustomerEvents(CustomerInterface customer) {
        List<CallEventInterface> customerEvents = new ArrayList<CallEventInterface>();
        for (CallEventInterface callEvent : this.getEvents()) {
            if (callEvent.getCaller().equals(customer.getPhoneNumber())) {
                customerEvents.add(callEvent);
            }
        }
        return customerEvents;
    }

    public List<Call> getCallsDetailsOf(CustomerInterface customer) {
        List<CallEventInterface> customerEvents = this.getCustomerEvents(customer);
        List<Call> calls = new ArrayList<Call>();
        CallEventInterface start = null;
        for (CallEventInterface event : customerEvents) {
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
