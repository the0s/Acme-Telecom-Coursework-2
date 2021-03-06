package com.acmetelecom.billingsystem;


import com.acmetelecom.billingsystem.utils.CustomDate;

public class CallStart extends CallEvent {
    public CallStart(String caller, String callee) {
        super(caller, callee, System.currentTimeMillis());
    }
    
    public CallStart(String caller, String callee, long customTimestamp) {
        super(caller, callee, customTimestamp); 
    }
    
    //Convenience constructor to allow user to input human readable dates
    public CallStart(String caller, String callee, CustomDate customDateTime) {
        super(caller, callee, customDateTime.getDate().getTime()); 
    }
    
}
