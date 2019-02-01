package com.ardakaplan.rdaretrofitlib.requestException;

import retrofit2.Response;

/**
 * Created by Arda Kaplan on 28-Dec-18 - 15:12
 */
public class NoNetworkRDARequestException  extends RDARequestException{

    public NoNetworkRDARequestException(Throwable cause, Response response) {
        super(cause, response);
    }
}
