package com.acmetelecom.factories;

import com.acmetelecom.billingsystems.AbstractBillingSystem;

public abstract class AbstractFactory {
	
	public abstract AbstractBillingSystem createBillingSystem();

}
