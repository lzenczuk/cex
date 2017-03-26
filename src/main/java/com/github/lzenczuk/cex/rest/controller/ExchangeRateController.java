package com.github.lzenczuk.cex.rest.controller;

import com.github.lzenczuk.cex.model.ConversionRate;
import com.github.lzenczuk.cex.model.CurrencySymbol;
import com.github.lzenczuk.cex.rest.exception.CexException;
import com.github.lzenczuk.cex.rest.exception.ExchangeRateNotFoundException;
import com.github.lzenczuk.cex.rest.exception.IncorrectLocalDateFormatException;
import com.github.lzenczuk.cex.rest.exception.UnknownCurrencySymbolException;
import com.github.lzenczuk.cex.rest.model.ExchangeRateResponse;
import com.github.lzenczuk.cex.rest.model.KnownCurrenciesSymbolsResponse;
import com.github.lzenczuk.cex.service.exchangerate.ExchangeRatesService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by lzenczuk on 24/03/17.
 */

@RestController
public class ExchangeRateController {

    @Autowired
    ExchangeRatesService exchangeRatesService;

    private Log logger = LogFactory.getLog(ExchangeRateController.class);

    @RequestMapping("/currencies")
    public KnownCurrenciesSymbolsResponse getLatestConversionRate() {
        return new KnownCurrenciesSymbolsResponse(CurrencySymbol.getAllSymbolsAsStrings());
    }

    @RequestMapping("/currencies/{symbol}")
    public ExchangeRateResponse getLatestConversionRate(@PathVariable("symbol") String symbolString) throws CexException {

        CurrencySymbol symbol;
        try {
            symbol = CurrencySymbol.valueOf(symbolString);
        }catch (IllegalArgumentException e){
            throw new UnknownCurrencySymbolException("Unknown currency symbol. Available: "+ CurrencySymbol.getAllSymbolsAsStrings().stream().collect(Collectors.joining(", ")));
        }

        Optional<ConversionRate> optionalConversionRate = exchangeRatesService.getLatestConversionRate(symbol);

        if(optionalConversionRate.isPresent()){
            ConversionRate conversionRate = optionalConversionRate.get();
            return new ExchangeRateResponse(conversionRate.getCurrency(), conversionRate.getRate(), conversionRate.getDate().toString());
        }else{
            throw new ExchangeRateNotFoundException();
        }
    }

    @RequestMapping("/currencies/{symbol}/{date}")
    public ExchangeRateResponse getConversionRate(@PathVariable("symbol") String symbolString, @PathVariable("date") String dateString) throws CexException {

        CurrencySymbol symbol;
        try {
            symbol = CurrencySymbol.valueOf(symbolString);
        }catch (IllegalArgumentException e){
            throw new UnknownCurrencySymbolException("Unknown currency symbol. Available: "+ CurrencySymbol.getAllSymbolsAsStrings().stream().collect(Collectors.joining(", ")));
        }

        LocalDate date;
        try{
            date = LocalDate.parse(dateString);
        }catch (DateTimeParseException e){
            throw new IncorrectLocalDateFormatException();
        }

        Optional<ConversionRate> optionalConversionRate = exchangeRatesService.getConversionRate(symbol, date);

        if(optionalConversionRate.isPresent()){
            ConversionRate conversionRate = optionalConversionRate.get();
            return new ExchangeRateResponse(conversionRate.getCurrency(), conversionRate.getRate(), conversionRate.getDate().toString());
        }else{
            throw new ExchangeRateNotFoundException();
        }
    }

}
