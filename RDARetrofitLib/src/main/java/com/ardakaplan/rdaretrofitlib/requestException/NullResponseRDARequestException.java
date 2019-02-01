package com.ardakaplan.rdaretrofitlib.requestException;

import retrofit2.Response;

/**
 * Created by Arda Kaplan on 28-Dec-18 - 15:35
 */
public class NullResponseRDARequestException extends RDARequestException {

    public NullResponseRDARequestException(Throwable cause, Response response) {
        super(cause, response);
    }
}
