package com.nosto.api.controller;

import com.nosto.api.service.ExchangeService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log
public class CurrencyExchangeController {

    private final ExchangeService service;

    @GetMapping(value = "/exchange/from/{fromCcy}/to/{toCcy}/value/{value}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBankStatement (@PathVariable("fromCcy") String fromCcy,
                                            @PathVariable("toCcy") String toCcy,
                                            @PathVariable("value") double value) {
        try {
            return new ResponseEntity<>(service.exchangeCurrencyValue(fromCcy, toCcy, value), HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            log.info(ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.severe(e.getMessage());
            return new ResponseEntity<>("Unexpected error, contact the support and provide the used request.",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/ping",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String ping () {
        return "ok";
    }

}
