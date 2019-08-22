package com.nosto.api.service;

import com.nosto.api.dtos.ExchangeRateApiResponse;
import com.nosto.api.dtos.ExchangeResponse;
import com.nosto.api.rest.client.ForeignExchangeRestClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExchangeServiceTest {

    private ExchangeService service;
    private ForeignExchangeRestClient foreignExchangeRestClient;

    @Before
    public void initialize() {
        foreignExchangeRestClient = Mockito.mock(ForeignExchangeRestClient.class);
        service = new ExchangeService(foreignExchangeRestClient);
    }

    @Test
    public void testConversion() {
        String fromCcy = "BRL";
        String toCcy = "EUR";
        ExchangeRateApiResponse exchangeRateApiResponse = mock(ExchangeRateApiResponse.class);
        when(foreignExchangeRestClient.getExchangeRate(fromCcy, toCcy)).thenReturn(exchangeRateApiResponse);
        when(exchangeRateApiResponse.getCurrencyRate(toCcy)).thenReturn(5.5);
        ExchangeResponse actual = service.exchangeCurrencyValue(fromCcy, toCcy, 10.0);
        ExchangeResponse expected = new ExchangeResponse("55,00 €", 10);
        assertEquals(expected.getConvertedValueFormat(), actual.getConvertedValueFormat());
    }



}