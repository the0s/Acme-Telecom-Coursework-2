package com.acmetelecom;

import com.acmetelecom.billingsystems.AbstractBillingSystem;
import com.acmetelecom.billingsystems.BillGeneratorInterface;
import com.acmetelecom.billingsystems.BillingSystem;
import com.acmetelecom.billingsystems.billinggenerators.BillGenerator;

public class ProductionFactory extends AbstractFactory {
	
	public AbstractBillingSystem createBillingSystem(){
		BillGeneratorInterface billGenerator= new BillGenerator();
		return new BillingSystem(billGenerator);
	}
}
