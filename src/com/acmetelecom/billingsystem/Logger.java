package com.acmetelecom.billingsystem;

import com.acmetelecom.billingsystem.customers.CustomerInterface;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 05/12/11
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */
public interface Logger {
    public void clear();
    public void add(CallEvent callEvent);
    public List<Call> getCallsDetailsOf(CustomerInterface customer);


}
