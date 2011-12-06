package com.acmetelecom.test;

import com.acmetelecom.billingsystem.BillGeneratorInterface;
import com.acmetelecom.billingsystem.Logger;
import com.acmetelecom.billingsystem.Printer;
import com.acmetelecom.billingsystem.Report;
import com.acmetelecom.billingsystem.customers.CustomerDatabaseInterface;
import com.acmetelecom.billingsystem.customers.TariffDatabaseInterface;
import com.acmetelecom.test.com.acmetelecom.fake.BillingSystemFake;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)


public class AbstractBillingSystemTest {
    Mockery context = new Mockery();
    Logger logger = context.mock(Logger.class);
    BillGeneratorInterface billGeneratorInterface = context.mock(BillGeneratorInterface.class);
    Report report = context.mock(Report.class);
    CustomerDatabaseInterface customerDatabase = context.mock(CustomerDatabaseInterface.class);
    TariffDatabaseInterface tariffDatabase = context.mock(TariffDatabaseInterface.class);
    Printer printer = context.mock(Printer.class);

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
//        context.checking(new Expectations() {{
//            oneOf(logger).clear();
//        }});
//        BillingSystemFake bsf = new BillingSystemFake(billGeneratorInterface, logger, report, customerDatabase, tariffDatabase, printer);
//        bsf.createCustomerBills();
//    }


}
