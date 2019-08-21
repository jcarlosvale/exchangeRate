package com.nosto.api.rest.client;

import com.nosto.api.dtos.ExchangeRateApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Rest Client using the Feign Rest Client,
 * using the single endpoint https://api.exchangeratesapi.io/latest?base={fromCcy}&symbols={toCcy}
 */
@FeignClient(name="foreignExchangeRestClient", url = "https://api.exchangeratesapi.io/")
public interface ForeignExchangeRestClient {

    @RequestMapping(method = RequestMethod.GET, value = "/latest?base={fromCcy}&symbols={toCcy}")
    ExchangeRateApiResponse getExchangeRate(@PathVariable("fromCcy") String fromCcy,
                                            @PathVariable("toCcy") String toCcy);
}
