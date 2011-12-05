package com.acmetelecom.factories;

import com.acmetelecom.billingsystems.AbstractBillingSystem;
import com.acmetelecom.billingsystems.Logger;
import com.acmetelecom.billingsystems.Report;
import com.acmetelecom.test.com.acmetelecom.fake.BillingSystemFake;

public class TestFactory extends AbstractFactory {
	
	public AbstractBillingSystem createBillingSystem(Logger logger, Report report){
		
		return new BillingSystemFake(logger, report);
	}

	
}
