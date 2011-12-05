package com.acmetelecom;

import com.acmetelecom.billingsystems.AbstractBillingSystem;
import com.acmetelecom.billingsystems.BillGeneratorInterface;
import com.acmetelecom.billingsystems.Logger;
import com.acmetelecom.billingsystems.Report;

public abstract class AbstractFactory {
	
	public abstract AbstractBillingSystem createBillingSystem(BillGeneratorInterface billGenerator, Logger logger, Report report);

}
