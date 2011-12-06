package com.acmetelecom.test.com.acmetelecom.fake;

import com.acmetelecom.billingsystem.BillGeneratorInterface;
import com.acmetelecom.billingsystem.LineItem;
import com.acmetelecom.billingsystem.Printer;
import com.acmetelecom.billingsystem.customers.CustomerInterface;
import com.acmetelecom.billingsystem.printers.ConsolePrinter;
import com.acmetelecom.billingsystem.utils.MoneyFormatter;
import com.acmetelecom.customer.Customer;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 03/12/11
 * Time: 23:08
 * To change this template use File | Settings | File Templates.
 */

public class BillGeneratorFake implements BillGeneratorInterface {
	

    public void send(Printer printerInstance, CustomerInterface customer, List<LineItem> calls, String totalBill) {
        Printer printer = printerInstance;
        printer.printHeading(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan());
        for (LineItem call : calls) {
            printer.printItem(call.date(), call.callee(), call.durationMinutes(), MoneyFormatter.penceToPounds(call.cost()));
        }
        printer.printTotal(totalBill);
    }
}
