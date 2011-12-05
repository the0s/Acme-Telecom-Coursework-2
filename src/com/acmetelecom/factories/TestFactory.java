package com.acmetelecom.factories;

import com.acmetelecom.billingsystems.AbstractBillingSystem;
import com.acmetelecom.test.com.acmetelecom.fake.BillingSystemFake;

public class TestFactory extends AbstractFactory {
	
	public AbstractBillingSystem createBillingSystem(){
		
		return new BillingSystemFake();
	}

	
}
