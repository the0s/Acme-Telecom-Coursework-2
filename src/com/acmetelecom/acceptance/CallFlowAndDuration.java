package com.acmetelecom.acceptance;

import com.acmetelecom.billingsystem.Call;
import com.acmetelecom.billingsystem.CallEnd;
import com.acmetelecom.billingsystem.CallEventInterface;
import com.acmetelecom.billingsystem.CallStart;
import fit.ActionFixture;

public class CallFlowAndDuration extends ActionFixture {

    private Call call;
    private CallEventInterface start;
    private CallEventInterface end;

    public void startCall() {
        start = new CallStart("Caller", "Callee");
    }

    public void speakForSoManySeconds(int duration) throws InterruptedException {
        Thread.sleep(duration * 1000);
    }

    public void endCall() {
        end = new CallEnd("Caller", "Callee");
    }

    public int durationOf() {
        call = new Call(start, end);
        return call.durationSeconds();
    }


}