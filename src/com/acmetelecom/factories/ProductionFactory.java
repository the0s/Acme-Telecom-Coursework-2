package com.acmetelecom.factories;

import com.acmetelecom.billingsystems.AbstractBillingSystem;
import com.acmetelecom.billingsystems.BillingSystem;

public class ProductionFactory extends AbstractFactory {
	
	public AbstractBillingSystem createBillingSystem(){
		return new BillingSystem();
	}
}
