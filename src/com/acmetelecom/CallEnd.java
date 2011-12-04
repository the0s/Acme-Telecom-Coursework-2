package com.acmetelecom;

import java.util.Date;

import com.acmetelecom.utils.CustomDate;

public class CallEnd extends CallEvent {
    public CallEnd(String caller, String callee) {
        super(caller, callee, System.currentTimeMillis());
    }
    
    public CallEnd(String caller, String callee, long customTimestamp) {
        super(caller, callee, customTimestamp);
    }
    
    //Convenience constructor to allow user to input human readable dates
    public CallEnd(String caller, String callee, CustomDate customDateTime) {
        super(caller, callee, customDateTime.getDate().getTime()); 
    }
    
    //Convenience constructor to allow user to input human readable dates
    public CallEnd(String caller, String callee, Date date) {
        super(caller, callee, date.getTime()); 
    }
}
