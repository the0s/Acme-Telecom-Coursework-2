package com.acmetelecom.billingsystems;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 05/12/11
 * Time: 15:15
 * To change this template use File | Settings | File Templates.
 */
public interface Logger {
    List<CallEvent> getEvents();
    void clear();
    void add(CallEvent callEvent);
}
