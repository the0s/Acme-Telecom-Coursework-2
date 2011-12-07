package com.acmetelecom.test;

import com.acmetelecom.billingsystem.utils.CustomDate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: Andreas
 * Date: 07/12/11
 * Time: 03:24
 * To change this template use File | Settings | File Templates.
 */
public class CustomDateTest {
    @Test
    public void testCustomDate() {
        CustomDate customDate = new CustomDate(11, 12, 07, 23, 00, 00);
        assertEquals(customDate.getDate().toString(), "Thu Jan 07 23:00:00 GMT 12");

    }

}
