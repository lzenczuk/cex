package com.github.lzenczuk.cex.service.ecb.client.impl;

import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesResponse;
import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesWebClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by lzenczuk on 24/03/17.
 */
public class AhcEcbExchangeRatesWebClientImpl implements EcbExchangeRatesWebClient {

    private Log logger = LogFactory.getLog(AhcEcbExchangeRatesWebClientImpl.class);

    @Override
    public EcbExchangeRatesResponse fetchLatestExchangeRates() {
        logger.info("Fetch latest exchange rates from Ecb page");
        return getXmlPricesInputStream("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
    }

    @Override
    public EcbExchangeRatesResponse fetchLast90DaysExchangeRates() {
        logger.info("Fetch historical exchange rates from Ecb page");
        return getXmlPricesInputStream("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml");
    }

    private EcbExchangeRatesResponse getXmlPricesInputStream(String uri) {
        logger.debug("Sending request to "+uri);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                logger.debug("Receive successful response.");
                return new AhcEcbExchangeRatesResponse(Optional.of(response));
            }else {
                logger.debug("Receive failure response: "+response.getStatusLine().toString());
            }
        } catch (IOException e) {
            logger.error("Error executing http request.");
        }


        return new AhcEcbExchangeRatesResponse(Optional.empty());
    }
}
