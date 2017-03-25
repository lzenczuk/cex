package com.github.lzenczuk.cex.service.ecb.client.impl;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.startsWith;
/**
 * Created by lzenczuk on 24/03/17.
 */
public class AhcEcbExchangeRatesWebClientImplTest{

    private Log logger = LogFactory.getLog(AhcEcbExchangeRatesWebClientImplTest.class);

    @Test
    public void shouldConnectToECBPageAndFetchLatestExhangeRates() throws IOException {
        AhcEcbExchangeRatesWebClientImpl client = new AhcEcbExchangeRatesWebClientImpl();

        Optional<InputStream> inputStreamOptional = client.fetchLatestExchangeRates();

        assertTrue(inputStreamOptional.isPresent());

        StringWriter writer = new StringWriter();
        logger.info("------------> fetched");
        IOUtils.copy(inputStreamOptional.get(), writer, Charset.defaultCharset());
        String content = writer.toString();

        assertThat(content, startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
    }
}
