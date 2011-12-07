package com.acmetelecom.test;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 07/12/11
 * Time: 01:49
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({BillingSystemTest.class,
        CallTest.class,
        DaytimePeakPeriodTest.class,
        LineItemTest.class,
        MoneyFormatterTest.class,
        BillGeneratorMockTest.class,
        BillingSystemMockTest.class,
        CustomDateTest.class})
public class TestSuite {
}


