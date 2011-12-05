package com.acmetelecom;

import com.acmetelecom.billingsystems.*;

public class ProductionFactory extends AbstractFactory {
	

    public AbstractBillingSystem createBillingSystem(BillGeneratorInterface billGenerator, Logger logger, Report report) {
        return new BillingSystem(billGenerator,logger,report);
    }
}
