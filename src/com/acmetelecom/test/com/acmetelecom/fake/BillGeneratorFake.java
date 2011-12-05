package com.acmetelecom.test.com.acmetelecom.fake;

import com.acmetelecom.billingsystems.LineItem;
import com.acmetelecom.billingsystems.billinggenerators.BillGeneratorInterface;
import com.acmetelecom.billingsystems.printers.ConsolePrinter;
import com.acmetelecom.billingsystems.printers.Printer;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.utils.FilePrinter;
import com.acmetelecom.utils.MoneyFormatter;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 03/12/11
 * Time: 23:08
 * To change this template use File | Settings | File Templates.
 */
public class BillGeneratorFake implements BillGeneratorInterface {
	
    public void send(Customer customer, List<LineItem> calls, String totalBill) {
        Printer printer = ConsolePrinter.getInstance();
        FilePrinter filePrinter = FilePrinter.getInstance();
        printer.printHeading(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan());
        for (LineItem call : calls) {
            printer.printItem(call.date(), call.callee(), call.durationMinutes(), MoneyFormatter.penceToPounds(call.cost()));
        }
        printer.printTotal(totalBill);
        filePrinter.writeToTestFile(customer.getPhoneNumber(), totalBill);

    }
}