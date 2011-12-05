package com.acmetelecom.billingsystems.billinggenerators;

import com.acmetelecom.billingsystems.LineItem;
import com.acmetelecom.billingsystems.printers.HtmlPrinter;
import com.acmetelecom.billingsystems.printers.Printer;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.utils.MoneyFormatter;

import java.util.List;

public class BillGenerator implements BillGeneratorInterface {
    public void send(Customer customer, List<LineItem> calls, String totalBill) {
        Printer printer = HtmlPrinter.getInstance();
        printer.printHeading(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan());
        for (LineItem call : calls) {
            printer.printItem(call.date(), call.callee(), call.durationMinutes(), MoneyFormatter.penceToPounds(call.cost()));
        }
        printer.printTotal(totalBill);
    }
}
