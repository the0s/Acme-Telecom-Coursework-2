package com.acmetelecom.billingsystem;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 06/12/11
 * Time: 23:50
 * To change this template use File | Settings | File Templates.
 */
public interface CallEventInterface {
    String getCaller();

    String getCallee();

    long time();
}
