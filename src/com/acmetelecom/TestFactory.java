package com.acmetelecom;

import com.acmetelecom.billingsystem.AbstractBillingSystem;
import com.acmetelecom.configs.TestConfig;

public class TestFactory extends AbstractFactory {
	
	public AbstractBillingSystem createBillingSystem(){
//		BillGeneratorInterface billGenerator = TestConfig.billGenerator;//new BillGeneratorFake();
//		Logger logger = TestConfig.logger;//new CallLogger();
//		Report report = TestConfig.report;//new BillReport();
//		return new BillingSystemFake(billGenerator, logger, report);
        return TestConfig.billingSystem;
	}

	
}
