package com.acmetelecom;

import com.acmetelecom.billingsystem.AbstractBillingSystem;
import com.acmetelecom.billingsystem.BillGeneratorInterface;
import com.acmetelecom.billingsystem.Logger;
import com.acmetelecom.billingsystem.Report;
import com.acmetelecom.billingsystem.loggers.CallLogger;
import com.acmetelecom.billingsystem.reports.BillReport;
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
