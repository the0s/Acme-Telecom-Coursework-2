package com.acmetelecom;

import com.acmetelecom.billingsystem.AbstractBillingSystem;


/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 16/11/11
 * Time: 23:13
 * To change this template use File | Settings | File Templates.
 */
public class Runner {
    public static void main(String[] args) throws Exception {
        System.out.println("Running...");
        AbstractFactory factory = FactoryMaker.getProductionFactory();
        //AbstractBillingSystem billingSystem = factory.createBillingSystem(new BillGenerator(), new CallLogger(), new BillReport());
        AbstractBillingSystem billingSystem = factory.createBillingSystem();

        billingSystem.callInitiated("447722113434", "447766814143");
        sleepSeconds(20);
        billingSystem.callCompleted("447722113434", "447766814143");
        billingSystem.callInitiated("447722113434", "447711111111");
        sleepSeconds(30);
        billingSystem.callCompleted("447722113434", "447711111111");
        billingSystem.callInitiated("447777765432", "447711111111");
        sleepSeconds(60);
        billingSystem.callCompleted("447777765432", "447711111111");
        billingSystem.createCustomerBills();
    }
    private static void sleepSeconds(int n) throws InterruptedException {
        Thread.sleep(n * 1000);
    }
}
