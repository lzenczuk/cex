package com.github.lzenczuk.cex.service.ecb.client;

import java.io.InputStream;
import java.util.Optional;

/**
 * Created by lzenczuk on 25/03/17.
 */
public interface EcbExchangeRatesResponse extends AutoCloseable{
    Optional<InputStream> getContent();
    void close();
}
