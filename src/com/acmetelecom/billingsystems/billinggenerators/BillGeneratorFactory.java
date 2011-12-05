package com.acmetelecom.billingsystems.billinggenerators;

import com.acmetelecom.test.com.acmetelecom.fake.BillGeneratorFake;

public class BillGeneratorFactory {
	
	public static BillGeneratorInterface createBillGenerator(){
		return new BillGenerator();
	}
	
	public static BillGeneratorInterface createBillGeneratorFake(){
		return new BillGeneratorFake();
	}
	
}

