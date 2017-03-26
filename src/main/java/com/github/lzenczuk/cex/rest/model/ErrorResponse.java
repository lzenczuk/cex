package com.github.lzenczuk.cex.rest.model;

/**
 * Created by lzenczuk on 26/03/17.
 */
public class ErrorResponse {
    private final long errorCode;
    private final String errorMessage;

    public ErrorResponse(long errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public long getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
