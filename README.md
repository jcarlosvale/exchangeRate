# Currency Exchange
by João Carlos (https://www.linkedin.com/in/joaocarlosvale/)

This project consists of an application that leverage the exchange rates provided at https://exchangeratesapi.io/ and leverage 
returning a converted value.

## Logic applied:
The API collect the exchange rates provided at https://exchangeratesapi.io/ and returns a converted value. 
If it receives an input as (30, USD and GBP), 30 USD will be converted to GBP.

The application provides an endpoint and using path parameters it is possible to convert a value from one currency code 
to another currency code. The result is a String in i18n format.
Applied a simple structure: a Controller receives the input, calls the Service that provides the integration with the 
ExchangeRatesApi by Feign Rest Client and calculates the new value.

The used endpoint from ExchangeRatesApi: https://api.exchangeratesapi.io/latest?base={fromCcy}&symbols={toCcy}
* fromCcY: origin or base currency
* toCcy: currency to collect the rate

## Technologies used:
* Java
* Spring
* Lombok
* Feign Client
* Maven 

## Main Endpoint

``` 
/exchange/from/{fromCcy}/to/{toCcy}/value/{value}  GET method
``` 
- fromCcy: from currency code or the currency code base
- toCcy: the currency code to convert the value
- value: value to be converted

## Examples:

### Application answer

* Input
```
http://localhost:8080/exchange/from/BRL/to/JPY/value/10
```

* Output

Body
```
￥262
```

Header
```
Server-Timing: app;dur=904
```

### ExchangeRatesApi answer
Getting rate fromCcy BRL to toCcy EUR
https://api.exchangeratesapi.io/latest?base=BRL&symbols=EUR
```json
{
    "rates": {
        "EUR": 0.222424876
    },
    "base": "BRL",
    "date": "2019-08-20"
}
``` 

## Commands:

To generate JAR:

    mvn clean package

To run:

    java -jar target/currencyExchange-0.0.1.jar
    
To run tests:

    mvn test
