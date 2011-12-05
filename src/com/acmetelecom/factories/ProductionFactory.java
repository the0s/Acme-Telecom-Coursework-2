package com.acmetelecom.factories;

import com.acmetelecom.AbstractBillingSystem;
import com.acmetelecom.BillingSystem;

public class ProductionFactory extends AbstractFactory {
	
	public AbstractBillingSystem createBillingSystem(){
		return new BillingSystem();
	}
}
