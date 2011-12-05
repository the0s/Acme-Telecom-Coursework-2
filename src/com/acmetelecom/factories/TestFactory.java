package com.acmetelecom.factories;

import com.acmetelecom.AbstractBillingSystem;
import com.acmetelecom.utils.BillingSystemFake;

public class TestFactory extends AbstractFactory {
	
	public AbstractBillingSystem createBillingSystem(){
		
		return new BillingSystemFake();
	}

	
}
