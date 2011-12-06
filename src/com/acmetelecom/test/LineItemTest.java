package com.acmetelecom.test;

import com.acmetelecom.billingsystem.Call;
import com.acmetelecom.billingsystem.CallEnd;
import com.acmetelecom.billingsystem.CallStart;
import com.acmetelecom.billingsystem.LineItem;
import com.acmetelecom.utils.CustomDate;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Andreas
 * Date: 04/12/11
 * Time: 17:48
 * To change this template use File | Settings | File Templates.
 */
public class LineItemTest {
    private CallStart start;
    private CallEnd end;
    private Call call;
    private LineItem lineItem;
    BigDecimal callCost;

    @Before
    public void setUp() throws Exception {

        CustomDate startDate = new CustomDate(2011, 11, 11, 11, 11, 11);
        CustomDate endDate = new CustomDate(2011, 11, 11, 11, 13, 11);
        this.start = new CallStart("Caller", "Callee", startDate);
        this.end = new CallEnd("Caller", "Callee", endDate);
        this.call = new Call(this.start, this.end);
        callCost = new BigDecimal(10);
        lineItem = new LineItem(call, callCost);


    }

    @Test
    public void testDate() throws Exception {
        assertEquals(lineItem.date(), "11/12/11 11:11");

    }

    @Test
    public void testCallee() throws Exception {
        assertEquals(lineItem.callee(), "Callee");

    }

    @Test
    public void testDurationMinutes() throws Exception {
        assertEquals(lineItem.durationMinutes(), "2:00");

    }

    @Test
    public void testCost() throws Exception {
        assertEquals(lineItem.cost().toString(), "10");

    }
}
