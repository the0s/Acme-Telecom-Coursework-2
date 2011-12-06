package com.acmetelecom;

import com.acmetelecom.billingsystem.AbstractBillingSystem;

public class ProductionFactory extends AbstractFactory {

    public AbstractBillingSystem createBillingSystem() {
        return ProdConfig.billingSystem;
    }
}
