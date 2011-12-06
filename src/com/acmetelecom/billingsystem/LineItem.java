package com.acmetelecom.billingsystem;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 03/12/11
 * Time: 23:17
 * To change this template use File | Settings | File Templates.
 */
public class LineItem {
    private Call call;
    private BigDecimal callCost;

    public LineItem(Call call, BigDecimal callCost) {
        this.call = call;
        this.callCost = callCost;
    }

    public String date() {
        return call.date();
    }

    public String callee() {
        return call.callee();
    }
    
    public String caller() {
        return call.caller();
    }

    public String durationMinutes() {
        return "" + call.durationSeconds() / 60 + ":" + String.format("%02d", call.durationSeconds() % 60);
    }

    public BigDecimal cost() {
        return callCost;
    }
}