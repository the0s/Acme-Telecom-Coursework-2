package com.acmetelecom;

import com.acmetelecom.billingsystem.AbstractBillingSystem;
import com.acmetelecom.configs.ProdConfig;

public class ProductionFactory extends AbstractFactory {
	
    public AbstractBillingSystem createBillingSystem() {
        return ProdConfig.billingSystem;
    }
}
