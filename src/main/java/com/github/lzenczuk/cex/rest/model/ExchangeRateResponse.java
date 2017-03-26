package com.github.lzenczuk.cex.rest.model;

import com.github.lzenczuk.cex.model.CurrencySymbol;

import java.math.BigDecimal;

/**
 * Created by lzenczuk on 26/03/17.
 */
public class ExchangeRateResponse {
    private final CurrencySymbol currency;
    private final BigDecimal rate;
    private final String date;

    public ExchangeRateResponse(CurrencySymbol currency, BigDecimal rate, String date) {
        this.currency = currency;
        this.rate = rate;
        this.date = date;
    }

    public CurrencySymbol getCurrency() {
        return currency;
    }

    public String getDate() {
        return date;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
