package com.github.lzenczuk.cex.rest.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lzenczuk on 24/03/17.
 */
public class ConversionRate {
    private CurrencySymbol currency;
    private BigDecimal rate;
    private Date time;

    public ConversionRate(CurrencySymbol currency, BigDecimal rate, Date time) {
        this.currency = currency;
        this.rate = rate;
        this.time = time;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ConversionRate{" +
                "currency=" + currency +
                ", rate=" + rate +
                ", time=" + time +
                '}';
    }
}
