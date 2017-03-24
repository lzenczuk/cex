package com.github.lzenczuk.cex.service.exchangerate.impl;

import com.github.lzenczuk.cex.model.ConversionRate;
import com.github.lzenczuk.cex.model.CurrencySymbol;
import com.github.lzenczuk.cex.service.exchangerate.ExchangeRatesService;

import java.util.List;
import java.util.Optional;

/**
 * Created by lzenczuk on 24/03/17.
 */
public class InMemoryExchangeRatesServiceImpl implements ExchangeRatesService{

    @Override
    public Optional<ConversionRate> getLatestConversionRate(CurrencySymbol symbol) {
        return Optional.empty();
    }

    @Override
    public void updateRates(List<ConversionRate> rates) {

    }
}
