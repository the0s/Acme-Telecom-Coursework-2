package com.acmetelecom;

import com.acmetelecom.billingsystem.*;
import com.acmetelecom.billingsystem.billinggenerators.BillGenerator;
import com.acmetelecom.billingsystem.loggers.CallLogger;
import com.acmetelecom.billingsystem.reports.BillReport;

public class ProductionFactory extends AbstractFactory {
	
    public AbstractBillingSystem createBillingSystem() {
    	BillGeneratorInterface billGenerator = new BillGenerator();
    	Logger logger = new CallLogger();
    	Report report = new BillReport();
        return new BillingSystem(billGenerator,logger, report);
    }
}
