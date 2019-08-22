package com.nosto.api.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void testFormatValue() {
        String actual = Util.formatValue(100, "EUR");
        String expected = "100,00 €";
        assertEquals(expected, actual);

        actual = Util.formatValue(100, "BRL");
        expected = "R$ 100,00";
        assertEquals(expected, actual);
    }

    @Test
    public void testNotCurrencyFormat() {
        String actual = Util.formatValue(100, "XXX");
        assertNotNull(actual);
    }

}