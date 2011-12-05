package com.acmetelecom.factories;

import com.acmetelecom.billingsystems.AbstractBillingSystem;
import com.acmetelecom.billingsystems.BillingSystem;
import com.acmetelecom.billingsystems.Logger;
import com.acmetelecom.billingsystems.Report;

public class ProductionFactory extends AbstractFactory {
	
	public AbstractBillingSystem createBillingSystem(Logger logger, Report report){
		return new BillingSystem(logger,report);
	}
}
