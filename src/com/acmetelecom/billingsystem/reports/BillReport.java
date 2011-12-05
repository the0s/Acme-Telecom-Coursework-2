package com.acmetelecom.billingsystem.reports;

import com.acmetelecom.billingsystem.LineItem;
import com.acmetelecom.billingsystem.Report;
import com.acmetelecom.customer.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 05/12/11
 * Time: 16:59
 * To change this template use File | Settings | File Templates.
 */
public class BillReport implements Report {
    List<LineItem> calls = new ArrayList<LineItem>();
    Map<String,BigDecimal> customerBills = new TreeMap<String,BigDecimal>();

    public void add(LineItem call) {
        this.calls.add(call);
    }

    public List<LineItem> getCalls() {
        return this.calls;
    }

    public void clear() {
        this.calls.clear();
        this.customerBills.clear();
    }

    public void clearCalls(){
        this.calls.clear();
    }

    public void addCustomerBill(Customer customer, BigDecimal totalBill) {
        this.customerBills.put(customer.getPhoneNumber(),totalBill);
    }

    public BigDecimal getTotalBillOf(Customer customer) {
        return this.customerBills.get(customer.getPhoneNumber());
    }


}
