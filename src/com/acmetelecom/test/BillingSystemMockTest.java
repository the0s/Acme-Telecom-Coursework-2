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

import java.util.ArrayList;
import java.util.List;

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
    Call call = context.mock(Call.class);

    @Test
    public void testClear() {
        context.checking(new Expectations() {{
            oneOf(logger).clear();
            oneOf(report).clear();
        }});
        BillingSystemFake bsf = new BillingSystemFake(billGeneratorInterface, logger, report, customerDatabase, tariffDatabase, printer);
        bsf.clear();
    }

    @Test
    public void testCreateCustomerBills() {
        final List<CustomerInterface> customerInterfaceList = new ArrayList<CustomerInterface>();
        final List<Call> callList = new ArrayList<Call>();
        customerInterfaceList.add(customerInterface);
        callList.add(call);
        context.checking(new Expectations() {{
            allowing(customerDatabase).getCustomers();
            will(returnValue(customerInterfaceList));
            allowing(logger).getCallsDetailsOf(customerInterface);
            will(returnValue(callList));
            oneOf(logger).clear();
        }});
        BillingSystemFake bsf = new BillingSystemFake(billGeneratorInterface, logger, report, customerDatabase, tariffDatabase, printer);
        bsf.createCustomerBills();
    }


}
