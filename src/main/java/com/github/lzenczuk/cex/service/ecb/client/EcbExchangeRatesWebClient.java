package com.github.lzenczuk.cex.service.ecb.client;

/**
 * Created by lzenczuk on 24/03/17.
 */
public interface EcbExchangeRatesWebClient {
    String fetchLatestExchangeRates();
    String fetchLast90DaysExchangeRates();
}
