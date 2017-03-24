package com.github.lzenczuk.cex.rest.controller;

import com.github.lzenczuk.cex.model.ConversionRate;
import com.github.lzenczuk.cex.model.CurrencySymbol;
import com.github.lzenczuk.cex.service.exchangerate.ExchangeRatesService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by lzenczuk on 24/03/17.
 */

@RestController
public class ExchangeRateController {

    @Autowired
    ExchangeRatesService exchangeRatesService;

    private Log logger = LogFactory.getLog(ExchangeRateController.class);

    @RequestMapping("/conversion/{symbol}")
    public Optional<ConversionRate> getConversionRate(@PathVariable("symbol") CurrencySymbol symbol){

        logger.info("Symbol: "+symbol);

        return exchangeRatesService.getLatestConversionRate(symbol);
    }

}
