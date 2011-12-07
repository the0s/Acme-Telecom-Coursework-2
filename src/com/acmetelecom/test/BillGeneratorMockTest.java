package com.acmetelecom.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import com.acmetelecom.billinggenerators.BillGenerator;
import com.acmetelecom.billingsystem.BillGeneratorInterface;
import com.acmetelecom.billingsystem.Call;
import com.acmetelecom.billingsystem.CallEventInterface;
import com.acmetelecom.billingsystem.LineItem;
import com.acmetelecom.billingsystem.Logger;
import com.acmetelecom.billingsystem.Printer;
import com.acmetelecom.billingsystem.Report;
import com.acmetelecom.billingsystem.customers.CustomerDatabaseInterface;
import com.acmetelecom.billingsystem.customers.CustomerInterface;
import com.acmetelecom.billingsystem.customers.TariffDatabaseInterface;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 07/12/11
 * Time: 03:25
 * To change this template use File | Settings | File Templates.
 */
public class BillGeneratorMockTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();
    Logger logger = context.mock(Logger.class);

    Report report = context.mock(Report.class);
    CustomerDatabaseInterface customerDatabase = context.mock(CustomerDatabaseInterface.class);
    TariffDatabaseInterface tariffDatabase = context.mock(TariffDatabaseInterface.class);
    Printer printer = context.mock(Printer.class);
    CustomerInterface customerInterface = context.mock(CustomerInterface.class);
    CallEventInterface callEvent = context.mock(CallEventInterface.class);
    Call call = new Call(callEvent, callEvent);
    BillGeneratorInterface billGenerator = new BillGenerator();
    List<LineItem> lineItemList = new ArrayList<LineItem>();
    LineItem lineItem = new LineItem(call, new BigDecimal(2));


    @Test
    public void sendUsingBillGenerator() {
        lineItemList.add(lineItem);
        context.checking(new Expectations() {{
            allowing(customerInterface).getFullName();
            will(returnValue("antonis"));
            allowing(customerInterface).getPhoneNumber();
            will(returnValue("123456"));
            allowing(customerInterface).getPricePlan();
            will(returnValue("Bussiness"));
            oneOf(printer).printHeading("antonis", "123456", "Bussiness");
            ignoring(callEvent).time();
            ignoring(callEvent).getCallee();
            ignoring(callEvent).getCaller();
            oneOf(printer).printItem("01/01/70 01:00", "", "0:00", "0.02");
            oneOf(printer).printTotal("0.00");
        }});

        billGenerator.send(printer, customerInterface, lineItemList, "0.00");


    }
}


