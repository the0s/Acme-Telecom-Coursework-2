package com.acmetelecom.billingsystem;

public abstract class CallEvent implements CallEventInterface {
    private String caller;
    private String callee;
    private long time;

    public CallEvent(String caller, String callee, long timeStamp) {
        this.caller = caller;
        this.callee = callee;
        this.time = timeStamp;
    }

    @Override
    public String getCaller() {
        return caller;
    }

    @Override
    public String getCallee() {
        return callee;
    }

    @Override
    public long time() {
        return time;
    }
}
