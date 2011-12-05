package com.acmetelecom.factories;

import com.acmetelecom.AbstractFactory;
import com.acmetelecom.billingsystems.*;
import com.acmetelecom.billingsystems.billinggenerators.BillGenerator;

public class ProductionFactory extends AbstractFactory {
	
	public AbstractBillingSystem createBillingSystem(BillGeneratorInterface billGenerator, Logger logger, Report report){
		return new BillingSystem(billGenerator,logger,report);
	}
}
