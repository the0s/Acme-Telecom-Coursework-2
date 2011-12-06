package com.acmetelecom.billingsystem.customers;

import java.math.BigDecimal;

public interface TariffDatabaseInterface {
	
	public BigDecimal getOffPeakRateFor(CustomerInterface customer);
	
	public BigDecimal getPeakRateFor(CustomerInterface customer);
}
