package com.github.lzenczuk.cex.service.ecb.impl;

import com.github.lzenczuk.cex.model.ConversionRate;
import com.github.lzenczuk.cex.service.ecb.EcbService;
import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesParser;
import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesWebClient;
import com.github.lzenczuk.cex.service.exchangerate.ExchangeRatesService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * Created by lzenczuk on 24/03/17.
 */
public class EcbServiceImpl implements EcbService{

    private Log logger = LogFactory.getLog(EcbServiceImpl.class);

    private final EcbExchangeRatesWebClient exchangeRatesWebClient;
    private final EcbExchangeRatesParser ecbExchangeRatesParser;
    private final ExchangeRatesService exchangeRatesService;

    public EcbServiceImpl(EcbExchangeRatesParser ecbExchangeRatesParser, EcbExchangeRatesWebClient exchangeRatesWebClient, ExchangeRatesService exchangeRatesService) {
        this.ecbExchangeRatesParser = ecbExchangeRatesParser;
        this.exchangeRatesWebClient = exchangeRatesWebClient;
        this.exchangeRatesService = exchangeRatesService;
    }

    @PostConstruct
    /**
     * TODO - remove this, this code only in purpose of checking spring configuration
     */
    public void init(){
        updateLatestExchangeRates();
    }

    @Override
    public void updateLatestExchangeRates() {
        logger.info("-------------> Update latest exchange rates");
        Optional<InputStream> latestExchangeRatesString = exchangeRatesWebClient.fetchLatestExchangeRates();
        List<ConversionRate> conversionRates = ecbExchangeRatesParser.parse(latestExchangeRatesString);
        exchangeRatesService.updateRates(conversionRates);
    }
}
