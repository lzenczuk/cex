package com.github.lzenczuk.cex.service.ecb.client.impl;

import com.github.lzenczuk.cex.model.ConversionRate;
import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesParser;
import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesWebClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by lzenczuk on 24/03/17.
 */
public class AhcEcbExchangeRatesWebClientImpl implements EcbExchangeRatesWebClient {

    private Log logger = LogFactory.getLog(AhcEcbExchangeRatesWebClientImpl.class);

    private final EcbExchangeRatesParser ecbExchangeRatesParser;

    public AhcEcbExchangeRatesWebClientImpl(EcbExchangeRatesParser ecbExchangeRatesParser) {
        this.ecbExchangeRatesParser = ecbExchangeRatesParser;
    }

    @Override
    public List<ConversionRate> fetchLatestExchangeRates() {
        logger.info("Fetch latest exchange rates");
        return getXmlPricesInputStream("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
    }

    @Override
    public List<ConversionRate> fetchLast90DaysExchangeRates() {
        return getXmlPricesInputStream("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml");
    }

    private List<ConversionRate> getXmlPricesInputStream(String uri) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);

        try(CloseableHttpResponse response = httpClient.execute(httpGet)) {
            if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){
                return ecbExchangeRatesParser.parse(response.getEntity().getContent());
            }
        } catch (ClientProtocolException e) {
            System.out.println("Client exception: "+e.getMessage());
        } catch (IOException e) {
            System.out.println("IO exception: "+e.getMessage());
        }

        return Optional.empty();
    }
}
