package com.acmetelecom;

import com.acmetelecom.billingsystem.AbstractBillingSystem;
import com.acmetelecom.configs.ProdConfig;

public class ProductionFactory extends AbstractFactory {
	
    public AbstractBillingSystem createBillingSystem() {
    	//BillGeneratorInterface billGenerator = new BillGenerator();
    	//Logger logger = new CallLogger();
    	//Report report = new BillReport();
        //return new BillingSystem(billGenerator,logger, report);
        return ProdConfig.billingSystem;
    }
}
