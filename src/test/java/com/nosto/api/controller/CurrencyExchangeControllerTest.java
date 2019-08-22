package com.nosto.api.controller;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CurrencyExchangeControllerTest {

    @LocalServerPort
    private int randomServerPort = 0;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(randomServerPort);

    @Test
    public void testExchange() {
        String fromCcy = "BRL";
        String toCcy = "EUR";
        String value = "10";
        stubFor(get(urlPathMatching("https://api.exchangeratesapi.io/latest?base="+fromCcy+"&symbols="+toCcy))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(getJson())));

        String url = "http://localhost:"+randomServerPort+"/exchange/from/"+fromCcy+"/to/"+toCcy+"/value/"+value;

        ResponseEntity<String> actualResponse =
                new RestTemplate().getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertNotNull(actualResponse.getHeaders().get("Server-Timing").get(0));
        assertEquals("2,24 €", actualResponse.getBody());
    }

    @Test(expected = HttpClientErrorException.BadRequest.class)
    public void testBadRequest() {
        String fromCcy = "BRL";
        String toCcy = "EUR";
        String value = "10";
        stubFor(get(urlPathMatching("https://api.exchangeratesapi.io/latest?base="+fromCcy+"&symbols="+toCcy))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(getJson())));

        String url = "http://localhost:"+randomServerPort+"/exchange/from/XXX/to/"+toCcy+"/value/"+value;

        ResponseEntity<String> actualResponse =
                new RestTemplate().getForEntity(url, String.class);

    }

    private String getJson() {
        return "* {\n" +
                "      \"rates\": {\n" +
                "          \"EUR\": 4.5\n" +
                "      },\n" +
                "      \"base\": \"BRL\",\n" +
                "      \"date\": \"2019-08-20\"\n" +
                "  }";
    }
}