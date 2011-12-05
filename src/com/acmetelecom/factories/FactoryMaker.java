package com.acmetelecom.factories;

public class FactoryMaker {
	
	private static AbstractFactory factory = null;
	
	public static AbstractFactory getProductionFactory(){
		factory = new ProductionFactory();
		return factory;
	}
	
	public static AbstractFactory getTestFactory(){
		factory = new TestFactory();
		return factory;
	}

}
