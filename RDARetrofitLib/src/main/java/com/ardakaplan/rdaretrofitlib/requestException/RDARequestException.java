package com.ardakaplan.rdaretrofitlib.requestException;

import retrofit2.Response;

/**
 * Created by Arda Kaplan on 28-Dec-18 - 15:08
 */
public abstract class RDARequestException extends Exception {

    protected Response response;

    public RDARequestException(Throwable cause, Response response) {
        super(cause);
        this.response = response;
    }

    public RDARequestException() {
        super();
    }


    public RDARequestException(String message) {
        super(message);
    }


    public RDARequestException(String message, Throwable cause) {
        super(message, cause);
    }


    public RDARequestException(Throwable cause) {
        super(cause);
    }
}
