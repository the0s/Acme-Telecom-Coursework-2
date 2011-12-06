package com.acmetelecom;

import com.acmetelecom.billingsystem.AbstractBillingSystem;
import com.acmetelecom.configs.TestConfig;

public class TestFactory extends AbstractFactory {
	
	public AbstractBillingSystem createBillingSystem(){
        return TestConfig.billingSystem;
	}	
}
