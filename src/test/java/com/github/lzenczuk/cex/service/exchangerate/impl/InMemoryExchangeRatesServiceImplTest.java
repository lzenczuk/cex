package com.github.lzenczuk.cex.service.exchangerate.impl;

import com.github.lzenczuk.cex.model.ConversionRate;
import com.github.lzenczuk.cex.model.CurrencySymbol;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by lzenczuk on 25/03/17.
 */
public class InMemoryExchangeRatesServiceImplTest {

    @Test
    public void shouldReturnEmptyOptionalWhenItsEmpty(){
        InMemoryExchangeRatesServiceImpl inMemoryExchangeRatesService = new InMemoryExchangeRatesServiceImpl();

        Optional<ConversionRate> optionalConversionRate = inMemoryExchangeRatesService.getConversionRate(CurrencySymbol.USD, LocalDate.parse("2017-03-24"));

        assertFalse(optionalConversionRate.isPresent());
    }

    @Test
    public void shouldAddSymbolAndReturnOne(){
        InMemoryExchangeRatesServiceImpl inMemoryExchangeRatesService = new InMemoryExchangeRatesServiceImpl();
        inMemoryExchangeRatesService.updateRate(new ConversionRate("USD", "1.08", "2017-03-04"));

        Optional<ConversionRate> optionalConversionRate = inMemoryExchangeRatesService.getConversionRate(CurrencySymbol.USD, LocalDate.parse("2017-03-04"));

        assertTrue(optionalConversionRate.isPresent());
        ConversionRate conversionRate = optionalConversionRate.get();

        assertEquals(CurrencySymbol.USD, conversionRate.getCurrency());
        assertEquals(LocalDate.parse("2017-03-04"), conversionRate.getDate());
        assertEquals(0, new BigDecimal("1.08").compareTo(conversionRate.getRate()));
    }

    @Test
    public void shouldReturnEmptyOptionalForMissingDate(){
        InMemoryExchangeRatesServiceImpl inMemoryExchangeRatesService = new InMemoryExchangeRatesServiceImpl();
        inMemoryExchangeRatesService.updateRate(new ConversionRate("USD", "1.08", "2017-03-04"));

        Optional<ConversionRate> optionalConversionRate = inMemoryExchangeRatesService.getConversionRate(CurrencySymbol.USD, LocalDate.parse("2017-03-24"));

        assertFalse(optionalConversionRate.isPresent());
    }

    @Test
    public void shouldReturnEmptyOptionalForMissingDateWhenFetchingLatestExchangeRate(){
        InMemoryExchangeRatesServiceImpl inMemoryExchangeRatesService = new InMemoryExchangeRatesServiceImpl();

        Optional<ConversionRate> optionalConversionRate = inMemoryExchangeRatesService.getLatestConversionRate(CurrencySymbol.USD);

        assertFalse(optionalConversionRate.isPresent());
    }

    @Test
    public void shouldReturnLatestExchangeRate(){
        InMemoryExchangeRatesServiceImpl inMemoryExchangeRatesService = new InMemoryExchangeRatesServiceImpl();
        inMemoryExchangeRatesService.updateRate(new ConversionRate("USD", "1.08", "2017-03-04"));
        inMemoryExchangeRatesService.updateRate(new ConversionRate("USD", "1.10", "2017-03-06"));
        inMemoryExchangeRatesService.updateRate(new ConversionRate("USD", "1.09", "2017-03-05"));

        Optional<ConversionRate> optionalConversionRate = inMemoryExchangeRatesService.getLatestConversionRate(CurrencySymbol.USD);

        assertTrue(optionalConversionRate.isPresent());
        ConversionRate conversionRate = optionalConversionRate.get();

        assertEquals(CurrencySymbol.USD, conversionRate.getCurrency());
        assertEquals(LocalDate.parse("2017-03-06"), conversionRate.getDate());
        assertEquals(0, new BigDecimal("1.10").compareTo(conversionRate.getRate()));
    }
}
