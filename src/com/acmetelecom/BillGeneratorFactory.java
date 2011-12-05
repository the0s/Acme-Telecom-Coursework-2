package com.acmetelecom;

import com.acmetelecom.utils.BillGeneratorFake;

public class BillGeneratorFactory {
	
	public static BillGeneratorInterface createBillGenerator(){
		return new BillGenerator();
	}
	
	public static BillGeneratorInterface createBillGeneratorFake(){
		return new BillGeneratorFake();
	}
	
}
