package com.nosto.api.dtos;

import lombok.Data;

@Data
public class ExchangeResponse {

    private final String convertedValueFormat;
    private final long timeElapsed;

}
