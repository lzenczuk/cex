package com.github.lzenczuk.cex.service.ecb.client;

import com.github.lzenczuk.cex.model.ConversionRate;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

/**
 * Created by lzenczuk on 24/03/17.
 */
public interface EcbExchangeRatesParser {
    List<ConversionRate> parse(Optional<InputStream> optionalInputStream);
}
