package com.nosto.api.service;

import com.nosto.api.dtos.ExchangeRateApiResponse;
import com.nosto.api.dtos.ExchangeResponse;
import com.nosto.api.rest.client.ForeignExchangeRestClient;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

/**
 * Main service responsible by the logic: retrieve the rates from the external ExchangeRatesApi and calculate the value
 * from a Currency to a new Currency.
 */
@Service
@AllArgsConstructor
public class ExchangeService {

    final ForeignExchangeRestClient foreignExchangeRestClient;

    /**
     *
     * @param fromCcy currency code from
     * @param toCcy  currency code to
     * @param value the value to be converted
     * @return the Response of processing with the i118n String and the time elapsed in the processing
     */
    public ExchangeResponse exchangeCurrencyValue(@NonNull String fromCcy, @NonNull String toCcy, @NonNull Double value) {
        validate(value);
        fromCcy = fromCcy.toUpperCase();
        toCcy = toCcy.toUpperCase();
        long before = System.currentTimeMillis();
        ExchangeRateApiResponse exchangeRateApiResponse = foreignExchangeRestClient.getExchangeRate(fromCcy, toCcy);
        double convertedValue = value * exchangeRateApiResponse.getCurrencyRate(toCcy);
        String convertedValueFormat = Util.formatValue(convertedValue, toCcy);
        return new ExchangeResponse(convertedValueFormat, System.currentTimeMillis() - before);
    }

    private void validate(double value) {
        if (value <= 0)  {
            throw new IllegalArgumentException("Negative value.");
        }
    }
}
