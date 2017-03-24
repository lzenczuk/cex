package com.github.lzenczuk.cex.service.ecb.client.impl;

import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesWebClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by lzenczuk on 24/03/17.
 */
public class AhcEcbExchangeRatesWebClientImpl implements EcbExchangeRatesWebClient {

    private Log logger = LogFactory.getLog(AhcEcbExchangeRatesWebClientImpl.class);

    @Override
    public String fetchLatestExchangeRates() {
        logger.info("Fetch latest exchange rates");
        return "";
    }

    @Override
    public String fetchLast90DaysExchangeRates() {
        return "";
    }
}
