package com.acmetelecom.factories;

import com.acmetelecom.billingsystems.AbstractBillingSystem;
import com.acmetelecom.billingsystems.Logger;
import com.acmetelecom.billingsystems.Report;

public abstract class AbstractFactory {
	
	public abstract AbstractBillingSystem createBillingSystem(Logger logger, Report report);

}
