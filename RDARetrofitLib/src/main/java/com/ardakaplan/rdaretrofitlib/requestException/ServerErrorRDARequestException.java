package com.ardakaplan.rdaretrofitlib.requestException;

import retrofit2.Response;

/**
 * Created by Arda Kaplan on 28-Dec-18 - 15:10
 */
public class ServerErrorRDARequestException extends RDARequestException {

    public ServerErrorRDARequestException(Throwable cause, Response response) {
        super(cause, response);
    }
}
