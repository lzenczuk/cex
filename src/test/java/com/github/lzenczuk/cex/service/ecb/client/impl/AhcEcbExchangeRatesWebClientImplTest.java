package com.github.lzenczuk.cex.service.ecb.client.impl;

import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
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

        try(EcbExchangeRatesResponse ecbExchangeRatesResponse = client.fetchLatestExchangeRates()) {

            assertNotNull(ecbExchangeRatesResponse);

            Optional<InputStream> optionalInputStream = ecbExchangeRatesResponse.getContent();
            assertNotNull(optionalInputStream);
            assertTrue(optionalInputStream.isPresent());

            StringWriter writer = new StringWriter();
            IOUtils.copy(optionalInputStream.get(), writer, Charset.defaultCharset());
            String content = writer.toString();

            assertThat(content, startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<gesmes:Envelope xmlns:gesmes=\"http://www.gesmes.org/xml/2002-08-01\" xmlns=\"http://www.ecb.int/vocabulary/2002-08-01/eurofxref\">"));
        }
    }
}
