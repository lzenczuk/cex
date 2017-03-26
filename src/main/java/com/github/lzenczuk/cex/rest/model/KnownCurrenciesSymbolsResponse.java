package com.github.lzenczuk.cex.rest.model;

import java.util.List;

/**
 * Created by lzenczuk on 26/03/17.
 */
public class KnownCurrenciesSymbolsResponse {
    private final List<String> availableSymbols;

    public KnownCurrenciesSymbolsResponse(List<String> availableSymbols) {
        this.availableSymbols = availableSymbols;
    }

    public List<String> getAvailableSymbols() {
        return availableSymbols;
    }
}
