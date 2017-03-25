package com.github.lzenczuk.cex.service.ecb.client.impl;

import com.github.lzenczuk.cex.model.ConversionRate;
import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * Created by lzenczuk on 24/03/17.
 */
public class SaxEcbExchangeRatesParserImpl implements EcbExchangeRatesParser {

    private Log logger = LogFactory.getLog(SaxEcbExchangeRatesParserImpl.class);

    @Override
    public List<ConversionRate> parse(InputStream dataInputStream) {
        logger.info("Parse exchange rates");
        return Collections.emptyList();
    }
}
