package com.ardakaplan.rdaretrofitlib.requestException;

import retrofit2.Response;

/**
 * Created by Arda Kaplan on 28-Dec-18 - 15:11
 */
public class UnknownRDARequestException extends RDARequestException {

    public UnknownRDARequestException(Throwable cause, Response response) {
        super(cause, response);
    }
}
