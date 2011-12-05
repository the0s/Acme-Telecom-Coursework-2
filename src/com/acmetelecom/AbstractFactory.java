package com.acmetelecom;

import com.acmetelecom.billingsystems.AbstractBillingSystem;

public abstract class AbstractFactory {
	
	public abstract AbstractBillingSystem createBillingSystem();

}
