package com.acmetelecom;

import org.junit.Test;

import java.math.BigDecimal;
import static junit.framework.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: Andreas
 * Date: 03/12/11
 * Time: 20:42
 * To change this template use File | Settings | File Templates.
 */
public class MoneyFormatterTest {
    @Test
    public void testPenceToPounds() throws Exception {
        BigDecimal pence = new BigDecimal(100);
        String pounds = MoneyFormatter.penceToPounds(pence);
        assertTrue(pounds.equals("1.00"));
    }
}
