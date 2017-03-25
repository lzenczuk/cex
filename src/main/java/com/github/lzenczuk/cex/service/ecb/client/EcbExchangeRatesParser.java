package com.github.lzenczuk.cex.service.ecb.client;

import com.github.lzenczuk.cex.model.ConversionRate;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by lzenczuk on 24/03/17.
 */
public interface EcbExchangeRatesParser {
    void parse(Optional<InputStream> optionalInputStream, Consumer<ConversionRate> conversionRateConsumer);
}
