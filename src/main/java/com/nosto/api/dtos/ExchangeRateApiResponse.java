package com.nosto.api.dtos;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class ExchangeRateApiResponse {

    private String base;
    private String date;

    @Setter(AccessLevel.NONE)
    private final Map<String, Float> rates = new HashMap<>();

    @JsonAnySetter
    public void addCurrencyRate(String currencyCode, float rate) {
        this.rates.put(currencyCode, rate);
    }

    public float getCurrencyRate(String ccyCode) {
        return rates.get(ccyCode);
    }
}
