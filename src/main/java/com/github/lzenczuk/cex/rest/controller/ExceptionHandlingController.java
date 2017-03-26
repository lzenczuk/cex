package com.github.lzenczuk.cex.rest.controller;

import com.github.lzenczuk.cex.rest.exception.*;
import com.github.lzenczuk.cex.rest.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lzenczuk on 26/03/17.
 */
@ControllerAdvice
@RestController
public class ExceptionHandlingController {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(Throwable e){
        return new ErrorResponse(ErrorCodes.INTERNAL_ERROR, "Server error");
    }

    @ExceptionHandler(CexException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(CexException e){
        return new ErrorResponse(ErrorCodes.INTERNAL_ERROR, "Server error");
    }

    @ExceptionHandler(UnknownCurrencySymbolException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(UnknownCurrencySymbolException e){
        return new ErrorResponse(ErrorCodes.UNKNOWN_CURRENCY, e.getMessage());
    }

    @ExceptionHandler(ExchangeRateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(ExchangeRateNotFoundException e){
        return new ErrorResponse(ErrorCodes.UNKNOWN_EXCHANGE_RATE, "Exchange rate not found.");
    }

    @ExceptionHandler(IncorrectLocalDateFormatException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(IncorrectLocalDateFormatException e){
        return new ErrorResponse(ErrorCodes.INCORRECT_DATE_FORMAT, "Incorrect date format. Expecting YYYY-MM-DD.");
    }

}
