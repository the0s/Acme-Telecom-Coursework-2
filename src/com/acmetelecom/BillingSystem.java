package com.acmetelecom;

public class BillingSystem extends AbstractBillingSystem {

    @Override
    public void callInitiated(String caller, String callee) {
        callLog.add(new CallStart(caller, callee));
    }

    @Override
    public void callCompleted(String caller, String callee) {
        callLog.add(new CallEnd(caller, callee));
    }

}
