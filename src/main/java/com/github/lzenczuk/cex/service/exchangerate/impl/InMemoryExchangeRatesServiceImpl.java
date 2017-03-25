package com.github.lzenczuk.cex.service.exchangerate.impl;

import com.github.lzenczuk.cex.model.ConversionRate;
import com.github.lzenczuk.cex.model.CurrencySymbol;
import com.github.lzenczuk.cex.service.exchangerate.ExchangeRatesService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lzenczuk on 24/03/17.
 */
public class InMemoryExchangeRatesServiceImpl implements ExchangeRatesService {

    private Log logger = LogFactory.getLog(InMemoryExchangeRatesServiceImpl.class);

    private ConcurrentHashMap<CurrencySymbol, ConcurrentHashMap<LocalDate, ConversionRate>> conversionsMap = new ConcurrentHashMap<>();

    public InMemoryExchangeRatesServiceImpl() {
        Arrays.asList(CurrencySymbol.values())
                .stream()
                .forEach(symbol -> conversionsMap.put(symbol, new ConcurrentHashMap<>()));
    }

    @Override
    public Optional<ConversionRate> getConversionRate(CurrencySymbol symbol, LocalDate date) {
        if (conversionsMap.containsKey(symbol)) {
            ConcurrentHashMap<LocalDate, ConversionRate> symbolConversionMap = conversionsMap.get(symbol);
            if (symbolConversionMap.containsKey(date)) {
                return Optional.of(symbolConversionMap.get(date));
            }
        }

        return Optional.empty();
    }

    @Override
    public void updateRate(ConversionRate conversionRate) {
        if(conversionsMap.containsKey(conversionRate.getCurrency())){
            conversionsMap.get(conversionRate.getCurrency()).put(conversionRate.getDate(), conversionRate);
        }else{
            logger.error("Trying add unknown symbol to exchange rates. "+conversionRate);
        }
    }
}
