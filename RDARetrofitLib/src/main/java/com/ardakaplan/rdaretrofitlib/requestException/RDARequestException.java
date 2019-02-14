package com.ardakaplan.rdaretrofitlib.requestException;

import com.ardakaplan.rdaretrofitlib.HttpError;

import retrofit2.Response;

/**
 * Created by Arda Kaplan on 28-Dec-18 - 15:08
 */
public abstract class RDARequestException extends Exception {

    private HttpError httpError;
    private Response response;


    public RDARequestException(HttpError httpError, Throwable cause, Response response) {
        super(cause);
        this.httpError = httpError;
        this.response = response;
    }

    public HttpError getHttpError() {
        return httpError;
    }

    public Response getResponse() {
        return response;
    }
}
