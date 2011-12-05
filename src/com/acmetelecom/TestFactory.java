package com.acmetelecom;

import com.acmetelecom.billingsystems.AbstractBillingSystem;
import com.acmetelecom.billingsystems.BillGeneratorInterface;
import com.acmetelecom.billingsystems.Logger;
import com.acmetelecom.billingsystems.Report;
import com.acmetelecom.billingsystems.loggers.CallLogger;
import com.acmetelecom.billingsystems.reports.BillReport;
import com.acmetelecom.test.com.acmetelecom.fake.BillGeneratorFake;
import com.acmetelecom.test.com.acmetelecom.fake.BillingSystemFake;

public class TestFactory extends AbstractFactory {
	
	public AbstractBillingSystem createBillingSystem(){
		BillGeneratorInterface billGenerator = new BillGeneratorFake();
		Logger logger = new CallLogger();
		Report report = new BillReport();
		return new BillingSystemFake(billGenerator, logger, report);
	}

	
}
