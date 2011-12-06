package com.acmetelecom.billingsystem.billinggenerators;

import com.acmetelecom.billingsystem.Printer;
import com.acmetelecom.billingsystem.BillGeneratorInterface;
import com.acmetelecom.billingsystem.LineItem;
import com.acmetelecom.billingsystem.customers.CustomerInterface;
import com.acmetelecom.billingsystem.printers.HtmlPrinter;
import com.acmetelecom.billingsystem.utils.MoneyFormatter;

import java.util.List;

public class BillGenerator implements BillGeneratorInterface {
    public void send(Printer printerInstance, CustomerInterface customer, List<LineItem> calls, String totalBill) {
        Printer printer = printerInstance;
        printer.printHeading(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan());
        for (LineItem call : calls) {
            printer.printItem(call.date(), call.callee(), call.durationMinutes(), MoneyFormatter.penceToPounds(call.cost()));
        }
        printer.printTotal(totalBill);
    }
}
