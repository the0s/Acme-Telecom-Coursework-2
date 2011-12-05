package com.acmetelecom;

import com.acmetelecom.billingsystems.*;
import com.acmetelecom.billingsystems.billinggenerators.BillGenerator;
import com.acmetelecom.billingsystems.loggers.CallLogger;
import com.acmetelecom.billingsystems.reports.BillReport;

public class ProductionFactory extends AbstractFactory {
	
    public AbstractBillingSystem createBillingSystem() {
    	BillGeneratorInterface billGenerator = new BillGenerator();
    	Logger logger = new CallLogger();
    	Report report = new BillReport();
        return new BillingSystem(billGenerator,logger, report);
    }
}
