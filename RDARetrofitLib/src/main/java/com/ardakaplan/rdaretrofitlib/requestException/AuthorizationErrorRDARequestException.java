package com.ardakaplan.rdaretrofitlib.requestException;

import retrofit2.Response;

/**
 * Created by Arda Kaplan at 2/13/2019
 * <p>
 * arda.kaplan09@gmail.com
 */
public class AuthorizationErrorRDARequestException extends RDARequestException {

    public AuthorizationErrorRDARequestException(Throwable cause, Response response) {
        super(cause, response);
    }

}
