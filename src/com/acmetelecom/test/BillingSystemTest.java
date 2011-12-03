package com.acmetelecom.test;


import com.acmetelecom.AbstractBillingSystem;
import com.acmetelecom.utils.BillingSystemFake;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 03/12/11
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */
public class BillingSystemTest {
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    //Customer customer = context.mock(Customer.class);


    @Test
    public void createAndCheckBillsForCustomersNonPeak() {
        List<Long> times = new ArrayList<Long>();
        times.add(new Long("1322950876823"));
        times.add(new Long("1322950877823"));
        AbstractBillingSystem billingSystem = new BillingSystemFake(times);
        billingSystem.callInitiated("447722113434", "447766814143");
        billingSystem.callCompleted("447722113434", "447766814143");
        billingSystem.createCustomerBills();
    }

//       @Test
//       public void createAndCheckBillsForCustomersPeak(){
//           List<Long> times = new ArrayList<Long>();
//           times.add(new Long("1322950876823"));
//           times.add(new Long("1322950877823"));
//           AbstractBillingSystem billingSystem = new BillingSystemFake(times);
//           billingSystem.callInitiated("447722113434", "447766814143");
//           billingSystem.callCompleted("447722113434", "447766814143");
//           billingSystem.createCustomerBills();
//       }

//       @Test
//       public void createAndCheckBillsForCustomersPeakToNonPeak(){
//           List<Long> times = new ArrayList<Long>();
//           times.add(new Long("1322950876823"));
//           times.add(new Long("1322950877823"));
//           AbstractBillingSystem billingSystem = new BillingSystemFake(times);
//           billingSystem.callInitiated("447722113434", "447766814143");
//           billingSystem.callCompleted("447722113434", "447766814143");
//           billingSystem.createCustomerBills();
//       }
//
//       @Test
//       public void createAndCheckBillsForCustomersNonPeakToPeak(){
//           List<Long> times = new ArrayList<Long>();
//           times.add(new Long("1322950876823"));
//           times.add(new Long("1322950877823"));
//           AbstractBillingSystem billingSystem = new BillingSystemFake(times);
//           billingSystem.callInitiated("447722113434", "447766814143");
//           billingSystem.callCompleted("447722113434", "447766814143");
//           billingSystem.createCustomerBills();
//       }
}
