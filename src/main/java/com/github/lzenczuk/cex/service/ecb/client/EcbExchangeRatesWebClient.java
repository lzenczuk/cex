package com.github.lzenczuk.cex.service.ecb.client;

import com.github.lzenczuk.cex.model.ConversionRate;

import java.util.List;

/**
 * Created by lzenczuk on 24/03/17.
 */
public interface EcbExchangeRatesWebClient {
    List<ConversionRate> fetchLatestExchangeRates();
    List<ConversionRate> fetchLast90DaysExchangeRates();
}
