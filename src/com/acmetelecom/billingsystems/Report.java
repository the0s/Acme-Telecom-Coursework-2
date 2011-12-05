package com.acmetelecom.billingsystems;

import com.acmetelecom.customer.Customer;

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

    void addCustomerBill(Customer customer, BigDecimal totalBill);
    public BigDecimal getTotalBillOf(Customer customer);
}
