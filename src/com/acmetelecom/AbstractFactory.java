package com.acmetelecom;

import com.acmetelecom.billingsystem.AbstractBillingSystem;

public abstract class AbstractFactory {
	
	public abstract AbstractBillingSystem createBillingSystem();

}
