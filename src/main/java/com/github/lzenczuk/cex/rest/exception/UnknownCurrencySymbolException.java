package com.github.lzenczuk.cex.rest.exception;

/**
 * Created by lzenczuk on 26/03/17.
 */
public class UnknownCurrencySymbolException extends CexException {

    public UnknownCurrencySymbolException(String message) {
        super(message);
    }
}
