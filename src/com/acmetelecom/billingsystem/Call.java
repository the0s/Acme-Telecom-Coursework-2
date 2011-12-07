package com.acmetelecom.billingsystem;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Call {
    private CallEventInterface start;
    private CallEventInterface end;

    public Call(CallEventInterface start, CallEventInterface end) {
        this.start = start;
        this.end = end;
    }

    public String caller() {
        return start.getCaller();
    }

    public String callee() {
        return start.getCallee();
    }

    public int durationSeconds() {
        return (int) (((end.time() - start.time()) / 1000));
    }

    public String date() {
        return SimpleDateFormat.getInstance().format(new Date(start.time()));
    }

    public Date startTime() {
        return new Date(start.time());
    }

    public Date endTime() {
        return new Date(end.time());
    }


}
