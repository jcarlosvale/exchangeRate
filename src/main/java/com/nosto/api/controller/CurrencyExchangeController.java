package com.nosto.api.controller;

import com.nosto.api.dtos.ExchangeResponse;
import com.nosto.api.service.ExchangeService;
import com.sun.javaws.exceptions.InvalidArgumentException;
import feign.FeignException;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

/**
 * The main controller exposing the endpoint /exchange/from/{fromCcy}/to/{toCcy}/value/{value}
 */
@RestController
@AllArgsConstructor
@Log
public class CurrencyExchangeController {

    private final ExchangeService service;

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/exchange/from/{fromCcy}/to/{toCcy}/value/{value}",
            produces = TEXT_PLAIN_VALUE)
    public ResponseEntity getExchangeValue (@PathVariable("fromCcy") String fromCcy,
                                            @PathVariable("toCcy") String toCcy,
                                            @PathVariable("value") double value) {
        try {
            ExchangeResponse exchangeResponse = service.exchangeCurrencyValue(fromCcy, toCcy, value);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Server-Timing", "app;dur=" + exchangeResponse.getTimeElapsed());
            return ResponseEntity.ok().headers(responseHeaders).body(exchangeResponse.getConvertedValueFormat());
        } catch (IllegalArgumentException e) {
            log.info(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (FeignException e) {
            return new ResponseEntity<>(null, HttpStatus.valueOf(e.status()));
        } catch (Exception e) {
            log.severe(e.getMessage());
            return new ResponseEntity<>("Unexpected error, contact the support and provide the used request.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
