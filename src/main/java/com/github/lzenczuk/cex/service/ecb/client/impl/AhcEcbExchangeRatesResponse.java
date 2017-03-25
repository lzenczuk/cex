package com.github.lzenczuk.cex.service.ecb.client.impl;

import com.github.lzenczuk.cex.service.ecb.client.EcbExchangeRatesResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * Created by lzenczuk on 25/03/17.
 */
public class AhcEcbExchangeRatesResponse implements EcbExchangeRatesResponse {

    private Log logger = LogFactory.getLog(AhcEcbExchangeRatesResponse.class);

    private final Optional<CloseableHttpResponse> response;

    public AhcEcbExchangeRatesResponse(Optional<CloseableHttpResponse> response) {
        this.response = response;
    }

    @Override
    public Optional<InputStream> getContent() {
        if (response == null) return Optional.empty();

        return response.flatMap(r -> {
            try {
                return Optional.of(r.getEntity().getContent());
            } catch (IOException e) {
                logger.error("Error getting content input stream from http response.");
                return Optional.empty();
            }
        });
    }

    @Override
    public void close() {
        if (response != null) {
            response.ifPresent(r -> {
                try {
                    r.close();
                } catch (IOException e) {
                    logger.error("Error closing http response.", e);
                }
            });
        }
    }
}
