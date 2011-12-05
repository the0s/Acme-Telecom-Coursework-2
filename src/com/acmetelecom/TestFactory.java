package com.acmetelecom;

import com.acmetelecom.billingsystems.AbstractBillingSystem;
import com.acmetelecom.billingsystems.BillGeneratorInterface;
import com.acmetelecom.billingsystems.Logger;
import com.acmetelecom.billingsystems.Report;
import com.acmetelecom.test.com.acmetelecom.fake.BillGeneratorFake;
import com.acmetelecom.test.com.acmetelecom.fake.BillingSystemFake;

public class TestFactory extends AbstractFactory {
	
	public AbstractBillingSystem createBillingSystem(BillGeneratorInterface billGenerator, Logger logger, Report report){
		return new BillingSystemFake(new BillGeneratorFake(),logger,report);
	}

	
}
