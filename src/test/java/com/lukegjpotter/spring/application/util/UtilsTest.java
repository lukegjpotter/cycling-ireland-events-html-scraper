package com.lukegjpotter.spring.application.util;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class UtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test(expected = NullPointerException.class)
    public void testConvertStringToDateString_NPECheck() {
        Date npeDate = Utils.convertStringToDate("Contiune-Commit-Streak");
        assertTrue(npeDate.equals(new Date()));
    }

    @Test(expected = NullPointerException.class)
    public void testConvertStringToDateStringString_NPECheck() {
        Date npeDate = Utils.convertStringToDate("Contiune-Commit-Streak", Constants.DATE_FORMAT_DDMMYYYY);
        assertTrue(npeDate.equals(new Date()));
    }

}
