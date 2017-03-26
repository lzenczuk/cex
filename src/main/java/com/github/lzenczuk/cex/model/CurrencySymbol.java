package com.github.lzenczuk.cex.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lzenczuk on 24/03/17.
 */
public enum CurrencySymbol {
    USD,
    JPY,
    BGN,
    CZK,
    DKK,
    GBP,
    HUF,
    PLN,
    RON,
    SEK,
    CHF,
    NOK,
    HRK,
    RUB,
    TRY,
    AUD,
    BRL,
    CAD,
    CNY,
    HKD,
    IDR,
    ILS,
    INR,
    KRW,
    MXN,
    MYR,
    NZD,
    PHP,
    SGD,
    THB,
    ZAR;

    static public List<CurrencySymbol> getAllSymbols(){
        return Arrays.asList(CurrencySymbol.values());
    }

    static public List<String> getAllSymbolsAsStrings(){
        return getAllSymbols().stream().map(CurrencySymbol::toString).collect(Collectors.toList());
    }
}
