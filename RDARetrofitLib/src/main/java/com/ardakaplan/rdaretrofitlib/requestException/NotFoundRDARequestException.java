package com.ardakaplan.rdaretrofitlib.requestException;

import retrofit2.Response;

/**
 * Created by Arda Kaplan on 28-Dec-18 - 15:10
 */
public class NotFoundRDARequestException extends RDARequestException {

    public NotFoundRDARequestException(Throwable cause, Response response) {
        super(cause, response);
    }
}
