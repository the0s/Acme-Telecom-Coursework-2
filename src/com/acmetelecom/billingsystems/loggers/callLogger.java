package com.acmetelecom.billingsystems.loggers;

import com.acmetelecom.billingsystems.CallEvent;
import com.acmetelecom.billingsystems.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 05/12/11
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
public class CallLogger implements Logger{
     private List<CallEvent> callLog = new ArrayList<CallEvent>();

     public CallLogger(){}

    public List<CallEvent> getEvents() {
        return this.callLog;
            }

    public void clear() {
        this.callLog.clear();
    }

    public void add(CallEvent callEvent) {
        this.callLog.add(callEvent);
    }
}
