package com.acmetelecom.utils;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 05/12/11
 * Time: 20:50
 * To change this template use File | Settings | File Templates.
 */
public enum DatetimePeakPeriodEnum {
    PEAKSTART(7),
    PEAKEND(19);

  private int time;

 private DatetimePeakPeriodEnum(int d) {
   this.time = d;
 }

 public int getDatetimePeakPeriodTime() {
   return time;
 }
}
