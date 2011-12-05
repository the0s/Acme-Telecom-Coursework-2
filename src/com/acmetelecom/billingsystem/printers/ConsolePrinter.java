package com.acmetelecom.billingsystem.printers;


import com.acmetelecom.billingsystem.Printer;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 03/12/11
 * Time: 23:34
 * To change this template use File | Settings | File Templates.
 */
public class ConsolePrinter implements Printer {
    private static Printer instance = new ConsolePrinter();

    private ConsolePrinter() {
    }

    public static Printer getInstance() {
        return instance;
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
