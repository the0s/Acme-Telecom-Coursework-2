package com.acmetelecom.configs;

import com.acmetelecom.billingsystem.*;
import com.acmetelecom.billingsystem.billinggenerators.BillGenerator;
import com.acmetelecom.billingsystem.loggers.CallLogger;
import com.acmetelecom.billingsystem.reports.BillReport;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 05/12/11
 * Time: 21:40
 * To change this template use File | Settings | File Templates.
 */
public class ProdConfig {
   private final static BillGeneratorInterface billGenerator = new BillGenerator();
   private final static Logger logger = new CallLogger();
   private final static Report report = new BillReport();
   public final static AbstractBillingSystem billingSystem = new BillingSystem(billGenerator,logger,report);
}
