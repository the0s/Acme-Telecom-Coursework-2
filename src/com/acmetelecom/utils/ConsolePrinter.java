package com.acmetelecom.utils;

import com.acmetelecom.LineItem;
import com.acmetelecom.Printer;
import com.acmetelecom.customer.Customer;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 03/12/11
 * Time: 23:34
 * To change this template use File | Settings | File Templates.
 */
public class ConsolePrinter implements Printer {
    Customer customer;
    List<LineItem> calls;
    String totalBill;

    public ConsolePrinter(Customer customer, List<LineItem> calls, String totalBill) {
        this.customer = customer;
        this.calls = calls;
        this.totalBill = totalBill;
    }

    public void printHeading(String name, String phoneNumber, String pricePlan) {
        System.out.println("Name: " + name + " PhoneNumber: " + phoneNumber + " PricePlan: " + pricePlan);
    }

    public void printItem(String time, String callee, String duration, String cost) {
        System.out.println("Time: " + time + " Calle: " + callee + " Duration: " + duration + " Cost: " + cost);
    }

    public void printTotal(String total) {
        System.out.println("Total: " + total);
    }
}
