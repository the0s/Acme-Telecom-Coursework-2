package com.acmetelecom.billingsystem;

import com.acmetelecom.billingsystem.customers.CustomerInterface;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 03/12/11
 * Time: 23:07
 * To change this template use File | Settings | File Templates.
 */
public interface BillGeneratorInterface {
    void send(Printer printerInstance, CustomerInterface customer, List<LineItem> calls, String totalBill);
}
