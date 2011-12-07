package com.acmetelecom.test;

import com.acmetelecom.billingsystem.*;
import com.acmetelecom.billingsystem.customers.CustomerDatabaseInterface;
import com.acmetelecom.billingsystem.customers.CustomerInterface;
import com.acmetelecom.billingsystem.customers.TariffDatabaseInterface;
import com.acmetelecom.test.com.acmetelecom.fake.BillingSystemFake;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;


public class BillingSystemMockTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();
    Logger logger = context.mock(Logger.class);
    BillGeneratorInterface billGeneratorInterface = context.mock(BillGeneratorInterface.class);
    Report report = context.mock(Report.class);
    CustomerDatabaseInterface customerDatabase = context.mock(CustomerDatabaseInterface.class);
    TariffDatabaseInterface tariffDatabase = context.mock(TariffDatabaseInterface.class);
    Printer printer = context.mock(Printer.class);
    CustomerInterface customerInterface = context.mock(CustomerInterface.class);
    CallEventInterface callEvent = context.mock(CallEventInterface.class);
    Call call = new Call(callEvent, callEvent);

    @Test
    public void testClear() {
        context.checking(new Expectations() {{
            oneOf(logger).clear();
            oneOf(report).clear();
        }});
        BillingSystemFake bsf = new BillingSystemFake(billGeneratorInterface, logger, report, customerDatabase, tariffDatabase, printer);
        bsf.clear();
    }

//    @Test
//    public void testCreateCustomerBills() {
//        final List<CustomerInterface> customerInterfaceList = new ArrayList<CustomerInterface>();
//        final List<Call> callList = new ArrayList<Call>();
//        customerInterfaceList.add(customerInterface);
//        callList.add(call);
//        final Long d = new Long(0);
//        final BigDecimal c = new BigDecimal(d);
//        context.checking(new Expectations() {{
//            allowing(customerDatabase).getCustomers();will(returnValue(customerInterfaceList));
//            allowing(logger).getCallsDetailsOf(customerInterface);will(returnValue(callList));
//            allowing(callEvent).time();will(returnValue(d));
//            allowing(tariffDatabase).getOffPeakRateFor(customerInterface);will(returnValue(c));
//            allowing(tariffDatabase).getPeakRateFor(customerInterface);will(returnValue(c));
//            allowing(report).add(new LineItem(call,c));
//            oneOf(logger).clear();
//        }});
//        BillingSystemFake bsf = new BillingSystemFake(billGeneratorInterface, logger, report, customerDatabase, tariffDatabase, printer);
//        bsf.createCustomerBills();
//    }


}
