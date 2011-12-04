package com.acmetelecom.test;


import com.acmetelecom.AbstractBillingSystem;
import com.acmetelecom.utils.BillingSystemFake;
import com.acmetelecom.utils.CustomDate;
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
    //@Rule
    //public final JUnitRuleMockery context = new JUnitRuleMockery();

    //Customer customer = context.mock(Customer.class);


    @Test
    public void createAndCheckBillsForCustomersNonPeak() {
        CustomDate startDate = new CustomDate(2011, 11, 11, 14, 00, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 14, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        AbstractBillingSystem billingSystem = new BillingSystemFake(times);
        billingSystem.callInitiated("447711232343", "447766814143");
        billingSystem.callCompleted("447711232343", "447766814143");
        billingSystem.createCustomerBills();
    }

    @Test
    public void createAndCheckBillsForCustomersPeak() {
        CustomDate startDate = new CustomDate(2011, 11, 11, 19, 00, 00);
        CustomDate endDate = new CustomDate(2011, 11, 11, 19, 20, 00);
        List<Long> times = new ArrayList<Long>();
        times.add(startDate.getDate().getTime());
        times.add(endDate.getDate().getTime());
        AbstractBillingSystem billingSystem = new BillingSystemFake(times);
        billingSystem.callInitiated("447711232343", "447766814143");
        billingSystem.callCompleted("447711232343", "447766814143");
        billingSystem.createCustomerBills();
    }

//       @Test
//       public void createAndCheckBillsForCustomersPeakToNonPeak(){
//           List<Long> times = new ArrayList<Long>();
//           times.add(new Long("1322950876823"));
//           times.add(new Long("1322950877823"));
//           AbstractBillingSystem billingSystem = new BillingSystemFake(times);
//        billingSystem.callInitiated("447711232343", "447766814143");
//        billingSystem.callCompleted("447711232343", "447766814143");
//           billingSystem.createCustomerBills();
//       }
//
//       @Test
//       public void createAndCheckBillsForCustomersNonPeakToPeak(){
//           List<Long> times = new ArrayList<Long>();
//           times.add(new Long("1322950876823"));
//           times.add(new Long("1322950877823"));
//           AbstractBillingSystem billingSystem = new BillingSystemFake(times);
//        billingSystem.callInitiated("447711232343", "447766814143");
//        billingSystem.callCompleted("447711232343", "447766814143");
//           billingSystem.createCustomerBills();
//       }
}
