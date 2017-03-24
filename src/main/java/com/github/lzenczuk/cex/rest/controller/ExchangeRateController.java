package com.github.lzenczuk.cex.rest.controller;

import com.github.lzenczuk.cex.rest.model.ConversionRate;
import com.github.lzenczuk.cex.rest.model.CurrencySymbol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by lzenczuk on 24/03/17.
 */

@RestController
public class ExchangeRateController {

    private Log logger = LogFactory.getLog(ExchangeRateController.class);

    @RequestMapping("/conversion/{symbol}")
    public ConversionRate getConversionRate(@PathVariable("symbol") CurrencySymbol symbol){

        logger.info("Symbol: "+symbol);

        return new ConversionRate(CurrencySymbol.USD, new BigDecimal("1.2"), new Date());
    }

}
