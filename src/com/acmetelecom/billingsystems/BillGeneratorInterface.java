package com.acmetelecom.billingsystems;

import com.acmetelecom.customer.Customer;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 03/12/11
 * Time: 23:07
 * To change this template use File | Settings | File Templates.
 */
public interface BillGeneratorInterface {
    void send(Customer customer, List<LineItem> calls, String totalBill);
}
