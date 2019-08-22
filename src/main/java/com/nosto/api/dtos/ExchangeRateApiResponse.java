package com.nosto.api.dtos;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the answer provided by https://api.exchangeratesapi.io/latest?base={fromCcy}&symbols={toCcy}
 * Example answer:
 * {
 *     "rates": {
 *         "EUR": 0.222424876
 *     },
 *     "base": "BRL",
 *     "date": "2019-08-20"
 * }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateApiResponse {

    private String base;
    private String date;

    @Setter(AccessLevel.NONE)
    private final Map<String, Double> rates = new HashMap<>();

    @JsonAnySetter
    public void addCurrencyRate(String currencyCode, Double rate) {
        this.rates.put(currencyCode, rate);
    }

    public Double getCurrencyRate(String ccyCode) {
        return rates.get(ccyCode);
    }
}
