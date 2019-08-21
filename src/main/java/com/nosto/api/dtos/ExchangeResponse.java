package com.nosto.api.dtos;

import lombok.Data;

/**
 * Represents the response of our endpoint:
 * - convertedValueFormat: the value in String i18n format
 * - timeElapsed: time of processing the calculation and collecting the rate from ExchangeRateAPI
 */
@Data
public class ExchangeResponse {

    private final String convertedValueFormat;
    private final long timeElapsed;

}
