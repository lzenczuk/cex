package com.github.lzenczuk.cex.service.ecb.client.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by lzenczuk on 25/03/17.
 */
public class SaxEcbExchangeRatesParserImplTest {

    private Log logger = LogFactory.getLog(SaxEcbExchangeRatesParserImplTest.class);

    @Test
    public void parseDailyEcbXmlFile() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        HashMap<String, String> conversionRatesMap = new HashMap<>();
        conversionRatesMap.put("2017-03-23-USD", "1.0786");
        conversionRatesMap.put("2017-03-23-JPY", "119.36");
        conversionRatesMap.put("2017-03-23-BGN", "1.9558");
        conversionRatesMap.put("2017-03-23-CZK", "27.021");
        conversionRatesMap.put("2017-03-23-DKK", "7.4356");
        conversionRatesMap.put("2017-03-23-GBP", "0.86273");
        conversionRatesMap.put("2017-03-23-HUF", "309.22");
        conversionRatesMap.put("2017-03-23-PLN", "4.2685");
        conversionRatesMap.put("2017-03-23-RON", "4.5555");
        conversionRatesMap.put("2017-03-23-SEK", "9.5095");
        conversionRatesMap.put("2017-03-23-CHF", "1.0700");
        conversionRatesMap.put("2017-03-23-NOK", "9.1478");
        conversionRatesMap.put("2017-03-23-HRK", "7.4178");
        conversionRatesMap.put("2017-03-23-RUB", "62.2001");
        conversionRatesMap.put("2017-03-23-TRY", "3.9038");
        conversionRatesMap.put("2017-03-23-AUD", "1.4132");
        conversionRatesMap.put("2017-03-23-BRL", "3.3608");
        conversionRatesMap.put("2017-03-23-CAD", "1.4387");
        conversionRatesMap.put("2017-03-23-CNY", "7.4268");
        conversionRatesMap.put("2017-03-23-HKD", "8.3780");
        conversionRatesMap.put("2017-03-23-IDR", "14363.72");
        conversionRatesMap.put("2017-03-23-ILS", "3.9322");
        conversionRatesMap.put("2017-03-23-INR", "70.6095");
        conversionRatesMap.put("2017-03-23-KRW", "1207.38");
        conversionRatesMap.put("2017-03-23-MXN", "20.5962");
        conversionRatesMap.put("2017-03-23-MYR", "4.7771");
        conversionRatesMap.put("2017-03-23-NZD", "1.5303");
        conversionRatesMap.put("2017-03-23-PHP", "54.309");
        conversionRatesMap.put("2017-03-23-SGD", "1.5086");
        conversionRatesMap.put("2017-03-23-THB", "37.320");
        conversionRatesMap.put("2017-03-23-ZAR", "13.4933");

        SaxEcbExchangeRatesParserImpl parser = new SaxEcbExchangeRatesParserImpl();
        parser.parse(Optional.of(classLoader.getResource("eurofxref-daily.xml").openStream()), conversionRate -> {
            String date = conversionRate.getDate().toString();
            String symbol = conversionRate.getCurrency().toString();
            String key = date+"-"+symbol;

            assertTrue(conversionRatesMap.containsKey(key));

            String rate = conversionRatesMap.get(key);

            logger.info("Checking: "+key+" -> "+rate);
            logger.info("CR: "+conversionRate);

            assertEquals(0, conversionRate.getRate().compareTo(new BigDecimal(rate)));
            conversionRatesMap.remove(key);
        });

        assertTrue(conversionRatesMap.isEmpty());
    }

    @Test
    public void parse90DaysEcbXmlFile() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        // Some randomly chosen rates from last 90 days
        HashMap<String, String> conversionRatesMap = new HashMap<>();
        conversionRatesMap.put("2017-03-24-TRY", "3.9176");
        conversionRatesMap.put("2017-03-23-USD", "1.0786");
        conversionRatesMap.put("2017-03-22-SGD", "1.5108");
        conversionRatesMap.put("2017-03-21-RUB", "61.811");
        conversionRatesMap.put("2017-03-20-GBP", "0.86793");
        conversionRatesMap.put("2017-03-17-PLN", "4.2982");
        conversionRatesMap.put("2017-03-16-AUD", "1.3945");
        conversionRatesMap.put("2017-03-06-PLN", "4.3091");
        conversionRatesMap.put("2017-02-28-RON", "4.5202");
        conversionRatesMap.put("2017-02-27-SEK", "9.5548");
        conversionRatesMap.put("2017-02-06-AUD", "1.4006");
        conversionRatesMap.put("2017-02-01-CHF", "1.068");
        conversionRatesMap.put("2017-01-18-SGD", "1.5176");
        conversionRatesMap.put("2016-12-27-SGD", "1.513");
        conversionRatesMap.put("2016-12-27-THB", "37.612");
        conversionRatesMap.put("2016-12-27-ZAR", "14.5792");

        final AtomicInteger counter = new AtomicInteger();

        SaxEcbExchangeRatesParserImpl parser = new SaxEcbExchangeRatesParserImpl();
        parser.parse(Optional.of(classLoader.getResource("eurofxref-hist-90d.xml").openStream()), conversionRate -> {
            String date = conversionRate.getDate().toString();
            String symbol = conversionRate.getCurrency().toString();
            String key = date+"-"+symbol;

            if(conversionRatesMap.containsKey(key)){
                String rate = conversionRatesMap.get(key);

                logger.info("Checking: "+key+" -> "+rate);
                logger.info("CR: "+conversionRate);

                assertEquals(0, conversionRate.getRate().compareTo(new BigDecimal(rate)));
                conversionRatesMap.remove(key);
            }

            counter.incrementAndGet();

        });

        conversionRatesMap.forEach((s, s2) -> logger.error(s+" -> "+s2));

        assertTrue(conversionRatesMap.isEmpty());
        assertEquals(1984, counter.get());
    }
}
