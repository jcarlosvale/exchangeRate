package com.nosto.api.service;

import com.nosto.api.dtos.ExchangeRateApiResponse;
import com.nosto.api.dtos.ExchangeResponse;
import com.nosto.api.rest.client.ForeignExchangeRestClient;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExchangeService {

    private final ForeignExchangeRestClient foreignExchangeRestClient;

    public ExchangeResponse exchangeCurrencyValue(@NonNull String fromCcy, @NonNull String toCcy, @NonNull Double value) {
        long before = System.currentTimeMillis();
        ExchangeRateApiResponse exchangeRateApiResponse = foreignExchangeRestClient.getExchangeRate(fromCcy, toCcy);
        double convertedValue = value * exchangeRateApiResponse.getCurrencyRate(toCcy);
        String convertedValueFormat = Util.formatValue(convertedValue, toCcy);
        return new ExchangeResponse(convertedValueFormat, System.currentTimeMillis() - before);
    }
}
