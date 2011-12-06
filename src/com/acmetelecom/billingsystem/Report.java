package com.acmetelecom.billingsystem;

import com.acmetelecom.billingsystem.customers.CustomerInterface;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 05/12/11
 * Time: 17:00
 * To change this template use File | Settings | File Templates.
 */
public interface Report {
    public void add(LineItem call);

    List<LineItem> getCalls();

    void clear();
    void clearCalls();
    void addCustomerBill(CustomerInterface customer, BigDecimal totalBill);
    public BigDecimal getTotalBillOf(CustomerInterface customer);
    public List<LineItem> getCallsOf(String customer);
}
