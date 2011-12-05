package com.acmetelecom;

import com.acmetelecom.billingsystem.AbstractBillingSystem;
import com.acmetelecom.billingsystem.BillGeneratorInterface;
import com.acmetelecom.billingsystem.Logger;
import com.acmetelecom.billingsystem.Report;
import com.acmetelecom.configs.TestConfig;
import com.acmetelecom.test.com.acmetelecom.fake.BillingSystemFake;

public class TestFactory extends AbstractFactory {
	
	public AbstractBillingSystem createBillingSystem(){
		BillGeneratorInterface billGenerator = TestConfig.billGenerator;//new BillGeneratorFake();
		Logger logger = TestConfig.logger;//new CallLogger();
		Report report = TestConfig.report;//new BillReport();
		return new BillingSystemFake(billGenerator, logger, report);
	}

	
}
