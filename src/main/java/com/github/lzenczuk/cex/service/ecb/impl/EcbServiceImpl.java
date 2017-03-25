package com.github.lzenczuk.cex.service.ecb.impl;

import com.github.lzenczuk.cex.model.ConversionRate;
import com.github.lzenczuk.cex.service.ecb.EcbService;
import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesParser;
import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesResponse;
import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesWebClient;
import com.github.lzenczuk.cex.service.exchangerate.ExchangeRatesService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PostConstruct;
import java.util.List;

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
        updateHistoricalExchangeRates();
        updateLatestExchangeRates();
    }

    @Override
    public void updateLatestExchangeRates() {
        try(EcbExchangeRatesResponse response = exchangeRatesWebClient.fetchLatestExchangeRates()){
            ecbExchangeRatesParser.parse(
                    response.getContent(),
                    exchangeRatesService::updateRate
            );
        }
    }

    @Override
    public void updateHistoricalExchangeRates() {
        try(EcbExchangeRatesResponse response = exchangeRatesWebClient.fetchLast90DaysExchangeRates()){
            ecbExchangeRatesParser.parse(
                    response.getContent(),
                    exchangeRatesService::updateRate
            );
        }
    }
}
