package com.github.lzenczuk.cex.service.exchangerate;

import com.github.lzenczuk.cex.model.ConversionRate;
import com.github.lzenczuk.cex.model.CurrencySymbol;

import java.util.List;
import java.util.Optional;

/**
 * Created by lzenczuk on 24/03/17.
 */
public interface ExchangeRatesService {
    Optional<ConversionRate> getLatestConversionRate(CurrencySymbol symbol);
    void updateRates(List<ConversionRate> rates);
}
