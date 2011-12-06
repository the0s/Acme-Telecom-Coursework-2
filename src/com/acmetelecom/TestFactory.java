package com.acmetelecom;

import com.acmetelecom.billingsystem.AbstractBillingSystem;

public class TestFactory extends AbstractFactory {

    public AbstractBillingSystem createBillingSystem() {
        return TestConfig.billingSystem;
    }
}
