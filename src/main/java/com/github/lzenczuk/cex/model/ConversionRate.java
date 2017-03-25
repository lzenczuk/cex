package com.github.lzenczuk.cex.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by lzenczuk on 24/03/17.
 */
public class ConversionRate {
    private CurrencySymbol currency;
    private BigDecimal rate;
    private LocalDate date;

    public ConversionRate(String symbol, String rate, String time) {
        this.currency = CurrencySymbol.valueOf(symbol);
        this.rate = new BigDecimal(rate);
        this.date = LocalDate.parse(time);
    }

    public CurrencySymbol getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencySymbol currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ConversionRate{" +
                "currency=" + currency +
                ", rate=" + rate +
                ", date=" + date +
                '}';
    }
}
